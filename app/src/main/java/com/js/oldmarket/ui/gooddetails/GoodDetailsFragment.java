package com.js.oldmarket.ui.gooddetails;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.js.oldmarket.R;
import com.js.oldmarket.bean.Good;
import com.js.oldmarket.bean.User;
import com.js.oldmarket.presenter.GoodDetailsPresenter;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.ToastUtils;
import com.js.oldmarket.view.BannerImg;
import com.js.oldmarket.view.SwipeRefresh;
import com.squareup.picasso.Picasso;

/*
* Class name :GoodDetailsFragment
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
public class GoodDetailsFragment extends Fragment implements IGoodDetailsView, View.OnClickListener{
    private TextView text_good_name;
    private TextView text_good_price;
    private TextView text_good_oldprice;
    private TextView text_good_condition;
    private TextView text_place;
    private TextView text_time;
    private TextView text_good_details;
    private TextView text_user_name;
    private ImageView image_user_head;
    private LinearLayout view_verfied;
    private LinearLayout view_schoolnum_verfied;

    private BannerImg banner_good_pic;


    private SwipeRefresh swipeRefresh;

    private GoodDetailsPresenter presenter = new GoodDetailsPresenter(this);

    private LinearLayout view_chat;
    private String goodid;
    private String phone;
    private String installationId;
    private final String TAG = this.getClass().getName();

    private boolean isFirst = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        goodid = intent.getStringExtra("goodid");
        isFirst = true;
        ToastUtils.toast(getActivity(), "goodid" + goodid);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_good_details, null);
        text_good_name = (TextView) view.findViewById(R.id.id_text_good_name);
        text_good_price = (TextView) view.findViewById(R.id.id_text_good_price);
        text_good_oldprice = (TextView) view.findViewById(R.id.id_text_good_oldprice);
        text_good_oldprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        text_good_condition = (TextView) view.findViewById(R.id.id_text_good_condition);
        text_place = (TextView) view.findViewById(R.id.id_text_place);
        text_time = (TextView) view.findViewById(R.id.id_text_time);
        text_good_details = (TextView) view.findViewById(R.id.id_text_good_details);
        text_user_name = (TextView) view.findViewById(R.id.id_user_name);
        image_user_head = (ImageView) view.findViewById(R.id.id_image_user_head);
        view_schoolnum_verfied = (LinearLayout) view.findViewById(R.id.id_view_shcoolnum_verfied);
        view_verfied = (LinearLayout) view.findViewById(R.id.id_view_verfied);
        swipeRefresh = (SwipeRefresh) view.findViewById(R.id.id_swiperefresh);

        //banner_good_pic = (BannerImg) view.findViewById(R.id.id_banner_good_pic);

        view_chat = (LinearLayout) view.findViewById(R.id.id_view_chat);
        view_chat.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_view_chat:
                presenter.openChatActivity();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirst){
            isFirst = false;
            presenter.startGetGood();
        }
    }

    @Override
    public void showGoodDetails(Good good) {
//        if (good.getName() != null){
//            text_good_name.setText(good.getName());
//        }
//        if (good.getPrice() != null){
//            text_good_price.setText(good.getPrice());
//        }
//        if (good.getOldPrice() != null){
//            text_good_oldprice.setText(good.getOldPrice());
//        }
//        if (good.getCondition() != null){
//            text_good_condition.setText(good.getCondition());
//        }
//        if (good.getPicUrls() != null){
//            if (good.getPicUrls().size() >= 0){
//                banner_good_pic.setImageUris(good.getPicUrls());
//            }
      //  }
//        if (good.getPlace() != null){
//            StringBuilder sb = new StringBuilder("交易地点：");
//            sb.append(good.getPlace());
//            text_place.setText(sb.toString());
//        }
//        if (good.getTime() != null){
//            StringBuilder sb1 = new StringBuilder("发布时间：");
//            sb1.append(good.getTime());
//            text_time.setText(sb1.toString());
//        }
//        if (good.getDetails() != null){
//            text_good_details.setText(good.getDetails());
//        }
    }

    @Override
    public void showUserDetails(User user) {
//        if (user.getPhone() != null){
//            phone = user.getPhone();
//            LogUtils.i(TAG, phone);
//        }
//        if (user.getInstallationId() != null){
//            installationId = user.getInstallationId();
//        }
//        if (user.getName() != null){
//            text_user_name.setText(user.getName());
//        }
//        if (user.getHeadPic() != null){
//            Picasso.with(getActivity()).load(user.getHeadPic()).into(image_user_head);
//        }
//        if (user.isSchoolNumberVerfied()){
//            view_verfied.setVisibility(View.VISIBLE);
//            view_schoolnum_verfied.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public void toastMsg(String msg) {
        ToastUtils.toast(getActivity(), msg);
    }

    @Override
    public void showSwipeRefresh() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideSwipeRefresh() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void toChatActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("user_head_url", "aaa");
        bundle.putString("installationId", "ds");
        //bundle.putString("installationId", installationId);
//        IntentUtils.doIntentWithBundle(getActivity(), ChatActivity.class, "other", bundle);
    }
}
