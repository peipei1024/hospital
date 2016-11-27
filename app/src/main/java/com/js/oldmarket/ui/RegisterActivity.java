package com.js.oldmarket.ui;/*
* Class name :RegisterActivity
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

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.http.HttpType;
import com.js.oldmarket.http.UserRequest;
import com.js.oldmarket.ui.login.LoginActivity;
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

public class RegisterActivity extends Activity {
    @Bind(R.id.id_phone_edit)
    EditText idPhoneEdit;
    @Bind(R.id.id_password_edit)
    EditText idPasswordEdit;
    @Bind(R.id.id_confimepassword_edit)
    EditText idConfimepasswordEdit;
    @Bind(R.id.id_register_button)
    Button idRegisterButton;
    @Bind(R.id.id_inuc_text)
    TextView idInucText;
    @Bind(R.id.id_login_text)
    TextView idLoginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActivityCollector.addActivity(this);
        ButterKnife.bind(this);
    }
    ProgressDialog dialog;
    @OnClick({R.id.id_register_button, R.id.id_inuc_text, R.id.id_login_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_register_button:
                String phone = idPhoneEdit.getText().toString().trim();
                String password = idPasswordEdit.getText().toString().trim();
                String confimepassword = idConfimepasswordEdit.getText().toString().trim();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confimepassword)){
                    if (password.equals(confimepassword)){
                        dialog = new ProgressDialog(this);
                        dialog.setMessage("正在注册");
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        AVUser user = new AVUser();// 新建 AVUser 对象实例
                        user.setUsername(phone);// 设置用户名
                        user.setPassword(password);// 设置密码
                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(AVException e) {
                                if (e == null) {
                                    // 注册成功
                                    ToastUtils.toast(RegisterActivity.this, "注册成功，请登录");
                                    dialog.dismiss();
                                    ActivityCollector.removeActivity(RegisterActivity.this);
                                } else {
                                    // 失败的原因可能有多种，常见的是用户名已经存在。
                                    ToastUtils.toast(RegisterActivity.this, "注册失败");
                                }
                            }
                        });
                    }else {
                        ToastUtils.toast(this, "密码不一致");
                    }

                }else {
                    ToastUtils.toast(this, "信息不能为空");
                }
                break;
            case R.id.id_inuc_text:
                IntentUtils.doIntentWithString(this, AgreementActivity.class, "url", "http://nuc.c365.com/home/help1");
                break;
            case R.id.id_login_text:
                IntentUtils.doIntent(this, LoginActivity.class);
                ActivityCollector.removeActivity(this);
                break;
        }
    }



}
