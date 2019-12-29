- Title: Java 事务回滚无效
- Category:
- Tag: Java, Sql
- Author: Kahle
- Creation Time: 2017-03-11T10:20:34.666+0800
- Update Time: 2017-03-11T10:20:34.666+0800
- Original:
- Reference:

---


### 导语

在 Java 中事务 rollback 无效的可能。


### 正文

- 多个数据库操作必须同个会话（连接）

- 设置 autoCommit 为 false

- MySQL的表类型为 Innodb


