package com.js.oldmarket.ui.konwledge;

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
import com.js.oldmarket.ui.WebviewActivity;
import com.js.oldmarket.utils.IntentUtils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentHolder extends RecyclerView.ViewHolder {


    @Bind(R.id.id_pic_iv)
    ImageView idPicIv;
    @Bind(R.id.id_title_tv)
    TextView idTitleTv;
    @Bind(R.id.id_content_tv)
    TextView idContentTv;
    @Bind(R.id.id_know_v)
    LinearLayout idKnowV;

    private Context context;
    private KonwLedge konwLedge;

    public CommentHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.holder_knowledge, parent, false));
    }

    public CommentHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bindData(KonwLedge commentBean, Context context) {
        this.context = context;
        this.konwLedge = commentBean;
        if (!TextUtils.isEmpty(commentBean.getUrl())) {
            Picasso.with(context).load(commentBean.getUrl()).placeholder(R.drawable.default_pic_load).error(R.drawable.default_pic_error).into(idPicIv);
        } else {
            Picasso.with(context).load(R.drawable.default_pic_load).into(idPicIv);
        }
        if (!TextUtils.isEmpty(commentBean.getTitle())) {
            if (commentBean.getTitle().length() > 12){
                idTitleTv.setText(commentBean.getTitle().substring(0, 12));
            }else {
                idTitleTv.setText(commentBean.getTitle());
            }
        } else {
            idTitleTv.setText("");
        }
        if (!TextUtils.isEmpty(commentBean.getConten())) {
            if (commentBean.getConten().length() > 40){
                idContentTv.setText(commentBean.getConten().substring(0, 40));
            }else {
                idContentTv.setText(commentBean.getConten());
            }
        } else {
            idContentTv.setText("");
        }
    }


    @OnClick(R.id.id_know_v)
    public void onClick() {
        Bundle bundle = new Bundle();
        bundle.putString("title", "健康小知识");
        bundle.putString("url", konwLedge.getScoure());
        IntentUtils.doIntentWithBundle(context, WebviewActivity.class, "urlbundle", bundle);
    }
}
