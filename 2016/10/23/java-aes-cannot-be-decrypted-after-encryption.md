- Title: Java AES加密实现 无法解密
- Category:
- Tag: Java, AES
- Author: Kahle
- Creation Time: 2016-10-23T10:24:01.666+0800
- Update Time: 2016-10-23T10:24:01.666+0800
- Original:
- Reference:

---


### 导语

一直尝试用Java 实现 AES加密，但是每次实现的加密结果和在线的AES对比，都是不一致的。然后各种百度老是出问题，然后就稍稍写一下咯。


### 正文

和在线AES能对上的加密例子：

```
@Test
public void test() throws Exception{
    String data = "hello,World!";
    String key = "1288fyh89tjda456";
    System.out.println(key.getBytes().length);
    //Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, skey/*, new IvParameterSpec(new byte[16])*/);
    byte[] encrypt = cipher.doFinal(data.getBytes());
    System.out.println(Base64Kit.encodeString(encrypt));
 }
```

之前一直对不上的原因应该是因为Key长度一致很随意，而且用了网上的另外一种写法来处理Key，所以可以导致Key长度随意而不会报错。

```
    KeyGenerator generator = KeyGenerator.getInstance("AES");
    SecureRandom random = new SecureRandom();
    generator.init(128, random);
    SecretKey secretKey = generator.generateKey();
```

而且如果Key长度随意的话，会导致那些在线的AES加密对Key做的处理也有可能不同，然后就有可能导致各家在线AES加密结果也不同。AES Key的长度应该是16，在处理的时候会自动处理成128、192或256吧，而Java那里默认是128的，貌似没法改。


