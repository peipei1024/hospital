package com.js.oldmarket.ui.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.js.oldmarket.R;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.http.HttpType;
import com.js.oldmarket.http.TokenUtils;
import com.js.oldmarket.http.UserRequest;
import com.js.oldmarket.ui.BaseActivity;
import com.js.oldmarket.ui.RecoveryPasswordActivity;
import com.js.oldmarket.ui.RegisterActivity;
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
* Class name :LoginActivity
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-17.
*
*/
public class LoginActivity extends Activity {

    @Bind(R.id.id_phone_edit)
    EditText idPhoneEdit;
    @Bind(R.id.id_password_edit)
    EditText idPasswordEdit;
    @Bind(R.id.id_login_button)
    Button idLoginButton;
    @Bind(R.id.id_register_text)
    TextView idRegisterText;
    @Bind(R.id.id_recoveryPassword_text)
    TextView idRecoveryPasswordText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);


    }

    public void onEventMainThread(HttpSuccessEvent event) {
        if (event.getType() == HttpType.TYPE_Login){
            UserRequest.login(this);
        }else if (event.getType() == HttpType.TYPE_GETPERSONNEL) {


        }
    }

    public void onEventMainThread(HttpFailEvent event) {
        if (event.getType() == HttpType.TYPE_Login){

        }else if (event.getType() == HttpType.TYPE_GETPERSONNEL) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }




    ProgressDialog dialog;

    @OnClick({R.id.id_login_button, R.id.id_register_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_login_button:
                String phone = idPhoneEdit.getText().toString().trim();
                String password = idPasswordEdit.getText().toString().trim();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
                    dialog = new ProgressDialog(this);
                    dialog.setMessage("正在登录");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    AVUser.logInInBackground(phone, password, new LogInCallback<AVUser>() {
                        @Override
                        public void done(AVUser avUser, AVException e) {
                            if (e == null){
                                ToastUtils.toast(LoginActivity.this, "登录成功");
                                dialog.dismiss();
                                finish();
                                IntentUtils.doIntent(LoginActivity.this, BaseActivity.class);
                            }else {
                                ToastUtils.toast(LoginActivity.this, "登录失败,密码错误");
                                dialog.dismiss();
                            }
                        }
                    });
                } else {
                    ToastUtils.toast(this, "信息不能为空");
                }
                break;
            case R.id.id_register_text:
                IntentUtils.doIntent(this, RegisterActivity.class);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            IntentUtils.doIntent(this, BaseActivity.class);
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.id_recoveryPassword_text)
    public void onClick() {
        IntentUtils.doIntent(this, RecoveryPasswordActivity.class);
    }
}
