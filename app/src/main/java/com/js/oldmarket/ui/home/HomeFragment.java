package com.js.oldmarket.ui.home;/*
* Class name :HomeFragment
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

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.js.oldmarket.R;
import com.js.oldmarket.bean.Banner;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.http.AppRequest;
import com.js.oldmarket.http.HttpType;
import com.js.oldmarket.ui.communice.CommuniceActivity;
import com.js.oldmarket.ui.konwledge.CommentItemAdapter;
import com.js.oldmarket.ui.konwledge.KnowLedgeActivity;
import com.js.oldmarket.ui.konwledge.KonwLedge;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.ToastUtils;
import com.js.oldmarket.view.BannerImg;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class HomeFragment extends Fragment {
    @Bind(R.id.id_banner)
    BannerImg banner;
    @Bind(R.id.id_knowledge_view)
    LinearLayout idKnowledgeView;
    @Bind(R.id.id_hos_view)
    LinearLayout idHosView;
    @Bind(R.id.id_doctor_view)
    LinearLayout idDoctorView;
    @Bind(R.id.id_communice_view)
    LinearLayout idCommuniceView;
    @Bind(R.id.id_recy)
    RecyclerView idRecy;


    private Context context;
    private boolean isFirst = false;

    private List<KonwLedge> mList = new ArrayList<>();
    private CommentItemAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        isFirst = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        adapter = new CommentItemAdapter(getActivity(), mList);
        layoutManager = new LinearLayoutManager(getActivity());
        idRecy.setLayoutManager(layoutManager);
        idRecy.setAdapter(adapter);

        EventBus.getDefault().register(this);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        banner.startPlay();
        if (isFirst) {
            ArrayList<String> banner_list = new ArrayList<>();
            banner_list.add("http://ac-wybixxjv.clouddn.com/ce142232962e252c2a39.png");
            banner_list.add("http://ac-wybixxjv.clouddn.com/ea74b434e6d87263780c.png");
            banner.setImageUris(banner_list);
            isFirst = false;
            getData();
        }

    }

    private void getData() {
        AVQuery<AVObject> query = new AVQuery<>("knowledge");
        query.setLimit(3);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    KonwLedge konwLedge = null;
                    for (int a = 0; a < list.size(); a++) {
                        AVObject object = list.get(a);
                        String title = object.getString("title");
                        String content = object.getString("content");
                        AVFile file = object.getAVFile("picture");
                        String url = file.getUrl();
                        String scoure = object.getString("source_url");
                        konwLedge = new KonwLedge();
                        konwLedge.setTitle(title);
                        konwLedge.setConten(content);
                        konwLedge.setUrl(url);
                        konwLedge.setScoure(scoure);
                        mList.add(konwLedge);
                    }
                    adapter.refresh(mList);
                } else {
                    ToastUtils.toast(getActivity(), "查询数据失败");
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopPlay();
    }

    public void onEventMainThread(HttpSuccessEvent event) {
        if (event.getType() == HttpType.TYPE_GetGoods) {

        } else if (event.getType() == HttpType.TYPE_Banner) {
            Gson gson = new Gson();
            ArrayList<Banner> bannerbeans = gson.fromJson(event.getContent(), new TypeToken<ArrayList<Banner>>() {
            }.getType());
            ArrayList<String> banner_list = new ArrayList<>();
            for (int a = 0; a < bannerbeans.size(); a++) {
                banner_list.add(bannerbeans.get(a).getContents());
            }
            banner.setImageUris(banner_list);
        }
    }

    public void onEventMainThread(HttpFailEvent event) {
        if (event.getType() == HttpType.TYPE_GetGoods) {
            ToastUtils.toast(context, "获取数据失败");
        } else if (event.getType() == HttpType.TYPE_Banner) {
            ToastUtils.toast(context, "获取banner失败");
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.id_knowledge_view, R.id.id_hos_view, R.id.id_doctor_view, R.id.id_communice_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_knowledge_view:
                IntentUtils.doIntent(getActivity(), KnowLedgeActivity.class);
                break;
            case R.id.id_hos_view:
                break;
            case R.id.id_doctor_view:
                break;
            case R.id.id_communice_view:
                IntentUtils.doIntent(getActivity(), CommuniceActivity.class);
                break;
        }
    }
}
