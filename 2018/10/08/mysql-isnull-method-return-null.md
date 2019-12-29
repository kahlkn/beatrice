- Title: MySQL 数据库 isNull 方法返回 NULL
- Category:
- Tag: Sql, Mysql
- Author: Kahle
- Creation Time: 2018-10-08T09:41:18.666+0800
- Update Time: 2018-10-08T09:41:18.666+0800
- Original:
- Reference:

---


### 导语

Mysql 数据库在调用 isNull 方法之后返回 NULL 这样的结果。一般来说 isNull 返回的肯定就是“是”或者“否”，不应该是 NULL 这样的结果。


### 正文

Mysql 中 sql 使用 isNull 方法，按理应该返回 0 或者 1，但是却返回了 NULL，原因是因为如果 count 是 0，那么 isNull 就是 NULL，如果 count 不是 0，但是记录中有字段为 NULL，这时 isNull 方法才有用。


