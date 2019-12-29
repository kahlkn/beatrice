- Title: 安卓 CM 系统 LineageOS 系统去除信号上的感叹号
- Category:
- Tag: LineageOS
- Author: Kahle
- Creation Time: 2016-11-07T14:53:26.666+0800
- Update Time: 2016-11-07T14:53:26.666+0800
- Original:
- Reference:
    - [关于 V2EX 提供的 Android Captive Portal Server 地址的更新](https://www.v2ex.com/t/303889)
    - [关于 ANDROID 5.0-7.1.2 网络图标上的感叹号及其解决办法](https://www.noisyfox.cn/android-captive-portal.html)

---


### 导语

CM大法（los大法）好，但是信号栏中的感叹号还是非常让人纠结的。作为强迫症患者，就是必须要干掉他。


### 正文

20170518 更新。

现在安卓版本7.12，之前语句去叉无用。新语句：
```
# 设置网络验证服务器
settings put global captive_portal_https_url https://captive.v2ex.co/generate_204
# 查询当前网络验证服务器地址
settings get global captive_portal_server
```


谷歌一下之后，运行这条命令，然后重启一下系统即可。命令如下：
```
settings put global captive_portal_detection_enabled 0
```

这是在安卓手机上的终端上运行的命令，当然也可以用adb工具来运行。


