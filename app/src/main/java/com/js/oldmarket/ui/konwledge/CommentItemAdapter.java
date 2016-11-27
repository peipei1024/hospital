package com.js.oldmarket.ui.konwledge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by JiaM on 2016/4/19.
 */
public class CommentItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{



    private Context context;
    private List<KonwLedge> mList;

    public CommentItemAdapter(Context context, List<KonwLedge> commentBeen) {
        this.context = context;
        this.mList = commentBeen;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        holder = new CommentHolder(LayoutInflater.from(context), parent);
        return holder;
    }

    public void refresh(List<KonwLedge> comments){
        this.mList = comments;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CommentHolder) holder).bindData(mList.get(position), context);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
