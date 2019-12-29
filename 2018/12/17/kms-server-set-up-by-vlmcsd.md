- Title: 通过 vlmcsd 搭建 KMS 激活服务器
- Category:
- Tag: Vlmcsd
- Author: Kahle
- Creation Time: 2018-12-17T11:01:01.666+0800
- Update Time: 2018-12-17T11:01:01.666+0800
- Original:
- Reference:
    - [基于vlmcsd搭建KMS服务器](https://www.jianshu.com/p/11d51983852e)
    - [教你搭建属于你的Windows激活服务器](https://cloud.tencent.com/developer/article/1361131)

---


### 导语
通过 vlmcsd 搭建 KMS 激活服务器。


### 什么是 KMS 

Key Management Service（简称:KMS）（Windows密钥管理服务），这个功能是在Windows Vista之后的产品中的一种新型产品激活机制，目的是为了Microsoft更好的遏制非法软件授权行为(盗版)。


### 什么是 vlmcsd

我也不知道这个东东怎么来的，我了解的是 windows server 系列系统 貌似可能安装 windows 激活服务。我的猜测和据说 vlmcsd 应该是大神通过 反编译 windows 自带的激活服务 弄出来的（仅仅是猜测而已）。


### 相关资源链接

- [vlmcsd 作者的文章](https://wind4.github.io/vlmcsd/)
- [vlmcsd 地址（源码）](https://github.com/Wind4/vlmcsd)
- [vlmcsd 地址（二进制）](https://github.com/Wind4/vlmcsd/releases)
- [vlmcsd 安装脚本（通过 yum git gcc 源码编译）](vlmcsd-install.sh)
- [vlmcsd 启动脚本](vlmcsd-launch.sh)
- [window 激活脚本](kms-active.bat)


### 大致流程

- 安装（源码的话需要下载编译，二进制的话就下载）
- 启动（可以参考启动脚本的内容，可以不加任何参数直接启动，Win 版本不支持 Win10）
- 激活（参考激活脚本，在 Windows 上用管理员身份进行处理）

关于 linux 上的一些细节操作参考脚本或者自己谷歌百度。相关脚本是在网络上下载的，保留着作者的信息。在 linux 上试了，确实可行。


