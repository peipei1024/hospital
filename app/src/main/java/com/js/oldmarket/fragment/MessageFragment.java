package com.js.oldmarket.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.js.oldmarket.R;
import com.js.oldmarket.ui.MessageFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/*
* Class name :MessageFragment
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-13.
*
*/
public class MessageFragment extends Fragment implements View.OnClickListener{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MessageFragmentAdapter goodFragmentAdapter;
    private List<Fragment> fragmetList = new ArrayList<>();
    String[] pageName = {"交易提醒", "通知", "私聊"};
    private List<String> pageNameList = new ArrayList<>();
    private ImageView image_back_arrow;
    static boolean isFirst = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirst = true;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);
        viewPager = (ViewPager) view.findViewById(R.id.id_viewpage);
        tabLayout = (TabLayout) view.findViewById(R.id.id_tablayout);
        image_back_arrow = (ImageView) view.findViewById(R.id.id_image_back_arrow);
        if (getActivity().getClass().getName().equals("com.js.oldmarket.ui.MessageActivity")){
            image_back_arrow.setVisibility(View.VISIBLE);
        }

        if (isFirst){
            TradeFragment tradeFragment = new TradeFragment();
            NoticeFragment noticeFragment = new NoticeFragment();
            ChatFragment chatFragment = new ChatFragment();

            fragmetList.add(tradeFragment);
            fragmetList.add(noticeFragment);
            fragmetList.add(chatFragment);
            pageNameList.add("交易提醒");
            pageNameList.add("通知");
            pageNameList.add("私聊");
            goodFragmentAdapter = new MessageFragmentAdapter(getActivity(), pageNameList, fragmetList, getActivity().getSupportFragmentManager());
            viewPager.setAdapter(goodFragmentAdapter);
            tabLayout.setupWithViewPager(viewPager);
            isFirst = false;
        }

        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPause() {
        super.onPause();
        fragmetList.clear();
        pageNameList.clear();
    }
}
