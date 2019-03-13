package org.fonuhuolian.xnumkeyboard;

public interface NumKeyboardListener {

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
}
