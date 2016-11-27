package com.js.oldmarket.event;

/*
* Class name :GoodIDEvent
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
public class GoodIDEvent {
    private String goodid;
    public GoodIDEvent(String str){
        this.goodid = str;
    }

    public String getGoodid() {
        return goodid;
    }
}
