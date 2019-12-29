- Title: Java 通过 Jedis 操作 Redis 的一些 DEMO
- Category:
- Tag: Java, Redis, Jedis
- Author: Kahle
- Creation Time: 2017-02-01T09:41:50.666+0800
- Update Time: 2017-02-01T09:41:50.666+0800
- Original:
- Reference:

---


### 导语

用Java操作Redis，Reids2.0+客户端集群和Redis3.0+服务器端集群，客户端选用Jedis，其中版本号我选择的是2.8.1，当然也试了2.7的版本的，也是OK滴。


### 正文

maven地址：

```
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.8.1</version>
</dependency>
```

代码：

```
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisDemo {

    public static JedisPoolConfig getJedisPoolConfig() {
        // 网上各种配置版本都有，这里我是Jedis2.8.1，当然试了2.7，也是可以的
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 配置连接池的最大连接数
        jedisPoolConfig.setMaxTotal(1000);
        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例
        jedisPoolConfig.setMaxIdle(10);  
        // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException  
        jedisPoolConfig.setMaxWaitMillis(100000);
        // 在borrow一个jedis实例时，是否提前进行validate操作，如果检验失败，则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }

    /**
     * redis 2.0+ 客户端集群操作
     */
    public static void demo3() {
        // 添加节点信息
        List shards = new ArrayList();
        shards.add(new JedisShardInfo("127.0.0.1", 6379));
        // 构造方法中没有带设置密码的构造，因此如果节点有密码，只能如此
        JedisShardInfo shardInfo = new JedisShardInfo("127.0.0.1", 7379);
        shardInfo.setPassword("123456");
        shards.add(shardInfo);
        JedisShardInfo shardInfo1 = new JedisShardInfo("127.0.0.1", 8379);
        shardInfo1.setPassword("123456");
        shards.add(shardInfo1);
        
        // 构造Jedis连接池对象
        @SuppressWarnings("resource")
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(getJedisPoolConfig(), shards);
        
        // 从Jedis连接池对象中获取redis连接
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        // 通过连接操作redis集群
        shardedJedis.set("hello", "world");
        System.out.println(shardedJedis.get("hello"));
        
        // 如果连接池不为空，则返回给连接池，不然就释放连接
        shardedJedis.close();
    }

    /**
     * redis 3.0+ 服务器端集群操作
     */
    public static void demo2() {
        Set jedisClusterNodes = new HashSet();
        // Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 6379));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7379));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 8379));
        
        // 客户端超时时间（单位是毫秒）
        int timeout = 15000;
        @SuppressWarnings("resource")
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, timeout, getJedisPoolConfig());
        
        // 如果使用Spring的话，直接注入jedisCluster即可
        jedisCluster.set("hello", "world");
        System.out.println(jedisCluster.get("hello"));
        // 20160902 ps : 如果redis3.0的集群，设置有密码，客户端该怎么连接呢？
    }

    /**
     * redis 单机操作demo
     */
    public static void demo1() {
        @SuppressWarnings("resource")
        Jedis jedis = new Jedis("localhost", 6379);
        // 添加redis的连接密码
        jedis.auth("123456");
        // 尝试ping
        System.out.println(jedis.ping());
        // 设置redis的值
        jedis.set("hello", "world");
        // 获取之前设置的redis的值
        System.out.println(jedis.get("hello"));
    }

}
```



