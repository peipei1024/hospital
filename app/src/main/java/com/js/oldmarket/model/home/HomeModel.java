package com.js.oldmarket.model.home;


import android.util.Log;

import com.js.oldmarket.bean.Good;
import com.js.oldmarket.tets.GetGood;

import java.util.List;

/*
* Class name :HomeModel
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
public class HomeModel implements IHomeModel{
    private HomeModel.OnLoadGoodListListener listener;
    public  HomeModel(final HomeModel.OnLoadGoodListListener l){
        this.listener = l;
    }
    Get get = new Get(listener);
    @Override
    public void getGoodData() {
        get = new Get(listener);
        get.start();
    }

    @Override
    public void stopGoodData() {
        get.interrupt();
    }

    class Get extends Thread{
        private HomeModel.OnLoadGoodListListener listener;
        public Get(HomeModel.OnLoadGoodListListener listener){
            this.listener = listener;
        }
        @Override
        public void run() {
            super.run();
            try {
                this.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Good> list = GetGood.get();
            if (list.size() >0){
                listener.onSuccess(list);
            }else {
                listener.onFailure("load news detail info failure.");
            }
        }
    }
    public interface OnLoadGoodListListener {
        void onSuccess(List<Good> list);
        void onFailure(String msg);
    }
}
