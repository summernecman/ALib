package com.android.lib.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import com.android.lib.base.activity.BaseActivity;
import com.android.lib.util.LogUtil;

/**
 * fragment布局基类
 */
public class BaseFrg extends Fragment {

    private BaseActivity activity;

    private  BaseFrg frag;

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

    public BaseFrg getFrag() {
        return frag;
    }
}
