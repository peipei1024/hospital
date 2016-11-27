package com.js.oldmarket.ui;

import android.app.Activity;
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
import com.js.oldmarket.opera.UserOpera;
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
* Class name :AccountEditActivity
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-28.
*
*/
public class AccountEditActivity extends Activity {


    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_name_edit)
    EditText idNameEdit;
    @Bind(R.id.id_save_button)
    Button idSaveButton;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);
        ActivityCollector.addActivity(this);
        ButterKnife.bind(this);
        user = UserOpera.getCurrentUser(this);
        EventBus.getDefault().register(this);
    }

    public void onEventMainThread(HttpSuccessEvent event){
        if (event.getType() == HttpType.TYPE_UpdateUser){
            LogUtils.i("AccountEditActivity", event.getContent());
            ToastUtils.toast(this, "修改成功");
            UserRequest.login(this);
            finish();
        }
    }
    public void onEventMainThread(HttpFailEvent event){
        if (event.getType() == HttpType.TYPE_UpdateUser){
            ToastUtils.toast(this, "修改信息失败");
        }
    }
    @OnClick({R.id.id_back_arrow_image, R.id.id_save_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_arrow_image:
                finish();
                break;
            case R.id.id_save_button:
                String name = idNameEdit.getText().toString().trim();
                if (!TextUtils.isEmpty(name)){
                    if (user != null){
                        UserRequest.update(this, "", user.getMobilePhone(), user.getSex(), name, "", user.getIMEI());
                    }else {
                        ToastUtils.toast(this, "修改信息失败");
                    }
                }else {
                    ToastUtils.toast(this, "请先输入昵称");
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

