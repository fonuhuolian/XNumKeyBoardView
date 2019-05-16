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
implementation 'com.github.fonuhuolian:XNumKeyBoardView:1.0.7'
```

> 混淆
```
-dontwarn org.fonuhuolian.xnumkeyboard.**
-keep class org.fonuhuolian.xnumkeyboard.**{*;}
```

> 用法

`系统属性`

```

<!-- 分割线颜色 默认灰色 -->
android:background="@color/colorPrimary"
<!-- 水平分割线高度 默认 0 -->
android:horizontalSpacing="1px"
<!-- 垂直分割线高度 默认 0 -->
android:verticalSpacing="1px"
<!-- 可利于此属性实现显示最上面的分割线以及分割线的高度 默认 0 -->
<!-- 由上面的android:background来控制分割线颜色 -->
android:paddingTop="1px"

```

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
<!-- 右下角btn的图片资源(R.x.xxx)-->
<attr name="kbd_lower_bottom_btn_img" format="reference" />
<!-- 可输入的最大长度(默认int的最大值)-->
<attr name="kbd_max_length" format="integer" />
<!-- 到达最大长度后是否清除数据(默认不清除)-->
<attr name="kbd_input_max_clear" format="boolean" />
<!-- 是否开启数字点击震动(默认震动)-->
<attr name="kbd_input_number_vibrate" format="boolean" />
<!-- 数字键盘的关闭方式(默认INVISIBLE，但这个View仍然会占用在xml文件中所分配的布局空间)-->
<attr name="kbd_input_close_mode" format="enum">
    <enum name="INVISIBLE" value="0" />
    <enum name="GONE" value="1" />
</attr>
```

`xml`

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

`监听`

```
/**
 * 输入内容的回调
 *
 * @param index             输入的index
 * @param textChanged       输入的内容
 * @param beforeTextChanged 上次输入的内容
 * @param afterTextChanged  输入后的内容
 */
void onInput(int index, int textChanged, String beforeTextChanged, String afterTextChanged);

/**
 * 删除按钮短按的回调
 *
 * @param removeIndex      移除的下标
 * @param removeText       移除的内容
 * @param beforeDeleteText 移除前的内容
 * @param afterDeleteText  移除后的内容
 */
void onDelete(int removeIndex, String removeText, String beforeDeleteText, String afterDeleteText);

/**
 * 到达可输入最大长度的回调
 *
 * @param content
 */
void onComplete(String content);

/**
 * 到达可输入最大长度并且设置自动清空属性的回调
 * 删除按钮长按得回调
 *
 * @param textChanged     重置后的内容
 * @param beforeResetText 重置前的内容
 */
void onReset(String textChanged, String beforeResetText);

```

`设置监听`

```
setNumKeyboardListener();
```

`重置键盘`
```
// 清空记录并回调监听
// void onReset(String textChanged, String beforeResetText);
resetKeyBoard();
```

`设置弹出、关闭动画`
```
openKeyboard();
closeKeyboard();
```
