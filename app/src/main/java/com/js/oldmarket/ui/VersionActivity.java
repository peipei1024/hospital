package com.js.oldmarket.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.utils.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* Class name :VersionActivity
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
public class VersionActivity extends Activity {
    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_version_text)
    TextView idVersionText;
    @Bind(R.id.id_content_text)
    TextView idContentText;
    @Bind(R.id.id_down_button)
    Button idDownButton;

    private String VersionName;
    private String LogContents;
    private String Url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("version");
        VersionName = bundle.getString("VersionName");
        LogContents = bundle.getString("LogContents");
        Url = bundle.getString("Url");
        LogUtils.i("VersionActivity", Url);
        ButterKnife.bind(this);
        ActivityCollector.addActivity(this);
        idVersionText.setText("v"+VersionName);
        idContentText.setText(LogContents);
    }

    @OnClick({R.id.id_back_arrow_image, R.id.id_down_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_arrow_image:
                ActivityCollector.removeActivity(this);
                break;
            case R.id.id_down_button:

                break;
        }
    }
}
