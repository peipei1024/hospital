package com.js.oldmarket.utils;/*
* Class name :SharedPreferencesUtils
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016/3/5.
*
*/

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Iterator;
import java.util.Map;

public class SharedPreferencesUtils {
    /**
     * 获取SharedPreferences文件内容
     * @param context 上下文
     * @param fileNameNoExt 文件名称（不用带后缀名）
     * @return
     */
    public static Map<String, ?> readShrePerface(Context context,String fileNameNoExt) {
        SharedPreferences preferences = context.getSharedPreferences(
                fileNameNoExt, Context.MODE_PRIVATE);
        return preferences.getAll();
    }

    /**
     * 删除SharedPreferences文件
     * @param context
     * @param filename 文件名，不带后缀
     */
    public static void deleteSharedPreferences(Context context, String filename){
        SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_APPEND);
        preferences.edit().clear().commit();
    }
    /**
     * 向SharedPreferences文件写入内容
     * @param context 上下文对象(不用带后缀名）
     * @param sharedPreferencesName 文件名字
     * @param values Map<String, Integer, boolean, float, long, int>
     */
    public static void writeSharedPreferences(final Context context, final String sharedPreferencesName, final Map<String, ?> values){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences= context.getSharedPreferences(sharedPreferencesName, Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                for (Iterator iterator = values.entrySet().iterator(); iterator.hasNext();)
                {
                    Map.Entry<String, ?> entry = (Map.Entry<String, ?>) iterator.next();
                    if (entry.getValue() instanceof String) {
                        editor.putString(entry.getKey(), (String) entry.getValue());
                    } else if (entry.getValue() instanceof Boolean) {
                        editor.putBoolean(entry.getKey(),(Boolean) entry.getValue());
                    } else if (entry.getValue() instanceof Float) {
                        editor.putFloat(entry.getKey(), (Float) entry.getValue());
                    } else if (entry.getValue() instanceof Long) {
                        editor.putLong(entry.getKey(), (Long) entry.getValue());
                    } else if (entry.getValue() instanceof Integer) {
                        editor.putInt(entry.getKey(),(Integer) entry.getValue());
                    }
                }
                editor.commit();
            }
        }).start();
    }

}
