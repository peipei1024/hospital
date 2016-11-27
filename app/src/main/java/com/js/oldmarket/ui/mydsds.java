package com.js.oldmarket.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.js.oldmarket.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
* Class name :mydsds
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
public class mydsds extends Fragment{
    @Bind(R.id.id_image_left_head)
    ImageView ima;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_chat_item, container, false);
        ButterKnife.bind(this, view);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
