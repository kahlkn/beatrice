- Title: 通过 Tinc 组建大局域网
- Category:
- Tag: Tinc
- Author: Kahle
- Create Time: 2020-11-09T20:03:00.000+0800
- Update Time: 2020-11-09T20:03:00.000+0800

---

### 什么是Tinc？

Tinc是虚拟专用网络（VPN）守护程序，它使用隧道和加密在Internet上的主机之间创建安全的专用网络。Tinc是免费软件，并根据GNU通用公共许可版本2或更高版本获得许可。由于VPN在IP级别的网络代码中看起来像是普通的网络设备，因此无需改编任何现有软件。这样，VPN站点就可以通过Internet彼此共享信息，而不会暴露任何信息给其他人。另外，Tinc具有以下功能：

- **加密，认证和压缩**

  可以选择使用zlib或LZO压缩所有流量，并且使用LibreSSL或OpenSSL加密流量并保护其免受消息身份验证代码和序列号的更改。

- **自动全网格路由**
  无论如何设置Tinc守护程序相互连接，VPN流量始终（如果可能）始终直接发送到目标，而无需经过中间跃点。

- **NAT遍历**
  只要VPN中的一个节点允许公共IP地址（即使它是动态IP地址）上的传入连接，tinc就可以进行NAT遍历，从而允许对等方之间进行直接通信。

- **轻松扩展您的VPN**
  当您要将节点添加到您的VPN时，您要做的就是添加一个额外的配置文件，而无需启动新的守护程序或创建和配置新的设备或网络接口。

- **桥接以太网段的能力**
  您可以将多个以太网网段链接在一起，就像一个网段一样工作，从而允许您运行通常只能在Internet上的LAN上运行的应用程序和游戏。

- **在许多操作系统上运行并支持IPv6**
  当前支持Linux，FreeBSD，OpenBSD，NetBSD，OS X，Solaris，Windows 2000，XP，Vista和Windows 7和8平台。有关端口状态的更多信息，请参见我们关于受支持平台的部分。tinc还完全支持IPv6，既可以在其隧道上隧道传输IPv6流量，又可以在现有IPv6网络上创建隧道。

以上内容来自[官网（https://www.tinc-vpn.org/）](https://www.tinc-vpn.org/)简介（机译）。


### 正文

文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。


### 正文1

文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。文章段落内容。

### Windows客户端安装

Windows 端需要先安装虚拟网卡，

1. 在 tinc 的安装目录下有虚拟网卡的驱动安装包，
2. 安装完成后需要将虚拟网卡名称改为与tinc.conf文件中的Interface名称相同，并且手动设置虚拟网卡的 IP 地址和子网掩码，
3. 然后进入到tinc的安装目录下再以管理员的身份运行，运行后会自动创建系统服务，需要停止的时候在 Windows 系统服务管理中停止服务。

```
# Linux/MacOS
tincd -n dock
# Windows(需要管理员权限)
tincd.exe -n dock
```

运行完后，使用ifconfig查看是否有tinc.conf设置的网卡名称，如果没有请检查tinc-up和tinc-down两个文件是否授予执行的权限；如果已经文件已经赋予权限还是没显示网卡，请使用调试模式运行查看问题

停止运行，该命令在Windows端会停止运行并删除系统服务。

```
# Linux/MacOS
tincd -n dock -k
# Windows(需要管理员权限)
tincd.exe -n dock -k
```

调试模式

```
# Linux/MacOS
tincd -n dock -D -d 3
# Windows
tincd.exe -n dock -D -d 3
```



![Windows 下 Tinc 服务注册和启动](通过%20Tinc%20组建大局域网.assets/Windows%20下%20Tinc%20服务注册和启动.png)



注意：调试模式时无法使用Ctrl+C停止运行，需要输入命令tinc -n dock -k才能停止运行

### 来源

- [Original Demo](https://github.com/kahlkn)

### 参考

- [使用 Tinc 组建大内网](https://zimiao.moe/posts/53555/)
- [Reference Demo2](https://github.com/kahlkn)
- https://gofinall.com/89.html
- [Reference Demo3](https://github.com/kahlkn)



https://blog.csdn.net/w670328683/article/details/51673757/



