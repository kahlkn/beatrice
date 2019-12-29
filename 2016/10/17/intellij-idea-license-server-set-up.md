- Title: IntelliJ IDEA License Server 搭建教程
- Category:
- Tag: IntelliJ IDEA
- Author: Kahle
- Creation Time: 2016-10-17T09:17:29.666+0800
- Update Time: 2016-10-17T09:17:29.666+0800
- Original:
- Reference:
    - [IntelliJ IDEA License Server本地搭建教程](http://blog.lanyus.com/archives/174.html) 

---


### 导语

IntelliJ IDEA License Server 搭建教程，作为强迫症患者，嫌弃注册码动不动要更换，嫌弃网络上的 License Server 容易遭封杀，所以决定自己搭建自己用咯。


### 正文

其实也没啥难的，就是要下对软件，然后启动就好了。详细信息请进这个传送门：http://blog.lanyus.com/archives/174.html 。注册码的话可以进这两个传送门参考参考：http://blog.lanyus.com/6.html、http://idea.qinxi1992.cn/。

参数介绍：

- 添加 -p 参数，用于指定监听的端口；

- 添加 -u 参数，用于指定用户名，当未设置 -u 参数，且计算机用户名为 ^[a-zA-Z0-9]+$ 时，使用计算机用户名作为 idea 用户名；

- 添加 -l 参数，用于指定绑定监听到哪个 IP；

- 添加 -prolongationPeriod 参数，具体干嘛的我也不知道；

- 修改当用户使用浏览器直接打开授权服务器时，若在程序工作目录中存在 IntelliJIDEALicenseServer.html 文件，则返回 IntelliJIDEALicenseServer.html 中的内容到用户浏览器；


