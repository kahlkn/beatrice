- Title: 解决 Java 的 Certificates does not conform to algorithm constraints 错误
- Category:
- Tag: Java
- Author: Kahle
- Creation Time: 2016-11-16T12:52:10.666+0800
- Update Time: 2016-11-16T12:52:10.666+0800
- Original:
- Reference:
    - [解決 java.security.cert.CertificateException: Certificates does not conform to algorithm constraints](http://www.cnblogs.com/zemliu/p/4121136.html)
    - [java.security.cert.CertificateException: Certificates does not conform to algorithm constraints](http://blog.csdn.net/applehepeach/article/details/50509782)

---


### 导语

在用 jfinal 的 httpkit 的时候，发现了这样一个问题，在访问某些 https 的网站的时候是OK的，但是有些 https 的网站的时候，就报错了，错误为“Certificates does not conform to algorithm constraints”。


### 正文

在没有翻墙的状态下，就顺手 百度 了一下咯，然后 找出的感觉还算信服的原因是：“在java1.6之后的这个配置文件中，认为MD2的加密方式安全性太低，因而不支持这种加密方式，同时也不支持RSA长度小于1024的密文”。

修改 %JAVA_HOME%/jre/lib/security/java.security 文件中的（jdk1.7）：
```
jdk.certpath.disabledAlgorithms=MD2, RSA keySize < 1024
```
为
```
jdk.certpath.disabledAlgorithms=
```
，或者注释掉此行代码即可。

由此可看出我之前那些会报错的 https 的站点使用的证书的加密算法应该是长度小于1024的rsa加密，因此才会出现这样的异常。


