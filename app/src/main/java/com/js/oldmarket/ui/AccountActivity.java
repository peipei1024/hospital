package com.js.oldmarket.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.bean.User;
import com.js.oldmarket.opera.UserOpera;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.LogUtils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* Class name :AccountActivity
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
public class AccountActivity extends Activity {
    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_head_image)
    ImageView idHeadImage;
    @Bind(R.id.id_name_text)
    TextView idNameText;
    @Bind(R.id.id_sex_text)
    TextView idSexText;
    @Bind(R.id.id_schoolnum_text)
    TextView idSchoolnumText;
    @Bind(R.id.id_phone_text)
    TextView idPhoneText;
    @Bind(R.id.id_view_password)
    RelativeLayout idViewPassword;
    private int NAME_CODE = 0;

    String ssex[] = {"男", "女"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ActivityCollector.addActivity(this);
        ButterKnife.bind(this);
        User user = UserOpera.getCurrentUser(this);
        showUser2UI(user);
    }

    private void showUser2UI(User user) {
        if (!TextUtils.isEmpty(user.getPictureUrl())) {
            Picasso.with(this).load(user.getPictureUrl()).placeholder(R.drawable.head).into(idHeadImage);
        }
        if (!TextUtils.isEmpty(user.getNickname())) {
            idNameText.setText(user.getNickname());
        }
        if (!TextUtils.isEmpty(user.getSex())) {
            idSexText.setText(user.getSex());
        }
        if (!TextUtils.isEmpty(user.getStudentNo())) {
            idSchoolnumText.setText("已认证");
        } else {
            idSchoolnumText.setText("未认证");
        }
        if (!TextUtils.isEmpty(user.getMobilePhone())) {
            idPhoneText.setText(user.getMobilePhone());
        }
    }


//        switch (v.getId()) {
//            case R.id.id_view_head:
//                break;
//            case R.id.id_view_name:
//                intent = new Intent(this, AccountEditActivity.class);
//                intent.putExtra("User_data", user);
//                startActivityForResult(intent, NAME_CODE);
//                break;
//            case R.id.id_view_sex:
//                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("性别");
//                builder.setSingleChoiceItems(ssex, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        user.setSex(ssex[which]);
//                        boolean b = GetGood.saveUser(user);
//                        if (b){
//                            text_sex.setText(ssex[which]);
//                            ToastUtils.toast(AccountActivity.this, "保存成功");
//                        }else {
//                            ToastUtils.toast(AccountActivity.this, "保存失败");
//                        }
//                        dialog.dismiss();
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//                break;
//            case R.id.id_view_password:
//                intent = new Intent(this, AccountEditPasswordActivity.class);
//                intent.putExtra("User_data", user);
//                startActivity(intent);
//                break;
//            case R.id.id_image_arrow_back:
//                ActivityCollector.removeActivity(this);
//                break;
//        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NAME_CODE) {
            if (resultCode == 2) {
                //text_name.setText(data.getExtras().getString("name"));
            }
        }

    }

    @OnClick({R.id.id_back_arrow_image, R.id.id_head_image, R.id.id_name_text, R.id.id_sex_text, R.id.id_schoolnum_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_arrow_image:
                ActivityCollector.removeActivity(this);
                break;
            case R.id.id_head_image:
                break;
            case R.id.id_name_text:
//                IntentUtils.doIntentForResult(this, AccountEditActivity.class, NAME_CODE);
//                finish();
                break;
            case R.id.id_sex_text:
                break;
            case R.id.id_schoolnum_text:
                break;
        }
    }

    @OnClick(R.id.id_view_password)
    public void onClick() {
        LogUtils.i("tag", "password");
        IntentUtils.doIntent(this, AccountEditPasswordActivity.class);
    }
}


