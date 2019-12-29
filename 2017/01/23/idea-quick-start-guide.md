- Title: IDEA 快速上手指南
- Category:
- Tag: IntelliJ IDEA
- Author: Kahle
- Creation Time: 2017-01-23T10:36:47.666+0800
- Update Time: 2017-01-23T10:36:47.666+0800
- Original:
- Reference:
    - [IntelliJ IDEA 常用设置讲解](http://www.phperz.com/article/15/0923/159043.html)
    - [idea 自动根据屏幕代码换行](https://www.cnblogs.com/signheart/p/6625127.html)

---


### 导语

多次尝试 使用Idea ，却总是失败，主要还是心态不对。就像一个朋友说的，如果公司强制使用Idea，估计哪怕再难，现在也已经很熟练呢。所以说到底还是心态问题，最后，献上今天上手Idea点滴（注意，不适合大菜鸟）。


### 默认设置

首先，在未打开任何项目的界面的右下角，有个 configure 选项，选择 project default -> settings 。如名，就是项目的默认设置，相对来说Idea基本上已经可以开箱即用了，但是一些比较的环境设置还是必须的。

1. 设置Maven的配置文件，如果没必要对maven版本的纠结，就直接idea的默认maven版本就好了，但是maven仓库就是很个人的了，所以需要配置一下。在Build, Execution, Deployment -> Build Tools下。

2. 设置版本控制的东东，我用的是git，所以需要指定一下git.exe（哈哈，宝宝还是大Windows阵营）的位置。位置在Version Control -> Git 中。

然后settings就差不多OK了，记得点击apply 和 ok。然后就是相同“目录”下的project structure，点开来咯。对了，不管以上还是一下都是个人见解，也许存在一定的不足。

1. Project 选项，添加 自己的jdk目录 （sdk目录）。

2. Libraries 选项，添加 自己的jdk目录 （sdk目录）。

3. SDKs 选项，添加 自己的jdk目录 （sdk目录）。

4. Global Libraries 选项，添加 自己的jdk目录 （sdk目录）。

然后就是apply 和 ok，对，就是那么搞，反正先一股脑的设置一下。然后就是同“目录”下的run configurations，点击，打开。

1. 左上角的加号上面，选择tomcat，然后local，然后根据选项添加一个本地的tomcat用作Idea的web项目的部署服务器。

然后就ok了。由于我是从eclipse上转过来的，所以项目的话，选择的是Import Project，反正就是一股脑的next就差不多了，这个大致看得懂E文，就选选看就好了。导入之后还是有些问题的。

1. file -> project structure ，那几个选项爆红的，稍微修改一下，该删的删掉（反正放弃eclipse）。然后在点击problems，对于问题，点fix咯，怎么选择，自己看E文咯。

2. 项目上右键，Add Framework Support... , 然后选择 maven，Ok，就好了，然后就可以快乐的撸码了。

3. file -> project structure -> artifacts 中，添加 jar ，因为 maven 的web，一般都会自动在这里生成一个war选项，但是jar就难说了，所以需要手动来一发（记得选择的是from models ... 的那个选项 ）。这种方式并不会追加到本地maven仓库去的，属于idea的打包方式吧。

4. Idea界面的左下角，有个图标，像个窗户的，点点看。然后选择maven projects，然后在Lifecycle中的install，来一发，就OK了。


### 其他设置

- 去掉拼写检查：在setting里面搜索spell将其中的拼写检查的√号去掉，搜索never used 关键字将其中的unused的检查去掉。

- 代码提示忽略大小写：在setting里面搜索sensitive，将右侧的 case sensitive completion 修改为NONE 即可。


### 其他设置

好了，大致的快速上手就是这些，这些貌似足以应对大部分开发了。对于一些小细节，比如快捷键，比如自动提示，自动补齐等的，可以百度谷歌咯。个人嘛，快捷键直接eclipse的，因为好几个快捷键不用的话，非常不习惯。还有就是自动提示和补齐，以前eclipse的时候是alt+/，说实话，idea一敲首字母就有真的很不习惯，然后就是默认的自动补齐和输入法切换冲突，所以就直接首字母自动补齐吧，不要去想这个问题了。


#### -- 20190221 19:10:56

首字母自动提示不区分大小写

Settings -> Editor -> General -> Code Completion  下
有 Case sensitive completion 从 First letter 改成 None



自动换行

Settings -> Editor -> General 下
有 Soft Wraps 下 Use soft wraps in editor 打勾


properties 文件下显示中文

Settings -> Editor -> File Encodings 下
Properties Files (*.properties) 栏目中
Default encoding for properties files:  选择为 UTF-8
Transparent native-to-ascii conversion  勾上

http://www.cnblogs.com/godtrue/p/6978883.html



