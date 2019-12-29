- Title: Zookeeper 的 Java 的简单演示
- Category:
- Tag: Java, Zookeeper
- Author: Kahle
- Creation Time: 2018-02-24T09:56:33.666+0800
- Update Time: 2018-02-24T09:56:33.666+0800
- Original:
- Reference:
    - [Zookeeper Api(java)入门与应用(转)](https://www.cnblogs.com/ggjucheng/p/3370359.html)

---


### 导语

Zookeeper 是一个分布式的服务框架，主要用来解决分布式集群中应用系统的一致性问题。


### 正文

简单使用DEMO

```
import artoria.logging.Logger;
import artoria.logging.LoggerFactory;
import artoria.util.ThreadUtils;
import org.apache.zookeeper.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static artoria.util.Const.ENDL;

public class FastStart {
    private static Logger log = LoggerFactory.getLogger(FastStart.class);

    /**
     * 会话超时时间，设置为与系统默认时间一致
     */
    private static final int SESSION_TIMEOUT = 30 * 1000;

    /**
     * 创建 ZooKeeper 实例
     */
    private ZooKeeper zk;

    /**
     * 创建 Watcher 实例
     */
    private Watcher wh = new Watcher() {
        /**
         * Watched事件
         */
        @Override
        public void process(WatchedEvent event) {
            log.info("WatchedEvent >>>> " + event.toString());
        }
    };

    // 初始化 ZooKeeper 实例
    @Before
    public void createInstance() throws IOException {
        // 连接到ZK服务，多个可以用逗号分割写
        System.out.println(">>>> 实例创建");
        zk = new ZooKeeper("192.168.19.130:2182,192.168.19.130:2183,127.0.0.1:2181", SESSION_TIMEOUT, wh);
        // 在连接的时候判断连接状态，如果未连接成功，程序自动使用其他连接去请求连接
        if (!zk.getState().equals(ZooKeeper.States.CONNECTED)) {
            while (true) {
                if (zk.getState().equals(ZooKeeper.States.CONNECTED)) {
                    break;
                }
                ThreadUtils.sleepQuietly(50000);
            }
        }
    }

    @After
    public void closeInstance() throws InterruptedException {
        System.out.println("<<<< 实例销毁");
        zk.close();
    }

    @Test
    public void test1() throws KeeperException, InterruptedException {
        System.out.println("1. 创建 ZooKeeper 节点 (znode：zoo1，数据：myData，权限：OPEN_ACL_UNSAFE，节点类型：Persistent" + ENDL);
        zk.create("/zoo1", "myData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 添加Watch
        System.out.println("2. 查看是否创建成功：" + new String(zk.getData("/zoo1", wh, null)) + ENDL);

        // 前面一行我们添加了对/zoo1节点的监视，所以这里对/zoo2进行修改的时候，会触发Watch事件。
        System.out.println("3. 修改节点数据：" + zk.setData("/zoo1", "myData1".getBytes(), -1).getVersion() + ENDL);

        // 这里再次进行修改，则不会触发Watch事件，这就是我们验证ZK的一个特性“一次性触发”，也就是说设置一次监视，只会对下次操作起一次作用。
        System.out.println("3-1. 再次修改节点数据：" + zk.setData("/zoo1", "myData2".getBytes(), -1));

        System.out.println("4. 查看是否修改成功：" + new String(zk.getData("/zoo1", false, null)) + ENDL);

        System.out.println("5. 删除节点" + ENDL);
        zk.delete("/zoo1", -1);

        System.out.println("6. 查看节点是否被删除： 节点状态： [" + zk.exists("/zoo1", false) + "]" + ENDL);
    }

    @Test
    public void test2() throws Exception {
        String path = "/zkTest";
        if (zk.exists(path, false) == null) {
            String resp = zk.create(path, "zkTestData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("创建目录节点成功：" + resp);
            System.out.println("该节点下的值为：" + new String(zk.getData(path, false, null)));
        }
        else {
            System.out.println("目录节点已经存在：" + path);
        }

        String childPath = "/zkTest/child1";
        if (zk.exists(childPath, false) == null) {
            String resp = zk.create(childPath, "zkTestChild1Data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(resp);
            System.out.println("该节点下的值为：" + new String(zk.getData(childPath, false, null)));
        }
        else {
            System.out.println("目录节点已经存在：" + childPath);
        }

        // 取出子目录节点列表
        System.out.println(zk.getChildren(path,true));

        // 修改子目录节点数据
        zk.setData(childPath, "zkTestChild1Data123".getBytes(), -1);
        System.out.println("目录节点状态：[" + zk.exists(path,true) + "]");

        // 删除子节点
        zk.delete(childPath, -1);
        // 删除父目录节点
        zk.delete(path, -1);
    }

}
```



