- Title: RedHat 或者 CentOS 装机必备的配置
- Category:
- Tag: Linux, RedHat, CentOS
- Author: Kahle
- Creation Time: 2017-01-08T17:11:46.666+0800
- Update Time: 2017-01-08T17:11:46.666+0800
- Original:
- Reference:

---


### 导语

一直接触的都是 CentOS ，但是也仅限于应用服务器搭建、各种数据库搭建等，对于一些基本设置，基本命令等的，都不了解。所以呢，还是准备记录一下会用到的一些设置要，命令呀，等等的东东咯。


### RedHat / CentOS 更改主机名

```
# 临时更改
hostname xxx
# 永久修改 - root下
vim /etc/sysconfig/network
vim /etc/hosts
service network restart
```


