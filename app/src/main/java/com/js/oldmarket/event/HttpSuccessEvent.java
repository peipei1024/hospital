package com.js.oldmarket.event;

import com.avos.avoscloud.AVObject;
import com.js.oldmarket.bean.Good;

import java.util.List;

/*
* Class name :HttpSuccessEvent
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
public class HttpSuccessEvent{
    private int type;
    private String content;
    private List<Good> list;
    public HttpSuccessEvent(int type, String content){
        this.type = type;
        this.content = content;
    }
//    public HttpSuccessEvent(int type, List<Good> list){
//        this.type = type;
//        this.list = list;
//    }
    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public List<Good> getList() {
        return list;
    }
}
