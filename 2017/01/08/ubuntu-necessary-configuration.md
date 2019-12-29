- Title: Ubuntu 装机必备的配置
- Category:
- Tag: Linux, Ubuntu
- Author: Kahle
- Creation Time: 2017-01-08T14:51:10.666+0800
- Update Time: 2017-01-08T14:51:10.666+0800
- Original:
- Reference:

---


### 导语

Ubuntu 的一些 基本设置，Ubuntu 的一些基本应用，准备好好的玩一玩ubuntu，貌似我装的经常报一些奇怪的错，尽管没啥影响，感觉好想回到 CentOS 的怀抱，诶，毕竟Debian系列也是Linux的一个重要分支，有必要接触一下的。


### Ubuntu 更改主机名

一般我都是习惯性的“hostname xxxx”，但是重启后就没有了哈。所以百度一下改名字，然后简单记录一下咯（vim需要自行安装，或使用其他编辑器即可），保存修改，重启网络或者重启电脑。
```
sudo vim /etc/hostname
sudo vim /etc/hosts
```


### 中文 Ubuntu 用户目录里的路径改成英文

```
export LANG=en_US
xdg-user-dirs-gtk-update
# 会提示 将目录 转换成英文路径，确定即可
export LANG=zh_CN
# 重启，提示 转换英文路径为中文，取消即可
```


### Ubuntu 去除 amazon 亚马逊等广告

```
sudo apt-get remove unity-webapps-common
```


### Ubuntu 16.04 LTS 将Unity启动器移动到桌面底部

```
gsettings set com.canonical.Unity.Launcher launcher-position Bottom
```


