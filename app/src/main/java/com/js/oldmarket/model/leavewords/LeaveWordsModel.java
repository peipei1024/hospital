package com.js.oldmarket.model.leavewords;

import com.js.oldmarket.bean.LeaveWordBean;
import com.js.oldmarket.tets.GetGood;

import java.util.ArrayList;
import java.util.List;

/*
* Class name :LeaveWordsModel
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
public class LeaveWordsModel implements ILeaveWordsModel{
    private OnLoadLeaveWordListListener listener;
    @Override
    public void getLeaveWordsList(OnLoadLeaveWordListListener listListener, int dataPage) {
        this.listener = listListener;
        List<LeaveWordBean> list = new ArrayList<>();
        try {
            new Thread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list = GetGood.getLeaveWords();
        if (list.size() > 0){
            listListener.onSuccess(list);
        }else {
            listListener.onFailed("失败");
        }

    }
    public interface OnLoadLeaveWordListListener{
        void onSuccess(List<LeaveWordBean> l);
        void onFailed(String msg);
    }
}
