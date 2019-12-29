- Title: 解决 java.io.InvalidClassException:local class incompatible: stream classdesc
- Category:
- Tag: Java
- Author: Kahle
- Creation Time: 2017-03-17T10:45:56.666+0800
- Update Time: 2017-03-17T10:45:56.666+0800
- Original:
- Reference:
    - [java.io.InvalidClassException:local class incompatible: stream classdesc serialV](https://cwj158.iteye.com/blog/1304979)

---


### 导语

异常：Caused by: java.io.InvalidClassException: test.TestBean; local class incompatible: stream classdesc serialVersionUID = 7494285070976537508, local class serialVersionUID = -2852652895817292056 。


### 正文

这个异常，仔细检查了代码，貌似没有问题。无奈之下就扔给度娘，然后得出的结果是JDK版本问题。思考之前，因为某个项目的关系，就把测试服务器上面的JDK版本从 open jdk 换成了oracle jdk，但是懒，就没有重启其他项目，还是简单的测试了一下，是没有问题的。然后就产生了这个异常了。

解决呢，重启一下项目即可。


