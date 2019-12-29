- Title: MyBatis 常用笔记整理
- Category:
- Tag: Java, Sql, Mybatis
- Author: Kahle
- Creation Time: 2018-04-03T11:32:25.666+0800
- Update Time: 2018-04-03T11:32:25.666+0800
- Original:
- Reference:

---


### SQL 中时间格式化方法

SQL 中时间格式化方法，并不是 MyBatis 的哈，示例如下。

```
<if test="beginTime!=null and beginTime!=''">
    <![CDATA[ and DATE_FORMAT(tr.add_time, '%Y-%m-%d') >= DATE_FORMAT(#{beginTime}, '%Y-%m-%d') ]]>
</if>
<if test="endTime!=null and endTime!=''">
    <![CDATA[ and DATE_FORMAT(tr.add_time, '%Y-%m-%d') <= DATE_FORMAT(#{endTime}, '%Y-%m-%d') ]]>
</if>
```



### MyBatis 中的 Foreach 功能

MyBatis 中的 Foreach 功能是非常重要的用来处理批量逻辑的功能，比如批量删除、根据ID集合查询等，示例如下。

```
<select id="dynamicForeachTest" parameterType="java.util.List" resultType="Blog">
        select * from t_blog where id in
        <foreach collection="list" index="index" item="item" open="(" separator="," close=")">
                #{item}       
        </foreach>    
</select>
```



### MyBatis 中判断 字符串 是否相等

MyBatis 中判断 字符串 是否相等，经常有的时候会有各种的坑点，示例如下。

```
<if test="newsImage != null and newsImage == 'y'.toString()">
</if>
```

其中判断 newsImage == 'y' 时，一般认为能成功，但实际上是不成功的，需要改为 newsImage == 'y'.toString() 方可成功，原因具体没有细入研究，根据实际使用推测应该是“等于”在 Java 中是个比较复杂问题，涉及的“等于”有可能是变量地址相等，或者是变量值内容相等，在XML文件中简单的 == 在经过MyBatis处理后无法判断是哪种类型的“相等”，所以加 .toString() 做强制转换操作，MyBatis 就知道是值内容的比较，当然就成功了。


### MyBatis 中转义字符的处理

CDATA 区：它的全称为“character data”，以“ <![CDATA[ ”开始，以“ ]]> ”结束，在两者之间嵌入不想被解析程序解析的原始数据，解析器不对 CDATA 区中的内容进行解析，而是将这些数据原封不动地交给下游程序处理。

```
<!-- 小于等于 -->
<![CDATA[ <= ]]>
<!-- 大于等于 -->
<![CDATA[ >= ]]>
```



