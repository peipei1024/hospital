package com.js.oldmarket.utils;

import android.content.Context;
import android.view.WindowManager;

/*
* Class name :ScreenUtils
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-5-27.
*
*/
public class ScreenUtils {
    public static int getScreenWidth(Context context){
        int width = 0;
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return width;
    }
}
