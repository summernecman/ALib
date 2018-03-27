package com.android.lib.view.pop;

//by summer on 2017-12-18.

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.lib.BR;
import com.android.lib.R;
import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.util.menu.popup.PopupUtil;
import com.android.lib.view.ItemDecoration.MyItemDecoration;

import java.util.List;

public class PopView extends RelativeLayout {

    private RecyclerView recyclerView;

    public PopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PopView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs){
        View view1 = LayoutInflater.from(context).inflate(R.layout.pup_list,this,true);
        recyclerView= (RecyclerView) view1.findViewById(R.id.rcv_pop);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    public void init(Activity activity,View view, List strings){
        recyclerView.setAdapter(new AppsDataBindingAdapter(activity,R.layout.item_txt, BR.item_txt,strings));
    }
}
