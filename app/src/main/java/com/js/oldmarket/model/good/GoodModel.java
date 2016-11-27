package com.js.oldmarket.model.good;

import com.js.oldmarket.bean.Good;
import com.js.oldmarket.bean.LeaveWordBean;
import com.js.oldmarket.bean.User;
import com.js.oldmarket.model.gooddetails.GoodDetailsModel;
import com.js.oldmarket.tets.GetGood;

import java.util.List;

/*
* Class name :GoodModel
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
public class GoodModel implements IGoodModel{
    @Override
    public void getGood(final OnLoadGoodListener listener, String goodId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Thread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Good g = GetGood.getGoodDetails();
                if (g != null){
                    listener.onSuccess(g);
                }else {
                    listener.onFailed("获取物品信息失败");
                }
            }
        }).start();
    }

    @Override
    public void getUser(final OnLoadUserListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Thread().sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                User user = GetGood.getUserDetails();
                if (user != null){
                    listener.onSuccess(user);
                }else {
                    listener.onFailed("获取数据失败");
                }
            }
        }).start();
    }

    @Override
    public void getNewLeaveWord(final OnLoadLeaveWordListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Thread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<LeaveWordBean> list = GetGood.getLeaveWords();
                if (list.size() > 0){
                    listener.onSuccess(list);
                }else {
                    listener.onFailed("获取数据失败");
                }
            }
        }).start();
    }

    public interface OnLoadGoodListener{
        void onSuccess(Good good);
        void onFailed(String msg);
    }
    public interface OnLoadUserListener{
        void onSuccess(User user);
        void onFailed(String msg);
    }
    public interface OnLoadLeaveWordListener{
        void onSuccess(List<LeaveWordBean> list);
        void onFailed(String msg);
    }
}
