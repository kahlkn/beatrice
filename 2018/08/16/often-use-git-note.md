- Title: Git 常用笔记
- Category:
- Tag: Git
- Author: Kahle
- Creation Time: 2018-08-16T11:15:20.666+0800
- Update Time: 2018-08-16T11:15:20.666+0800
- Original:
- Reference:
    - [Git 中创建新的空白分支](https://blog.csdn.net/playboyanta123/article/details/48975175)
    - [Git 配置多个 SSH-Key](https://my.oschina.net/stefanzhlg/blog/529403)

---


### 导语

Git 的常用笔记集合。


### 利用命令行生成 SSH-Key

在 git 的命令行中使用以下命令，然后按提示操作即可。

```
$ ssh-keygen -t rsa -C "your_email@youremail.com"
```

生成完毕之后，在目录“C:\Users\用户名\.ssh”下有 id-rsa 和 id-rsa.pub 的私钥和公钥，即刚刚生成的 SSH-Key。


### 将私钥转成 ppk 格式的文件

在 Putty 程序中，或调用 Putty 程序的程序中，是不认识 ssh-keygen 生成的密钥文件的，Putty 只认识自己的 ppk 格式的文件，因此需要进行转换，需要 puttygen 这个程序（Windows 下是 puttygen.exe 文件）。具体步骤大致为：导入 ssh-keygen 生成的私钥 -> 保存为 ppk 文件格式的私钥。具体菜单自行点点就知道了。


#### 创建一个名为 empty-branch 的空分支

Git 创建一个干净的分支（空分支），具体命令如下。

```
git checkout --orphan empty-branch
```



#### Git 冲突解决（基于 Sourcetree 和 Idea）

首先用 Sourcetree 将两个不同的分支合并，假设有冲突，Sourcetree 会报需要先解决冲突再提交，然后当前分支上的文件已经处于需要解决冲突状态（即在有冲突的地方会出现“>>>>”和“<<<<”这样的符号）。这时用 Idea 打开项目，在 Idea 的右上角有一个箭头朝下的按钮“Update Project”，点击，然后选择“Branch Default”和“Using Stash”再点击“OK”，然后 Idea 会显示出冲突的文件，然后解决冲突即可。


#### Git 使用多个 ssh-keygen 生成的 key

在使用 Git 时我们经常会碰到访问公司的 gitlab 一个私钥，访问自己的 github 一个私钥。但是令人郁闷的是 git 程序貌似默认只会去使用名称为“id-rsa”的私钥（也许是我了解的不够），这样就无法两个私钥同时用了。

解决方案：假设在 ~/.ssh/ 目录中有 id-rsa 和 id-rsa.pub 的私钥和公钥，然后再在 ~/.ssh/ 目录中增加 github-rsa 和 github-rsa.pub 的私钥和公钥。在 ~/.ssh 目录下新建一个 config 文件，其内容为：

```
# gitlab
Host gitlab.com
HostName gitlab.com
PreferredAuthentications publickey
IdentityFile ~/.ssh/id_rsa
# github
Host github.com
HostName github.com
PreferredAuthentications publickey
IdentityFile ~/.ssh/github_rsa
```

个人简单测试了一下，貌似并没有什么效果。


