package org.fonuhuolian.xnumkeyboard;

import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * GridView实现数字型键盘
 * ①支持数字打乱顺序
 * ②支持是否显示点击动画
 */
public class XNumKeyboardView extends GridView {

    // 数字0-9
    private int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    // 数字键盘数据源
    private List<Integer> list = new ArrayList<>();
    // 适配器
    private NumKeyBoardAdapter numKeyBoardAdapter;

    //创建震动服务对象
    private Vibrator mVibrator;
    // 是否数字按键震动
    private boolean numberVibrate;

    // 是否随机数(默认不随机)
    private boolean random;
    // 是否有点击动画(默认无动画)
    private boolean isClickAnim;
    // 可输入最大长度(默认无限)
    private int maxLength;
    // 到达最大长度后是否删除(默认不清除)
    private boolean maxClear;

    // 按钮的颜色
    private int btnColor;
    // 左下角按钮的颜色
    private int leftBottomBtnColor;
    // 右下角按钮的颜色
    private int rightBottomBtnColor;
    // 按钮的高度
    private float btnHeight;
    // 按钮的字体大小
    private float btnTextSize;
    // 按钮的字体颜色
    private int btnTextColor;
    // 删除按钮的图片
    private int rightBottomImg;


    // 输入的内容
    private StringBuffer inputStr = new StringBuffer();
    // 密码的监听
    private NumKeyboardListener listener;

    private ObjectAnimator openAnim;
    private ObjectAnimator closeAnim;

    public XNumKeyboardView(Context context) {
        this(context, null);
    }

    public XNumKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        getAttrs(context, attrs);

        mVibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);

        // 初始化 密码的顺序
        initList();
        // 初始化适配器
        numKeyBoardAdapter = new NumKeyBoardAdapter(context, list, new XBtnStyle(isClickAnim, btnColor, leftBottomBtnColor, rightBottomBtnColor,
                btnHeight, btnTextSize, btnTextColor, rightBottomImg));
        this.setAdapter(numKeyBoardAdapter);
        // 每行三个
        this.setNumColumns(3);
        // 点击事件
        this.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 9) {
                    // 左下角不做任何操作
                } else if (i == 11) {

                    // 右下角删除操作
                    if (inputStr.length() == 0) {
                        return;
                    }

                    String deleteContent = inputStr.substring(inputStr.length() - 1, inputStr.length());

                    // 执行删除操作
                    inputStr.deleteCharAt(inputStr.length() - 1);

                    if (listener != null)
                        listener.onDelete(inputStr.length(), deleteContent, inputStr.toString() + deleteContent, inputStr.toString());

                } else {

                    // 输入内容

                    // 不超过最大长度可执行输入保存
                    if (inputStr.length() >= maxLength) {
                        return;
                    }

                    if (numberVibrate) {
                        try {
                            // 开启震动
                            mVibrator.vibrate(new long[]{0, 60}, -1);
                        } catch (Exception e) {
                            Log.e("XNumKeyboardView", "", e);
                        }
                    }

                    // 添加到数据源
                    inputStr.append(list.get(i));

                    // 通知监听器
                    if (listener != null) {
                        listener.onInput(inputStr.length() - 1, list.get(i), inputStr.substring(0, inputStr.length() - 1), inputStr.toString());

                        if (inputStr.length() >= maxLength && listener != null) {
                            listener.onComplete(inputStr.substring(0, maxLength));

                            if (maxClear) {
                                listener.onReset("", inputStr.toString());
                                reset();
                            }
                        }

                    }
                }
            }
        });

        this.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 11) {
                    if (listener != null) {
                        listener.onReset("", inputStr.toString());
                    }
                    reset();
                }

                return position == 11 ? true : false;
            }
        });
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XNumKeyboardView);
        random = ta.getBoolean(R.styleable.XNumKeyboardView_kbd_random, false);
        isClickAnim = ta.getBoolean(R.styleable.XNumKeyboardView_kbd_clickAnim, false);
        maxLength = ta.getInteger(R.styleable.XNumKeyboardView_kbd_max_length, Integer.MAX_VALUE);
        maxClear = ta.getBoolean(R.styleable.XNumKeyboardView_kbd_input_max_clear, false);

        btnColor = ta.getColor(R.styleable.XNumKeyboardView_kbd_btn_bg_color, Color.parseColor("#ffffff"));
        leftBottomBtnColor = ta.getColor(R.styleable.XNumKeyboardView_kbd_lower_left_btn_bg_color, Color.parseColor("#f4f9fd"));
        rightBottomBtnColor = ta.getColor(R.styleable.XNumKeyboardView_kbd_lower_right_btn_bg_color, Color.parseColor("#f4f9fd"));

        btnHeight = ta.getDimension(R.styleable.XNumKeyboardView_kbd_btn_height, dip2px(53));
        btnTextSize = ta.getDimension(R.styleable.XNumKeyboardView_kbd_btn_text_size, dip2px(14));
        btnTextColor = ta.getColor(R.styleable.XNumKeyboardView_kbd_btn_text_color, Color.parseColor("#000000"));

        rightBottomImg = ta.getResourceId(R.styleable.XNumKeyboardView_kbd_lower_bottom_btn_img, R.drawable.x_kbd_delete);

        numberVibrate = ta.getBoolean(R.styleable.XNumKeyboardView_kbd_input_number_vibrate, true);

        ta.recycle();
    }

    private void initList() {

        // 是否随机

        if (random)
            shuffleSort();

        // 构造适配器数据

        int index_temp = 0;

        for (int i = 0; i < 12; i++) {

            if (i == 9) {
                // 左下角
                list.add(-1);
            } else if (i == 11) {
                // 右下角
                list.add(-1);
            } else {
                // 数字
                list.add(nums[index_temp]);
                index_temp++;
            }
        }
    }

    /**
     * 随机数字0-9数组的顺序
     */
    private void shuffleSort() {
        for (int i = 0; i < nums.length - 1; i++) {
            int j = (int) (nums.length * Math.random());
            swap(nums, i, j);
        }
    }

    private void swap(int[] data, int i, int j) {
        if (i == j) {
            return;
        }
        data[i] = data[i] + data[j];
        data[j] = data[i] - data[j];
        data[i] = data[i] - data[j];
    }

    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int getTranslationY(View view) {
        final DisplayMetrics dm = getResources().getDisplayMetrics();
        this.measure(
                View.MeasureSpec.makeMeasureSpec(dm.widthPixels, View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(dm.heightPixels, View.MeasureSpec.AT_MOST));
        return view.getMeasuredHeight();
    }

    public void setNumKeyboardListener(NumKeyboardListener listener) {
        this.listener = listener;
    }

    /**
     * 重置
     */
    public void reset() {
        inputStr.delete(0, inputStr.length());
    }


    public void startOpenKbdAnim() {


        if (openAnim != null) {
            // 开启动画
            openAnim.start();
        } else {

            int translationY = getTranslationY(this);

            openAnim = ObjectAnimator.ofFloat(this, "translationY", translationY, 0);
            openAnim.setDuration(300);

            closeAnim = ObjectAnimator.ofFloat(this, "translationY", 0f, translationY);
            closeAnim.setDuration(300);

            openAnim.start();
        }

    }

    public void startCloseKbdAnim() {

        if (closeAnim != null) {
            // 开启动画
            closeAnim.start();
        } else {

            int translationY = getTranslationY(this);

            closeAnim = ObjectAnimator.ofFloat(this, "translationY", 0f, translationY);
            closeAnim.setDuration(300);

            openAnim = ObjectAnimator.ofFloat(this, "translationY", translationY, 0);
            openAnim.setDuration(300);

            closeAnim.start();
        }
    }
}
