package com.js.oldmarket.ui.leavewords;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.bean.Good;
import com.js.oldmarket.bean.LeaveWordBean;
import com.js.oldmarket.bean.User;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.http.GoodRequest;
import com.js.oldmarket.http.HttpType;
import com.js.oldmarket.http.WordRequest;
import com.js.oldmarket.opera.PushOpera;
import com.js.oldmarket.opera.UserOpera;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.OKHttpUtils;
import com.js.oldmarket.utils.ToastUtils;
import com.js.oldmarket.view.PullUpMoreListView;
import com.js.oldmarket.view.SwipeRefresh;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/*
* Class name :LeaveWordsActivity
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-22.
*
*/
public class LeaveWordsActivity extends Activity implements PullUpMoreListView.ILoadListener,SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_word_edit)
    EditText idWordEdit;
    @Bind(R.id.id_send_button)
    Button idSendButton;
    @Bind(R.id.id_price_edit)
    EditText idPriceEdit;
    @Bind(R.id.id_price_view)
    LinearLayout idPriceView;
    @Bind(R.id.id_listview)
    PullUpMoreListView idListview;
    @Bind(R.id.id_swiperefresh)
    SwipeRefresh idSwiperefresh;


    private String goodid;
    private int page = 1;
    private int pageSize = 10;

    private final String TAG = this.getClass().getName();

    private LeaveWordsAdapter adapter;

    private ArrayList<LeaveWordBean> list_word = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_words);
        Intent intent = getIntent();
        goodid = intent.getStringExtra("goodid");
        LogUtils.i(TAG, goodid);
        ActivityCollector.addActivity(this);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
    }
    private void init(){
        WordRequest.getWord(this, goodid, page, pageSize);
        adapter = new LeaveWordsAdapter(this, list_word);
        idListview.setAdapter(adapter);
        idListview.setLoadListener(this);
        idSwiperefresh.setRefreshing(true);
        idSwiperefresh.setOnRefreshListener(this);
    }






    public void onEventMainThread(HttpSuccessEvent event) {
        if (event.getType() == HttpType.TYPE_PublishGoodRemark) {
            LogUtils.i("LeaveWordsActivity:HttpSuccessEvent", event.getContent());
            dialog.dismiss();
            idPriceEdit.setText("");
            idWordEdit.setText("");
            ToastUtils.toast(this, "留言成功");
        }else if (event.getType() == HttpType.TYPE_PublishGoodRemark_get){
            idListview.loadComplete();
            idSwiperefresh.setRefreshing(false);
            LogUtils.i("LeaveWordsActivity:HttpSuccessEvent", event.getContent());
            if (event.getContent().equals("[]")){
                ToastUtils.toast(this, "没有数据了");
            }
            Gson gson = new Gson();
            ArrayList<LeaveWordBean> l = gson.fromJson(event.getContent(), new TypeToken<ArrayList<LeaveWordBean>>() {
            }.getType());
            for (int a= 0; a < l.size(); a++){
                list_word.add(l.get(a));
            }
            adapter.myNotifyDataSetChanged(list_word);
            page++;
        }
    }

    public void onEventMainThread(HttpFailEvent event) {
        if (event.getType() == HttpType.TYPE_PublishGoodRemark) {
            ToastUtils.toast(this, "留言失败");
            dialog.dismiss();
        }else if (event.getType() == HttpType.TYPE_PublishGoodRemark_get){
            ToastUtils.toast(this, "获取数据失败");
        }
    }
    ProgressDialog dialog;
    @OnClick({R.id.id_back_arrow_image, R.id.id_send_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_arrow_image:
                ActivityCollector.removeActivity(this);
                break;
            case R.id.id_send_button:
                String word = idWordEdit.getText().toString().trim();
                String price = idPriceEdit.getText().toString().trim();
                if (!TextUtils.isEmpty(word)){
                    if (UserOpera.isLogin(this)){
                        dialog = new ProgressDialog(this);
                        dialog.setMessage("正在发表");
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        String phone = UserOpera.getCurrentUser(this).getMobilePhone();
                        WordRequest.sendWord(this, goodid, word, price, phone, PushOpera.getCurrentIMEI());
                    }else {
                        ToastUtils.toast(this, "请先登录");
                    }
                }else {
                    ToastUtils.toast(this, "请填写留言信息");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLoad() {
        WordRequest.getWord(this, goodid, page, pageSize);

    }

    @Override
    public void onRefresh() {
        list_word.clear();
        page = 1;
        idSwiperefresh.setRefreshing(true);
        WordRequest.getWord(this, goodid, page, pageSize);
    }
}
