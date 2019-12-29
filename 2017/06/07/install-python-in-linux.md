- Title: Linux 下安装 Python
- Category:
- Tag: Linux, Python
- Author: Kahle
- Creation Time: 2017-06-07T10:18:40.666+0800
- Update Time: 2017-06-07T10:18:40.666+0800
- Original:
- Reference:
    - [Linux 下 Python 安装升级详细步骤 | Python2 升级 Python3](http://www.cnblogs.com/idotest/p/5442173.html)

---


### 导语

Linux 下安装 Python。


### 正文

[Python 官网](https://www.python.org/)

````
# 下载Python安装包
wget http://www.python.org/ftp/python/3.6.1/Python-3.6.1.tgz
tar -xzvf Python-3.6.1.tgz
cd Python-3.6.1
# 创建python3文件夹以保存py3
mkdir /usr/local/python3
# 编译安装
./configure --prefix=/usr/local/python3
make && make install
# 修改旧python为python2
mv /usr/bin/python /usr/bin/python2
# 建立新版本python的链接
ln -s /usr/local/python3/bin/python3 /usr/bin/python
# 查看当前python的版本
python -V
````



