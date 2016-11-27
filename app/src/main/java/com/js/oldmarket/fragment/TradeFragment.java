package com.js.oldmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.js.oldmarket.R;
import com.js.oldmarket.utils.IntentUtils;

/*
* Class name :TradeFragment
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-14.
*
*/
public class TradeFragment extends Fragment implements View.OnClickListener{
    private LinearLayout view_confirm_item;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trade, null);
        view_confirm_item = (LinearLayout) view.findViewById(R.id.id_view_confirm_item);
        view_confirm_item.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_view_confirm_item:
//                IntentUtils.doIntent(getActivity(), GoodActivity.class);
                break;
        }
    }
}
