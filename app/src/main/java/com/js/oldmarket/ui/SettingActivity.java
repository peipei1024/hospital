package com.js.oldmarket.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.js.oldmarket.App;
import com.js.oldmarket.R;
import com.js.oldmarket.bean.Vsersion;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.event.LogoutEvent;
import com.js.oldmarket.http.AppRequest;
import com.js.oldmarket.http.HttpType;
import com.js.oldmarket.opera.UserOpera;
import com.js.oldmarket.utils.AppUtils;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.SharedPreferencesUtils;
import com.js.oldmarket.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/*
* Class name :SettingActivity
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-15.
*
*/
public class SettingActivity extends Activity {
    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_logout_button)
    TextView idLogoutButton;
    @Bind(R.id.id_about_view)
    RelativeLayout idAboutView;
    @Bind(R.id.id_version_view)
    RelativeLayout idVersionView;
    @Bind(R.id.id_help_view)
    RelativeLayout idHelpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        if (!UserOpera.isLogin(this)) {
            idLogoutButton.setVisibility(View.GONE);
        } else {
            idLogoutButton.setVisibility(View.VISIBLE);
        }
    }

    public void onEventMainThread(HttpSuccessEvent event) {
        if (event.getType() == HttpType.TYPE_GetLastAPP) {
            LogUtils.i("个人信息", event.getContent());
            Gson gson = new Gson();
            Vsersion vsersion = gson.fromJson(event.getContent(), Vsersion.class);
            if (Float.valueOf(AppUtils.getVersionName(this)) < Float.valueOf(vsersion.getVersionName())) {
                LogUtils.i("ds", Float.valueOf(AppUtils.getVersionName(this)) + " " + Float.valueOf(vsersion.getVersionName()));
                Bundle bundle = new Bundle();
                bundle.putString("VersionName", vsersion.getVersionName());
                bundle.putString("LogContents", vsersion.getLogContents());
                bundle.putString("Url", vsersion.getUrl());
                IntentUtils.doIntentWithBundle(this, VersionActivity.class, "version", bundle);
            } else {
                ToastUtils.toast(this, "已是最新版本");
            }
        }
    }

    public void onEventMainThread(HttpFailEvent event) {
        if (event.getType() == HttpType.TYPE_GetLastAPP) {
            ToastUtils.toast(this, "检查更新失败");
        }
    }

    @OnClick({R.id.id_help_view, R.id.id_version_view, R.id.id_about_view, R.id.id_back_arrow_image, R.id.id_logout_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_help_view:
                ToastUtils.toast(this, "正在努力开发,稍后上线");
                break;
            case R.id.id_version_view:
                AppRequest.version();
                break;
            case R.id.id_about_view:
                IntentUtils.doIntent(this, AboutMyActivity.class);
                break;
            case R.id.id_back_arrow_image:
                finish();
                break;
            case R.id.id_logout_button:
                SharedPreferencesUtils.deleteSharedPreferences(this, "user");
                EventBus.getDefault().post(new LogoutEvent(true));
                finish();
                break;
        }
    }
    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("检测到新版本");
        builder.setMessage("是否下载更新?");
        builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//				MapApp.isDownload = true;
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
