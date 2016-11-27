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
import com.js.oldmarket.ui.WebviewActivity;
import com.js.oldmarket.ui.konwledge.CommentHolder;
import com.js.oldmarket.ui.konwledge.KonwLedge;
import com.js.oldmarket.utils.IntentUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* Class name :CommuniceAdapter
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
public class CommuniceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{



    private Context context;
    private List<Question> mList;

    public CommuniceAdapter(Context context, List<Question> commentBeen) {
        this.context = context;
        this.mList = commentBeen;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        holder = new CommuniceHolder(LayoutInflater.from(context), parent);
        return holder;
    }

    public void refresh(List<Question> comments){
        this.mList = comments;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CommuniceHolder) holder).bindData(mList.get(position), context);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
