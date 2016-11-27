package com.js.oldmarket.opera;

import com.avos.avoscloud.AVInstallation;

/*
* Class name :PushOpera
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
public class PushOpera {
    public static String getCurrentIMEI(){
        return AVInstallation.getCurrentInstallation().getInstallationId();
    }
}
