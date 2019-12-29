- Title: 在 JDK7 之后不应该再使用 guava 来创建 map 了
- Category:
- Tag: Java, Guava
- Author: Kahle
- Creation Time: 2018-10-15T15:45:59.666+0800
- Update Time: 2018-10-15T15:45:59.666+0800
- Original:
- Reference:

---


### 导语

在 JDK7 之后不应该再使用 guava 来创建 map 了，比如使用“newHashMap”等方法。


### 正文

据了解当初有这些方法是因为 JDK7 之前，也就是 1.6 有的泛型时 new 对象后面必须将泛型写齐，嫌弃麻烦而封装的这些方法。但是 1.7 之后有了泛型智能推断就不需要，而且谷歌自己也注释了可以弃用，最少“newHashMap”这个方法上有这样的注释。

```
  /**
   * Creates a <i>mutable</i>, empty {@code HashMap} instance.
   *
   * <p><b>Note:</b> if mutability is not required, use {@link
   * ImmutableMap#of()} instead.
   *
   * <p><b>Note:</b> if {@code K} is an {@code enum} type, use {@link
   * #newEnumMap} instead.
   *
   * <p><b>Note for Java 7 and later:</b> this method is now unnecessary and
   * should be treated as deprecated. Instead, use the {@code HashMap}
   * constructor directly, taking advantage of the new
   * <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
   *
   * @return a new, empty {@code HashMap}
   */
  public static <K, V> HashMap<K, V> newHashMap() {
    return new HashMap<K, V>();
  }
```



