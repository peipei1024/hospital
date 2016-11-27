package com.js.oldmarket.ui.answer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.SaveCallback;
import com.js.oldmarket.R;
import com.js.oldmarket.ui.communice.Question;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* Class name :AnswerActivity
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-11-27.
*
*/
public class AnswerActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_recy)
    RecyclerView idRecy;
    @Bind(R.id.id_swip)
    SwipeRefreshLayout idSwip;
    @Bind(R.id.id_word_edit)
    EditText idWordEdit;
    @Bind(R.id.id_send_button)
    Button idSendButton;
    String id;
    String name;
    String content;
    private Question question = new Question();
    AVObject av_question;

    private AnswerAdapter adapter;
    private List<TComment> mList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("urlbundle");
        id = bundle.getString("id");
        name = bundle.getString("name");
        content = bundle.getString("content");
        setContentView(R.layout.activity_answer);
        ButterKnife.bind(this);
        getData();
        adapter = new AnswerAdapter(this, mList);
        layoutManager = new LinearLayoutManager(this);
        idRecy.setLayoutManager(layoutManager);
        idRecy.setAdapter(adapter);
        idSwip.setOnRefreshListener(this);
    }

    @OnClick({R.id.id_back_arrow_image, R.id.id_send_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_arrow_image:
                finish();
                break;
            case R.id.id_send_button:
                String acontent = idWordEdit.getText().toString().trim();
                if (!TextUtils.isEmpty(acontent)){

                    final AVObject object = new AVObject("answer");
                    object.put("user", AVUser.getCurrentUser());
                    object.put("content", acontent);
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null){
                                AVRelation<AVObject> relation = av_question.getRelation("ans");// 新建一个 AVRelation
                                relation.add(object);
                                av_question.saveInBackground();
                                ToastUtils.toast(AnswerActivity.this, "发表成功");
                                idWordEdit.setText("");
                                onRefresh();
                            }else {
                                ToastUtils.toast(AnswerActivity.this, "发表失败，检查网络");
                            }
                        }
                    });
                }
                break;
        }
    }

    private void getData(){
        AVQuery<AVObject> query11 = new AVQuery<>("Question");
        query11.include("user");
        query11.getInBackground(id, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (e == null){
                    av_question = avObject;

                    String id = avObject.getObjectId();
                    String content = avObject.getString("content");
                    List<String> piclist = avObject.getList("pic");
                    AVUser user = avObject.getAVObject("user");
                    String name = user.getUsername();
                    question = new Question();
                    question.setName(name);
                    question.setId(id);
                    question.setPiclist(piclist);
                    question.setContent(content);
                    TComment qcomment = new TComment();
                    qcomment.setType(1);
                    qcomment.setQuestion(question);
                    mList.add(qcomment);

                    AVRelation<AVObject> relation = av_question.getRelation("ans");
                    AVQuery<AVObject> query = relation.getQuery();
                    query.include("user");
                    query.findInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            if (e == null){
                                TComment tc;
                                Answer answer;
                                for (int a = 0; a<list.size(); a++){
                                    tc = new TComment();
                                    answer = new Answer();
                                    AVObject object = list.get(a);
                                    String content = object.getString("content");
                                    String time = String.valueOf(object.getCreatedAt());
                                    AVUser user = object.getAVObject("user");
                                    String name = user.getUsername();
                                    answer.setName(name);
                                    answer.setContent(content);
                                    answer.setTime(time);
                                    tc.setType(2);
                                    tc.setAnswer(answer);
                                    mList.add(tc);
                                }
                                adapter.refresh(mList);
                                idSwip.setRefreshing(false);
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onRefresh() {
        mList.clear();
        adapter.refresh(mList);
        getData();
    }
}
