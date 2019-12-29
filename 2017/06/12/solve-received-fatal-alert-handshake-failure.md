- Title: 解决 javax.net.ssl.SSLHandshakeException: Received fatal alert: handshake_failure
- Category:
- Tag: Java
- Author: Kahle
- Creation Time: 2017-06-12T11:15:31.666+0800
- Update Time: 2017-06-12T11:15:31.666+0800
- Original:
- Reference:
    - [javax.net.ssl.SSLHandshakeException: Received fatal alert: handshake_failure](https://www.oschina.net/question/2282830_247657)

---


### 导语

异常：java.security.InvalidKeyException: Illegal key size or default parameters 也是同方法解决。异常：javax.net.ssl.SSLHandshakeException: Received fatal alert: handshake_failure 解决。在邮件开启ssl的情况下，Java版本1.8，收邮件的时候，报了这个异常。


### 正文

在目录“%JAVA_HOME%\jre\lib\security”里有“local_policy.jar”和“US_export_policy.jar”，从官网“http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html”下载并且替换掉即可解决（最好备份一下原先的）。


