package com.js.oldmarket.presenter;

import android.content.Context;
import android.os.Handler;

import com.js.oldmarket.bean.LeaveWordBean;
import com.js.oldmarket.model.leavewords.LeaveWordsModel;
import com.js.oldmarket.ui.leavewords.ILeaveWordsView;
import com.js.oldmarket.ui.leavewords.LeaveWordsFragment;

import java.util.List;

/*
* Class name :LeaveWordsPresenter
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
public class LeaveWordsPresenter implements LeaveWordsModel.OnLoadLeaveWordListListener{
    private LeaveWordsModel model;
    private Context context;
    private ILeaveWordsView iView;
    private Handler handler = new Handler();
    public LeaveWordsPresenter(Context context, ILeaveWordsView iLeaveWordsView){
        model = new LeaveWordsModel();
        this.context = context;
        this.iView = iLeaveWordsView;
    }

    public void getLeaveWordsData(int i){
        if (i == 1){
            //iView.showPullDownRefresh();
            model.getLeaveWordsList(this, i);
        }else {
            model.getLeaveWordsList(this, i);
        }
    }
    @Override
    public void onSuccess(final List<LeaveWordBean> l) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                iView.refreshAdapter(l);
            }
        });
    }

    @Override
    public void onFailed(final String msg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                iView.toastMsg(msg);
                iView.hidePullDpwnRefresh();
            }
        });
    }
}
