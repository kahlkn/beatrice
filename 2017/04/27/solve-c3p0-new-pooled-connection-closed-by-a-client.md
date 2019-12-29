- Title: 解决 C3P0 的 com.mchange.v2.c3p0.impl.NewPooledConnection@1deb7ab6 closed by a client.
- Category:
- Tag: Java, C3p0
- Author: Kahle
- Creation Time: 2017-04-27T19:03:17.666+0800
- Update Time: 2017-04-27T19:03:17.666+0800
- Original:
- Reference:
    - [NewPooledConnection - closed by a client.](http://blog.sina.com.cn/s/blog_002e20310101ezte.html)
    - [com.mchange.v2.c3p0.impl.NewPooledConnection@1285252 closed by a client.](http://blog.csdn.net/antswallow/article/details/6306302)

---


### 导语

在整合 ssh 时用的是 c3p0 连接池，日志级别是debug级别的，然后惊讶的发现了一个神奇的异常，叫做：“com.mchange.v2.c3p0.impl.NewPooledConnection@1deb7ab6 closed by a client.”。非常纠结，以为出了什么问题，各种度娘ING。


### 正文

然后度娘告诉我，不要慌，这都不是事，确实，后来仔细研究了一下日志信息，确实，这都不是事。

日志信息如下：

```
2017-03-27 18:53:03
-- [DEBUG]-[Thread: Timer-0]-[com.mchange.v2.resourcepool.BasicResourcePool.trace()]
-- trace com.mchange.v2.resourcepool.BasicResourcePool@514c1f00 [managed: 4, unused: 4, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@51c43709)

2017-03-27 18:53:03
-- [DEBUG]-[Thread: Timer-0]-[com.mchange.v2.resourcepool.BasicResourcePool.cullExpired()]
-- FINISHED check for expired resources.  [com.mchange.v2.resourcepool.BasicResourcePool@514c1f00]

2017-03-27 18:53:03
-- [DEBUG]-[Thread: Timer-0]-[com.mchange.v2.resourcepool.BasicResourcePool.incrementPendingAcquires()]
-- incremented pending_acquires: 1

2017-03-27 18:53:03
-- [DEBUG]-[Thread: com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread-#0]-[com.mchange.v2.resourcepool.BasicResourcePool$1DestroyResourceTask.run()]
-- Preparing to destroy resource: com.mchange.v2.c3p0.impl.NewPooledConnection@1deb7ab6

2017-03-27 18:53:03
-- [DEBUG]-[Thread: com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread-#0]-[com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.destroyResource()]
-- Preparing to destroy PooledConnection: com.mchange.v2.c3p0.impl.NewPooledConnection@1deb7ab6

2017-03-27 18:53:03
-- [DEBUG]-[Thread: com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread-#0]-[com.mchange.v2.c3p0.impl.NewPooledConnection.close()]
-- com.mchange.v2.c3p0.impl.NewPooledConnection@1deb7ab6 closed by a client.
java.lang.Exception: DEBUG -- CLOSE BY CLIENT STACK TRACE
	at com.mchange.v2.c3p0.impl.NewPooledConnection.close(NewPooledConnection.java:566)
	at com.mchange.v2.c3p0.impl.NewPooledConnection.close(NewPooledConnection.java:234)
	at com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.destroyResource(C3P0PooledConnectionPool.java:470)
	at com.mchange.v2.resourcepool.BasicResourcePool$1DestroyResourceTask.run(BasicResourcePool.java:964)
	at com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread.run(ThreadPoolAsynchronousRunner.java:547)

2017-03-27 18:53:03
-- [DEBUG]-[Thread: com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread-#0]-[com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.destroyResource()]
-- Successfully destroyed PooledConnection: com.mchange.v2.c3p0.impl.NewPooledConnection@1deb7ab6

2017-03-27 18:53:03
-- [DEBUG]-[Thread: com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread-#0]-[com.mchange.v2.resourcepool.BasicResourcePool$1DestroyResourceTask.run()]
-- Successfully destroyed resource: com.mchange.v2.c3p0.impl.NewPooledConnection@1deb7ab6

2017-03-27 18:53:03
-- [DEBUG]-[Thread: com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread-#2]-[com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource()]
-- com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager@74365e59.acquireResource() returning. 

2017-03-27 18:53:03
-- [DEBUG]-[Thread: com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread-#2]-[com.mchange.v2.resourcepool.BasicResourcePool.trace()]
-- trace com.mchange.v2.resourcepool.BasicResourcePool@514c1f00 [managed: 5, unused: 5, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@51c43709)

2017-03-27 18:53:03
-- [DEBUG]-[Thread: com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread-#2]-[com.mchange.v2.resourcepool.BasicResourcePool.decrementPendingAcquires()]
-- decremented pending_acquires: 0

2017-03-27 18:53:18
-- [DEBUG]-[Thread: Timer-0]-[com.mchange.v2.resourcepool.BasicResourcePool$CullTask.run()]
-- Checking for expired resources - Mon Mar 27 18:53:18 CST 2017 [com.mchange.v2.resourcepool.BasicResourcePool@514c1f00]
```

大致意思就是（英语不好哈）：

1. 检查过期资源完成。

2. 准备摧毁资源XXX（PooledConnection）

3. 被客户端关闭（就是摧毁资源成功，然后报了个debug异常通知）

4. 成功摧毁资源

所以最后总结呢，这个日志信息可以忽略哈，这都不是事，不要慌。


