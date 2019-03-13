# XNumKeyBoardView
数字键盘


> 添加依赖

`root build.gradle `
```
allprojects {
    repositories {
        ...
        maven {
            url 'https://jitpack.io'
        }
    }
}
```
`module build.gradle `
```
implementation 'com.github.fonuhuolian:XNumKeyBoardView:0.0.1'
```

> 混淆
```
-dontwarn org.fonuhuolian.xnumkeyboard.**
-keep class org.fonuhuolian.xnumkeyboard.**{*;}
```
