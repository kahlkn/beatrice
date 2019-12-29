- Title: 解决 Caused by: java.lang.ClassNotFoundException: org.hibernate.engine.transaction.spi.TransactionContext
- Category:
- Tag: Java, Hibernate
- Author: Kahle
- Creation Time: 2017-04-26T14:12:42.666+0800
- Update Time: 2017-04-26T14:12:42.666+0800
- Original:
- Reference:
    - [Caused by: java.lang.ClassNotFoundException: org.hibernate.engine.transaction.spi.TransactionContext](http://blog.csdn.net/mr_pang/article/details/50623533)

---


### 导语

在整合ssh（spring mvc的）时，出现了这个问题“Caused by: java.lang.ClassNotFoundException: org.hibernate.engine.transaction.spi.TransactionContext”，百思不得其解，各种度娘，然后发现了问题了。


### 正文

TM的，居然是版本问题。5.0版本 Hibernate，和4.0版本Spring。5.0版本的Hibernate中的相应包中把那个类给取消了。而在 Spring 中配置时，我们最多只能配置到 Hibernate4，所以就出现了上述问题。

总之就是5.0的 Hibernate 中没了某个东西，然后导致和4.0版本 Spring 不能整合了。至于5.0版本的 Spring 能否整合就不清楚了，反正最后我是将 Hibernate 降到了4.0，然后就OK了。


