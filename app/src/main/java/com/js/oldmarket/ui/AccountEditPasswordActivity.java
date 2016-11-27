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
* Class name :AccountEditPasswordActivity
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
public class AccountEditPasswordActivity extends Activity {

    @Bind(R.id.id_image_back_arrow)
    ImageView idImageBackArrow;
    @Bind(R.id.id_edit_phone)
    EditText idEditPhone;
    @Bind(R.id.id_edit_password1)
    EditText idEditPassword1;
    @Bind(R.id.id_edit_password2)
    EditText idEditPassword2;
    @Bind(R.id.id_button_save)
    Button idButtonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit_password);
        ActivityCollector.addActivity(this);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

    }
    public void onEventMainThread(HttpSuccessEvent event) {
        if (event.getType() == HttpType.TYPE_updatePassword) {
            LogUtils.i("个人信息", event.getContent());
            ToastUtils.toast(this, "修改成功");
            idEditPhone.setText("");
            idEditPassword1.setText("");
            idEditPassword2.setText("");
            dialog.dismiss();

        }
    }

    public void onEventMainThread(HttpFailEvent event) {
        if (event.getType() == HttpType.TYPE_updatePassword){
            ToastUtils.toast(this, "修改密码失败");
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    ProgressDialog dialog;
    @OnClick({R.id.id_image_back_arrow, R.id.id_button_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_image_back_arrow:
                ActivityCollector.removeActivity(this);
                break;
            case R.id.id_button_save:
                String phone = idEditPhone.getText().toString().trim();
                String oldpassword = idEditPassword1.getText().toString().trim();
                String newpassword = idEditPassword2.getText().toString().trim();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(oldpassword) && !TextUtils.isEmpty(newpassword)){
                    if (newpassword.length() >= 6){
                        dialog = new ProgressDialog(this);
                        dialog.setMessage("正在修改");
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        UserRequest.updatePassword(phone, oldpassword, newpassword);
                    }else {
                        ToastUtils.toast(this, "新密码必须大于等于6位");
                    }

                }else {
                    ToastUtils.toast(this, "请将信息填写完整");
                }
                break;
        }
    }
    //    @Override
//    public void onClick(View v) {
//                if (edit_password1.getText().toString().trim().equals(edit_password2.getText().toString().trim())) {
//                    user.setPassword(edit_password1.getText().toString().trim());
//                    boolean b = GetGood.saveUser(user);
//                    if (b){
//                        ToastUtils.toast(this, "修改成功");
//                        ActivityCollector.removeActivity(this);
//                    }else {
//                        ToastUtils.toast(this, "修改失败");
//                    }
//                } else {
//                    ToastUtils.toast(this, "两次输入密码不一致");
//                }
//                break;
//        }
//    }

}
