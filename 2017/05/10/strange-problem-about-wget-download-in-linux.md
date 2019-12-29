- Title: 在 Linux 用 wget 下载的一个奇葩问题
- Category:
- Tag: Linux
- Author: Kahle
- Creation Time: 2017-05-10T09:33:10.666+0800
- Update Time: 2017-05-10T09:33:10.666+0800
- Original:
- Reference:

---


### 导语

在 Linux 用 wget 下载的一个奇葩问题，某天我在 oracle 官网下载了 jdk，然后 tar -xvf jdk-8u121-linux-x64.tar.gz，然后搞笑的是，tar 报了一个不是 gzip 格式的错误。刚刚开始以为是 oracle 打包方式独特，只是后缀是这个，所以百度了 tar 的解各种包的命令试了一遍，都不行。


### 正文

然后惊讶的发现了一个问题，下载下来的 jdk 包居然只有5K左右，这他妈不是吓人嘛。然后 cat 了一下这个文件，居然是个 html 文件。然后我就明白了什么了。反正 wget 的时候，要小心，一定要先确定下载下来的是不是你要的，也许就是一个名字一样的 html 文件，那就像我一样尴尬了。


