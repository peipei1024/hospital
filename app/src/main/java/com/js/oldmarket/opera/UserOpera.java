package com.js.oldmarket.opera;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.js.oldmarket.bean.User;
import com.js.oldmarket.utils.SharedPreferencesUtils;

import java.util.Map;

/*
* Class name :UserOpera
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-5-23.
*
*/
public class UserOpera {
    public static User getCurrentUser(Context context){
        User user = null;
        Map<String, String> map = (Map<String, String>) SharedPreferencesUtils.readShrePerface(context, "user");
        if (!TextUtils.isEmpty(map.get("user"))){
            String a = map.get("user");
            Gson gson = new Gson();
            user = gson.fromJson(a, User.class);
        }
        return user;
    }

    public static boolean isLogin(Context context){
        boolean b = false;
        Map<String, String> map = (Map<String, String>) SharedPreferencesUtils.readShrePerface(context, "user");
        if (!TextUtils.isEmpty(map.get("user"))){
            b = true;
        }
        return b;
    }
}
