package me.nereo.multi_image_selector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by 徐泽 on 2015/10/26.
 */
public class PhotoPreview extends LinearLayout {

    private ImageView ivContent;
    private OnClickListener l;

    public PhotoPreview(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_photopreview, this, true);

        ivContent = (ImageView) findViewById(R.id.iv_content_vpp);
        //ivContent.setOnClickListener(this);
    }

    public PhotoPreview(Context context, AttributeSet attrs, int defStyle) {
        this(context);
    }

    public PhotoPreview(Context context, AttributeSet attrs) {
        this(context);
    }



    public void loadImage(String path) {
       Bitmap bitmap = compressImageFromFile(path);
        ivContent.setImageBitmap(bitmap);
    }

    private Bitmap compressImageFromFile(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;//
        float ww = 480f;//
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置采样率

        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
//		return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
        //其实是无效的,大家尽管尝试
        return bitmap;
    }

}
