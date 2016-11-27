package com.js.oldmarket.bean;

/*
* Class name :OrderBean
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-28.
*
*/
public class OrderBean {
    private String id;
    private Good good;
    private String money;
    /**交易状态*/
    private String state;
    private User buyUser;

    public User getBuyUser() {
        return buyUser;
    }

    public void setBuyUser(User buyUser) {
        this.buyUser = buyUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
