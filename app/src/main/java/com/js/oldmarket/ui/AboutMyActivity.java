package com.js.oldmarket.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.utils.AppUtils;
import com.js.oldmarket.utils.IntentUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* Class name :AboutMyActivity
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-5-25.
*
*/
public class AboutMyActivity extends Activity {
    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_version_text)
    TextView idVersionText;
    @Bind(R.id.id_text)
    TextView idText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_my);
        ButterKnife.bind(this);
        ActivityCollector.addActivity(this);
        idVersionText.setText("v" + AppUtils.getVersionName(this));
    }

    @OnClick({R.id.id_back_arrow_image, R.id.id_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_arrow_image:
                ActivityCollector.removeActivity(this);
                break;
            case R.id.id_text:
                IntentUtils.doIntentWithString(this, AgreementActivity.class, "url", "http://nuc.c365.com/home/help2");
                break;
        }
    }
}
