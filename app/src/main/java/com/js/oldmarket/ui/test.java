package com.js.oldmarket.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.js.oldmarket.R;
import com.js.oldmarket.view.PullUpMoreListView;

import java.util.ArrayList;
import java.util.List;

/*
* Class name :test
*
* Version information :
*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-20.
*
*/
public class test extends Activity{
    private PullUpMoreListView listView;
    private ArrayAdapter<String> adapter;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.test);



    }


}
