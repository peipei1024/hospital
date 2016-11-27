package com.js.oldmarket.ui.communice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.js.oldmarket.R;
import com.js.oldmarket.ui.answer.AnswerActivity;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.ToastUtils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* Class name :CommuniceHolder
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
public class CommuniceHolder extends RecyclerView.ViewHolder {


    @Bind(R.id.id_name_tv)
    TextView idNameTv;
    @Bind(R.id.id_content_tv)
    TextView idContentTv;
    @Bind(R.id.btnComments)
    ImageView btnComments;
    @Bind(R.id.btnMore)
    ImageView btnMore;
    @Bind(R.id.id_communice_view)
    LinearLayout idCommuniceView;
    @Bind(R.id.id_1)
    ImageView id1;
    @Bind(R.id.id_2)
    ImageView id2;
    @Bind(R.id.id_3)
    ImageView id3;

    private Context context;
    private Question question;

    public CommuniceHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.holder_communice, parent, false));
    }

    public CommuniceHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bindData(final Question question, Context context) {
        this.context = context;
        this.question = question;
        if (question.getPiclist().size() == 1) {
            Picasso.with(context).load(question.getPiclist().get(0)).placeholder(R.drawable.default_pic_load).error(R.drawable.default_pic_error).into(id1);
        }
        if (question.getPiclist().size() == 2) {
            Picasso.with(context).load(question.getPiclist().get(0)).placeholder(R.drawable.default_pic_load).error(R.drawable.default_pic_error).into(id1);
            Picasso.with(context).load(question.getPiclist().get(1)).placeholder(R.drawable.default_pic_load).error(R.drawable.default_pic_error).into(id2);
        }
        if (question.getPiclist().size() == 3) {
            Picasso.with(context).load(question.getPiclist().get(0)).placeholder(R.drawable.default_pic_load).error(R.drawable.default_pic_error).into(id1);
            Picasso.with(context).load(question.getPiclist().get(1)).placeholder(R.drawable.default_pic_load).error(R.drawable.default_pic_error).into(id2);
            Picasso.with(context).load(question.getPiclist().get(2)).placeholder(R.drawable.default_pic_load).error(R.drawable.default_pic_error).into(id3);
        }

//        if (!TextUtils.isEmpty(commentBean.getUrl())) {
//            Picasso.with(context).load(commentBean.getUrl()).placeholder(R.drawable.default_pic_load).error(R.drawable.default_pic_error).into(idPicIv);
//        } else {
//            Picasso.with(context).load(R.drawable.default_pic_load).into(idPicIv);
//        }
        if (!TextUtils.isEmpty(question.getName())) {
            idNameTv.setText(question.getName());
        } else {
            idNameTv.setText("");
        }
        if (!TextUtils.isEmpty(question.getContent())) {
            idContentTv.setText(question.getContent());
        } else {
            idContentTv.setText("");
        }
    }


    @OnClick({R.id.id_content_tv, R.id.btnComments, R.id.btnMore, R.id.id_communice_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_content_tv:
                Bundle bundle1 = new Bundle();
                bundle1.putString("id", question.getId());
                bundle1.putString("name", question.getName());
                bundle1.putString("content", question.getContent());
                IntentUtils.doIntentWithBundle(context, AnswerActivity.class, "urlbundle", bundle1);
                break;
            case R.id.btnComments:
                break;
            case R.id.btnMore:
                ToastUtils.toast(context, "此功能正在开发");
                break;
            case R.id.id_communice_view:
                Bundle bundle = new Bundle();
                bundle.putString("id", question.getId());
                bundle.putString("name", question.getName());
                bundle.putString("content", question.getContent());
                IntentUtils.doIntentWithBundle(context, AnswerActivity.class, "urlbundle", bundle);
                break;
        }
    }
}


