- Title: 常用 SQL 语句笔记
- Category:
- Tag: Sql
- Author: Kahle
- Creation Time: 2017-01-19T09:53:32.666+0800
- Update Time: 2017-01-19T09:53:32.666+0800
- Original:
- Reference:
    - [mysql from dual插入实现不插入重复记录](https://www.cnblogs.com/lihuanliu/p/6764048.html)
    - [mysql update不能直接使用select的结果](https://www.cnblogs.com/dong-blog/p/5823739.html)

---


### 导语

如同 linux 命令一样，sql 的一些关键字等，很长一段时间不用，加上英语一般的话，还是很容易拼写不出的。还有就是对于 表 的各种操作等，尽管 表 数据库的增删改查一般都没问题，但是 表 的一些修改等的，就不行了哈。总之还是不够熟练，也懒得总是去网上找，所以就自己记录一下咯。


### 正文

修改 mysql 的 root 用户可以远程连接，并且修改密码为：123456
```
# 修改 root 用户可以远程连接，并且修改密码为：123456
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;
# 刷新权限
flush privileges;
```

处理时间戳的sql方法
```
select unix_timestamp(now());
select from_unixtime(1479438447);
```

修改表信息
```
ALTER TABLE `table` AUTO_INCREMENT = 1;
```

Case when
```
select case code when 1 then 'ok'
  when 2 then 'busy'
  when 3 then 'failure'
  else 'error' end  
as status from biz_table
```

If 判断
```
if(expr1, expr2, expr3)
```
如果 expr1 是 true，则 if() 的返回值为 expr2，否则返回值则为 expr3。
代码例子：
```
select if(sex = 1,"男","女") from t_user where id = '1'
```

批量插入
```
INSERT INTO `insert_table` (`datetime`, `uid`, `content`, `type`) 
    VALUES ('0', 'userid_0', 'content_0', 0), ('1', 'userid_1', 'content_1', 1); 
```

自增主键插入并返回插入的主键
```
insert into t_cms_topic_relation(`TOPIC_ID`,`BIZ_ID`,`BIZ_TYPE`) values ('1', '456', '1') ;
SELECT LAST_INSERT_ID() AS id;
```

插入实现不插入重复记录
```
INSERT INTO user  (id, no,add_time,remark)
select * from (
SELECT 1 id, 1 no, NOW() add_time,'1,2,3,1,2' remark FROM DUAL
UNION ALL
SELECT 1 no, 2 no, NOW() add_time,'1,2,3,1,2' remark FROM DUAL
UNION ALL
SELECT 1 no, 3 no, NOW() add_time,'1,2,3,1,2' remark FROM DUAL
) a where not exists (select no from user b where a.no = b.no)
```

Mysql更新时做条件判断
```
UPDATE t_cms_topic ct
LEFT JOIN (
	SELECT
		ID,
		IF (ISNULL(pv), 0, pv) + 1 AS newPv
	FROM
		t_cms_topic
	WHERE
		id = 12
) tmp ON ct.ID = tmp.ID
SET ct.pv = tmp.newPv
WHERE
    ct.ID = 12
```


