package com.android.lib.base.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.android.lib.aplication.LibAplication;
import com.android.lib.util.LogUtil;
import com.android.lib.util.activity.ActivityUtil;


/**
 * 所有activity的基类
 * 封装一些常用的activity方法
 * Created by summer on 2016/4/15 0015 16:26.
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getApplication() instanceof LibAplication) {
            ActivityUtil.getInstance().getActMap().put(getClass().getSimpleName(), getActivity());
            ActivityUtil.getInstance().getActList().add(getActivity());
        } else {
            LogUtil.E("你的application最好继承LibAplication以便享有方便的方法");
        }
    }

    public FragmentActivity getActivity() {
        return this;
    }
}
