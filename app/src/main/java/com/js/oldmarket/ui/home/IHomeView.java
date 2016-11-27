package com.js.oldmarket.ui.home;

import com.js.oldmarket.bean.Good;

import java.util.List;

/*
* Class name :IHomeView
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
public interface IHomeView {
    void showPullDownRefresh();
    void hidePullDownRefresh();
    void getDataSuccessRefresh(List<Good> list);
    void toastMsg(String msg);
    void toGoodActivity(String id);
}
