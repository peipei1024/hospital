package com.js.oldmarket.http;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.event.TokenEvent;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.SharedPreferencesUtils;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

/*
* Class name :TokenUtils
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
public class TokenUtils {
    private final static String TAG = TokenUtils.class.getSimpleName();
    private static TokenUtils ourInstance = new TokenUtils();
    private OkHttpClient okHttpClient;

    public static TokenUtils getInstance() {
        return ourInstance;
    }

    private TokenUtils() {
        okHttpClient = new OkHttpClient();
    }

    public String getToken(Context context){
        String token = null;
        Map<String,String> map = (Map<String, String>) SharedPreferencesUtils.readShrePerface(context, "token");
        String a = map.get("access_token");
        StringBuilder sb = new StringBuilder("bearer ");
        sb.append(a);
        token = sb.toString();
        LogUtils.i(TAG, token);
        return token;
    }

    /**
     * 创建,保存令牌,获得个人信息
     * @param context
     * @param phone
     * @param password
     * @return 100成功，其它都是失败
     */
    public int buildToken(Context context, String phone, String password){
        int result = 101;
        RequestBody requestBody = new FormEncodingBuilder()
                .add("grant_type", "password")
                .add("username", phone)
                .add("password", password).build();
        Request request = new Request.Builder().url("http://nuc.c365.com/token")
                .post(requestBody).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            LogUtils.i(TAG, response.code() + " ");
            if (response.isSuccessful()){
                String a = response.body().string();
                LogUtils.i(TAG, a + " ");
                Gson gson = new Gson();
                com.js.oldmarket.bean.Token token = gson.fromJson(a, com.js.oldmarket.bean.Token.class);
                LogUtils.i(TAG, token.getAccess_token());
                if (token.getAccess_token() != null){
                    Map<String, String> map = new HashMap<>();
                    map.put("access_token", token.getAccess_token());
                    SharedPreferencesUtils.writeSharedPreferences(context, "token", map);
                    result = 100;
                    EventBus.getDefault().post(new HttpSuccessEvent(HttpType.TYPE_Login, " "));
                }
            }else {
                EventBus.getDefault().post(new HttpFailEvent(HttpType.TYPE_Login, "密码错误"));
                result = 102;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
