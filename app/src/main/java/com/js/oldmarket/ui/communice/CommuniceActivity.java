package com.js.oldmarket.ui.communice;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.js.oldmarket.R;
import com.js.oldmarket.ui.publish.PublishActivity;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* Class name :CommuniceActivity
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
public class CommuniceActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_pub_btn)
    TextView idPubBtn;
    @Bind(R.id.id_recy)
    RecyclerView idRecy;
    @Bind(R.id.id_swip)
    SwipeRefreshLayout idSwip;


    private List<Question> mList = new ArrayList<>();
    private CommuniceAdapter adapter;
    private LinearLayoutManager layoutManager;
    private int skip = 0;
    private int lastVisibleItem;
    private int page = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communice);
        ButterKnife.bind(this);
        getData();
        adapter = new CommuniceAdapter(this, mList);
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
                    skip = page*10;
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

    @OnClick({R.id.id_back_arrow_image, R.id.id_pub_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_arrow_image:
                finish();
                break;
            case R.id.id_pub_btn:
                IntentUtils.doIntent(this, PublishActivity.class);
                break;
        }
    }
private void getData(){
    idSwip.setRefreshing(true);
    final AVQuery<AVObject> query = new AVQuery<>("Question");
    query.setLimit(10);
    query.setSkip(skip);
    query.include("user");
    query.findInBackground(new FindCallback<AVObject>() {
        @Override
        public void done(List<AVObject> list, AVException e) {
            if (e == null){
                Question question = null;
                for (int a = 0; a < list.size(); a++){
                    AVObject object = list.get(a);
                    String id = object.getObjectId();
                    String content = object.getString("content");
                    List<String> piclist = object.getList("pic");
                    AVUser user = object.getAVObject("user");
                    String name = user.getUsername();
                    question = new Question();
                    question.setName(name);
                    question.setId(id);
                    question.setPiclist(piclist);
                    question.setContent(content);
                    mList.add(question);
                }
                adapter.refresh(mList);
                idSwip.setRefreshing(false);
            }else {
                ToastUtils.toast(CommuniceActivity.this, "检查网络");
                idSwip.setRefreshing(false);
            }
        }
    });
}
    @Override
    public void onRefresh() {
        mList.clear();
        adapter.refresh(mList);
        skip = 0;
        getData();
    }
}
