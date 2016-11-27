package com.js.oldmarket.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.js.oldmarket.R;
import com.js.oldmarket.fragment.MessageFragment;

import java.util.AbstractCollection;

/*
* Class name :MessageActivity
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
public class MessageActivity extends FragmentActivity{
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        init();
    }
    private void init(){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_framelayout, new MessageFragment());
        fragmentTransaction.commit();
    }
}
