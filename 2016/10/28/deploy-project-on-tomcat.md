- Title: 在 Tomcat 中部署项目的三种方法
- Category:
- Tag: Tomcat
- Author: Kahle
- Creation Time: 2016-10-28T14:23:01.666+0800
- Update Time: 2016-10-28T14:23:01.666+0800
- Original:
- Reference:
    - [Java Web开发Tomcat中三种部署项目的方法](http://shuyangyang.blog.51cto.com/1685768/1040127)

---


### 导语

在 Tomcat 中部署项目习惯性的就是直接放到 webapps 文件夹下，或者说删除ROOT文件夹，将自己的项目文件夹重命名成ROOT。但是在一些特殊的情况下，还需要用 一些特殊的部署方式，所以就简单记录一下咯。


### 正文

将 web 项目文件件拷贝到 webapps 目录中，如果不想显示项目名的话，可以将项目文件夹重命名成ROOT。

在tomcat中的conf目录中，在server.xml中的，<host/>节点中添加：
```
<Context path="/hello" docBase="D:hello" debug="0" privileged="true"></Context>
```

很灵活，在conf目录中，新建 Catalina（注意大小写）localhost目录，在该目录中新建一个xml文件，名字可以随意取，只要和当前文件中的文件名不重复就行了，该xml文件的内容为：
```
<Context path="/hello" docBase="D:hello" debug="0" privileged="true"></Context>
```


