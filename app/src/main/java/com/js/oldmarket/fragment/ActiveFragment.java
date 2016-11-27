package com.js.oldmarket.fragment;/*
* Class name :ActiveFragment
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-11.
*
*/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.js.oldmarket.R;
import com.js.oldmarket.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActiveFragment extends Fragment {
    @Bind(R.id.ididid)
    CardView cardView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active, null);
        ButterKnife.bind(this, view);
        return view;
    }
    @OnClick(R.id.ididid)
    public void ny(){
        ToastUtils.toast(getActivity(), "dsd");
    }
}
