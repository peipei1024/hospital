package com.js.oldmarket.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.utils.IntentUtils;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.ToolUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends Activity {
    private static final long DELAY_TIME = 1000L;
    @Bind(R.id.id_200)
    ImageView id200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        ActivityCollector.addActivity(this);
        redirectByTime();
        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds = true;
        //Bitmap bmp = BitmapFactory.decodeFile("", options);
        //int he = options.outHeight * 200 / options.outWidth;
        //options.outWidth = 200;
        //options.outHeight = he;
        //options.inJustDecodeBounds = false;
        //Bitmap bmp1 = BitmapFactory.decodeFile("", options);
        //id200.setImageBitmap(bmp1);
    }

    /**
     * 根据时间进行页面跳转
     */
    private void redirectByTime() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentUtils.doIntent(SplashActivity.this, BaseActivity.class);
                ActivityCollector.removeActivity(SplashActivity.this);
            }
        }, DELAY_TIME);
    }


}
