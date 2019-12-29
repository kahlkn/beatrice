- Title: 解决在 Linux 下 Tomcat 启动慢的问题
- Category:
- Tag: Linux, Tomcat
- Author: Kahle
- Creation Time: 2017-10-20T15:54:31.666+0800
- Update Time: 2017-10-20T15:54:31.666+0800
- Original:
- Reference:
    - [Tomcat 在 Linux 中启动慢的解决方案](http://www.cnblogs.com/sunyj/p/6229576.html)
    - [Java 中的 SecureRandom 在 Linux 中的实现](http://blog.csdn.net/raintungli/article/details/42876073)

---


### 导语

在Linux下，不管是JDK1.7还是JDK1.8，Tomcat都启动很慢，作为开发环境，不至于像生产环境那样。


### 正文

主要是Linux默认用的是“真随机”，在Tomcat启动的时候要采集嫡值，所以会非常慢。


#### 1. 在 Tomcat 环境中

可以通过配置 JRE 使用非阻塞的 Entropy Source。在catalina.sh中加入这么一行即可。

```
-Djava.security.egd=file:/dev/./urandom
```

加入后再启动 Tomcat，整个启动耗时下降到 Server startup in 2912 ms。


#### 2. 在 JVM 环境中

打开 $JAVA_PATH/jre/lib/security/java.security 这个文件，找到下面的内容：

```
securerandom.source=file:/dev/random
```

替换成

```
securerandom.source=file:/dev/urandom
```



#### 3. 个人测试后结果

Tomcat 环境下无效，在 JVM 环境下的配置有效。


