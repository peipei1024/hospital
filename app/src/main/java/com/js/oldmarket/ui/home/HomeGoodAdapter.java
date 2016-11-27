package com.js.oldmarket.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.js.oldmarket.R;
import com.js.oldmarket.bean.Good;
import com.js.oldmarket.ui.BaseActivity;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.LogUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/*
* Class name :HomeGoodAdapter
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-16.
*
*/
public class HomeGoodAdapter extends BaseAdapter{
    private List<Good> list;
    private Context context;
    public  HomeGoodAdapter(Context context, ArrayList<Good> list){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    public void myNotifyDataSetChanged(ArrayList<Good> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GoodHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.view_gridview_item, null);
            holder = new GoodHolder();
            holder.view_good_item = (LinearLayout) convertView.findViewById(R.id.id_view_good_item);
            holder.image_pic = (ImageView) convertView.findViewById(R.id.id_image_pic);
            holder.text_name = (TextView) convertView.findViewById(R.id.id_text_name);
            holder.text_price = (TextView) convertView.findViewById(R.id.id_text_price);
            holder.text_oldPrice = (TextView) convertView.findViewById(R.id.id_text_oldprice);
            holder.text_oldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
            convertView.setTag(holder);
        }else {
            holder = (GoodHolder) convertView.getTag();
        }
        Good good = list.get(position);
        if (!TextUtils.isEmpty(good.getTitle())){
            holder.text_name.setText(good.getTitle());
        }
        if (!TextUtils.isEmpty(good.getPrice())){
            holder.text_price.setText("现价" + good.getPrice() + "￥");
        }
        if (!TextUtils.isEmpty(good.getOriginalPrice())){
            holder.text_oldPrice.setText(good.getOriginalPrice() + "￥");
        }
        if (!TextUtils.isEmpty(good.getPictureUrl())){
            Picasso.with(context).load(good.getPictureUrl()).resize(600,600).centerCrop().placeholder(R.drawable.good_default).into(holder.image_pic);
        }
        holder.view_good_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IntentUtils.doIntentWithString(context, GoodActivity.class, "goodid", list.get(position).getGoodID());
            }
        });

        return convertView;
    }
    class GoodHolder{
        public LinearLayout view_good_item;
        public ImageView image_pic;
        public TextView text_name;
        public TextView text_price;
        public TextView text_oldPrice;
    }
}
