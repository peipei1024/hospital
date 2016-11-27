package com.js.oldmarket.utils;

import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import de.greenrobot.event.EventBus;

/*
* Class name :OKHttpUtils
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
public class OKHttpUtils {
    private final static String TAG = OKHttpUtils.class.getSimpleName();
    public static void okhttp(final int httpType, Request request){
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //EventBus.getDefault().post(new HttpFailEvent(httpType, ));
            }

            @Override
            public void onResponse(Response response) throws IOException {
                LogUtils.i(TAG, response.code() + ":code");
                if (response.isSuccessful()){
                    String a = response.body().string();
                    LogUtils.i(TAG, "response:" + a);
                    EventBus.getDefault().post(new HttpSuccessEvent(httpType, a));
                }else {
                    EventBus.getDefault().post(new HttpFailEvent(httpType, String.valueOf(response.code())));
                }
            }
        });
    }
}
