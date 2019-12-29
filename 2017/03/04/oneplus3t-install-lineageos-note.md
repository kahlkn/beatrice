- Title: 一加3T（OnePlus3T）刷 LineageOS 各种雷区、各种坑
- Category:
- Tag: LineageOS
- Author: Kahle
- Creation Time: 2017-03-04T20:34:48.666+0800
- Update Time: 2017-03-04T20:34:48.666+0800
- Original:
- Reference:
    - [关于一加3刷不进2月14号版本的 lineageos 的解决方法](https://tieba.baidu.com/p/4982354636)
    - [现在一加3和一加3t可以通刷了](https://tieba.baidu.com/f?kz=4981095786)
    - [氧OS官方、氧OS官网](http://downloads.oneplus.net/)

---


### 导语

一加3T、OnePlus3T刷LineageOS（旧CM14.1）各种雷区、各种坑。为一加3T刷 LineageOS 原以为应该是轻轻松松的，也就懒得扒各种资源了，以为还是解BL锁，刷recovery，刷lineage就OK了。结果，我TM的刷砖了2次，所以，小白就要小心了，这个雷区很多，不好踩，没把握就别刷了。


### 正文

我来说说我的雷区吧，相信经典刷机套路大家都懂，即解BL锁，刷recovery，刷第三方ROM。但是呢，一加3T如果要刷 LineageOS 的话，这个套路有可能就走不通了。

首先，氢OS 貌似是没法刷 LineageOS 的。其次呢，氧OS 4.03以下版本也没法刷 LineageOS 的。当然这个是网上说的，但是貌似也相差不远。因为在其他版本下刷的时候，确实会报错，错误是带个7的一排红字。至于原因，按网上的说法就是 没有刷入正确的 firmware。LineageOS 其实2月7号就可以开始通刷了（OnePlus3 和 OnePlus3T），有些人刷不进去是因为没有刷入正确的 firmware。

然后有个雷区，不知道会不会有人踩到，反正我是踩到了，因为刷过不少机，所以对于双清、或者三清一般我都不做的，因为在资源有限的情况下，万一清掉之后刷机没有成功，手机就变成无系统状态了。而不清的话，完全可以在刷完之后，再去清掉缓存，其实也就差不多了。但是【注意事项：1.在刷 lineageOS 前，前先刷 氧OS 4.0.2（为了使 firmware 在正确的版本）再清 data、system、同时清 cache/dalvik，再刷 LineageOS。3、3t用户注意别刷2月7日前的版本。氧OS 4.02 失败的可以试试 4.03 版本】。总之就是必须三清，不然还是会报错的。

在这个帖子中【 http://www.oneplusbbs.com/thread-3264869-1-1.html 】，所说的先刷 OnePlus3_4.0.3-FIRMWARE MODEM-flashable.zip 这个文件，应该也没有错，但是，刷完这个文件，立马三清，刷 LineageOS，也许是能成功的。但是我就是蠢在刷完这个就重启了，然后就砖了，貌似两次都是这样。

在这个帖子中【 http://www.oneplusbbs.com/thread-3249966-1-1.html 】，有不少资源，所说的大概也没有错。其实 LineageOS 的底包貌似不刷也没事，我就是直接刷了最新版的 LineageOS，没刷底包。我的猜测是 氧OS 4.03 就已经包含了需要的底包了吧，或者说 LineageOS 每版都是包含了底包的，而所谓的 LineageOS 实验版其实是从 cm 切换到 LineageOS 而不让数据丢失用的（我不是大神，只是猜测）。

稍微理一下大致步骤吧，先刷 氧OS 4.03（主要就是包含在内的 OnePlus3_4.0.3-FIRMWARE MODEM-flashable.zip 这个文件），然后进入 recovery，三清，刷入 LineageOS 最新版。

当然，有可能是因为我砖过，然后线刷救砖之后导致 BL版本 低，所以 twrp recovery 进去会黑屏，并且感觉整个手机有种不稳定的感觉。后来在未解锁状态下，先刷了官方的氧OS4.03，然后再解锁刷 twrp recovery 的。当前最新版的 twrp 3.10，在进入 recovery 后再进系统，系统数据会被清，应该是个 BUG，就不知道到底是哪个东西的问题了，可能是系统、可能是硬件，也可能是 recovery。最后总结，一加还是老老实实用官方吧，还好有 氧OS 原生系统，不然我要骂人了。主要还是 BUG 太可怕了。

20170606 新增

貌似这篇文章搜索到的人蛮多的，说一下我现在的状况，LineageOS 最新版，这篇文章中说的那个进 rec 会自动清除数据的 BUG 没了。我 twrp 也是最新版哈，3T 刷机相对来说还是很轻松地。LOS 大法好。。。

关于 氢OS 和 氧OS 互刷（氢氧互博术），其实很简单，进入官方 rec，选择双清一下，然后选择要刷的 ROM 就可以刷入了（是不是意想不到的简单）。


