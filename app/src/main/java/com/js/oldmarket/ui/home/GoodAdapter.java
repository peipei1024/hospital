package com.js.oldmarket.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.js.oldmarket.R;
import com.js.oldmarket.bean.Good;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/*
* Class name :GoodAdapter
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-5-23.
*
*/
public class GoodAdapter extends RecyclerView.Adapter<GoodHolder>{
    ArrayList<Good> list = new ArrayList<>();
    private Context context;

    public GoodAdapter(Context context, ArrayList<Good> list){
        this.list = list;
        this.context = context;
    }
    @Override
    public GoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_gridview_item, null);
        GoodHolder holder = new GoodHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GoodHolder holder, int position) {
        Good good = list.get(position);
        if (!TextUtils.isEmpty(good.getTitle())){
            holder.text_name.setText(good.getTitle());
        }
        if (!TextUtils.isEmpty(good.getPrice())){
            holder.text_price.setText(good.getPrice());
        }
        if (!TextUtils.isEmpty(good.getOriginalPrice())){
            holder.text_oldPrice.setText(good.getOriginalPrice());
        }
        if (!TextUtils.isEmpty(good.getPictureUrl())){
            Picasso.with(context).load(good.getPictureUrl()).placeholder(R.drawable.good_default).into(holder.image_pic);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
