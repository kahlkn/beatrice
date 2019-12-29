- Title: 重定向打印流
- Category:
- Tag: Java
- Author: Kahle
- Creation Time: 2017-12-13T16:21:55.666+0800
- Update Time: 2017-12-13T16:21:55.666+0800
- Original:
- Reference:

---


### 导语

重定向打印流。已知一段代码，问其内部方法是怎么实现的。


### 正文

已知代码：

```
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        method(a, b);
        System.out.println(a);
        System.out.println(b);
    }
```

其结果：

```
100
200
```

参考：

```
import java.io.PrintStream;

/**
 * 重定向打印流
 */
public class RedirectSysOut {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        method(a, b);
        System.out.println(a);
        System.out.println(b);
    }

    private static void method(int a, int b) {
        PrintStream m = new PrintStream(System.out) {
            @Override
            public void println(int x) {
                if(x == 10) { super.println(100); }
                if(x == 20) { super.println(200); }
            }
        };
        System.setOut(m);
    }

}
```



