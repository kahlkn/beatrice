- Title: Linux 下安装 Nginx
- Category:
- Tag: Linux, Nginx
- Author: Kahle
- Creation Time: 2017-03-20T17:31:50.666+0800
- Update Time: 2017-03-20T17:31:50.666+0800
- Original:
- Reference:
    - [the "ssl" parameter requires ngx_http_ssl_module ...](http://www.cnblogs.com/ghjbk/p/6744131.html)

---


### 导语

Linux 下安装 Nginx。


### 正文

```
# 安装依赖
yum install gcc gcc-c++ openssl openssl-devel  zib-devel zib
# 切换 目录
cd /data/package
# 安装 pcre 环境 依赖
wget https://sourceforge.net/projects/pcre/files/pcre/8.38/pcre-8.40.tar.gz
# 解压，进入目录，配置编译并安装，离开目录
tar -xvf pcre-8.40.tar.gz
cd pcre-8.40
./configure && make -j4 && make install
cd ../
# 下载，解压，进入目录，配置，编译并安装
wget http://nginx.org/download/nginx-1.9.8.tar.gz
tar -xvf nginx-1.9.8.tar.gz
cd nginx-1.9.8
./configure --prefix=/data/nginx-1.9.8
make -j4 && make install
# 测试
cd /data/nginx-1.9.8/sbin
./nginx -t
# 出现OK或者Successful表示成功，出现Error等表示失败
# 错误：“nginx: error while loading shared libraries: libpcre.so.1: cannot open shared object file: No such file or directory”
# 原因是pcre的lib目录没指定正确，由于系统新老版本的缘故有一些目录名改变了。
# 64位系统解决
ln -s /usr/local/lib/libpcre.so.1 /lib64
# 32位系统解决
ln -s /usr/local/lib/libpcre.so.1 /lib
# 安装成功


# 启动
# 启动 nginx，在 /data/nginx-1.9.8/sbin 目录下
./nginx
# 重启
./nginx -s reload
# 判断配置文件是否正确
./nginx -t
# 或
./nginx -t -c /data/nginx-1.9.8/conf/nginx.conf
# 快速停止，可能并不保存相关信息
./nginx -s stop
# 完整有序的停止，保存相关信息
./nginx -s quit
# 查看Nginx版本
./nginx -v
```


更新：关于nginx开启SSL模块（我是直接重新编译安装的，记得备份）

```
# 配置
./configure --prefix=/data/nginx-1.9.8 --with-http_stub_status_module --with-http_ssl_module
# 编译安装
make -j4 && make install
```



