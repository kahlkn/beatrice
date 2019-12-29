- Title: CentOS 7 服务器安装
- Category:
- Tag: CentOS
- Author: Kahle
- Creation Time: 2018-07-21T14:34:01.666+0800
- Update Time: 2018-07-21T14:34:01.666+0800
- Original:
- Reference:
    - [CentOS 7 安装及配置网卡](https://blog.csdn.net/boom_man/article/details/78192385)
    - [CentOS 7 系统配置国内yum源和epel源](https://www.cnblogs.com/renpingsheng/p/7845096.html)
    - [Linux服务器开启ssh服务，实现ssh远程登陆](https://www.cnblogs.com/zxf100/archive/2017/05/09/6832671.html)
    - [centos 7 开启ssh服务](https://blog.csdn.net/baibaigao/article/details/80956417)

---


### 导语
CentOS 7 服务器安装。


### 安装

从 [CentOS 官网](https://www.centos.org/) 下载 CentOS 安装包，如果服务器可以考虑下载 Minimal 版本的，当然其他版本也是可以的。 虚拟机安装的话，建议显存给点比如 32 MB 以上，内存最好大于 1GB，CPU 给个 2 核心最好。这样在启动的时候会图形化安装，我自己的实践第一次配置给的比较低，启动时就卡在那里了报错了。第二次配置给的稍微高了一点点，就可以正常进去安装了。反正安装完毕之后可以再降下去的。


### 网络配置

如何判断是那个文件，一般都是“ifcfg-”开头的，排除掉“ifcfg-lo”。
```
cd /etc/sysconfig/network-scripts/ 
vi ifcfg-enp0s3
```

将“ONBOOT”的值改成“yes”，然后重启网络服务（或者重启电脑）即可（一般都是有 dhcp 的，当然看具体情况，如果希望 IP 不会变动，改成 静态 是最保险的）。
```
// 重启 网络服务
systemctl restart network.service
// 停止 网络服务
systemctl stop network.service

// centos 7 之前的命令 仍然适用
service restart network
```


### 配置国内 yum 源和 epel 源

试了一下，安装完毕之后，随便通过 yum 安装了一个软件，貌似速度都很快。貌似就是 163 的源。可能是因为镜像下载的时候用的是国内 镜像点 下载的缘故吧。

即使 yum 源不用修改了，但是 epel 源还是要安装的（毕竟很多程序在 epel 源中）。
```
yum install -y epel-release
// 检查是否存在 epel.repo 和 epel-testing.repo 文件
cd /etc/yum.repos.d/
```


