package com.js.oldmarket.bean;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;

/*
* Class name :Good
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-16.
*
*/
public class Good {
    private String GoodID;//id
    private String Title;//商品名称
    private String OriginalPrice;//原价
    private String Price;//旧价
    private String Place;//地点
    private String Newness;//几层新
    private String PublishTime;
    private String TradeStateID;//分类
    private String Description;//物品详情
    private ArrayList<String> picUrls;

    private String Contact;
    private String Nickname;
    private String PictureUrl;//图片
    private String Picture2Url;//tup
    private String Picture3Url;//图片
    private String MobilePhone;
    private String IMEI;
    private String Labels;//类别
    private String RemarkCount;
    private String EndTradeTime;//最迟交易时间
    private String CloseTradeTime;
    private String Avatar;

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getGoodID() {
        return GoodID;
    }

    public void setGoodID(String goodID) {
        GoodID = goodID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        OriginalPrice = originalPrice;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getNewness() {
        return Newness;
    }

    public void setNewness(String newness) {
        Newness = newness;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }

    public String getTradeStateID() {
        return TradeStateID;
    }

    public void setTradeStateID(String tradeStateID) {
        TradeStateID = tradeStateID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public ArrayList<String> getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(ArrayList<String> picUrls) {
        this.picUrls = picUrls;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getPictureUrl() {
        return PictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        PictureUrl = pictureUrl;
    }

    public String getPicture2Url() {
        return Picture2Url;
    }

    public void setPicture2Url(String picture2Url) {
        Picture2Url = picture2Url;
    }

    public String getPicture3Url() {
        return Picture3Url;
    }

    public void setPicture3Url(String picture3Url) {
        Picture3Url = picture3Url;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getLabels() {
        return Labels;
    }

    public void setLabels(String labels) {
        Labels = labels;
    }

    public String getRemarkCount() {
        return RemarkCount;
    }

    public void setRemarkCount(String remarkCount) {
        RemarkCount = remarkCount;
    }

    public String getEndTradeTime() {
        return EndTradeTime;
    }

    public void setEndTradeTime(String endTradeTime) {
        EndTradeTime = endTradeTime;
    }

    public String getCloseTradeTime() {
        return CloseTradeTime;
    }

    public void setCloseTradeTime(String closeTradeTime) {
        CloseTradeTime = closeTradeTime;
    }
}
