package com.js.oldmarket.ui.publish;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.js.oldmarket.R;
import com.js.oldmarket.opera.SelectPhotoOpera;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PicHolder extends RecyclerView.ViewHolder {


    @Bind(R.id.id_pic_iv)
    ImageView idPicIv;
    private Context context;

    public PicHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.holder_pic, parent, false));
    }

    public PicHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bindData(Bitmap commentBean, Context context) {
        this.context = context;
        idPicIv.setImageBitmap(commentBean);
    }


    @OnClick(R.id.id_pic_iv)
    public void onClick() {
        SelectPhotoOpera.openAlbum((Activity) context, true, 3, PublishActivity.str_photo_path, 1);
    }
}
