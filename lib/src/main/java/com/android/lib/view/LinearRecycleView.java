package com.android.lib.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by summer on 2018/2/3 18:24.
 */

public class LinearRecycleView extends RecyclerView{

    public LinearRecycleView(Context context) {
        super(context);
        init();
    }

    public LinearRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinearRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init(){
        setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
