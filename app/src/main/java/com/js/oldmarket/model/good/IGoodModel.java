package com.js.oldmarket.model.good;

import com.js.oldmarket.model.leavewords.LeaveWordsModel;

/*
* Class name :IGoodModel
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-21.
*
*/
public interface IGoodModel {
    void getGood(GoodModel.OnLoadGoodListener listener, String goodId);
    void getUser(GoodModel.OnLoadUserListener listener);
    void getNewLeaveWord(GoodModel.OnLoadLeaveWordListener listener);
}
