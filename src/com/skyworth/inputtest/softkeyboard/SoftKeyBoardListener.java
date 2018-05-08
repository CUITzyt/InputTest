package com.skyworth.inputtest.softkeyboard;

public interface SoftKeyBoardListener {
    public void onCommitText(SoftKey key);

    public void onDelete(SoftKey key);

    public void onBack(SoftKey key);

    public void onUpperCase(SoftKey key);

    public void onNumSwitch(SoftKey key);

    public void onChinEng(SoftKey key);
}
