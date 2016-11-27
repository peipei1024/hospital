package com.js.oldmarket.ui.answer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.js.oldmarket.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
* Class name :AnswerHolder
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
public class AnswerHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.comment_iv)
    ImageView commentIv;
    @Bind(R.id.comment_user_name)
    TextView commentUserName;
    //    @Bind(R.id.comment_month)
//    TextView commentMonth;
    @Bind(R.id.comment_time)
    TextView commentTime;
    @Bind(R.id.comment_content)
    TextView commentContent;

    public AnswerHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.holder_answer, parent, false));
    }

    public AnswerHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bindData(Answer answer, Context context){
//        if (!TextUtils.isEmpty(commentBean.getAvatar())){
//            StringBuffer sb = new StringBuffer("http://7xstnm.com2.z0.glb.clouddn.com/");
//            sb.append(commentBean.getAvatar());
//            String r = sb.toString();
//            Picasso.with(context).load(r).placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar).into(commentIv);
//        }else {
//            Picasso.with(context).load(R.drawable.default_avatar).into(commentIv);
//        }
        if (!TextUtils.isEmpty(answer.getName())){
            commentUserName.setText(answer.getName());
        }else {
            commentUserName.setText("");
        }
        if (!TextUtils.isEmpty(answer.getTime())){
            commentTime.setText("");
        }else {
            commentTime.setText("");
        }
        if (!TextUtils.isEmpty(answer.getContent())){
            commentContent.setText(answer.getContent());
        }else {
            commentContent.setText("");
        }

    }

    public ImageView getCommentIv() {
        return commentIv;
    }

    public TextView getCommentContent() {
        return commentContent;
    }

    public TextView getCommentTime() {
        return commentTime;
    }

    public TextView getCommentUserName() {
        return commentUserName;
    }

}

