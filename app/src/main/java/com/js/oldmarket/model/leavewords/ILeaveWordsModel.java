package com.js.oldmarket.model.leavewords;

/*
* Class name :ILeaveWordsModel
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
public interface ILeaveWordsModel {
    void getLeaveWordsList(LeaveWordsModel.OnLoadLeaveWordListListener l, int dataPage);
}
