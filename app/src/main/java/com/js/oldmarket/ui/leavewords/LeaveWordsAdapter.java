package com.js.oldmarket.ui.leavewords;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.BinderThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.js.oldmarket.R;
import com.js.oldmarket.bean.LeaveWordBean;
import com.js.oldmarket.bean.User;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.view.RoundImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* Class name :LeaveWordsAdapter
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-20.
*
*/
public class LeaveWordsAdapter extends BaseAdapter{
    private List<LeaveWordBean> list = new ArrayList<>();
    private Context context;
    public LeaveWordsAdapter(Context context, ArrayList<LeaveWordBean> l){
        this.context = context;
        this.list = l;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    public void myNotifyDataSetChanged(ArrayList<LeaveWordBean> l){
        this.list = l;
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
        LeaveWordHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.view_leave_word_item, null);
            holder = new LeaveWordHolder(convertView);
            holder.test = (TextView) convertView.findViewById(R.id.id_text_time);
            convertView.setTag(holder);
        }else {
            holder = (LeaveWordHolder) convertView.getTag();
        }

        LeaveWordBean b = list.get(position);
        if (b.getRemarks() != null){
            holder.text_content.setText(b.getRemarks());
        }
        if (b.getBid() != null){
            holder.text_offer.setVisibility(View.VISIBLE);
            holder.text_offer.setText("出价:" + b.getBid());
        }else {
            holder.text_offer.setVisibility(View.GONE);
        }
        if (b.getRemarkTime() != null){
            holder.text_time.setText("时间:" + b.getRemarkTime());
        }
        if (!TextUtils.isEmpty(b.getContact())){
            StringBuffer sb = new StringBuffer(b.getContact());
            String a = sb.substring(0, 3).toString();
            StringBuffer sb1 = new StringBuffer(b.getContact());
            String cc = sb1.substring(7, 11);
            holder.text_phone.setText(a + "****" + cc);
        }
        if (b.getNickname() != null){
            holder.text_user_name.setText(b.getNickname());
        }
        holder.text_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + list.get(position).getContact()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class LeaveWordHolder{
        public TextView test;
        @Bind(R.id.id_image_head)
        RoundImageView image_head;
        @Bind(R.id.id_text_user_name)
        TextView text_user_name;
        @Bind(R.id.id_text_phone)
        TextView text_phone;
        @Bind(R.id.id_text_time)
        TextView text_time;
        @Bind(R.id.id_text_content)
        TextView text_content;
        @Bind(R.id.id_text_offer)
        TextView text_offer;
        @Bind(R.id.id_view_reply)
        LinearLayout view_reply;
        @Bind(R.id.id_text_reply)
        Button text_reply;
        public LeaveWordHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
