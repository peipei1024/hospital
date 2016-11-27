package com.js.oldmarket.bean;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;

/*
* Class name :LeaveWordBean
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-20.
*
*/
public class LeaveWordBean {
    private String RemarkID;
    private String Nickname;
    private String RemarkTime;
    private String Remarks;
    private String Bid;
    private String Contact;

    public String getRemarkID() {
        return RemarkID;
    }

    public void setRemarkID(String remarkID) {
        RemarkID = remarkID;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getRemarkTime() {
        return RemarkTime;
    }

    public void setRemarkTime(String remarkTime) {
        RemarkTime = remarkTime;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getBid() {
        return Bid;
    }

    public void setBid(String bid) {
        Bid = bid;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
