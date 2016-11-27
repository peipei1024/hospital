package com.js.oldmarket.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.utils.ToastUtils;

/*
* Class name :SubmitOrderActivity
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
public class SubmitOrderActivity extends Activity implements View.OnClickListener{
    private Button button_submit_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);
        init();
    }
    private void init(){
        button_submit_order = (Button) findViewById(R.id.id_button_submit_order);

        button_submit_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_button_submit_order:
                ToastUtils.toast(this, "订单提交成功，等待对方确认");
                ActivityCollector.removeActivity(this);
                break;
        }
    }
}
