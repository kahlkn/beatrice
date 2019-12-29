- Title: 解决 SEVERE: Context [] startup failed due to previous errors 错误
- Category:
- Tag: Java, Tomcat
- Author: Kahle
- Creation Time: 2016-12-03T10:35:43.666+0800
- Update Time: 2016-12-03T10:35:43.666+0800
- Original:
- Reference:

---


### 导语

在项目部署后，启动 Tomcat 日志中总是出现“SEVERE: Context [] startup failed due to previous errors”这样一条日志，并且去访问项目页面的时候，也是未响应，尝试多次重新部署，并未解决这个问题。


### 正文

然后把这个日志信息拖进 百度 中，看了一些结果，总结一下。一般这个错误的话，有可能是类有缺失，或者jar包有缺失，也有可能是配置文件有缺失，但是我是因为配置文件中有一个条目有缺失导致的。

因此，项目部署的时候，要不就是完全部署，然后修改配置文件。或者说将原来的配置文件拿过来，更新配置文件中有更新的项目，再完全部署一下。反正，总之，小心，因为这个错误刚开始确实好折腾人，毕竟没有明显的异常，就这样一条信息，并不好定位问题在哪。


