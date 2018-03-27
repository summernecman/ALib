package com.android.lib.base.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * viewpager适配器的基类
 */
public class AppBasePagerAdapter extends FragmentStatePagerAdapter {

    /**
     * 上下文
     */
    protected Context context;

    protected List<Fragment> fragments;


    public AppBasePagerAdapter(FragmentManager fm, Context context,List<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
}
