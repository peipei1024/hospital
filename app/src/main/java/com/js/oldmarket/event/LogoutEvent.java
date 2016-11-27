package com.js.oldmarket.event;

/*
* Class name :LogoutEvent
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-5-24.
*
*/
public class LogoutEvent {
    private boolean logout;
    public LogoutEvent(boolean b){
        this.logout = b;
    }

    public boolean isLogout() {
        return logout;
    }
}
