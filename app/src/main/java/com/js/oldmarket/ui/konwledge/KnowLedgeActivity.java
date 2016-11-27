package com.js.oldmarket.ui.konwledge;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.js.oldmarket.R;
import com.js.oldmarket.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* Class name :KnowLedgeActivity
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-11-25.
*
*/
public class KnowLedgeActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_recy)
    RecyclerView idRecy;
    @Bind(R.id.id_swip)
    SwipeRefreshLayout idSwip;


    private List<KonwLedge> mList = new ArrayList<>();
    private CommentItemAdapter adapter;
    private int lastVisibleItem;
    private int skipnum = 0;
    private int page = 0;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);
        ButterKnife.bind(this);


        adapter = new CommentItemAdapter(this, mList);
        getData();
        layoutManager = new LinearLayoutManager(this);
        idRecy.setLayoutManager(layoutManager);
        idRecy.setAdapter(adapter);
        idRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    idSwip.setRefreshing(true);
                    page++;
                    skipnum = page*10;
                    getData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
        idSwip.setOnRefreshListener(this);
    }

    @OnClick(R.id.id_back_arrow_image)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {
        skipnum = 0;
        mList.clear();
        getData();
    }

    private void getData(){
        AVQuery<AVObject> query = new AVQuery<>("knowledge");
        query.setLimit(10);
        query.skip(skipnum);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null){
                    if (list.size() == 0){
                        ToastUtils.toast(KnowLedgeActivity.this, "没有数据了");
                    }
                    KonwLedge konwLedge = null;
                    for (int a = 0; a < list.size(); a++){
                        AVObject object = list.get(a);
                        String title = object.getString("title");
                        String content = object.getString("content");
                        AVFile file = object.getAVFile("picture");
                        String url = file.getUrl();
                        String scoure = object.getString("source_url");
                        konwLedge = new KonwLedge();
                        konwLedge.setTitle(title);
                        konwLedge.setConten(content);
                        konwLedge.setUrl(url);
                        konwLedge.setScoure(scoure);
                        mList.add(konwLedge);
                    }
                    adapter.refresh(mList);
                    idSwip.setRefreshing(false);
                }else {
                    ToastUtils.toast(KnowLedgeActivity.this, "查询数据失败");
                }
            }
        });
    }
}
