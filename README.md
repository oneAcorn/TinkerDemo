# TinkerDemo
测试Tinker热更新

### 参考地址
官方地址
https://github.com/Tencent/tinker
使用方法
https://blog.csdn.net/u012364659/article/details/108279540
### 使用步骤
#### 打基准包
1 修改gradle.properties里的TINKER_ID,这是个int值,只有打基准包时需要修改,和versionCode原理一样.
2 gradle->Tasks->build>assemble
任务完成后可以在app/build/bakApk下找到1:基准包apk,2:基准包的混淆文件(如果打开混淆了的话),3基准包的R文件
#### 打补丁包
增加补丁代码后
1. 在gradle ext {}中修改tinkerOldApkPath=基准包的路径,tinkerApplyResourcePath=基准包R文件的路径,tinkerApplyMappingPath=补丁包混淆文件的路径(生成此文件需要在补丁包代码修改完成后运行assemble任务后在bakApk文件夹下找到,和打基准包步骤相同)
2. gradle->Tasks->tinker->tinkerPatchRelease(Debug)
任务完成后可以在app/build/outputs/tinkerPatch/release(debug)下找到xxx-patch_signed_7zip.apk.这个就是补丁包,使用TinkerInstaller.onReceiveUpgradePatch()方法即可安装补丁包
### 注意事项/总结
* 基准包上可以附加数量不限的补丁包
    > 比如补丁包1.1新增了一个页面,补丁包1.2新增了一个按钮.基准包可以先安装补丁包1.1再安装1.2,或者直接安装1.2.两种方式效果一样.
* Can't update AndroidManifest.xml, such as add Android Component.
* Do not support some Samsung models with os version android-21.
* Due to Google Play Developer Distribution Agreement, we can't dynamic update our apk.
