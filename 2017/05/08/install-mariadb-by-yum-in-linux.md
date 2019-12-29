- Title: Linux 下用 YUM 快速安装 MySQL 的代替品 MariaDB
- Category:
- Tag: Linux, MariaDB
- Author: Kahle
- Creation Time: 2017-05-08T10:15:33.666+0800
- Update Time: 2017-05-08T10:15:33.666+0800
- Original:
- Reference:

---


### 导语

Linux下用YUM快速安装MySQL的代替品MariaDB。如果以类似MyCat的中间件来做数据库集群的话，数据库集群访问方式相当于单点数据库，所以作为开发，就没必要在测试环境上面搭建MySQL集群了，直接单点就够了。那么，就快速单点把。


### 正文

首先呢，去[官网逛逛](https://downloads.mariadb.org/mariadb/repositories/#mirror=yamagata-university&distro=CentOS&distro_release=centos7-amd64--centos7&version=10.1)，这个连接已经写明白怎么用YUM安装了。当然详细的话，可以看官网的[这个连接](https://mariadb.com/kb/en/mariadb/yum/)，该有的都有了。

大致解释一下，针对于CentOS系统的奥。先进入/etc/yum.repos.d/目录下，创建一个名为MariaDB.repo（建议）文件，然后粘贴一下内容进去（其实就是设置YUM的仓库咯）。

```
# MariaDB 10.1 CentOS repository list - created 2017-05-03 02:31 UTC
# http://downloads.mariadb.org/mariadb/repositories/
[mariadb]
name = MariaDB
baseurl = http://yum.mariadb.org/10.1/centos7-amd64
gpgkey=https://yum.mariadb.org/RPM-GPG-KEY-MariaDB
gpgcheck=1
```

粘贴完毕，记得保存。然后就可以执行以下命令了，以root的身份执行。

```
yum install MariaDB-server MariaDB-client
```

安装完毕，安装过程大致就是yes一路下来。然后执行以下命令启动（root身份）。

```
# 系统有用systemctl
systemctl start mariadb
# 没有用systemctl
/etc/init.d/mysql start
```

然后可以直接敲mysql，进入MySQL终端，如果需要远程访问的话，可以在MySQL终端中，执行以下SQL。

```
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;
# 更改权限之后一定要刷新
flush privileges;
```

然后单点就完成了。

然后问题来了，在国内，这个YUM源的速度非常慢，阿里云上好几次超时，那么，就没办法了，祭出我的神器，镜像站呗，记得自己先研究一下这个URL地址。模板文件。

```
[mariadb]
name = MariaDB
baseurl = https://mirrors.tuna.tsinghua.edu.cn/mariadb/mariadb-10.1.22/yum/centos7-amd64/
```

大连东软信息学院：http://mirrors.neusoft.edu.cn/mariadb/mariadb-10.1.22/yum/centos7-amd64/

清华大学 TUNA 协会：https://mirrors.tuna.tsinghua.edu.cn/mariadb/mariadb-10.1.22/yum/centos7-amd64/


