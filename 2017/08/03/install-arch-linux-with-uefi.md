- Title: Arch Linux 的 uefi 安装
- Category:
- Tag: Linux
- Author: Kahle
- Creation Time: 2017-08-03T15:31:52.666+0800
- Update Time: 2017-08-03T15:31:52.666+0800
- Original:
- Reference:
    - [Arch Linux 安装过程(UEFI + GRUB + GNOME)](http://www.linuxidc.com/Linux/2016-09/134953.htm)
    - [Arch Linux 安装和使用技巧](http://www.cnblogs.com/vachester/p/5635819.html)

---


### 导语

一直纠结工作环境切换Linux用那个发行版好，看了很多版本的介绍呀等等的。最终还是选择了Arch Linux。不管什么东西，都装一遍，也就有点熟了。而Arch就是这种从头开始折腾的东西。


### 正文

1@下载Arch Linux镜像

Archlinux官网：https://www.archlinux.org/

Archlinux下载地址：https://www.archlinux.org/download/

记得选择自己的国家的镜像点来下载。

2@刻盘（U盘，光盘，哪个方便哪个）

如果Windows的话，可以选择UltraISO、USBWriter等工具，Linux的话，据说dd命令不错。

我是小米笔记本，这两个工具刻盘我都试了，一直都进不去（错误也没怎么关注，只记得Secure Boot什么的，于是百度了一下小米笔记本的Secure Boot才有了解，并且关掉了这个玩意）。

其实用USBWriter刻录（我是用这个的）之后，U盘的分区表已经变成GPT了，并且也支持uefi启动了。只是Secure Boot有点蛋疼。启动就不说了，插上，修改一下BIOS中的boot，将USB设备修改到最前面去。

3@磁盘分区（efi分区，swap分区和ext4分区）

```
# 查看硬盘分区（假设我们在/dev/sda上操作）
fdisk -l
# 如果sda上之前有Windows分区或者其他数据，先做好备份
# 将整块硬盘格式化一下，其实这个步骤应该不是很有必要
mkfs -t ext4 /dev/sda

# 进行硬盘分区
fdisk /dev/sda
# 创建分区
n
# 主分区
p
# 分区编号默认
# 起始卷
# 结束卷

# 分区完毕，保存分区操作并退出fdisk
w

# 查看分区是否生效
cat /proc/partitions
# 刷新分区
partprobe /dev/sda
```

扇区需要计算一下，由于前面fdisk -l中提到一个扇区为512B，所以1G=1024\*1024\*1024/512个扇区，由于分区是从2048开始的，故结束点是1024\*1024\*1024/512+2048。

我是分了三个主分区，分别是efi分区，swap分区和ext4分区。

4@磁盘格式化并挂载

```
# 格式化efi分区
mkfs.vfat -F32 /dev/sda1
# 格式化swap分区
mkswap /dev/sda2
swapon /dev/sda2
# 格式化ext4分区
mkfs.ext4 /dev/sda3
# 挂载 / 
mount /dev/sda3 /mnt
# 建立efi文件夹
mkdir -p /mnt/boot/efi
# 挂载efi分区
mount /dev/sda1 /mnt/boot/efi
```

5@联网

```
iw dev # 识别无线网卡
wifi-menu wlp3s0 # 连接网卡
# 也可以直接
wifi-menu
# 有线网络
dhcpcd 
# 测试一下网络
ping www.baidu.com
```

6@修改源并安装

```
# 修改/etc/pacman.conf文件
vim /etc/pacman.conf
# 在最后添加以下内容
[archlinuxcn]
SigLevel = Optional TrustAll
Server = http://repo.archlinuxcn.org/$arch
# 或者添加163镜像
# 163镜像帮助：http://mirrors.163.com/.help/archlinux-cn.html
[archlinuxcn]
SigLevel = Optional TrustedOnly
Server = http://mirrors.163.com/archlinux-cn/$arch
# 或者添加中科大的镜像
# 中科大镜像帮助：https://lug.ustc.edu.cn/wiki/mirrors/help/archlinuxcn
[archlinuxcn]
Server = https://mirrors.ustc.edu.cn/archlinuxcn/$arch

# 修改/etc/pacman.d/mirrorlist文件
vim /etc/pacman.d/mirrorlist
# 如果China的源被注释了，记得去除
# 可以尝试添加一下aliyun源、163源和sohu源，在文件开头添加
Server = http://mirrors.aliyun.com/archlinux/$repo/os/$arch
Server = http://mirrors.163.com/archlinux/$repo/os/$arch
Server = http://mirrors.sohu.com/archlinux/$repo/os/$arch

# 更新源
pacman -Syy
# 安装基本系统
pacstrap /mnt base base-devel
```

如果出现：“required keys missing keyring”错误，应该是没有安装 archlinuxcn-keyring 包用来导入 GPG key。可以考虑：“SigLevel = Never”，以应付安装基本系统失败。

7@为启动做一些操作

```
# 记录磁盘挂载信息
genfstab -U -p /mnt >> /mnt/etc/fstab
# 检查磁盘挂载信息
cat /mnt/etc/fstab
# 切换根目录（Change Root）
# chroot你本地的arch，你会发现目录变了
arch-chroot /mnt

# 安装vim，arch内置vi有问题
pacman -S vim

# 设置语言环境
编辑并添加内容：LANG=en_US.UTF-8
vim /etc/locale.conf
# 去除en_US，zh_CN语言的UTF-8，GBK，GB2312编码
vim /etc/locale.gen
# 更新语言环境
locale-gen

# 设置时间
ln -s /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
hwclock --systohc --utc

# 设置主机名
echo 你的主机名 > /etc/hostname

# 设置root密码
passwd

# 安装wifi（重启后可能没有wifi配置软件）
pacman -S dialog wpa_supplicant netctl wireless_tools

# 安装并设置GRUB
# 安装GRUB UEFI版本本体
pacman -S grub-efi-x86_64
# 安装EFI管理器
pacman -S efibootmgr
# 将GRUB安装到EFI分区
grub-install --efi-directory=/boot/efi --bootloader-id=grub
# 创建GRUB配置文件
grub-mkconfig -o /boot/grub/grub.cfg

# 结束并重启
# 退出chroot
exit
# 取消挂载
umount /mnt/boot/efi
umount /mnt
# 重启（记得取出U盘）
reboot
```

9@结束语

安装图形化界面槽点太多，我按照传送门安装了一下图形化，感觉不是很顺，估计到时候得专门写一篇文章来操作，也得抓们折腾一下图形化安装。


