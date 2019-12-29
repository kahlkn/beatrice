- Title: Linux 常用命令笔记
- Category:
- Tag: Linux
- Author: Kahle
- Creation Time: 2017-01-06T11:21:45.666+0800
- Update Time: 2017-01-06T11:21:45.666+0800
- Original:
- Reference:

---


### 导语

如果 Linux 一段时间不用，一些原本很熟练的命令，也会变得模糊不清（特别加上英语不怎么好的话，可能就是字母记错而已），而我就是这样的人咯。所以，准备把我常用的一些 linux 命令都摘记下来，先不说分不分类，刚开始肯定不会很全的。


### 正文

Linux 的信息查看
```
# Linux 下查看 是32位 还是 64 位
getconf LONG_BIT
# 查看linux内核版本号
cat /proc/version
# 查看linux内核版本号
uname -a
# 查看linux内核版本号
lsb_release -a
```

Linux  vim / vi命令 - vim / vi 编辑器
```
# 向下搜索关键字
命令模式下，按‘/’，然后输入要查找的字符，Enter
# 向上搜索关键字
命令模式下，按‘?’，然后输入要查找的字符，Enter
```

Linux  fdisk命令 - 磁盘分区的程序
```
# 列出外围设备的分区表
fdisk -l
```

Linux  mount命令 - 挂载命令
```
# 将 xvdb1 挂载到 /data 目录下
mount /dev/xvdb1/ /data
# 卸载磁盘挂载 /data
umount /data
# 查看 已挂载 的磁盘
mount
# ???
mount -a
# 自动挂载
/etc/fstab
```

Linux  mkfs命令 - 格式化磁盘命令
```
# 将sda1磁盘分区格式化为ext3格式
mkfs -t ext3 /dev/sda1
```

Linux  zip命令 和 unzip命令 - Linux下的zip压缩和解压缩
```
# 把/home目录下面的mydata目录压缩为mydata.zip
zip -r mydata.zip mydata #压缩mydata目录
# 把/home目录下面的mydata.zip解压到mydatabak目录里面
unzip mydata.zip -d mydatabak
# 把/home目录下面的abc文件夹和123.txt压缩成为abc123.zip
zip -r abc123.zip abc 123.txt
# 把/home目录下面的wwwroot.zip直接解压到/home目录里面
unzip wwwroot.zip
```

Linux  nohup命令 - 让不能后台运行的程序（比如JavaSE程序），后台运行：
```
nohup command > log.file 2>&1 &
nohup java -jar demo.jar > log.file 2>&1 &
```

Linux  chmod命令 - 权限修改命令：
```
chmod (用户权限)(组权限)(其他权限) 文件
权限：r = 4, w = 2, x = 1, - = 0 
授予“用户读写执行权限，组用户读权限，其他用户读权限”
chmod 744 demo.file
```

Linux grep 命令 - 文本搜索命令
```
r：递归搜索，n：列出行号，i：忽略大小写，
c：计算找到的次数，--color，关键字高亮显示。
grep -rn "rackBin不能为空" ./
ps -ef | grep java
# 将/etc/passwd，有出现 root 的行取出来
grep root /etc/passwd
```


