package com.js.oldmarket.http;

import android.content.Context;

import com.js.oldmarket.utils.OKHttpUtils;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

/*
* Class name :WordRequest
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-5-25.
*
*/
public class WordRequest {
    public static void sendWord(Context context, String goodid, String content, String price, String phone, String imei){
        RequestBody body = new FormEncodingBuilder()
                .add("GoodID", goodid)
                .add("Remarks", content)
                .add("Bid", price)
                .add("Contact", phone)
                .add("IMEI", imei)
                .build();
        Request request = new Request.Builder()
                .url("http://nuc.c365.com/api/Goods/PublishGoodRemark")
                .addHeader("Authorization", TokenUtils.getInstance().getToken(context))
                .post(body)
                .build();
        OKHttpUtils.okhttp(HttpType.TYPE_PublishGoodRemark, request);
    }
    public static void getWord(Context context, String goodID, int page, int pageSize){
        String url = "http://nuc.c365.com/api/Goods/GetGoodRemarks?goodID=" + goodID + "&pageIndex=" + page + "&pageSize=" +pageSize;
        Request request = new Request.Builder()
                .url(url)
                .build();
        OKHttpUtils.okhttp(HttpType.TYPE_PublishGoodRemark_get, request);
    }
}
