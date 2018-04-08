package com.siweisoft.app.second;

//by summer on 2018-04-08.

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.databinding.LayoutBaseuiBinding;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.app.R;
import com.siweisoft.app.main.MainAct;

import butterknife.OnClick;

public class MainFrag extends BaseUIFrag<SecUIOpe,SecDAOpe> {

    @Override
    public void initNow() {
        super.initNow();
        LogUtil.E(System.currentTimeMillis()- MainAct.time);
    }

    @OnClick({R.id.container})
    public void onClick(View v) {
        super.onClick(v);
        FragManager2.getInstance().finish(getBaseUIAct(),"main",false);
    }

    @Override
    public int getBaseUILayout() {
        return R.layout.frag_sec;
    }



    @Override
    protected int delayTime() {
        return 400;
    }
}
