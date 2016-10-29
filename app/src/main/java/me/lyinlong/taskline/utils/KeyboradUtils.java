package me.lyinlong.taskline.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * 键盘工具类
 * Created by ownde on 2016/10/29.
 */

public class KeyboradUtils {

    /**
     * 隐藏或显示键盘
     */
    public static void showOrHide(final Activity _this){
        InputMethodManager imm = (InputMethodManager) _this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
