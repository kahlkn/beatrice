- Title: Java 和正则有关的 java.lang.StackOverflowError 异常
- Category:
- Tag: Java
- Author: Kahle
- Creation Time: 2017-08-18T14:23:19.666+0800
- Update Time: 2017-08-18T14:23:19.666+0800
- Original:
- Reference:
    - [JAVA 正则表达式的溢出问题 及不完全解决方案](http://www.blogjava.net/roymoro/archive/2011/04/28/349163.html)

---


### 导语

写过一些从 html 中拿出所以 a 标签 类似的工具类，一次在爬淘宝的站点的a标签的时候发生了内存溢出异常，惊奇，惊奇，惊奇。因为程序中并没有一些明显的会造成内存溢出的代码。而且很正则有关，非常好奇，度娘了一波。


### 正文

异常代码类似：

```
Exception in thread "main" java.lang.StackOverflowError 
at java.lang.Character.codePointAt(Character.java:2335) 
at java.util.regex.Pattern$CharProperty.match(Pattern.java:3344) 
at java.util.regex.Pattern$Branch.match(Pattern.java:4114) 
at java.util.regex.Pattern$GroupHead.match(Pattern.java:4168) 
at java.util.regex.Pattern$LazyLoop.match(Pattern.java:4357) 
at java.util.regex.Pattern$GroupTail.match(Pattern.java:4227)
at java.util.regex.Pattern$BranchConn.match(Pattern.java:4078)
```

其实当被匹配的字符串长度大于900（我是900分隔，然后没有抛异常）时，这个正则就有可能会抛出异常（具体多少未测试）。


