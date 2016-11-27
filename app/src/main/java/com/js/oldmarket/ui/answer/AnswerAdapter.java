package com.js.oldmarket.ui.answer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.js.oldmarket.ui.communice.CommuniceHolder;

import java.util.List;

/*
* Class name :CommentAdapter
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-7-31.
*
*/
public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int IMAGE = 0;
    private final int COMMENT = 1;
    private final int count = 2;

    private Context context;
    private List<TComment> mList;

    public AnswerAdapter(Context context, List<TComment> commentBeen) {
        this.context = context;
        this.mList = commentBeen;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == IMAGE){
            holder = new CommuniceHolder(LayoutInflater.from(context), parent);
        }else if (viewType == count){
            holder = new AnswerHolder(LayoutInflater.from(context), parent);
        }
        return holder;
    }

    public void refresh(List<TComment> comments){
        this.mList = comments;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommuniceHolder){
            ((CommuniceHolder) holder).bindData(mList.get(position).getQuestion(), context);
        }else if (holder instanceof AnswerHolder){
            ((AnswerHolder) holder).bindData(mList.get(position).getAnswer(), context);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int reslut = 0;
        if (mList.get(position).getType() == IMAGE){
            reslut = IMAGE;
        }else if (mList.get(position).getType() == count){
            reslut = count;
        }
        return reslut;
    }


}
