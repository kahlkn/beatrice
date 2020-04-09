找个成都的大厂。想多照顾家庭就进入养老模式，想再多挣点钱就干点兼职，机会也不会少。说实话，一线对人的吸引力其实主要是机会多，孩子高考不见得比老家容易，毕竟北上广深个个都是硕士培养的娃，录取率高竞争压力也大。其实说白了，就是自己的小骄傲，自己不甘烟酒糖茶，觉得一线机会多，说不定自己以后能碰到个好老板，遇上风头，然后年薪百万，走上人生巅峰。现在人到 30 多，上有老下有小，明白自己不过是芸芸众生普通一员。钱多就吃好点，钱少就吃少点的事儿。都是打工仔。钱差不了多少，生活才是最重要的。我碰到从一线回来买房买车的，除非碰到很坑的小公司，总体生活幸福感提高了很多。
其实对于孩子来说，在哪儿都一样，只要爸爸妈妈在，房间大点小点都无所谓的，很多家长自己强行加戏，觉得对不起孩子。其实现在想想，自己小时候成长环境更差，房间更小，但真正留下深刻记忆的，都是一件件事情，父母的陪伴，旅游，出去玩，不会有人记得自己房间小的。
总之~ 建议老兄好好考虑一下。若回成都，除非非常靠谱的朋友推荐小厂，则非大厂不入



SELECT
	REPLACE (UUID(), '-', '')
FROM
	DUAL



https://www.cnblogs.com/moxiaoan/p/5683743.html
启动： systemctl start firewalld
关闭： systemctl stop firewalld
查看状态： systemctl status firewalld 
开机禁用  ： systemctl disable firewalld
开机启用  ： systemctl enable firewalld



https://juejin.im/post/5bf66fa1f265da611801580b
bin/elasticsearch -d




https://blog.csdn.net/zhuwei_clark/article/details/92649540
配置中心报错：“Cannot pull from remote XX.git the working tree is not clean.”
其实在Spring Cloud官网给出了答案
https://github.com/spring-cloud/spring-cloud-config/blob/master/docs/src/main/asciidoc/spring-cloud-config.adoc#force-pull-in-git-repositories

  Spring Cloud配置服务器会复制远程git存储库，如果本地副本变得不干净(例如，通过OS进程更改文件夹内容)，那么Spring Cloud配置服务器就不能更新远程存储库中的本地副本。为了解决这个问题，有一个强制拉属性，如果本地副本是脏的，它将使Spring Cloud配置服务器从远程存储库中强制pull.
```
spring:
  application:
    name: public-config
  cloud:
    config:
      server:
        git:
          uri: https://git.zhubanxian.com/zhu/online_edu_config.git
          username: ***
          password: ***
          basedir: C:/work/config/tmp
          force-pull: true
```


