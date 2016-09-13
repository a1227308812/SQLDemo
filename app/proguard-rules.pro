# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\adt-bundle-windows-x86-20130219\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#保持webView的interface不被混淆
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
# public *;
#}-opti
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-dontshrink
#需要生声明出jar包
#-libraryjars libs/zxing_3.0.0.jar
#忽略zxing警告
-dontwarn com.google.zxing.**
#保持v4包不被混淆
-keep class android.support.v4.**{*;}
#保持zxing包不被混淆
-keep class com.google.zxing.** { *; }
#保持签名不变
-keepattributes Signature
#fastjson混淆
-keep class com.alibaba.fastjson.**{*;}
-keep class com.alibaba.fastjson.annotation.**{*;}
-keep class com.alibaba.fastjson.asm.**{*;}
-keep class com.alibaba.fastjson.parser.**{*;}
-keep class com.alibaba.fastjson.parser.deserializer.**{*;}
-keep class com.alibaba.fastjson.serializer.**{*;}
-keep class com.alibaba.fastjson.support.spring.**{*;}
-keep class com.alibaba.fastjson.util.**{*;}
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
#-----------------------------------以下是对Android原生的一些保持------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
#保持本地方法不被混淆
-keepclasseswithmembernames class * {
native <methods>;
}#保持自定义属性不被混淆
-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet, int);
}
#保持View不被混淆
-keepclassmembers class * extends android.app.Activity {
public void *(android.view.View);
}
 #保持enum不被混淆
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}
#保持Parcelable序列化不被混淆
-keep class * implements android.os.Parcelable {
public static final android.os.Parcelable$Creator *;
}
#保持Serializable序列化不被混淆
-keepclassmembers class * implements java.io.Serializable {
<fields>;
}
