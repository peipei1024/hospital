package com.js.oldmarket.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.bean.Good;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.http.GoodRequest;
import com.js.oldmarket.http.HttpType;
import com.js.oldmarket.ui.home.HomeGoodAdapter;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.ToastUtils;
import com.js.oldmarket.view.GridViewForScrollView;
import com.js.oldmarket.view.SwipeRefresh;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/*
* Class name :AssortmentActivity
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-13.
*
*/
public class AssortmentActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_type_image)
    ImageView idTypeImage;
    @Bind(R.id.id_type_text)
    TextView idTypeText;
    @Bind(R.id.id_girdview)
    GridViewForScrollView idGirdview;
    @Bind(R.id.id_more_text)
    TextView idMoreText;
    @Bind(R.id.id_swiperefresh)
    SwipeRefresh idSwiperefresh;

    private String type = null;
    private int type_res;

    private int page = 1;
    private int pageSize = 10;

    private ArrayList<Good> list_good = new ArrayList<>();
    private HomeGoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assortment);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("type");
        type = bundle.getString("type_str");
        type_res = bundle.getInt("type_res");
        LogUtils.i("AssortmentActivity", type);
        ActivityCollector.addActivity(this);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();

    }

    private void init() {
        idTypeText.setText(type);
        idTypeImage.setImageDrawable(getResources().getDrawable(type_res));
        adapter = new HomeGoodAdapter(this, list_good);
        idGirdview.setAdapter(adapter);
        idSwiperefresh.setOnRefreshListener(this);

        GoodRequest.getGoods(this, type, page, pageSize);
        idSwiperefresh.setRefreshing(true);
    }

    @OnClick({R.id.id_back_arrow_image, R.id.id_more_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_arrow_image:
                ActivityCollector.removeActivity(this);
                break;
            case R.id.id_more_text:
                GoodRequest.getGoods(this, type, page, pageSize);
                idMoreText.setText("加载中...");
                break;
        }
    }

    public void onEventMainThread(HttpSuccessEvent event) {
        if (event.getType() == HttpType.TYPE_GetGoods) {
            LogUtils.i("AssortmentActivity:HttpSuccessEvent", event.getContent());
            if (event.getContent().equals("[]")) {
                ToastUtils.toast(this, "没有数据了");
                idMoreText.setVisibility(View.GONE);
            }
            Gson gson = new Gson();
            ArrayList<Good> l = gson.fromJson(event.getContent(), new TypeToken<ArrayList<Good>>() {
            }.getType());
            for (int a = 0; a < l.size(); a++) {
                list_good.add(l.get(a));
            }
            adapter.myNotifyDataSetChanged(list_good);
            idSwiperefresh.setRefreshing(false);
            idMoreText.setText("加载更多");
            page++;
        }
    }

    public void onEventMainThread(HttpFailEvent event) {
        if (event.getType() == HttpType.TYPE_GetGoods) {
            ToastUtils.toast(this, "获取数据失败");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRefresh() {
        list_good.clear();
        page = 1;
        idSwiperefresh.setRefreshing(true);
        GoodRequest.getGoods(this, type, page, pageSize);
    }
}
