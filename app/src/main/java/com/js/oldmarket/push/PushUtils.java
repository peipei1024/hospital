package com.js.oldmarket.push;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVPush;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SendCallback;

/*
* Class name :PushUtils
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-5-22.
*
*/
public class PushUtils {
    public static void saveInstallation(){
        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null){

                }else {
                    //保存失败
                }
            }
        });
    }

    public static void pushOne(String installation){
        AVQuery pushQuery = AVInstallation.getQuery();
        // 假设 THE_INSTALLATION_ID 是保存在用户表里的 installationId，
        // 可以在应用启动的时候获取并保存到用户表
        pushQuery.whereEqualTo("installationId", installation);
        AVPush.sendMessageInBackground("message to installation",  pushQuery, new SendCallback() {
            @Override
            public void done(AVException e) {

            }
        });
    }
}
