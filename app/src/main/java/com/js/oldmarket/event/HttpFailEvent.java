package com.js.oldmarket.event;

/*
* Class name :HttpFailEvent
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
public class HttpFailEvent {
    private int type;
    private String content;
    public HttpFailEvent(int type, String content){
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
