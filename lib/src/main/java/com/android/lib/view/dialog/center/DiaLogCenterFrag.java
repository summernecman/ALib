package com.android.lib.view.dialog.center;

//by summer on 2017-12-18.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.R;
import com.android.lib.base.fragment.BaseUIFrag;

public class DiaLogCenterFrag extends BaseUIFrag<DialogCenterUIOpe,DialogCenterDAOpe> {

    private View v;

    View.OnClickListener onClickListener;

    private int[] ids;


    public void setV(View v,View.OnClickListener onClickListener,int... ids) {
        this.v = v;
        this.onClickListener = onClickListener;
        this.ids = ids;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup group = (ViewGroup) view.findViewById(R.id.rl_lll);
        if(v!=null){
            group.addView(v,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        for(int i=0;i<ids.length;i++){
            group.findViewById(ids[i]).setOnClickListener(this);
            group.findViewById(ids[i]).setTag(R.id.data,this);
        }

    }

    public void close(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onClick(View v) {
        if(onClickListener!=null){
            onClickListener.onClick(v);
        }
        close();
    }
}
