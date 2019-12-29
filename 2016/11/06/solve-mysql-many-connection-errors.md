- Title: MySql many connection errors 原因 解决
- Category:
- Tag: Mysql
- Author: Kahle
- Creation Time: 2016-11-06T17:33:01.666+0800
- Update Time: 2016-11-06T17:33:01.666+0800
- Original:
- Reference:
    - [【mysql】many connection errors解决方法](http://jingyan.baidu.com/article/9f7e7ec087dcbe6f2815542d.html)
    - [mysqladmin flush-hosts 解决方法](http://blog.sina.com.cn/s/blog_721cd3390101rlu8.html)
    - [MySQL数据库连接超时(wait_timeout)问题的处理](http://sarin.iteye.com/blog/580311/)

---


### 导语

同个 IP 因调用了 timeout 的 connection 被数据库标记 connection_errors 的次数大于 max_connection_errors，就会被数据库锁定，而报“ Host is blocked because of many connection errors; unblock with 'mysqladmin flush-hosts' ”错误。


### 正文

解决方法：

清除计数缓存，执行以下命令即可：
```
mysqladmin flush-hosts -h192.168.1.1 -P3307 -uroot -proot
```

或者用命令行登录mysql服务器后，执行以下命令：
```
flush hosts
```

顺便可以提高 max_connection_errors 的数量，具体就自行百度或者谷歌哦！其实重要的还是程序中别产生这种错误就好了。


