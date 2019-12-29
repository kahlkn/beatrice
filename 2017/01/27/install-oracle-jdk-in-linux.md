- Title: 在 Linux 下安装 Oracle JDK
- Category:
- Tag: Linux, Jdk
- Author: Kahle
- Creation Time: 2017-01-27T14:23:34.666+0800
- Update Time: 2017-01-27T14:23:34.666+0800
- Original:
- Reference:

---


### 导语

Linux 下安装 Oracle JDK，Ubuntu 安装 jdk，RedHat / CentOS 安装 jdk。对，这就是 linux 下万能的 jdk 安装方法，因为 jdk 安装，除了设置环境变量以外，貌似没啥难度了哈，环境变量设置又是 固定的套路咯，就是这篇文章存在的意义，Copy。


### 正文

首先，官网下载 JDK ，自己看版本、机型下载咯。传送门：http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html。我下载的是：jdk-8u121-linux-x64.tar.gz。

```
# 在 root 用户下
mkdir /usr/local/java
cp jdk-8u121-linux-x64.tar.gz /usr/local/java
cd /usr/local/java
tar -xvf jdk-8u121-linux-x64.tar.gz
# 在 ubuntu 系列下修改 环境变量
vim ~/.bashrc
# 在 centos 系列下修改 环境变量
vim /etc/profile
# 环境变量 增加部分
export JAVA_HOME=/usr/local/java/jdk1.8.0_121 
export JRE_HOME=${JAVA_HOME}/jre 
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib 
export PATH=${JAVA_HOME}/bin:$PATH
# 重新加载 环境变量
source ~/.bashrc # ubuntu
source /etc/profile # centos
# 验证 jdk 是否 成功
java -version
```

这样，jdk 就安装成功了。

然后，还需要修改/etc/environment ，这TM一个坑，坑了我好久。

```
vim /etc/environment
# 增加这样一个东西，可以让非命令行的也调用jdk
JAVA_HOME="/usr/local/java/jdk1.8.0_121"
```


