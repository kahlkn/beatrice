- Title: 解决 Sourcetree 出现不能同意 key 交换算法的错误
- Category:
- Tag: Git
- Author: Kahle
- Creation Time: 2018-11-15T09:21:18.666+0800
- Update Time: 2018-11-15T09:21:18.666+0800
- Original:
- Reference:

---


### 导语

Sourcetree 在使用 ssh-key 提交时出现不能同意 key 交换算法的错误。


### 正文

Sourcetree 在使用 ssh-key 提交时出现以下错误信息。

```
git -c diff.mnemonicprefix=false -c core.quotepath=false fetch origin
FATAL ERROR: Couldn't agree a key exchange algorithm (available: curve25519-sha256@libssh.org,ecdh-sha2-nistp256,ecdh-sha2-nistp384,ecdh-sha2-nistp521)
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.
```

大致意思是 key 的交换算法不一致，不能读取远程仓库，请确认你是否有访问权限或远程仓库是否存在。

解决方案：错误意思大致很容易明白，而且基本能保证有访问权限并且远程仓库存在，错误大致是出现在交换算法不一致上，我是将 git 版本 和 Sourcetree 版本都升级了一下就解决了。目测应该是 git 版本造成的。


