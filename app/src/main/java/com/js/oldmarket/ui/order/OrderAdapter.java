package com.js.oldmarket.ui.order;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.js.oldmarket.R;
import com.js.oldmarket.bean.Good;
import com.js.oldmarket.event.GoodIDEvent;
import com.js.oldmarket.http.GoodRequest;
import com.js.oldmarket.ui.gooddetails.GoodDetailsFragment;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.LogUtils;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import me.nereo.multi_image_selector.bean.Image;

/*
* Class name :OrderAdapter
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-28.
*
*/
public class OrderAdapter extends BaseAdapter{
    private ArrayList<Good> list;
    private Context context;
    public OrderAdapter(Context context, ArrayList<Good> list){
        this.context = context;
        this.list = list;
    }
    public void myNotifyDataSetChanged(ArrayList<Good> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.view_order, null);
            holder = new OrderHolder();
            holder.order = (RelativeLayout) convertView.findViewById(R.id.id_view_order);
            holder.pic = (ImageView) convertView.findViewById(R.id.id_order_image);
            holder.name = (TextView) convertView.findViewById(R.id.id_name_text);
            holder.state = (TextView) convertView.findViewById(R.id.id_state_text);
            holder.price = (TextView) convertView.findViewById(R.id.id_money_text);
            holder.confine = (Button) convertView.findViewById(R.id.id_button_right);
            convertView.setTag(holder);
        }else {
            holder = (OrderHolder) convertView.getTag();
        }
        final Good good = list.get(position);
        if (!TextUtils.isEmpty(good.getTitle())){
            holder.name.setText(good.getTitle());
        }
        if (!TextUtils.isEmpty(good.getPictureUrl())){
            Picasso.with(context).load(good.getPictureUrl()).placeholder(R.drawable.good_default).into(holder.pic);
        }
        if (!TextUtils.isEmpty(good.getPrice())){
            holder.price.setText("金额:" + good.getPrice());
        }
        if (!TextUtils.isEmpty(good.getCloseTradeTime())){
            holder.state.setText("交易成功");
            holder.confine.setVisibility(View.GONE);
        }else {
            holder.state.setText("未交易成功");
            holder.confine.setVisibility(View.VISIBLE);
            holder.confine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    LogUtils.i("ds", formatter.format(new Date()));
                    GoodRequest.updateGood(context, good.getGoodID(), formatter.format(new Date()));
                }
            });
        }
        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GoodIDEvent(good.getGoodID()));

            }
        });
        return convertView;
    }

    class OrderHolder{
        public RelativeLayout order;
        public ImageView pic;
        public TextView name;
        public TextView state;
        public TextView price;
        public Button confine;
    }
}
