package com.js.oldmarket.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.js.oldmarket.utils.DisplayUtil;
import com.js.oldmarket.utils.LogUtils;
import com.js.oldmarket.utils.ScreenUtils;
import com.squareup.picasso.Transformation;

/*
* Class name :GoodItemTransfor
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-5-27.
*
*/
public class GoodItemTransfor implements Transformation{
    private Context context;
    public GoodItemTransfor(Context context){
        this.context = context;
    }
    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap result;

        if (source.getWidth() > source.getHeight()){//宽大于高
            result = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight());

        }else if (source.getWidth() < source.getHeight()){
            //宽小于高
            //0，with
            int allwith = ScreenUtils.getScreenWidth(context);
            int a = DisplayUtil.dip2px(context, 10);
            int itemwith = (allwith - a) / 2;
            int itemheight = DisplayUtil.dip2px(context, 200);
            float model = itemheight/itemwith;
            Matrix matrix = new Matrix();

            float with = source.getWidth();
            float height = source.getHeight()*model;
            matrix.postScale(100, 100);
            LogUtils.i("弧", itemwith + " " +model+" "    +" " + height);
            result = Bitmap.createBitmap(source, 0, 0,100, 100,matrix, true);
        }else {
            result = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight());
        }



//        int size = Math.min(source.getWidth(), source.getHeight());
//        int x = (source.getWidth() - size) / 2;
//        int y = (source.getHeight() - size) / 2;
        LogUtils.i("弧度", source.getWidth() + " " + source.getHeight());
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override
    public String key() {
        return "square()";
    }
}
