- Title: 利用 Redis 的 incr 命令或 incrby 命令实现自增计数器
- Category:
- Tag: Java, Redis
- Author: Kahle
- Creation Time: 2018-03-15T14:27:51.666+0800
- Update Time: 2018-03-15T14:27:51.666+0800
- Original:
- Reference:

---


### 导语

利用 Redis 的 incr 命令或 incrby 命令实现自增计数器。


### 正文

Redis 的 incr 命令将 key 中储存的数字值增1，如果 key 不存在，那么 key 的值会先被初始化为 0，然后再执行 INCR 操作。

Redis 的 incrby 命令将 key 中储存的数字加上指定的增量值，如果 key 不存在，那么 key 的值会先被初始化为 0，然后再执行 INCR 操作。

代码例子

```
// 该类需要增加比如 @Component 注解以托管到 Spring 容器中
private static final String PREFIX = "CODE";
private static final String DATE_FORMAT_PATTERN = "yyyyMMdd";
private static final String PROM_EXPERT_SHOW_CODE_LOCK = "PROM_EXPERT_SHOW_CODE_LOCK";
private static final Integer STEP = 1;
private static final Integer CODE_NUM_LEN = 3;
private static StringRedisTemplate stringRedisTemplate;

// 该类需要实现 ApplicationContextAware 接口
@Override
public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    stringRedisTemplate = applicationContext.getBean(StringRedisTemplate.class);
}

// 这里使用的是 spring redis 工具
public static String next() {
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN);
    StringBuilder code = new StringBuilder(PREFIX).append(dateFormat.format(new Date()));

    ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
    Long num = opsForValue.increment(PROM_EXPERT_SHOW_CODE_LOCK, STEP);
    Date date = new Date();
    date = DateUtils.setHours(date, 23);
    date = DateUtils.setMinutes(date, 59);
    date = DateUtils.setSeconds(date, 59);
    date = DateUtils.setMilliseconds(date, 999);
    stringRedisTemplate.expireAt(PROM_EXPERT_SHOW_CODE_LOCK, date);

    StringBuilder codeNum = new StringBuilder().append(num);
    while (codeNum.length() < CODE_NUM_LEN) {
        codeNum.insert(0, "0");
    }

    return code.append(codeNum).toString();
}
```



