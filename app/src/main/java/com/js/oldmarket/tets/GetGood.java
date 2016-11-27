package com.js.oldmarket.tets;

import com.js.oldmarket.bean.Good;
import com.js.oldmarket.bean.LeaveWordBean;
import com.js.oldmarket.bean.User;

import java.util.ArrayList;
import java.util.List;

/*
* Class name :GetGood
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
public class GetGood {
    public static Good getGoodDetails(){
       Good good = new Good();
//        good.setId("1");
//        good.setName("iPhone SE 16GB");
//        good.setPrice("￥1200");
//        good.setOldPrice("￥3200");
//        ArrayList<String> urls = new ArrayList<>();
//        urls.add("http://down.laifudao.com/images/tupian/20151111105134.jpg");
//        urls.add("http://down.laifudao.com/images/tupian/2016118141346.jpg");
//        good.setPicUrls(urls);
//        good.setCondition("九成新");
//        good.setPlace("12教学楼前边");
//        good.setTime("16-4-18 17：00");
//        good.setDetails(" 三月份购买，无划痕，因屏幕太小出售。美版插卡直接使用，二手物品，售出概不退换");
//
//        LeaveWordBean lb = new LeaveWordBean();
//        lb.setContent("不能便宜了1");
//        lb.setId("1");
//        lb.setTime("16-4-20 18:00");
//        lb.setOffer("500");
//        User user1 = new User();
//        user1.setHeadPic("http://down.laifudao.com/images/tupian/2016118141346.jpg");
//        user1.setName("可乐大爷");
//        user1.setPhone("13283619575");
//        lb.setUser(user1);
//        good.setLeaveWord(lb);
//
//
//        User user = new User();
//        user.setId("1");
//        user.setName("光着脚的青春");
//        user.setPhone("13283619575");
//        user.setHeadPic("http://down.laifudao.com/images/tupian/2016118141346.jpg");
//        user.setSchoolNumberVerfied(true);
//        user.setInstallationId("Dsds");
//        good.setUser(user);
        return good;
    }

    public static List<LeaveWordBean> getLeaveWords2(){
        List<LeaveWordBean> list = new ArrayList<>();
        User user1 = new User();
        //user1.setHeadPic("http://down.laifudao.com/images/tupian/2016118141346.jpg");
        user1.setName("可乐大爷");
        //user1.setPhone("13283619575");
        LeaveWordBean lb = new LeaveWordBean();


        LeaveWordBean lb1 = new LeaveWordBean();


        return list;
    }

    public static boolean saveUser(User user){
        boolean isSucc = false;
        isSucc = true;
        return isSucc;
    }

    public static User getCurrentUser(){
        User user = new User();
        //user.setId("1");
        //user.setName("光着脚丫的青村");
        //user.setSchoolNumberVerfied(true);
        //user.setPhone("13283629575");
        //user.setPassword("123456");
       // user.setHeadPic("http://down.laifudao.com/images/tupian/2016118141346.jpg");
       // user.setInstallationId("dsidjsodjs");
        return user;
    }














    public static List<String> getBannerImageUrl(){
        List<String> list = new ArrayList<>();
        list.add("http://down.laifudao.com/images/tupian/20151111105134.jpg");
        list.add("http://down.laifudao.com/images/tupian/20151111105134.jpg");
        return list;
    }

    public static List<LeaveWordBean> getLeaveWords(){
        List<LeaveWordBean> list = new ArrayList<>();
        LeaveWordBean lb = new LeaveWordBean();

        list.add(lb);

//        LeaveWordBean lb1 = new LeaveWordBean();
//        lb1.setContent("不能便宜了2");
//        lb1.setId("1");
//        lb1.setTime("4-20 18:00");
//        lb1.setOffer("500");
//        list.add(lb1);
//
//        LeaveWordBean lb2 = new LeaveWordBean();
//        lb2.setContent("不能便宜了3");
//        lb2.setId("1");
//        lb2.setTime("4-20 18:00");
//        list.add(lb2);
        return list;
    }

    public static User getUserDetails(){
        User user = new User();
        //user.setId("1");
       // user.setName("光着脚的青春");
        //user.setPhone("13283619575");
        //user.setHeadPic("http://down.laifudao.com/images/tupian/2016118141346.jpg");
        //user.setSchoolNumberVerfied(true);
        //user.setInstallationId("Dsds");
        return user;
    }
    public static List<Good> get(){
        List<Good> list = new ArrayList<>();
//        Good good = new Good();
//        good.setId("1");
//        ArrayList<String> urls = new ArrayList<>();
//        urls.add("http://down.laifudao.com/images/tupian/20151111105134.jpg");
//        good.setPicUrls(urls);
//        good.setName("iPhone SE 16GB");
//        good.setPrice("￥2000");
//        good.setOldPrice("￥3200");
//        list.add(good);
//
//        Good good1 = new Good();
//        good1.setId("1");
//        good1.setName("iPhone 5s 16GB");
//        good1.setPicUrls(urls);
//        good1.setPrice("￥2000");
//        good1.setOldPrice("￥3200");
//        list.add(good1);
//
//        Good good2 = new Good();
//        good2.setId("1");
//        good2.setName("iPhone 6s 16GB");
//        good2.setPrice("￥2000");
//        good2.setPicUrls(urls);
//        good2.setOldPrice("￥3200");
//        list.add(good2);
//        list.add(good);
//        list.add(good);
//        list.add(good);
//        list.add(good);
        return list;
    }
}
