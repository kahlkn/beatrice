- Title: 解决 Ubuntu 16.04 LTS 下 acer-wireless soft block（软阻塞）导致无线网卡无法使用 的问题
- Category:
- Tag: Linux, Ubuntu
- Author: Kahle
- Creation Time: 2017-02-03T10:05:19.666+0800
- Update Time: 2017-02-03T10:05:19.666+0800
- Original:
- Reference:

---


### 导语

rfkill list 命令下，acer-wireless soft block（软阻塞）导致无线网卡无法使用，在 Ubuntu 很多版本上都有出现，我装了 Ubuntu 16.04 LTS 也出现了这个问题，各种百度折腾之后OK了，所以记录一下经验。


### 正文

网上方法很多，也试了很多，貌似都没有效果，后来主要是以下方法生效了。

```
# 在 /etc/modprobe.d/blacklist.conf 加入即可
blacklist acer-wmi
```

注意，一般网上说都是注销后生效，或者重启网络服务生效，如果不生效，尝试重启电脑。因为我就是不生效，后来重启了一下就好了，然后把之前修改的都干掉，最后才确定这个是有效的。

因为间隔太远，就不提供传送门了，我自己也没有保存下来几个，相信稍微百度一下都能找到不少相关资料的，在提供一些命令把。

```
rfkill list all
rfkill unblock all
```



