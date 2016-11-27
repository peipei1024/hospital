package com.js.oldmarket.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;

/*
* Class name :ReplyActivity
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
public class ReplyActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        ActivityCollector.addActivity(this);
    }

    @Override
    public void onClick(View v) {

    }
}
