package com.android.lib.view.dialog.list;

//by summer on 2017-12-18.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.BR;
import com.android.lib.R;
import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.databinding.FragDialogListBinding;

import java.util.List;

public class DialogListUIOpe extends BaseUIOpe<FragDialogListBinding> {


    public void init(List<String> strs, ViewListener listener){
        getBind().recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBind().recycle.setAdapter(new AppsDataBindingAdapter(getActivity(), R.layout.item_txt, BR.item_txt,strs,listener));
    }
}
