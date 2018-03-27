package com.android.lib.view.viewpager;

//by summer on 2018-03-21.

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.BaseOnPagerChangeListener;
import com.android.lib.util.LogUtil;

import java.util.ArrayList;

public class MyViewPager extends ViewPager {

    public interface OnPageSelected{
        public void onPageSelected(int position);
    }


    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(@Nullable PagerAdapter adapter) {
        super.setAdapter(adapter);
    }

    public void init(BaseUIFrag frag, ArrayList<Fragment> fragments, final OnPageSelected onPageSelected){
        setOffscreenPageLimit(fragments.size());
        setAdapter(new AppBasePagerAdapter2(frag.getChildFragmentManager(),frag.getBaseUIAct(),fragments));
        final BaseOnPagerChangeListener baseOnPagerChangeListener = new BaseOnPagerChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                onPageSelected.onPageSelected(position);
            }
        };
        addOnPageChangeListener(baseOnPagerChangeListener);
        if(fragments.size()>0){
            post(new Runnable() {
                @Override
                public void run() {
                    baseOnPagerChangeListener.onPageSelected(0);
                }
            });
        }
    }
}
