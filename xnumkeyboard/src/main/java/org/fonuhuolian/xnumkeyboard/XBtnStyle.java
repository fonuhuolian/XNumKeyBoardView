package org.fonuhuolian.xnumkeyboard;

public class XBtnStyle {

    // 是否有点击动画
    private boolean isClickAnim;
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

    public XBtnStyle(boolean isClickAnim, int btnColor, int leftBottomBtnColor, int rightBottomBtnColor, float btnHeight, float btnTextSize, int btnTextColor, int rightBottomImg) {
        this.isClickAnim = isClickAnim;
        this.btnColor = btnColor;
        this.leftBottomBtnColor = leftBottomBtnColor;
        this.rightBottomBtnColor = rightBottomBtnColor;
        this.btnHeight = btnHeight;
        this.btnTextSize = btnTextSize;
        this.btnTextColor = btnTextColor;
        this.rightBottomImg = rightBottomImg;
    }

    public boolean isClickAnim() {
        return isClickAnim;
    }

    public void setClickAnim(boolean clickAnim) {
        isClickAnim = clickAnim;
    }

    public int getBtnColor() {
        return btnColor;
    }

    public void setBtnColor(int btnColor) {
        this.btnColor = btnColor;
    }

    public int getLeftBottomBtnColor() {
        return leftBottomBtnColor;
    }

    public void setLeftBottomBtnColor(int leftBottomBtnColor) {
        this.leftBottomBtnColor = leftBottomBtnColor;
    }

    public int getRightBottomBtnColor() {
        return rightBottomBtnColor;
    }

    public void setRightBottomBtnColor(int rightBottomBtnColor) {
        this.rightBottomBtnColor = rightBottomBtnColor;
    }

    public float getBtnHeight() {
        return btnHeight;
    }

    public void setBtnHeight(float btnHeight) {
        this.btnHeight = btnHeight;
    }

    public float getBtnTextSize() {
        return btnTextSize;
    }

    public void setBtnTextSize(float btnTextSize) {
        this.btnTextSize = btnTextSize;
    }

    public int getBtnTextColor() {
        return btnTextColor;
    }

    public void setBtnTextColor(int btnTextColor) {
        this.btnTextColor = btnTextColor;
    }

    public int getRightBottomImg() {
        return rightBottomImg;
    }

    public void setRightBottomImg(int rightBottomImg) {
        this.rightBottomImg = rightBottomImg;
    }
}
