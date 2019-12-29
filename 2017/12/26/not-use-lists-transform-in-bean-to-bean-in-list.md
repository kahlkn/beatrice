- Title: 不能使用 Lists.transform 来做 beanToBeanInList 的操作
- Category:
- Tag: Java
- Author: Kahle
- Creation Time: 2017-12-26T09:55:43.666+0800
- Update Time: 2017-12-26T09:55:43.666+0800
- Original:
- Reference:

---


### 导语

不能使用 Lists.transform 来做 beanToBeanInList 的操作。


### 正文

目标代码：

```
public static <D, S> List<D> beanToBeanInList(List<S> srcList, final Class<D> distClass) {
    return Lists.transform(srcList, new Function<S, D>() {
        @Override
        public D apply(S input) {
            return beanToBean(input, distClass);
        }
    });
}
```

然后进入 Lists.transform 中，其实是创建 TransformingRandomAccessList 集合或者 TransformingSequentialList 集合。而 TransformingSequentialList 继承于 AbstractSequentialList 集合。

这个集合的 get 方法为：

```
public E get(int index) {
    try {
        return listIterator(index).next();
    } catch (NoSuchElementException exc) {
        throw new IndexOutOfBoundsException("Index: "+index);
    }
}
```

而这个迭代器的方法为：

```
@Override
public ListIterator listIterator(final int index) {
    return new TransformedListIterator<F, T>(fromList.listIterator(index)) {
        @Override
        T transform(F from) {
            return function.apply(from);
        }
    };
}
```

由此可以得出， 每次 get 值的时候，其实都重新生成了对象，所以给对象 set 值没用。即遍历数组，并且给对象设值，是无效的，因为下一次取对象，又是一个新的对象，除非在包装的时候用反射。但是 每次取值都反射，貌似 性能上 有点 说不过去。

在 AbstractSequentialList 中 set 方法和 add 方法都是通过 listIterator() 方法来实现的，而这个方法返回的 TransformedListIterator 对象中是不支持 add 和 set 的。

目标代码为：

```
@Override
public void set(T element) {
    throw new UnsupportedOperationException();
}

@Override
public void add(T element) {
    throw new UnsupportedOperationException();
}
```

所以应该用新建list的方式来代替使用Lists.transform。这个东西的场景只适用于读取一次，并且输入多，读取少的场景上。而业务中都是转换用用于操作，甚至多次读取的。当然转换后直接扔出去的场景也不少，所以是否要保留这种方式？


