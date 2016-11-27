package com.js.oldmarket.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.utils.IntentUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* Class name :AgreementActivity
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
public class AgreementActivity extends Activity {
    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_webview)
    WebView idWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        ButterKnife.bind(this);
        ActivityCollector.addActivity(this);
        idWebview.loadUrl(url);
    }

    @OnClick(R.id.id_back_arrow_image)
    public void onClick() {
        ActivityCollector.removeActivity(this);
    }
}
