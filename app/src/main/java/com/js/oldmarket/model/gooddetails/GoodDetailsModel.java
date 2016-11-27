package com.js.oldmarket.model.gooddetails;

import com.js.oldmarket.bean.Good;
import com.js.oldmarket.bean.User;
import com.js.oldmarket.tets.GetGood;

import java.util.List;

/*
* Class name :GoodDetailsModel
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
public class GoodDetailsModel implements IGoodDetailsModel{
    private OnLoadGoodDetailsListener listener;
    Get get = new Get(listener);
    public GoodDetailsModel(OnLoadGoodDetailsListener l){
        this.listener = l;
    }
    @Override
    public void startGetDoodDetails() {
        get = new Get(listener);
        get.start();
    }

    @Override
    public void stopGetDoodDetails() {
        get.interrupt();
    }



    class Get extends Thread{
        private GoodDetailsModel.OnLoadGoodDetailsListener listener;
        public Get(GoodDetailsModel.OnLoadGoodDetailsListener listener){
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
            Good good = GetGood.getGoodDetails();
            if (good != null){
                listener.onSuccess(good);
            }else {
                listener.onFailure("获取内容失败");
            }
        }
    }

    public interface OnLoadGoodDetailsListener {
        void onSuccess(Good good);
        void onFailure(String msg);
    }
}
