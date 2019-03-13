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

> xml

`支持如下自定义属性`

```
<!-- 数字是否随机(默认不随机) true false-->
<attr name="kbd_random" format="boolean" />
<!-- 是否有点击动画效果(仅支持Android6.0+ 默认无动画)true false-->
<attr name="kbd_clickAnim" format="boolean" />
<!-- btn背景颜色 可引用资源 可#FFFFFF(默认色)-->
<attr name="kbd_btn_bg_color" format="color" />
<!-- 左下角btn颜色 可引用资源 可#f4f9fd(默认色)-->
<attr name="kbd_lower_left_btn_bg_color" format="color" />
<!-- 右下角btn颜色 可引用资源 可#f4f9fd(默认色)-->
<attr name="kbd_lower_right_btn_bg_color" format="color" />
<!-- 按钮的高度(默认53dp)-->
<attr name="kbd_btn_height" format="dimension" />
<!-- btn字体的大小(默认14dp)-->
<attr name="kbd_btn_text_size" format="dimension" />
<!-- btn字体的颜色(默认黑色)-->
<attr name="kbd_btn_text_color" format="color" />
<!--右下角btn的图片资源(R.x.xxx)-->
<attr name="kbd_lower_bottom_btn_img" format="reference" />
<!-- 分割线颜色 可引用资源 可#999999(默认)-->
<attr name="kbd_dividerColor" format="color" />
<!-- 分割线高度(默认1px) -->
<attr name="kbd_dividerHeight" format="dimension" />
<!-- 可输入的最大长度-->
<attr name="kbd_max_length" format="integer" />
<!-- 到达最大长度后是否清除数据(默认不清除)-->
<attr name="kbd_input_max_clear" format="boolean" />

```

```
<org.fonuhuolian.xnumkeyboard.XNumKeyboardView
        android:id="@+id/xv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:kbd_btn_text_size="20dp"
        app:kbd_clickAnim="true"
        app:kbd_input_max_clear="false"
        app:kbd_max_length="6"
        app:kbd_random="false" />
```
