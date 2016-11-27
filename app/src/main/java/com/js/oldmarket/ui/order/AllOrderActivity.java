package com.js.oldmarket.ui.order;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.bean.Good;
import com.js.oldmarket.bean.LeaveWordBean;
import com.js.oldmarket.event.GoodIDEvent;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.http.GoodRequest;
import com.js.oldmarket.http.HttpType;
import com.js.oldmarket.http.WordRequest;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.ToastUtils;
import com.js.oldmarket.view.PullUpMoreListView;
import com.js.oldmarket.view.SwipeRefresh;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/*
* Class name :AllOrderActivity
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-15.
*
*/
public class AllOrderActivity extends Activity implements PullUpMoreListView.ILoadListener, SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_listview)
    PullUpMoreListView idListview;
    @Bind(R.id.id_swiperefresh)
    SwipeRefresh idSwiperefresh;

    private OrderAdapter adapter;
    private ArrayList<Good> list_good = new ArrayList<>();
    private int page = 1;
    private int pageSize = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
        ActivityCollector.addActivity(this);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
    }
    private void init(){
        adapter = new OrderAdapter(this, list_good);
        idListview.setAdapter(adapter);
        idSwiperefresh.setRefreshing(true);
        idSwiperefresh.setOnRefreshListener(this);
        idListview.setLoadListener(this);
        GoodRequest.getMyGoods(this, page, pageSize);
    }

    public void onEventMainThread(HttpSuccessEvent event) {
        if (event.getType() == HttpType.TYPE_GetMyGoods){
            idListview.loadComplete();
            idSwiperefresh.setRefreshing(false);
            LogUtils.i("AllOrderActivity:HttpSuccessEvent", event.getContent());
            if (event.getContent().equals("[]")){
                ToastUtils.toast(this, "没有数据了");
            }
            Gson gson = new Gson();
            ArrayList<Good> l = gson.fromJson(event.getContent(), new TypeToken<ArrayList<Good>>() {
            }.getType());
            for (int a= 0; a < l.size(); a++){
                list_good.add(l.get(a));
            }
            adapter.myNotifyDataSetChanged(list_good);
            page++;
        }else if (event.getType() == HttpType.TYPE_UpdateGood){
            ToastUtils.toast(this, "确认交易成功");
            list_good.clear();
            page = 1;
            idSwiperefresh.setRefreshing(true);
            GoodRequest.getMyGoods(this, page, pageSize);
        }
    }
    public void onEventMainThread(GoodIDEvent event) {
//        IntentUtils.doIntentWithString(this, GoodActivity.class, "goodid", event.getGoodid());
    }
    public void onEventMainThread(HttpFailEvent event) {
        if (event.getType() == HttpType.TYPE_GetMyGoods){
            ToastUtils.toast(this, "获取数据失败");
        }else if (event.getType() == HttpType.TYPE_UpdateGood){
            ToastUtils.toast(this, "确认交易失败");
        }
    }
    @Override
    public void onLoad() {
        GoodRequest.getMyGoods(this, page, pageSize);
    }

    @Override
    public void onRefresh() {
        list_good.clear();
        page = 1;
        idSwiperefresh.setRefreshing(true);
        GoodRequest.getMyGoods(this, page, pageSize);
    }

    @OnClick(R.id.id_back_arrow_image)
    public void onClick() {
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
