package com.js.oldmarket.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;

import com.js.oldmarket.R;

/*
* Class name :PullUpMoreGridView
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
public class PullUpMoreGridView extends GridView implements AbsListView.OnScrollListener{
    View footer;// 底部布局；
    int totalItemCount;// 总数量；
    int lastVisibleItem;// 最后一个可见的item；
    boolean isLoading;// 正在加载；
    public PullUpMoreGridView(Context context) {
        super(context);
    }

    public PullUpMoreGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullUpMoreGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
