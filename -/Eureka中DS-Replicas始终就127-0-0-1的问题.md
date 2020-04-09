

https://blog.csdn.net/u012817635/article/details/80189579


2019 04 02 00 20 30

通过spring boot 启动参数的方式，大致如下

```
java -jar eureka-server.jar --server.port=8761 --eureka.instance.ip-address=127.0.0.1 --spring.application.name=eureka-server1 --eureka.client.service-url.defaultZone=http://127.0.0.1:8762/eureka/,http://127.0.0.1:8763/eureka/,http://127.0.0.1:8764/eureka/
```

在启动之后 DS Replicas 中始终就一个 127.0.0.1 并且其对应的地址为三个中的其中一个。一开始以为是启动参数的格式有问题，毕竟 service-url 这个东西其实是个 map 来接受的。

后来在启动日志中瞄到了这样几行，说明是 eureka web 页面展示的时候有问题，他应该将 ip （或者说主机名）做了去重了，去重时并没有带上 IP 才导致了这个问题。

```
2019-04-01 23:52:13.173  INFO 4784 --- [           main] c.n.eureka.cluster.PeerEurekaNodes       : Replica node URL:  http://127.0.0.1:8764/eureka/
2019-04-01 23:52:13.173  INFO 4784 --- [           main] c.n.eureka.cluster.PeerEurekaNodes       : Replica node URL:  http://127.0.0.1:8762/eureka/
2019-04-01 23:52:13.173  INFO 4784 --- [           main] c.n.eureka.cluster.PeerEurekaNodes       : Replica node URL:  http://127.0.0.1:8763/eureka/
```


