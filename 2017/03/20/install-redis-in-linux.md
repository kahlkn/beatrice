- Title: Linux 下安装 Redis
- Category:
- Tag: Linux, Redis
- Author: Kahle
- Creation Time: 2017-03-20T10:52:10.666+0800
- Update Time: 2017-03-20T10:52:10.666+0800
- Original:
- Reference:

---


### 导语

Linux 下安装 Redis。


### 正文

```
# 下载 redis 源码包
wget http://download.redis.io/releases/redis-3.2.8.tar.gz
# 解压
tar -xvf redis-3.2.8.tar.gz
cd redis-3.2.8
# 注意：make 指令是需要linux下安装gcc的 如果没有gcc可以尝试安装
make
# 在 redis-3.2.8 目录下
src/redis-server
# 在 另一个 窗口 下的 redis-3.2.8 目录下
src/redis-cli
```

Redis配置相关，在 redis-3.2.8 目录下，修改 redis.conf 文件。

1. 启用 redis 守护进程模式：daemonize no
2. Redis监听端口（默认端口为6379）：port 6379
3. 绑定的主机地址：bind 127.0.0.1
4. 客户端闲置多长时间后关闭连接（0表示关闭该功能）：timeout 0
5. 给 redis 设置 访问 密码：requirepass 123456
6. 以配置文件启动Redis：“redis-server redis.conf”。

Redis客户端连接：“redis-cli -h 127.0.0.1 -p 6379 -a 123456”。


