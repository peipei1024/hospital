package com.js.oldmarket.ui.home;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.js.oldmarket.R;

import butterknife.ButterKnife;

/*
* Class name :GoodHolder
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
public class GoodHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public LinearLayout view_good_item;
    public ImageView image_pic;
    public TextView text_name;
    public TextView text_price;
    public TextView text_oldPrice;

    public GoodHolder(View itemView) {
        super(itemView);
        view_good_item = (LinearLayout) itemView.findViewById(R.id.id_view_good_item);
        image_pic = (ImageView) itemView.findViewById(R.id.id_image_pic);
        text_name = (TextView) itemView.findViewById(R.id.id_text_name);
        text_price = (TextView) itemView.findViewById(R.id.id_text_price);
        text_oldPrice = (TextView) itemView.findViewById(R.id.id_text_oldprice);
        text_oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.id_view_good_item){

        }
    }
}
