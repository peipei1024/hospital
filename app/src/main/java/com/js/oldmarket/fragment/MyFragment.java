package com.js.oldmarket.fragment;/*
* Class name :MyFragment
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.js.oldmarket.R;
import com.js.oldmarket.event.LogoutEvent;
import com.js.oldmarket.opera.UserOpera;
import com.js.oldmarket.ui.AccountActivity;
import com.js.oldmarket.ui.SettingActivity;
import com.js.oldmarket.ui.login.LoginActivity;
import com.js.oldmarket.ui.order.AllOrderActivity;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.ToastUtils;
import com.js.oldmarket.view.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class MyFragment extends Fragment {


    @Bind(R.id.id_message_image)
    ImageView idMessageImage;
    @Bind(R.id.id_setting_image)
    ImageView idSettingImage;
    @Bind(R.id.id_no_account_view)
    LinearLayout idNoAccountView;
    @Bind(R.id.id_name_text)
    TextView idNameText;
    @Bind(R.id.id_schoolnum_text)
    TextView idSchoolnumText;
    @Bind(R.id.id_account_view)
    LinearLayout idAccountView;
    @Bind(R.id.id_yes_account_view)
    LinearLayout idYesAccountView;
    @Bind(R.id.id_view_all_order)
    RelativeLayout idViewAllOrder;
    @Bind(R.id.id_view_finish_order)
    LinearLayout idViewFinishOrder;
    @Bind(R.id.id_view_collect_good)
    RelativeLayout idViewCollectGood;
    @Bind(R.id.id_verfied_view)
    RelativeLayout idVerfiedView;
    @Bind(R.id.id_head_image)
    RoundImageView idHeadImage;
    @Bind(R.id.id_logout_btn)
    Button idLogoutBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (AVUser.getCurrentUser() != null) {
            idNoAccountView.setVisibility(View.GONE);
            idYesAccountView.setVisibility(View.VISIBLE);
            AVUser user = AVUser.getCurrentUser();
            showUser2UI(user);
        } else {
            idNoAccountView.setVisibility(View.VISIBLE);
            idYesAccountView.setVisibility(View.GONE);
        }
    }

    private void showUser2UI(AVUser user) {
//        if (user.getPictureUrl() != null) {
//            Picasso.with(getActivity()).load(user.getPictureUrl()).placeholder(R.drawable.head).error(R.drawable.head).into(idHeadImage);
//        }
        if (user.getUsername() != null) {
            idNameText.setText(user.getUsername());
        }
//        if (user.getStudentNo() == null) {
//            idSchoolnumText.setText("未验证");
//        } else {
//            idSchoolnumText.setText("已验证");
//        }
    }


    @OnClick({R.id.id_verfied_view, R.id.id_message_image, R.id.id_setting_image, R.id.id_no_account_view, R.id.id_account_view, R.id.id_yes_account_view, R.id.id_view_all_order, R.id.id_view_finish_order, R.id.id_view_collect_good})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_verfied_view:
                ToastUtils.toast(getActivity(), "耐心等待，正在努力开发");
                break;
            case R.id.id_message_image:
                ToastUtils.toast(getActivity(), "正在努力开发,稍后上线");
                break;
            case R.id.id_setting_image:
                IntentUtils.doIntent(getActivity(), SettingActivity.class);
                break;
            case R.id.id_no_account_view:
                IntentUtils.doIntent(getActivity(), LoginActivity.class);
                getActivity().finish();
                break;
            case R.id.id_account_view:
//                IntentUtils.doIntent(getContext(), AccountActivity.class);
                break;
            case R.id.id_yes_account_view://不用实现
                break;
            case R.id.id_view_all_order:
//                if (UserOpera.isLogin(getActivity())) {
//                    IntentUtils.doIntent(getActivity(), AllOrderActivity.class);
//                } else {
//                    ToastUtils.toast(getActivity(), "请先登录");
//                }
                break;
            case R.id.id_view_finish_order:

                break;
            case R.id.id_view_collect_good:
                ToastUtils.toast(getActivity(), "耐心等待，正在努力开发");
                break;

        }
    }


    public void onEventMainThread(LogoutEvent event) {
        LogUtils.i("ds", "logout");
        if (event.isLogout()) {
            idNoAccountView.setVisibility(View.VISIBLE);
            idYesAccountView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }


    @OnClick(R.id.id_logout_btn)
    public void onClick() {
        AVUser.getCurrentUser().logOut();
        ToastUtils.toast(getActivity(), "已退出");
        if (AVUser.getCurrentUser() != null) {
            idNoAccountView.setVisibility(View.GONE);
            idYesAccountView.setVisibility(View.VISIBLE);
            AVUser user = AVUser.getCurrentUser();
            showUser2UI(user);
        } else {
            idNoAccountView.setVisibility(View.VISIBLE);
            idYesAccountView.setVisibility(View.GONE);
        }
    }
}
