package com.js.oldmarket.ui.leavewords;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.js.oldmarket.R;
import com.js.oldmarket.bean.LeaveWordBean;
import com.js.oldmarket.presenter.LeaveWordsPresenter;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.ToastUtils;
import com.js.oldmarket.view.PullUpMoreListView;
import com.js.oldmarket.view.SwipeRefresh;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/*
* Class name :LeaveWordsFragment
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
public class LeaveWordsFragment extends Fragment implements ILeaveWordsView, SwipeRefreshLayout.OnRefreshListener,PullUpMoreListView.ILoadListener{
//    @Bind(R.id.id_listview)
//    PullUpMoreListView listView;
    @Bind(R.id.id_swiperefresh)
    SwipeRefresh swiperefresh;

    //PullUpMoreListView listView;

    private LeaveWordsAdapter adapter;
    private List<LeaveWordBean> list = new ArrayList<>();

    boolean isFirst = false;

    private int dataPage = 1;

    private LeaveWordsPresenter presenter = new LeaveWordsPresenter(getActivity(), this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirst = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_leave_words, container, false);
        ButterKnife.bind(this, view);
        //listView = (PullUpMoreListView) view.findViewById(R.id.id_listview);
        adapter = new LeaveWordsAdapter(getActivity(), (ArrayList<LeaveWordBean>) list);
        //listView.setAdapter(adapter);
        //listView.setLoadListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirst){
            presenter.getLeaveWordsData(dataPage);
            dataPage++;
            isFirst = false;
        }
    }
    //下拉刷新
    @Override
    public void onRefresh() {
        dataPage = 1;
        presenter.getLeaveWordsData(dataPage);
    }

    //上拉更多
    @Override
    public void onLoad() {
        LogUtils.i("刷新", "shuain");
        LeaveWordBean lb = new LeaveWordBean();

        list.add(lb);
        adapter.myNotifyDataSetChanged((ArrayList<LeaveWordBean>) list);
        ToastUtils.toast(getActivity(), "shuaixn");
        presenter.getLeaveWordsData(dataPage);
        dataPage++;
    }

    @OnItemClick(R.id.id_listview)
    public void listClink(int position) {
        ToastUtils.toast(getActivity(), "Dsd" + position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void refreshAdapter(List<LeaveWordBean> l) {
        adapter.myNotifyDataSetChanged((ArrayList<LeaveWordBean>) l);
    }

    @Override
    public void toActivity() {
        //转跳到回复详情页面
    }

    @Override
    public void replyWithPhone() {
        //点开电话
    }

    @Override
    public void replyWithSms() {
        //打开短信
    }

    @Override
    public void reply() {
        //代开回复对话框
    }

    @Override
    public void showPullDownRefresh() {
        swiperefresh.setRefreshing(true);
    }

    @Override
    public void hidePullDpwnRefresh() {
        swiperefresh.setRefreshing(false);
    }

    @Override
    public void toastMsg(String msg) {
        ToastUtils.toast(getActivity(), msg);
    }
}
