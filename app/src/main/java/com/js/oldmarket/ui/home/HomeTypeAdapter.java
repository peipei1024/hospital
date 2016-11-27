package com.js.oldmarket.ui.home;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.js.oldmarket.R;
import com.js.oldmarket.bean.Good;
import com.js.oldmarket.ui.AssortmentActivity;
import com.js.oldmarket.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

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
public class HomeTypeAdapter extends BaseAdapter{
    private List<TypeBean> list = new ArrayList<>();
    private Context context;
    public HomeTypeAdapter(Context context, List<TypeBean> l){
        this.list = l;
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        TypeHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.view_type, null);
            holder = new TypeHolder();
            holder.view_type = (LinearLayout) convertView.findViewById(R.id.id_view_type);
            holder.image_type = (ImageView) convertView.findViewById(R.id.id_image_type);
            holder.text_type = (TextView) convertView.findViewById(R.id.id_text_type);
            convertView.setTag(holder);
        }else {
            holder = (TypeHolder) convertView.getTag();
        }

        TypeBean type = list.get(position);
        //设置图片holder.image_pic
        holder.image_type.setImageResource(type.getRes_type());
        holder.text_type.setText(type.getStr_type());

        holder.view_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("type_str", list.get(position).getStr_type());
                bundle.putInt("type_res", list.get(position).getRes_type());
                IntentUtils.doIntentWithBundle(context, AssortmentActivity.class, "type", bundle);
            }
        });
        return convertView;
    }
    class TypeHolder{
        public LinearLayout view_type;
        public ImageView image_type;
        public TextView text_type;
    }
}
