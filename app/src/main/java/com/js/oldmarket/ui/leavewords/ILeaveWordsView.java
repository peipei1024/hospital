package com.js.oldmarket.ui.leavewords;

import com.js.oldmarket.bean.LeaveWordBean;

import java.util.List;

/*
* Class name :ILeaveWordsView
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-19.
*
*/
public interface ILeaveWordsView {
    void refreshAdapter(List<LeaveWordBean> list);
    void toActivity();
    void replyWithPhone();
    void replyWithSms();
    void reply();
    void showPullDownRefresh();
    void hidePullDpwnRefresh();
    void toastMsg(String msg);
}
