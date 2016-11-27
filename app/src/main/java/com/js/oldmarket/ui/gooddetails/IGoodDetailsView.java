package com.js.oldmarket.ui.gooddetails;

import com.js.oldmarket.bean.Good;
import com.js.oldmarket.bean.User;

/*
* Class name :IGoodDetailsView
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-17.
*
*/
public interface IGoodDetailsView {
    void showGoodDetails(Good good);
    void showUserDetails(User user);
    void toastMsg(String msg);
    void showSwipeRefresh();
    void hideSwipeRefresh();
    void toChatActivity();
}
