package com.js.oldmarket.opera;/*
* Class name :SelectPhotoUtils
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016/3/31.
*
*/

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class SelectPhotoOpera {
    /**
     * 启动相册
     * @param activity
     * @param isCamera boolean 是否启用相机，启动为true
     * @param count int 最大可选择的图片
     * @param selectPath ArrayList<String> 已经选择的图片的路径list
     * @param request_code startActivityForResult的请求码
     */
    public static void openAlbum(Activity activity, boolean isCamera, int count, ArrayList<String> selectPath, int request_code){
        Intent intent = new Intent(activity, MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, isCamera);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, count);
        if (count > 1){
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        }else {
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
        }
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        // 默认选择
        if(selectPath != null && selectPath.size()>0){
            intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, selectPath);
        }
        activity.startActivityForResult(intent, request_code);
    }
}
