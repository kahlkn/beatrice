- Title: MySQL 查询前 n 条记录
- Category:
- Tag: Mysql
- Author: Kahle
- Creation Time: 2016-10-23T10:27:23.666+0800
- Update Time: 2016-10-23T10:27:23.666+0800
- Original:
- Reference:
    - [MySql查询前n条记录](https://blog.csdn.net/xyw_blog/article/details/8781047)

---


### 导语

受Sql Server影响，每次到查询MySql前N条记录的时候，总是习惯性的top，然后必然性的sql报错。然后就度娘了一下，才发现MySql的前N条记录居然还是用分页的limit，甚是惊讶！


### 正文

MySql前N条记录代码示例：

```
SELECT * FROM table LIMIT 10;
SELECT * FROM table LIMIT 0,10;
SELECT * FROM table LIMIT 10,20;
```

第一条SQL是返回前10条记录；

第二条SQL是返回从第1条到第10条记录，也就是前10条记录，由此，0可以省略；

第三条SQL是返回第10条到第20条记录；


