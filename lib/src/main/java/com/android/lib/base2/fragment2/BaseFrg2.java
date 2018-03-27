package com.android.lib.base2.fragment2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.activity.BaseActivity;

/**
 * fragment布局基类
 */
public class BaseFrg2 extends Fragment {

    private BaseActivity activity;

    private BaseFrg2 frag;

    private long uniqueid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uniqueid = System.currentTimeMillis();
        frag = this;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            activity = (BaseActivity) context;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public long getUniqueid() {
        return uniqueid;
    }


    public BaseActivity getBaseAct(){
        return activity;
    }

    public BaseFrg2 getFrag() {
        return frag;
    }
}
