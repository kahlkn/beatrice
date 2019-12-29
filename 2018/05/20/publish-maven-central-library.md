- Title: 将项目发布到Maven中央仓库笔记
- Category:
- Tag: Java, Maven
- Author: Kahle
- Creation Time: 2018-05-20T05:20:00.000+0800
- Update Time: 2018-05-20T05:20:00.000+0800
- Original:
- Reference:
    - [向 maven 中央仓库提交 jar](https://www.cnblogs.com/gaoxing/p/4359795.html)
    - [将 jar 发布到 maven 中央仓库小记](http://blog.csdn.net/hj7jay/article/details/51130398)
    - [项目发布到 maven 中央仓库踩过的坑](http://blog.csdn.net/h3243212/article/details/72374363)
    - [使用 profile 解决 maven 不同的环境 deploy 到不同的 repositories](http://blog.csdn.net/sonycong/article/details/51700700)

---


### 导语

作为开发我觉得最自豪最有意思的事情就是自己写的代码有好多好多的人在用。一直用着Maven中央仓库的东西，何不把自己的项目也提交到Maven中央仓库呢。

- 官网地址：[https://issues.sonatype.org](https://issues.sonatype.org/secure/Dashboard.jspa)
- Nexus地址：[https://oss.sonatype.org](https://oss.sonatype.org/#welcome)


### 在官网上注册一个 Sonatype 的账户

注意 Sonatype 的账户的用户名是不能修改的，所以用户名强迫症患者一定要注意哈。


### 创建一个 Issue 

“Project”相当于项目类型把，选择为为：“Community Support - Open Source Project Repository Hosting (OSSRH)”。“Issue Type”选择为：“New Project”。然后把表单带星的填一下，当然这种项目介绍表单填的稍微详细点也没关系。


### 使用 GPG 生成密钥对

其实 Windows 下可以考虑 [Gpg4win](https://www.gpg4win.org) 这个软件，有中文，比较容易操作。当然命令貌似也不复杂，也可以试试。大致命令如下：

```
# 检查是否安装成功
gpg --version

# 生成密钥对
gpg --gen-key

# 查看公钥
gpg --list-keys

# 将公钥发布到 PGP 密钥服务器（服务器地址就这个）
gpg --keyserver hkp://pool.sks-keyservers.net --send-keys 公钥ID

# 查询公钥是否发布成功
gpg --keyserver hkp://pool.sks-keyservers.net --recv-keys 公钥ID
```



### POM 文件配置（部署方式有多种，这里选择的是 Maven 部署插件部署）

正确的 groupId, artifactId 和 version，大致像我这样就好了。

```
<groupId>com.github.kahlkn</groupId>
<artifactId>artoria</artifactId>
<version>0.5.29.beta</version>
```


项目名称，项目地址，项目描述（projectName, description, url）我的大致就这样弄得，至于是否必选我也不是很清楚，个人认为这个写一下也不错哈。

```
<name>Artoria</name>
<url>https://github.com/kahlkn/artoria</url>
<description>A toolkit only rely on the jdk.</description>
```


证书（license）信息，反正开源项目就 Copy 一个 Apache License 就差不多了，如果细究的话，还是稍微了解一下的好。

```
<licenses>
    <license>
        <name>The Apache Software License, Version 2.0</name>
        <url>http://apache.org/licenses/LICENSE-2.0.txt</url>
    </license>
</licenses>
```


开发者信息。

```
<developers>
    <developer>
        <name>Kahle Kernel</name>
        <email>kahlkn@gmail.com</email>
        <url>https://kahlkn.github.io</url>
    </developer>
</developers>
```


SCM信息。

```
<scm>
    <url>https://github.com/kahlkn/artoria</url>
    <connection>scm:git:https://github.com/kahlkn/artoria.git</connection>
    <developerConnection>https://kahlkn.github.io</developerConnection>
</scm>
```


分发管理（Maven中心库貌似都是这两个地址，注意这里的id，是需要用到的）。

```
<distributionManagement>
    <snapshotRepository>
        <id>ossrh</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    </snapshotRepository>
    <repository>
        <id>ossrh</id>
        <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>
    </repository>
</distributionManagement>
```


生成 javadoc 和 sources 包的插件（注意版本，如果版本有问题请去 [mvnrepository](http://mvnrepository.com) 搜一下是否还有这个版本，如果有是否是你的本地配置问题）。

```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-source-plugin</artifactId>
    <version>2.1</version>
    <configuration>
        <attach>true</attach>
    </configuration>
    <executions>
        <execution>
            <id>attach-sources</id>
            <goals>
                <goal>jar-no-fork</goal>
            </goals>
        </execution>
    </executions>
</plugin>

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-javadoc-plugin</artifactId>
    <version>2.9.1</version>
    <executions>
        <execution>
            <id>attach-javadocs</id>
            <goals>
                <goal>jar</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```


GPG自动签名的插件。

```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-gpg-plugin</artifactId>
    <version>1.5</version>
    <executions>
        <execution>
            <id>sign-artifacts</id>
            <phase>verify</phase>
            <goals>
                <goal>sign</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```



### Maven 的 settings.xml 文件的配置

配置 Sonatype 的账号和密码。

```
<settings>
  <servers>
    <server>
      <id>ossrh</id>
      <username>Sonatype 账号</username>
      <password>Sonatype 密码</password>
    </server>
  </servers>
</settings>
```


配置 GPG 的签名（就是之前生成的密钥对，我是 Windows 机子，并且使用的 Gpg4win，用 cmd 发布的，反正感觉这个配置没用到，因为每次发布 Gpg4win 都会弹框然后要我输入密码）。

```
<settings>
  <profiles>
	<profile>
      <id>ossrh</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <properties>
        <gpg.executable>gpg</gpg.executable>
        <gpg.passphrase>私钥的密码</gpg.passphrase>
      </properties>
    </profile>
  </profiles>
</settings>
```



### 使用 Profile

上述的那些配置，日常打包的时候也会自动走一遍，比较耗时而且没必要，特别是顺带的还把doc包和源码包打出来了，这样日常中肯定受不鸟，献上我自己在用的 Profile。

```
<profiles>

    <profile>
        <id>release</id>
        <build>
            <plugins>

                <plugin>
                    <groupId>org.sonatype.plugins</groupId>
                    <artifactId>nexus-staging-maven-plugin</artifactId>
                    <version>1.6.4</version>
                    <extensions>true</extensions>
                    <configuration>
                        <serverId>ossrh</serverId>
                        <nexusUrl>https://oss.sonatype.org/</nexusUrl>
                        <autoReleaseAfterClose>true</autoReleaseAfterClose>
                    </configuration>
                </plugin>

                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-release-plugin</artifactId>
                    <version>2.5</version>
                    <configuration>
                        <autoVersionSubmodules>true</autoVersionSubmodules>
                        <useReleaseProfile>false</useReleaseProfile>
                        <releaseProfiles>release</releaseProfiles>
                        <goals>deploy</goals>
                    </configuration>
                </plugin>

                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.0</version>
                    <configuration>
                        <source>1.6</source>
                        <target>1.6</target>
                    </configuration>
                </plugin>

                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-gpg-plugin</artifactId>
                    <version>1.5</version>
                    <executions>
                        <execution>
                            <id>sign-artifacts</id>
                            <phase>verify</phase>
                            <goals>
                                <goal>sign</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>

                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-source-plugin</artifactId>
                    <version>2.1</version>
                    <executions>
                        <execution>
                            <id>attach-sources</id>
                            <goals>
                                <goal>jar-no-fork</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>

                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-javadoc-plugin</artifactId>
                    <version>2.9.1</version>
                    <executions>
                        <execution>
                            <id>attach-javadocs</id>
                            <goals>
                                <goal>jar</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>

                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.4.2</version>
                    <configuration>
                        <skipTests>false</skipTests>
                    </configuration>
                </plugin>

            </plugins>

        </build>
    </profile>

</profiles>
```



### 提交一个 Snapshot 版本

修改 POM 文件中的 version 加一个"-SNAPSHOT"，然后执行以下命令部署（这个我没试过）。

```
mvn clean deploy
```



### 提交一个 Release 版本

修改 POM 文件中的 version 不要加"-SNAPSHOT"，然后执行以下命令部署（-P 是指定 profile 的 id 的意思，没有就去掉）。

```
mvn clean deploy -P release
```





```
<parent>
    <groupId>org.sonatype.oss</groupId>
    <artifactId>oss-parent</artifactId>
    <version>7</version>
</parent>
```

这个东西应该是可以不加的，具体的可以点进去看一下其内部，就一些配置信息。


