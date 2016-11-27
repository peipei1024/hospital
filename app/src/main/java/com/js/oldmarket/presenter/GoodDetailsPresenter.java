package com.js.oldmarket.presenter;

import android.os.Handler;

import com.js.oldmarket.bean.Good;
import com.js.oldmarket.bean.User;
import com.js.oldmarket.model.gooddetails.GoodDetailsModel;
import com.js.oldmarket.tets.GetGood;
import com.js.oldmarket.ui.gooddetails.IGoodDetailsView;

import java.net.PortUnreachableException;

/*
* Class name :GoodDetailsPresenter
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
public class GoodDetailsPresenter implements GoodDetailsModel.OnLoadGoodDetailsListener{
    private GoodDetailsModel goodDetailsModel;
    private IGoodDetailsView iGoodDetailsView;
    private Handler handler = new Handler();
    public GoodDetailsPresenter(IGoodDetailsView i){
        this.iGoodDetailsView = i;
        goodDetailsModel = new GoodDetailsModel(this);
    }

    public void startGetGood(){
        iGoodDetailsView.showSwipeRefresh();
        goodDetailsModel.startGetDoodDetails();
    }
    public void openChatActivity(){
        iGoodDetailsView.toChatActivity();
    }
    public void stopGetGood(){

    }
    @Override
    public void onSuccess(final Good good) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                iGoodDetailsView.showGoodDetails(good);
                iGoodDetailsView.hideSwipeRefresh();
            }
        });
    }

    @Override
    public void onFailure(String msg) {

    }
}
