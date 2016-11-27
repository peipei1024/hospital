package com.js.oldmarket.presenter;

import android.content.Context;
import android.os.Handler;

import com.js.oldmarket.bean.Good;
import com.js.oldmarket.model.home.HomeModel;
import com.js.oldmarket.ui.home.IHomeView;

import java.util.List;

/*
* Class name :HomePresenter
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
public class HomePresenter implements HomeModel.OnLoadGoodListListener{
    private HomeModel homeModel;
    private IHomeView iHomeView;
    private Context context;
    private Handler handler = new Handler();
    private List<Good> list;
    public HomePresenter(Context context, IHomeView iHomeView){
        this.context = context;
        this.iHomeView = iHomeView;
        homeModel = new HomeModel(this);
    }
    public void startLoadData(){
        iHomeView.showPullDownRefresh();
        homeModel.getGoodData();
    }
    public void stopLoadData(){
        homeModel.stopGoodData();
    }
    public void openGoodActivity(int position){
        //iHomeView.toGoodActivity(list.get(position).getId());
    }
    @Override
    public void onSuccess(final List<Good> l) {
        list = l;
        handler.post(new Runnable() {
            @Override
            public void run() {
                iHomeView.getDataSuccessRefresh(list);
                iHomeView.hidePullDownRefresh();
            }
        });
    }

    @Override
    public void onFailure(final String msg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                iHomeView.toastMsg(msg);
                iHomeView.hidePullDownRefresh();
            }
        });
    }
}
