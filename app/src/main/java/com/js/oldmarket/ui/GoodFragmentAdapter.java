package com.js.oldmarket.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/*
* Class name :GoodFragmentAdapter
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
public class GoodFragmentAdapter extends FragmentPagerAdapter{
    private Context mContext;
    private List<Fragment> mList;
    private String[] mPageName;

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageName[position];
    }
    public GoodFragmentAdapter(Context context, String[] pageName, List<Fragment> list, FragmentManager fm) {
        super(fm);
        mContext = context;
        mList = list;
        mPageName = pageName;

    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
