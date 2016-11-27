package com.js.oldmarket.ui;/*
* Class name :BaseActivity
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-11.
*
*/

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.bean.Good;
import com.js.oldmarket.bean.Vsersion;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.fragment.ActiveFragment;
import com.js.oldmarket.http.AppRequest;
import com.js.oldmarket.http.HttpType;
import com.js.oldmarket.http.TokenUtils;
import com.js.oldmarket.ui.home.GoodAdapter;
import com.js.oldmarket.ui.home.HomeFragment;
import com.js.oldmarket.fragment.MessageFragment;
import com.js.oldmarket.fragment.MyFragment;
import com.js.oldmarket.utils.AppUtils;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.OKHttpUtils;
import com.js.oldmarket.utils.ToastUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class BaseActivity extends FragmentActivity implements View.OnClickListener{
    private LinearLayout view_home;
    private LinearLayout view_my;
    private TextView text_home;
    private TextView text_my;
    private ImageView image_home;
    private ImageView image_my;

    private FragmentManager fragmentManager;
    HomeFragment homeFragment;
    MyFragment myFragment;

    private int[] res_unselect = {R.drawable.icon_home_unselect,  R.drawable.icon_my_unselect};
    private int[] res_select = {R.drawable.icon_home_select,  R.drawable.icon_my_select};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        //PushService.setDefaultPushCallback(this, BaseActivity.class);
        EventBus.getDefault().register(this);
        init();
        AppRequest.version();
        //处理“内存重启”
        if (savedInstanceState != null) {
            fragmentManager = getSupportFragmentManager();
            List<Fragment> fragmentList = fragmentManager.getFragments();
            for (Fragment fragment : fragmentList) {
                if (fragment instanceof HomeFragment) {
                    homeFragment = (HomeFragment) fragment;
                } else if (fragment instanceof MyFragment) {
                    myFragment = (MyFragment) fragment;
                }

            }
            // 解决重叠问题
            fragmentManager.beginTransaction().show(homeFragment).hide(myFragment).commit();
        }else{
            // 正常时
            homeFragment = new HomeFragment();
            myFragment = new MyFragment();

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.id_fragment, homeFragment, homeFragment.getClass().getName())
                    .add(R.id.id_fragment, myFragment, myFragment.getClass().getName()).
                    show(homeFragment).hide(myFragment).commit();
        }


    }
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");

    public void onEventMainThread(HttpSuccessEvent event) {
        if (event.getType() == HttpType.TYPE_GetLastAPP) {
            LogUtils.i("个人信息", event.getContent());
            Gson gson = new Gson();
            Vsersion vsersion = gson.fromJson(event.getContent(), Vsersion.class);
            if (Float.valueOf(AppUtils.getVersionName(this)) < Float.valueOf(vsersion.getVersionName())) {
                LogUtils.i("ds", Float.valueOf(AppUtils.getVersionName(this)) + " " + Float.valueOf(vsersion.getVersionName()));
                ToastUtils.toast(this, "有新版本，快去官网下载吧");
            } else {
                ToastUtils.toast(this, "已是最新版本");
            }
        }
    }

    public void onEventMainThread(HttpFailEvent event) {
        if (event.getType() == HttpType.TYPE_GetLastAPP) {
            ToastUtils.toast(this, "检查更新失败");
        }
    }
    private void init(){
        view_home = (LinearLayout) findViewById(R.id.id_home);
        view_my = (LinearLayout) findViewById(R.id.id_my);

        text_home = (TextView) findViewById(R.id.id_home_text);
        text_my = (TextView) findViewById(R.id.id_my_text);

        image_home = (ImageView) findViewById(R.id.id_home_image);
        image_my = (ImageView) findViewById(R.id.id_my_image);

        view_home.setOnClickListener(this);
        view_my.setOnClickListener(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        init4Button();
        FragmentTransaction f = fragmentManager.beginTransaction();
        f.commit();
        switch (v.getId()){
            case R.id.id_home:
                f.show(homeFragment).hide(myFragment);
                text_home.setTextColor(getResources().getColor(R.color.colorMain));
                image_home.setImageResource(res_select[0]);
                break;
            case R.id.id_my:
                f.hide(homeFragment).show(myFragment);
                text_my.setTextColor(getResources().getColor(R.color.colorMain));
                image_my.setImageResource(res_select[1]);
                break;
        }
    }

    /**
     * 初始化四个选项卡,进入没有选中状态
     */
    private void init4Button(){
        text_home.setTextColor(getResources().getColor(R.color.colorTextMain));
        text_my.setTextColor(getResources().getColor(R.color.colorTextMain));

        image_home.setImageResource(res_unselect[0]);
        image_my.setImageResource(res_unselect[1]);
    }

    private static long firstTime;
    /**
     * 连续按两次返回键就退出
     */
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if (firstTime + 2000 > System.currentTimeMillis()) {
            ActivityCollector.finishAll();
            super.onBackPressed();
        } else {
            ToastUtils.toast(this, "再按一次退出程序");
        }
        firstTime = System.currentTimeMillis();
    }
}
