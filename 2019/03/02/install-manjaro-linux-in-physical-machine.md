- Title: 在物理机上安装 Manjaro Linux
- Category:
- Tag: Linux
- Author: Kahle
- Creation Time: 2019-03-02T14:36:53.666+0800
- Update Time: 2019-03-02T14:36:53.666+0800
- Original:
- Reference:
    - [Manjaro 安装以及美化教程](https://blog.csdn.net/weixin_41301508/article/details/81193217)
    - [记录我的（Manjaro）Linux Web 开发环境的搭建过程](https://yqc.im/linux-web-development-environment.html)
    - [Manjaro Linux安装Tim](https://www.jianshu.com/p/d6d628ce4928)
    - [Manjaro 修改主目录为英文](https://www.jianshu.com/p/73299b8e3f58)
    - [传入文件的已配置目录不存在](https://askubuntu.com/questions/837977/configured-directory-for-incoming-file-does-not-exist)
    - [在 Linux 系统(CentOS 7 版本)下，如何实现某个服务的开机自启动？](https://www.cnblogs.com/heyi-77/p/8717843.html)
    - [Ubuntu 通过 xinput 禁用及启用联想笔记本的触摸板](https://www.cnblogs.com/colben/p/4111643.html)

---


### 导语

为什么选择 Manjaro ？遥想当年折腾 Ubuntu 时，我记得安装 有道词典 时，由于一个依赖问题，安装了一个东西，又因为一些原因删掉了一些东西，然后重启之后各种报错。应该是一不小心删除了很重要的依赖。

对于新手来说，在捣鼓 Linux 的时候，最大的问题就是安装软件时的依赖问题。而 arch linux 的 aur 恰好解决了这个痛点。但是 arch 对于新手确实不友好。所以 Manjaro 是一个很不错的选择。


### 正文

安装 Manjaro 还是很简单的。就是“下载镜像”->“U 盘刻录”->“U 盘启动并安装”，运气好就可以正常启动，并且没有比如的驱动问题等。如果碰到驱动问题，只能自行 百度 （谷歌） 解决咯。


#### 1. 更新源

使用 Manjaro 不需要手动去找国内源，使用下面的命令（直接查找最快的源并设置）。

```
sudo pacman-mirrors -i -c China -m rank
sudo pacman -Syyu
```



#### 2. 修改主目录为英文

主要操作为：

```
$ sudo pacman -S xdg-user-dirs-gtk
$ export LANG=en_US
$ xdg-user-dirs-gtk-update
$ # 然后会有个窗口提示语言更改，更新名称即可
$ export LANG=zh_CN.UTF-8
```

然后重启之后可能出现一个错误：“configured directory for incoming file does not exist”。其主要原因为“Blueman Services”任然设置这原来的路径，即“/home/用户名/下载”,而因为主目录改成了英文，导致对应的文件夹找不到，所以出现了这样的错误。通过以下操作就可以修改了。

```
gsettings set org.blueman.transfer shared-path '/home/your_user_name/Downloads'
```

建议具体路径请进入对应文件夹之后 Copy 过来，防止敲入时敲错。


#### 3. 安装 yaourt

```
sudo pacman -S yaourt
```



#### 4. 解决右下角时差问题

时差问题，可以在 系统设置 中的 时间设置 中来进行设置，然后重启电脑。


#### 5. 安装中文输入法

```
sudo pacman -S fcitx-rime
sudo pacman -S fcitx-im # 全部安装
sudo pacman -S fcitx-configtool # 图形化配置工具
```

之后就是还需要更改（如果不存在则创建）“~/.xprofile”。

```
export GTK_IM_MODULE=fcitx
export QT_IM_MODULE=fcitx
export XMODIFIERS="@im=fcitx"
```



#### 6. 开启 ssh 密码登陆，并将服务设置为开机启动

编辑“sudo nano /etc/ssh/sshd_config”，去掉这句话的注释，并确保其是“yes”。

```
PasswordAuthentication yes
```

把服务添加到 Linux 系统的自启动服务列表中去。

```
systemctl enable sshd.service
```

查看是否添加成功。

```
systemctl status sshd.service
```



