package com.js.oldmarket.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.bean.User;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.http.HttpType;
import com.js.oldmarket.http.UserRequest;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.SharedPreferencesUtils;
import com.js.oldmarket.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/*
* Class name :RecoveryPasswordActivity
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-5-26.
*
*/
public class RecoveryPasswordActivity extends Activity {
    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_phone_edit)
    EditText idPhoneEdit;
    @Bind(R.id.id_button)
    Button idButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_password);
        ButterKnife.bind(this);
        ActivityCollector.addActivity(this);
        EventBus.getDefault().register(this);
    }
    public void onEventMainThread(HttpSuccessEvent event) {
        if (event.getType() == HttpType.TYPE_RecoveryPassword) {
            dialog.dismiss();
            ToastUtils.toast(this, "重置密码成功");
            ActivityCollector.removeActivity(this);
        }
    }

    public void onEventMainThread(HttpFailEvent event) {
        if (event.getType() == HttpType.TYPE_RecoveryPassword) {
            dialog.dismiss();
            ToastUtils.toast(this, "重置密码失败");
        }
    }
    ProgressDialog dialog;
    @OnClick({R.id.id_back_arrow_image, R.id.id_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_arrow_image:
                ActivityCollector.removeActivity(this);
                break;
            case R.id.id_button:
                String phone = idPhoneEdit.getText().toString().trim();
                if (!TextUtils.isEmpty(phone)){
                    dialog = new ProgressDialog(this);
                    dialog.setMessage("正在重置密码");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    UserRequest.recoveryPassword(phone);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
