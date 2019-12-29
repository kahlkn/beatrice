- Title: 解决 idea 出现 Unable to open debugger port
- Category:
- Tag: Java
- Author: Kahle
- Creation Time: 2018-11-14T15:33:35.666+0800
- Update Time: 2018-11-14T15:33:35.666+0800
- Original:
- Reference:

---


### 导语

Idea 出现 Unable to open debugger port: java.net.SocketException "socket closed" 这样的错误。


### 正文

Intellij IDEA + tomcat7 运行项目时出现 Unable to open debugger port: java.net.SocketException "socket closed" ，在 Idea 旁边的 Event Log 查看真正占用的端口，然后在使用“netstat -ano | findstr”找到端口号，用任务管理器将其关闭即可。


