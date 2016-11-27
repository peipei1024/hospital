package com.js.oldmarket.http;

import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.google.gson.Gson;
import com.js.oldmarket.bean.Good;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.utils.Base64Utils;
import com.js.oldmarket.utils.ImageUtils;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.OKHttpUtils;
import com.js.oldmarket.utils.SharedPreferencesUtils;
import com.js.oldmarket.utils.ToastUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

/*
* Class name :GoodRequest
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
public class GoodRequest {
    public static void publishGood(Context context, String Title, String Labels, String Price, String OriginalPrice,
                                   String Place, String TradeEndTime, String Newness, String Description,
                                   ArrayList<String> pics){
        RequestBody body = null;
        try {
            switch (pics.size()){
                case 1:
                    body = new FormEncodingBuilder()
                            .add("Title", Title)
                            .add("Labels", Labels)
                            .add("Price", Price)
                            .add("OriginalPrice", OriginalPrice)
                            .add("Place", Place)
                            .add("TradeEndTime", TradeEndTime)
                            .add("Newness", Newness)
                            .add("Description", Description)
                            .add("Pic1", Base64Utils.bitmapToBase64(Base64Utils.revitionImageSize(pics.get(0))))
                            .add("Pic2", "")
                            .add("Pic3", "")
                            .build();
                    LogUtils.i("oil", Price + "  " + OriginalPrice);
                    break;
                case 2:
                    body = new FormEncodingBuilder()
                            .add("Title", Title)
                            .add("Labels", Labels)
                            .add("Price", Price)
                            .add("OriginalPrice", OriginalPrice)
                            .add("Place", Place)
                            .add("TradeEndTime", TradeEndTime)
                            .add("Newness", Newness)
                            .add("Description", Description)
                            .add("Pic1", Base64Utils.bitmapToBase64(Base64Utils.revitionImageSize(pics.get(0))))
                            .add("Pic2", Base64Utils.bitmapToBase64(Base64Utils.revitionImageSize(pics.get(1))))
                            .add("Pic3", "")
                            .build();
                    break;
                case 3:
                    body = new FormEncodingBuilder()
                            .add("Title", Title)
                            .add("Labels", Labels)
                            .add("Price", Price)
                            .add("OriginalPrice", OriginalPrice)
                            .add("Place", Place)
                            .add("TradeEndTime", TradeEndTime)
                            .add("Newness", Newness)
                            .add("Description", Description)
                            .add("Pic1", Base64Utils.bitmapToBase64(Base64Utils.revitionImageSize(pics.get(0))))
                            .add("Pic2", Base64Utils.bitmapToBase64(Base64Utils.revitionImageSize(pics.get(1))))
                            .add("Pic3", Base64Utils.bitmapToBase64(Base64Utils.revitionImageSize(pics.get(2))))
                            .build();
                    break;
            }
            Map<String, String> map = new HashMap<>();
            map.put("base", Base64Utils.bitmapToBase64(Base64Utils.revitionImageSize(pics.get(0))));
            SharedPreferencesUtils.writeSharedPreferences(context, "base64", map);
            LogUtils.i("GoodRequest", Base64Utils.bitmapToBase64(Base64Utils.revitionImageSize(pics.get(0))));
            Request request = new Request.Builder()
                    .url("http://nuc.c365.com/api/Goods/PublishGood")
                    .addHeader("Authorization", TokenUtils.getInstance().getToken(context))
                    .post(body)
                    .build();
            OKHttpUtils.okhttp(HttpType.TYPE_PublishGood, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getGoods(Context context, String labels, int pageIndex, int pageSize){
//        String url = "http://nuc.c365.com/api/Goods/GetGoods?labels=" + labels + "&pageIndex=" + pageIndex + "&pageSize=" +pageSize;
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        OKHttpUtils.okhttp(HttpType.TYPE_GetGoods, request);

        AVQuery<AVObject> query = new AVQuery<>("Good");
        query.limit(10);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null){
                    List<Good> goodList = new ArrayList<Good>();
                    for (int i = 0; i < list.size(); i++){
                        Good good = new Good();
                        AVObject avObject = list.get(i);
                        good.setGoodID((String)avObject.get("GoodID"));
                        good.setTitle((String)avObject.get("Title"));
                        good.setOriginalPrice((String) avObject.get("OriginalPrice"));
                        good.setPrice((String) avObject.get("Price"));
                        good.setPlace((String) avObject.get("Place"));
                        good.setNewness((String) avObject.get("Newness"));
                        good.setPublishTime((String) avObject.get("PublishTime"));
                        good.setTradeStateID((String) avObject.get("TradeStateID"));
                        good.setDescription((String) avObject.get("Description"));

                        good.setContact((String) avObject.get("Contact"));
                        good.setNickname((String) avObject.get("Nickname"));
                        good.setPictureUrl((String) avObject.get("PictureUrl"));
                        good.setPicture2Url((String) avObject.get("Picture2Url"));
                        good.setPicture3Url((String) avObject.get("Picture3Url"));
                        good.setMobilePhone((String) avObject.get("MobilePhone"));
                        good.setIMEI((String) avObject.get("IMEI"));
                        good.setLabels((String) avObject.get("Labels"));
                        good.setRemarkCount((String) avObject.get("RemarkCount"));
                        good.setEndTradeTime((String) avObject.get("EndTradeTime"));
                        good.setCloseTradeTime((String) avObject.get("CloseTradeTime"));
                        good.setAvatar((String) avObject.get("Avatar"));

                        goodList.add(good);
                    }
                    Gson gson = new Gson();
                    String str = gson.toJson(goodList);
                    EventBus.getDefault().post(new HttpSuccessEvent(HttpType.TYPE_GetGoods, str));
                }else {
                    EventBus.getDefault().post(new HttpFailEvent(HttpType.TYPE_GetGoods, "404"));
                    LogUtils.i("tag", e.getMessage());
                }
            }
        });



    }
    public static void getGood(Context context, final String goodid){
//        String url = "http://nuc.c365.com/api/Goods/GetGood?GoodID=" + goodid;
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        OKHttpUtils.okhttp(HttpType.TYPE_GetGood, request);

        AVQuery<AVObject> query = new AVQuery<>("Good");
        query.whereEqualTo("GoodID", goodid);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null){
                    Good good = null;
                    for (int i = 0; i < list.size(); i++){
                        good = new Good();
                        AVObject avObject = list.get(i);
                        good.setGoodID((String)avObject.get("GoodID"));
                        good.setTitle((String)avObject.get("Title"));
                        good.setOriginalPrice((String) avObject.get("OriginalPrice"));
                        good.setPrice((String) avObject.get("Price"));
                        good.setPlace((String) avObject.get("Place"));
                        good.setNewness((String) avObject.get("Newness"));
                        good.setPublishTime((String) avObject.get("PublishTime"));
                        good.setTradeStateID((String) avObject.get("TradeStateID"));
                        good.setDescription((String) avObject.get("Description"));

                        good.setContact((String) avObject.get("Contact"));
                        good.setNickname((String) avObject.get("Nickname"));
                        good.setPictureUrl((String) avObject.get("PictureUrl"));
                        good.setPicture2Url((String) avObject.get("Picture2Url"));
                        good.setPicture3Url((String) avObject.get("Picture3Url"));
                        good.setMobilePhone((String) avObject.get("MobilePhone"));
                        good.setIMEI((String) avObject.get("IMEI"));
                        good.setLabels((String) avObject.get("Labels"));
                        good.setRemarkCount((String) avObject.get("RemarkCount"));
                        good.setEndTradeTime((String) avObject.get("EndTradeTime"));
                        good.setCloseTradeTime((String) avObject.get("CloseTradeTime"));
                        good.setAvatar((String) avObject.get("Avatar"));


                    }
                    Gson gson = new Gson();
                    String str = gson.toJson(good);
                    EventBus.getDefault().post(new HttpSuccessEvent(HttpType.TYPE_GetGood, str));
                }else {
                    EventBus.getDefault().post(new HttpFailEvent(HttpType.TYPE_GetGood, "404"));
                    LogUtils.i("tag", e.getMessage());
                }
            }
        });
    }
    public static void getMyGoods(Context context, int page, int pageSize){
        String url = "http://nuc.c365.com/api/Goods/GetMyGoods?pageIndex=" + page + "&pageSize=" + pageSize;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", TokenUtils.getInstance().getToken(context))
                .build();
        OKHttpUtils.okhttp(HttpType.TYPE_GetMyGoods, request);
    }
    public static void updateGood(Context context,String GoodID, String closeTradeTime){
        RequestBody body = new FormEncodingBuilder()
                .add("GoodID", GoodID)
                .add("endTradeTime", "")
                .add("closeTradeTime", closeTradeTime)
                .build();
        Request request = new Request.Builder()
                .url("http://nuc.c365.com/api/Goods/UpdateGood")
                .addHeader("Authorization", TokenUtils.getInstance().getToken(context))
                .post(body)
                .build();
        OKHttpUtils.okhttp(HttpType.TYPE_UpdateGood, request);
    }
}
