# neteaseLibrary
 云信相关功能封装

AndroidManifest.xml文件配置
```xml
<!-- SDK 权限申明, 第三方 APP 接入时，请将 com.netease.nim.demo 替换为自己的包名 -->
<!-- 和下面的 uses-permission 一起加入到你的 AndroidManifest 文件中。 -->
<permission
    android:name="com.netease.nim.demo.permission.RECEIVE_MSG"
        android:protectionLevel="signature"/>
<!-- 接收 SDK 消息广播权限， 第三方 APP 接入时，请将 com.netease.nim.demo 替换为自己的包名 -->
<uses-permission android:name="com.netease.nim.demo.permission.RECEIVE_MSG"/>
```

代码混淆
```
-dontwarn com.netease.**
-keep class com.netease.** {*;}
```