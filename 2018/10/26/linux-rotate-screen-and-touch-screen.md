- Title: Linux 上旋转显示屏和触摸屏
- Category:
- Tag: Linux
- Author: Kahle
- Creation Time: 2018-10-26T14:32:16.666+0800
- Update Time: 2018-10-26T14:32:16.666+0800
- Original:
- Reference:
    - [What is LightDM](https://wiki.ubuntu.com/LightDM)
    - [rotated monitor. login screen needs rotation](https://askubuntu.com/questions/408302/rotated-monitor-login-screen-needs-rotation#answer-516766)
    - [Tablet PC Rotation](https://wiki.archlinux.org/index.php/Tablet_PC#Rotation)
    - [Xinput command not available in manjaro](https://forum.manjaro.org/t/xinput-command-not-available-in-manjaro/35982)
    - [A simplish script for rotating touchscreens](https://github.com/yourealwaysbe/grox)

---


### 导语

对于一些特殊的机器，比如小屏幕的机器。装完 Linux 之后屏幕可能会是竖屏的，这时候就得旋转屏幕和触摸屏了。而登陆界面任然是竖屏，不管如何折腾都无法解决。并且触摸屏还会经常抽风。


### 正文

首先前两篇文章中的 LightDM 不支持去尝试，大佬除外。反正我是改了之后，系统重启就卡在某个界面上没法继续了。所以如果是大佬，可以参考一下，像我们这种彩笔就绕行把。

旋转 显示屏 和 触摸屏 的命令：

```
#!/bin/sh
# rotate screen && rotate touchscreen
xrandr --output $screen --rotate right && xinput --set-prop '$id' --type=float 'Coordinate Transformation Matrix' 0 1 0 -1 0 1 0 0 1
```

本脚本中“$screen”和“$id”表示是占位符。其中“$screen”表示的是通过 xrandr 命令找到的显示器设备，比如 eDP1 设备。其中“$id”表示通过 xinput 命令找到的 触摸屏 设备的设备 ID，也可以是设备名称。

命令 xinput 后面的一串数字的字符串是触摸屏反转的参数，其方向对应的具体参数为：

```
# normal       1 0 0 0 1 0 0 0 1
# left         0 -1 1 1 0 0 0 0 1
# right        0 1 0 -1 0 1 0 0 1
# inverted    -1 0 1 0 -1 1 0 0 1
```

具体的详细脚本请移步：[grox](https://github.com/yourealwaysbe/grox/blob/master/grox.rb)。

在 Manjaro 中没有“xinput”命令，并且用“pacman -S xinput”也无法安装。这是因为 xinput 的名称不是这个，是“xorg-xinput”，所以输入“pacman -S xorg-xinput”即可安装。


