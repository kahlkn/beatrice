- Title: Nginx 配置详解和优化，负载均衡，反向代理，WEB服务器
- Category:
- Tag: Nginx
- Author: Kahle
- Creation Time: 2017-05-17T11:17:09.666+0800
- Update Time: 2017-05-17T11:17:09.666+0800
- Original:
- Reference:
    - [Nginx 配置反向代理示例](http://www.cnblogs.com/sixiweb/p/3988805.html)
    - [WEB请求处理二：Nginx 请求反向代理](http://www.jianshu.com/p/bed000e1830b)
    - [Nginx 配置性能优化](http://blog.csdn.net/xifeijian/article/details/20956605)
    - [Nginx 强制使用 https 访问( http 跳转到 https )](http://www.cnblogs.com/yun007/p/3739182.html)
    - [免费 SSL 安全证书 Let's Encrypt 安装使用教程(附 Nginx / Apache 配置)](https://www.vpser.net/build/letsencrypt-free-ssl.html)

---


### 导语

Nginx 配置详解和优化，负载均衡，反向代理，WEB服务器。直接上配置文件哈。


### 正文

```
# 运行 nginx 进程的属主
#user nobody;
# 启动的进程数，通常设置成和cpu的数量相等
worker_processes  1;

# 日志和PID文件
error_log  logs/error.log;
error_log  logs/error.log  notice;
error_log  logs/error.log  info;
#pid        logs/nginx.pid;

# 工作模式及连接数上限
events {
    # epoll是多路复用IO(I/O Multiplexing)中的一种方式
    # 仅用于linux2.6以上内核，可以大大提高nginx的性能
    use   epoll;

    # 单个后台worker process进程的最大并发链接数
    worker_connections  1024;

    # 并发总数是 worker_processes 和 worker_connections 的乘积
    # 即 max_clients = worker_processes * worker_connections
    # 在设置了反向代理的情况下，max_clients = worker_processes * worker_connections / 4  为什么
    # 为什么上面反向代理要除以4，应该说是一个经验值
    # 根据以上条件，正常情况下的Nginx Server可以应付的最大连接数为：4 * 8000 = 32000
    # worker_connections 值的设置跟物理内存大小有关
    # 因为并发受IO约束，max_clients的值须小于系统可以打开的最大文件数
    # 而系统可以打开的最大文件数和内存大小成正比，一般1GB内存的机器上可以打开的文件数大约是10万左右
    # 我们来看看360M内存的VPS可以打开的文件句柄数是多少：
    # $ cat /proc/sys/fs/file-max
    # 输出 34336
    # 32000 < 34336，即并发连接总数小于系统可以打开的文件句柄总数，这样就在操作系统可以承受的范围之内
    # 所以，worker_connections 的值需根据 worker_processes 进程数目和系统可以打开的最大文件总数进行适当地进行设置
    # 使得并发总数小于操作系统可以打开的最大文件数目
    # 其实质也就是根据主机的物理CPU和内存进行配置
    # 当然，理论上的并发总数可能会和实际有所偏差，因为主机还有其他的工作进程需要消耗系统资源。
    # ulimit -SHn 65535

}


http {
    # 设定mime类型，类型由mime.type文件定义
    include    mime.types;
    default_type  application/octet-stream;

    # 设定日志格式
    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    '$status $body_bytes_sent "$http_referer" '
    '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  logs/access.log  main;

    # 关闭在错误页面中的nginx版本数字
    server_tokens off;

    # sendfile 指令指定 nginx 是否调用 sendfile 函数（zero copy 方式）来输出文件
    # 对于普通应用，必须设为 on
    # 如果用来进行下载等应用磁盘IO重负载应用，可设置为 off
    # 以平衡磁盘与网络I/O处理速度，降低系统的uptime
    sendfile     on;

    # 告诉nginx在一个数据包里发送所有头文件，而不一个接一个的发送
    tcp_nopush     on;
    # 告诉nginx不要缓存数据，而是一段一段的发送
    tcp_nodelay     on;

    # 连接超时时间
    keepalive_timeout  32;
    # 请求头的超时时间
    client_header_timeout  10;
    # 请求体的超时时间
    client_body_timeout  10;

    # 关闭不响应的客户端连接，这会释放掉客户端所占有的内存空间
    reset_timedout_connection  on;

    # 客户端的响应超时时间（两次客户端读取操作之间）
    send_timeout  10;

    # 开启gzip压缩
    gzip  on;
    # 为指定的客户端禁用gzip功能
    gzip_disable "MSIE [1-6].";
    
    # 设定请求缓冲
    client_header_buffer_size    128k;
    large_client_header_buffers  4 128k;




    # 本地虚拟主机配置
    server {
        # 侦听80端口
        listen       80;
        # 定义使用 www.youdomain.com 访问
        server_name  www.youdomain.com;
        
        # 定义服务器的默认网站根目录位置
        root  /data/web;
        
        # 设定本虚拟主机的访问日志
        access_log  logs/nginx.access.log  main;
        
        # 默认请求
        location / {
            # 定义首页索引文件的名称
            index index.php index.html index.htm;
        }
        
        # 定义错误提示页面
        error_page   500 502 503 504 /50x.html;
        location = /50x.html {
        }
        
        # 静态文件，nginx自己处理
        location ~ ^/(images|javascript|js|css|flash|media|static)/ {
            # 过期30天，静态文件不怎么更新，过期可以设大一点，
            # 如果频繁更新，则可以设置得小一点。
            expires 30d;
        }
        
        # PHP 脚本请求全部转发到 FastCGI处理. 使用FastCGI默认配置.
        location ~ .php$ {
            fastcgi_pass 127.0.0.1:9000;
            fastcgi_index index.php;
            fastcgi_param  SCRIPT_FILENAME  $document_root$fastcgi_script_name;
            include fastcgi_params;
        }
        
        # 禁止访问 .htxxx 文件
        location ~ /.ht {
            deny all;
        }
    }




    # 反向代理配置
    server {
        # 侦听的80端口
        listen       80;
        server_name  www.youdomain.com;
        
        # 匹配以jsp结尾的，tomcat的网页文件是以jsp结尾
        location / {
            index  index.jsp;
            proxy_pass   http://proxydomain.com;
            
            # 以下是一些反向代理的配置可删除
            proxy_redirect             off;
            proxy_set_header           Host $host;
            # 后端的Web服务器可以通过X-Real-IP获取用户真实IP
            proxy_set_header           X-Real-IP $remote_addr;
            proxy_set_header           X-Forwarded-Host $server_name;
            proxy_set_header           X-Forwarded-For $proxy_add_x_forwarded_for;
            client_max_body_size       10m;   # 允许客户端请求的最大单文件字节数
            client_body_buffer_size    128k;  # 缓冲区代理缓冲用户端请求的最大字节数
            proxy_connect_timeout      300;   # nginx跟后端服务器连接超时时间(代理连接超时)
            proxy_send_timeout         300;   # 后端服务器数据回传时间(代理发送超时)
            proxy_read_timeout         300;   # 连接成功后，后端服务器响应时间(代理接收超时)
            proxy_buffer_size          4k;    # 设置代理服务器（nginx）保存用户头信息的缓冲区大小
            proxy_buffers              4 32k; # proxy_buffers缓冲区，网页平均在32k以下的话，这样设置
            proxy_busy_buffers_size    64k;   # 高负荷下缓冲大小（proxy_buffers*2）
            proxy_temp_file_write_size 64k;   # 设定缓存文件夹大小，大于这个值，将从upstream服务器传
        }
    }




    # 负载均衡配置
    # 设定负载均衡的服务器列表
    # Weigth参数表示权值，权值越高被分配到的几率越大
    upstream test {
        server 192.168.68.43:8080 weight=1;
        server 192.168.68.45:8080 weight=1;
    }

    server {
        listen       80;
        server_name  www.youdomain.com;
        location / {
            proxy_pass                 http://test;
            proxy_set_header           Host $host;
            proxy_set_header           X-Real-IP $remote_addr;
            proxy_set_header           X-Forwarded-Host $server_name;
            proxy_set_header           X-Forwarded-For $proxy_add_x_forwarded_for;
        }
    }




    # https配置
    server {
        listen       443 ssl;
        server_name  www.youdomain.com;
        
        ssl_certificate /etc/letsencrypt/live/www.youdomain.com/fullchain.pem;
        ssl_certificate_key /etc/letsencrypt/live/www.youdomain.com/privkey.pem;
        ssl_ciphers "EECDH+CHACHA20:EECDH+CHACHA20-draft:EECDH+AES128:RSA+AES128:EECDH+AES256:RSA+AES256:EECDH+3DES:RSA+3DES:!MD5";
        ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
        ssl_prefer_server_ciphers on;
        ssl_session_cache shared:SSL:10m;
        
        location / {
            proxy_pass                 https://127.0.0.1:8080;
            proxy_set_header           Host $host;
            proxy_set_header           X-Real-IP $remote_addr;
            proxy_set_header           X-Forwarded-Host $server_name;
            proxy_set_header           X-Forwarded-For $proxy_add_x_forwarded_for;
        }
    }




    # 重定向，用于http跳转到https
    server {
        listen       80;
        server_name  www.youdomain.com;
        rewrite ^(.*)$  https://$host$1 permanent;
    }


}
```



