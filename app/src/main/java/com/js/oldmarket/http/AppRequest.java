package com.js.oldmarket.http;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.google.gson.Gson;
import com.js.oldmarket.bean.Banner;
import com.js.oldmarket.bean.Good;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.OKHttpUtils;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/*
* Class name :AppRequest
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-5-26.
*
*/
public class AppRequest {
    public static void banner(){
//        Request request = new Request.Builder()
//                .url("http://nuc.c365.com/api/Goods/GetPromotions?pageSize=2")
//                .build();
//        OKHttpUtils.okhttp(HttpType.TYPE_Banner, request);


        AVQuery<AVObject> query = new AVQuery<>("Banner");
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null){
                    List<Banner> bannerList = new ArrayList<Banner>();
                    for (int i = 0; i < list.size(); i++){
                        Banner banner = new Banner();
                        AVObject object = list.get(i);
                        banner.setContents((String) object.get("Contents"));
                        bannerList.add(banner);
                    }
                    Gson gson = new Gson();
                    String str = gson.toJson(bannerList);
                    EventBus.getDefault().post(new HttpSuccessEvent(HttpType.TYPE_Banner, str));
                }else {
                    EventBus.getDefault().post(new HttpFailEvent(HttpType.TYPE_Banner, String.valueOf(e.getCode())));
                }
            }
        });
    }
    public static void version(){
        Request request = new Request.Builder()
                .url("http://nuc.c365.com/api/Goods/GetLastAPP")
                .build();
        OKHttpUtils.okhttp(HttpType.TYPE_GetLastAPP, request);
    }
}
