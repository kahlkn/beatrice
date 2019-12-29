- Title: 猜测推荐可能认识的人
- Category:
- Tag: Java
- Author: Kahle
- Creation Time: 2017-09-07T13:18:35.666+0800
- Update Time: 2017-09-07T13:18:35.666+0800
- Original:
- Reference:

---


### 导语

有一次面试碰到了一个手写的编程题，你朋友的朋友也是你的朋友，你朋友的朋友的朋友也是你的朋友，以此类推，写一个拿出你所有朋友的程序。当时我是写了一个递归，尽管我知道递归问题很多，但是受制于时间、紧张程度等，还是选择了递归，没有选择队列。


### 正文

不过想想这个东西还是很实用的，因为他一说之后我就想到了好友列表和好友推荐。不过当时没事细致去想，今天突然想到了QQ的好友推荐，貌似原理就是你的好友的好友，就是你可能认识的人。

那么QQ要向你推荐好友，是不是就是遍历你的好友列表，然后拿到你好友的好友，然后再根据一定的其他联系，来判断这些好友中哪些人可能是你认识的。我们既然是模拟，就不考虑一些其他联系（毕竟人工智能呀大数据分析呀），毕竟对于一些小系统要实现好友推荐，完全可以用我的模拟代码来搞定。

首先，先来造点假数据

```
public static Map createData() {
    Map all = new HashMap();
    List tmp_a = new ArrayList();
    Collections.addAll(tmp_a, "a1", "a2", "a3", "b");
    all.put("a", tmp_a);
    List tmp_b = new ArrayList();
    Collections.addAll(tmp_b, "b1", "b2", "b3", "c");
    all.put("b", tmp_b);
    List tmp_c = new ArrayList();
    Collections.addAll(tmp_c, "c1", "c2", "c3", "d");
    all.put("c", tmp_c);
    List tmp_d = new ArrayList();
    Collections.addAll(tmp_d, "d1", "d2", "d3", "e");
    all.put("d", tmp_d);
    List tmp_e = new ArrayList();
    Collections.addAll(tmp_e, "e1", "e2", "e3", "f");
    all.put("e", tmp_e);
    List tmp_f = new ArrayList();
    Collections.addAll(tmp_f, "f1", "f2", "f3", "g");
    all.put("f", tmp_f);
    List tmp_g = new ArrayList();
    Collections.addAll(tmp_g, "g1", "g2", "g3", "g4");
    all.put("g", tmp_g);
    return all;
}
```

大致就是

a的朋友：a1, a2, a3, b

b的朋友：b1, b2, b3, c

c的朋友：c1, c2, c3, d

d的朋友：d1, d2, d3, e

e的朋友：e1, e2, e3, f

f的朋友：f1, f2, f3, g

g的朋友：g1, g2, g3, g4

这个数据也是为了后面的层级铺垫的。

然后先是用递归

```
public static List findAllFriends(String who, Map all) {
    List result = new ArrayList();
    List friends = all.get(who);
    if (friends != null) {
        result.addAll(friends);
        for (String s : friends) {
            List list = findAllFriends(s, all);
            result.addAll(list);
        }
    }
    return result;
}
```

整体代码看起来还是蛮简单的，不过很容易爆炸。如果嵌套的足够深，诶，反正递归风险大。

然后队列来一波

```
public static List findAllFriends1(String who, Map all) {
    List result = new ArrayList();
    LinkedList queue = new LinkedList();
    List friends = all.get(who);
    if (friends != null) {
        queue.addAll(friends);
        while (queue.size() > 0) {
            String s = queue.removeFirst();
            List list = all.get(s);
            if (list != null) {
                queue.addAll(list);
            }
            result.add(s);
        }
    }
    return result;
}
```

整个看起来就有点复杂了，但是如果嵌套的深的话，并且用户好友比较多，那么就可能导致溢出了，而且层级过深的好友，其实也就没有推荐的必要了，想想 六度人脉 理论，6层就可以是地球上任何一个人了。所以还需要一个层级来限制，一般1～2层估计就差不多了，但是也许特殊情况呢，所以再次增加一个层数限制。

```
/**
 * tier 层级，第一层 为 who 的 好友 的 好友， 以此 类推
 * 因为好友的好友的好友可以一直循环下去，如果不碰到没有好友的人
 */
public static List findAllFriends2(String who, Map all, int tier) {
    List result = new ArrayList();
    LinkedList queue = new LinkedList();
    List friends = all.get(who);
    if (friends != null) {
        for (String friend : friends) {
            queue.addLast(new Node(friend, 0));
        }
        while (queue.size() > 0) {
            Node node = queue.removeFirst();
            List list = all.get(node.getWho());
            if (list != null && node.getTier() < tier) {
                for (String s : list) {
                    queue.addLast(new Node(s, node.getTier() + 1));
                }
            }
            result.add(node.getWho());
        }
    }
    return result;
}

private static class Node{
    private String who;
    private int tier;

    public Node() {}

    public Node(String who, int tier) {
        this.who = who;
        this.tier = tier;
    }

    public String getWho() {
        return who;
    }

    public Node setWho(String who) {
        this.who = who;
        return this;
    }

    public int getTier() {
        return tier;
    }

    public Node setTier(int tier) {
        this.tier = tier;
        return this;
    }

}
```

关于Map<String, List>这个东西奥，我想如果要设计好友功能的话，数据库中应该会有一个好友表，字段大致为：用户id，好友id。不过估计数据量会非常大。当然也可以用redis，key为用户id，value为一个list，list中为好友id。

反正不管什么数据库，都可以表达成Map这种形式。不过最好别实时计算，以任务调度的方式来得出用户可能认识的人。当然是不带上人工智能、大数据分析啥的的哈，应该还是蛮有实际意义的吧。代码随手写的，如果小问题请忽视哈，严重问题记得邮件我哈。


