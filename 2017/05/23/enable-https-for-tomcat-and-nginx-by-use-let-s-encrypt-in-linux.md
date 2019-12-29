- Title: Linux 下用 Let's Encrypt 给 tomcat 和 nginx 启用 https
- Category:
- Tag: Linux, Tomcat, Nginx
- Author: Kahle
- Creation Time: 2017-05-23T14:55:12.666+0800
- Update Time: 2017-05-23T14:55:12.666+0800
- Original:
- Reference:
    - [免费 SSL 证书 Let's Encrypt (certbot) 安装使用教程](https://www.vpser.net/build/letsencrypt-certbot.html)
    - [免费 SSL 安全证书 Let's Encrypt 安装使用教程(附 Nginx / Apache 配置)](https://www.vpser.net/build/letsencrypt-free-ssl.html)
    - [Tomcat 部署 Let’s Encrypt 免费 SSL 证书&&自动续期](http://www.linuxdiyf.com/linux/24147.html)

---


### 导语

Linux 下用 Let’s Encrypt 给 tomcat 和 nginx 启用 https。Https 是众望所归，并且 nginx 做负载均衡也是非常流行的。那么就折腾了一把 https，至于为啥选择 Let’s Encrypt，最主要是免费，尽管阿里云也有个免费的 https 证书，但是那家公司的名声貌似不是很好，而 Let’s Encrypt 是火狐那个组织和思科等很多大公司一起发起的，所以肯定是很靠谱的。


### 正文

```
# 建议先执行一遍这个
yum install epel-release
cd /root/
wget https://dl.eff.org/certbot-auto --no-check-certificate
chmod +x ./certbot-auto
# 尝试安装依赖包（建议将pip源修改为国内的）
./certbot-auto -n


# 单域名生成证书
./certbot-auto certonly --email youemail@mail.com --agree-tos --no-eff-email --webroot -w /home/wwwroot/www.domain.com -d www.domain.com

# 多域名单目录生成单证书(即一个网站多个域名使用同一个证书)
./certbot-auto certonly --email youemail@mail.com --agree-tos --no-eff-email --webroot -w /home/wwwroot/domain_and1 -d www.domain.com -d www.domain1.com

# 多域名多目录生成一个证书(即一次生成多个域名的一个证书)
./certbot-auto certonly --email youemail@mail.com --agree-tos --no-eff-email --webroot -w /home/wwwroot/domain_and1 -d www.domain.com -d www.domain1.com -w /home/wwwroot/domain2_and3  -d www.domain2.com -d www.domain3.com
```

成功提示：

```
IMPORTANT NOTES:
- Congratulations! Your certificate and chain have been saved at /etc/letsencrypt/live/www.domain.com/fullchain.pem. Your cert will
expire on 2017-12-01. To obtain a new or tweaked version of this
certificate in the future, simply run certbot-auto again. To
non-interactively renew *all* of your certificates, run
"certbot-auto renew"
- If you like Certbot, please consider supporting our work by:

Donating to ISRG / Let's Encrypt: https://letsencrypt.org/donate
Donating to EFF: https://eff.org/donate-le
```

生成的证书保存在：/etc/letsencrypt/live/www.domain.com/


