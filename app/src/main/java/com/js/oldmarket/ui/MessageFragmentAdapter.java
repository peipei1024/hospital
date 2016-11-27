package com.js.oldmarket.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.js.oldmarket.utils.LogUtils;

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
public class MessageFragmentAdapter extends FragmentPagerAdapter{
    private Context mContext;
    private List<Fragment> mList;
    private List<String> mPageName;

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageName.get(position);
    }
    public MessageFragmentAdapter(Context context, List<String> mPageName, List<Fragment> list, FragmentManager fm) {
        super(fm);
        mContext = context;
        mList = list;
        this.mPageName = mPageName;
        LogUtils.i("会读书", "1");
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
