package com.js.oldmarket.ui.publish;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.js.oldmarket.ui.konwledge.KonwLedge;

import java.util.List;

/**
 * Created by JiaM on 2016/4/19.
 */
public class PicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{



    private Context context;
    private List<Bitmap> mList;

    public PicAdapter(Context context, List<Bitmap> commentBeen) {
        this.context = context;
        this.mList = commentBeen;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        holder = new PicHolder(LayoutInflater.from(context), parent);
        return holder;
    }

    public void refresh(List<Bitmap> comments){
        this.mList = comments;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PicHolder) holder).bindData(mList.get(position), context);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
