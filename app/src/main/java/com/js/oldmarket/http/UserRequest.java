package com.js.oldmarket.http;

import android.content.Context;

import com.js.oldmarket.testevent;
import com.js.oldmarket.utils.Base64Utils;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.OKHttpUtils;
import com.js.oldmarket.utils.SharedPreferencesUtils;
import com.js.oldmarket.utils.ToastUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

/*
* Class name :UserRequest
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-5-21.
*
*/
public class UserRequest {
    public static void testGet(Context context){
        Request request = new Request.Builder().url("http://nuc.c365.com/api/goods/test")
                .addHeader("Authorization", TokenUtils.getInstance().getToken(context)).build();
        OKHttpUtils.okhttp(2, request);
    }

    public static void login(Context context){
        Request request = new Request.Builder()
                .url("http://nuc.c365.com/api/Goods/GetPersonnel")
                .addHeader("Authorization", TokenUtils.getInstance().getToken(context))
                .build();
        OKHttpUtils.okhttp(HttpType.TYPE_GETPERSONNEL, request);
    }

    public static void register(Context context, String phone, String password){
        RequestBody body = new FormEncodingBuilder()
                .add("MobilePhone", phone)
                .add("Password", password)
                .add("Nickname", phone)
                .add("Sex", "男")
                .build();
        Request request = new Request.Builder()
                .url("http://nuc.c365.com/api/Goods/CreateUser")
                .addHeader("Authorization", TokenUtils.getInstance().getToken(context))
                .post(body)
                .build();
        OKHttpUtils.okhttp(HttpType.TYPE_CREATEUSER, request);
    }

    public static void update(Context context, String Name, String MobilePhone, String Sex, String Nickname, String picCode, String IMEI){
        try {
            RequestBody body = new FormEncodingBuilder()
                    .add("Name", Name)
                    .add("MobilePhone", MobilePhone)
                    .add("Sex", Sex)
                    .add("Nickname", Nickname)
                    .build();
            Request request = new Request.Builder()
                    .url("http://nuc.c365.com/api/Goods/UpdateUser")
                    .addHeader("Authorization", TokenUtils.getInstance().getToken(context))
                    .post(body)
                    .build();
            OKHttpUtils.okhttp(HttpType.TYPE_UpdateUser, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void recoveryPassword(String phone){
        RequestBody body = new FormEncodingBuilder()
                .add("MobilePhone", phone)
                .build();
        Request request = new Request.Builder()
                .url("http://nuc.c365.com/api/Goods/RecoveryPassword")
                .post(body)
                .build();
        OKHttpUtils.okhttp(HttpType.TYPE_RecoveryPassword, request);
    }

    public static void updatePassword(String phone, String oldpassword, String NewPassword){
        RequestBody body = new FormEncodingBuilder()
                .add("MobilePhone", phone)
                .add("OldPassword", oldpassword)
                .add("NewPassword", NewPassword)
                .build();
        Request request = new Request.Builder()
                .url("http://nuc.c365.com/api/Goods/ChangePassword")
                .post(body)
                .build();
        OKHttpUtils.okhttp(HttpType.TYPE_updatePassword, request);
    }
}
