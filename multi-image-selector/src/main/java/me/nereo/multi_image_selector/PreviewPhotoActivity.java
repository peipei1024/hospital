package me.nereo.multi_image_selector;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 徐泽 on 2015/10/26.
 */
public class PreviewPhotoActivity  extends Activity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;
    private ImageButton btnBack;
    protected List<String> photos;
    protected int current;
    private int num;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent it = getIntent();
        num=it.getIntExtra("num",100);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        setContentView(R.layout.activity_previewphoto);
        btnBack = (ImageButton) findViewById(R.id.btn_back_app);
        mViewPager = (ViewPager) findViewById(R.id.vp_base_app);
        photos = new ArrayList<String>();
        btnBack.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(this);

        SharedPreferences sharedPreferences= getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        Log.e("info",String.valueOf(num));
        for (int a= 0; a<num;a++)
        {
            photos.add(sharedPreferences.getString(String.valueOf(a),""));
            Log.w("info",sharedPreferences.getString(String.valueOf(a),""));
        }
       bindData();


    }

    /** 绑定数据，更新界面 */
    protected void bindData() {
        mViewPager.setAdapter(mPagerAdapter);
       // mViewPager.setCurrentItem(current);
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {

        @Override
        public int getCount() {
            if (photos == null) {
                return 0;
            } else {
                return photos.size();
            }
        }

        @Override
        public View instantiateItem(final ViewGroup container, final int position) {
            PhotoPreview photoPreview = new PhotoPreview(getApplicationContext());
            ((ViewPager) container).addView(photoPreview);
            photoPreview.loadImage(photos.get(position));
            return photoPreview;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    };
    protected boolean isUp;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back_app)
            finish();
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        current = arg0;
        //updatePercent();
    }





}

