package com.js.oldmarket.ui.publish;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.js.oldmarket.ActivityCollector;
import com.js.oldmarket.R;
import com.js.oldmarket.event.HttpFailEvent;
import com.js.oldmarket.event.HttpSuccessEvent;
import com.js.oldmarket.http.HttpType;
import com.js.oldmarket.opera.SelectPhotoOpera;
import com.js.oldmarket.utils.ImageUtils;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/*
* Class name :PublishActivity
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-5-24.
*
*/
public class PublishActivity extends FragmentActivity {


    @Bind(R.id.id_back_arrow_image)
    ImageView idBackArrowImage;
    @Bind(R.id.id_publish_button)
    TextView idPublishButton;
    @Bind(R.id.id_content_edit)
    EditText idContentEdit;
    @Bind(R.id.id_recy)
    RecyclerView idRecy;


    public static ArrayList<String> str_photo_path = new ArrayList<>();
    private List<Bitmap> bitmapList = new ArrayList<>();
    GridLayoutManager manager;
    PicAdapter adapter;
    private List<String> urllist = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ActivityCollector.addActivity(this);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
    }

    private void init(){
        bitmapList.add(ImageUtils.drawableToBitmap(getResources().getDrawable(R.drawable.icon_plus)));
        manager = new GridLayoutManager(this, 3);
        adapter = new PicAdapter(this, bitmapList);
        idRecy.setLayoutManager(manager);
        idRecy.setAdapter(adapter);

    }
    public void onEventMainThread(HttpSuccessEvent event) {
        if (event.getType() == HttpType.TYPE_PublishGood) {
            dialog.dismiss();
            ToastUtils.toast(this, "上传成功");
            ActivityCollector.removeActivity(this);
        }
    }

    public void onEventMainThread(HttpFailEvent event) {
        if (event.getType() == HttpType.TYPE_PublishGood) {
            dialog.dismiss();
            ToastUtils.toast(this, "发布商品失败");
        }
    }


    ProgressDialog dialog;

    @OnClick({R.id.id_back_arrow_image, R.id.id_publish_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_arrow_image:
                ActivityCollector.removeActivity(this);
                break;
            case R.id.id_publish_button:
                String content = idContentEdit.getText().toString().trim();
                dialog = new ProgressDialog(this);
                dialog.setMessage("正在发布");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                if (str_photo_path.size() > 0){
                    upload(content);
                }else {
                    publisQue(content);
                }
                break;
        }
    }
    private void publisQue(String content){
        AVObject object = new AVObject("Question");
        AVUser user = AVUser.getCurrentUser();
        object.put("user", user);
        object.put("pic", urllist);
        object.put("content", content);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null){
                    ToastUtils.toast(PublishActivity.this, "发表成功");
                    finish();
                }else {
                    ToastUtils.toast(PublishActivity.this, "发表失败，请检查网络");
                }
                dialog.dismiss();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

private void upload(final String content){
    if (bitmapList.size() != 3){
        bitmapList.remove(bitmapList.size() - 1);
    }
    for (int a = 0; a < bitmapList.size(); a++){
        final AVFile file = new AVFile("pic", ImageUtils.toBtyeArray(bitmapList.get(a)));
        file.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null){
                    urllist.add(file.getUrl());
                    LogUtils.i("TTTT", file.getUrl());
                    publisQue(content);
                }else {

                }
            }
        });
    }

}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                str_photo_path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                bitmapList.clear();
                for (int a =0; a < str_photo_path.size(); a++){
                    Bitmap bitmap = ImageUtils.filePathToBitmap(str_photo_path.get(a));
                    bitmapList.add(bitmap);
                }
                if (str_photo_path.size() != 3){
                    bitmapList.add(ImageUtils.drawableToBitmap(getResources().getDrawable(R.drawable.icon_plus)));
                }
                adapter.refresh(bitmapList);
            }
        }
    }



}
