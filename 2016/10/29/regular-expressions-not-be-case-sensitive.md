- Title: 正则表达式 怎么 不区分大小写
- Category:
- Tag:
- Author: Kahle
- Creation Time: 2016-10-29T13:21:01.666+0800
- Update Time: 2016-10-29T13:21:01.666+0800
- Original:
- Reference:
    - [正则表达式中不区分大小写的写法](http://www.jb51.net/article/64297.htm)

---


### 导语

有时候在用正则做一些验证的时候，总是会被大小写干扰，因此，度娘之后再小记一下 。


### 正文

直接在需要不区分大小写的正则 `^[A-Z]{3}$` 前面加上 `(?i)` 即可，即 `(?i)^[A-Z]{3}$` 。


