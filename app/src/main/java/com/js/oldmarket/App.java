package com.js.oldmarket;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/*
* Class name :App
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016/2/22.
*
*/
public class App extends Application{
    private static App myApplication = null;

    public static String TAG;


    @Override
    public void onCreate() {
        super.onCreate();
        TAG = this.getClass().getSimpleName();
        myApplication = this;
        //initImageLoader();
        AVOSCloud.initialize(this, "wybIXxJVpBIvKSt8lJy6skW0-gzGzoHsz", "5qiVCQj5ctzx0s3WAA9VWBIe");
    }

    public static App getInstance(){
        return myApplication;
    }
}
