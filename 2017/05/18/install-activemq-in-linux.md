- Title: Linux 快速搭建 ActiveMQ 记录
- Category:
- Tag: Linux, ActiveMQ
- Author: Kahle
- Creation Time: 2017-05-18T19:50:10.666+0800
- Update Time: 2017-05-18T19:50:10.666+0800
- Original:
- Reference:

---


### 导语

Linux 快速搭建 ActiveMQ 记录。总之就是非常快奥。


### 正文

首先安装 JDK，建议用 Oracle 的 JDK，不要用 OpenJDK。

ActiveMQ 官方网址：http://activemq.apache.org，ActiveMQ下载地址：http://activemq.apache.org/download-archives.html 。下载安装包到Linux。

```
# 解压压缩包
tar -xvf apache-activemq-5.5.1-bin.tar.gz
# 64电脑进linux-x86-64，32电脑进linux-x86-32
cd apache-activemq-5.5.1/bin/linux-x86-64/
# 启动MQ
./activemq start
# 停止MQ
./activemq stop
# 重新启动MQ
./activemq restart
```

管理界面地址：youdomain.com:8161/admin

默认登录用户：admin

密码：admin

启用登录验证：apache-activemq-5.5.1/conf/jetty.xml

```
<bean id="securityConstraint" class="org.eclipse.jetty.http.security.Constraint">
  <property name="name" value="BASIC" />
  <property name="roles" value="admin" />
  <property name="authenticate" value="false" />
  # 将false改成true
</bean>
```

修改用户信息：apache-activemq-5.5.1/conf/jetty-realm.properties

程序调用时默认端口：61616


