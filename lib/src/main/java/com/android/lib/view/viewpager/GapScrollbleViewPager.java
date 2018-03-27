package com.android.lib.view.viewpager;

/**
 * Created by summer on 2018/3/3 14:10.
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.android.lib.util.LogUtil;

/**
 * Created by ${viwmox} on 2016-11-24.
 */
public class GapScrollbleViewPager extends ViewPager {


    private boolean ingap = true;

    private int gapw = -1;

    public GapScrollbleViewPager(Context context) {
        super(context);
    }

    public GapScrollbleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        gapw = getWidth();
    }

    float downx = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downx =  ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.E(inGapArea());
                if (!inGapArea()) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }


    public void setGapw(int gapw) {
        this.gapw = gapw;
    }

    public  boolean inGapArea(){
        LogUtil.E(getWidth()+":"+downx+":"+gapw);
        if(getWidth()-downx>gapw&&downx>gapw){
            ingap = false;
        }else{
            ingap = true;
        }
        return ingap;
    }

    public boolean rightmove(float x){
        if(x - downx>0){
            return true;
        }
        return false;
    }

}

