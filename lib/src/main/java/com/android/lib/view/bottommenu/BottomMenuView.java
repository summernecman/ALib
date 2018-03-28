package com.android.lib.view.bottommenu;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.R;
import com.android.lib.base.interf.view.OnAppItemLongClickListener;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.base.listener.BaseOnPagerChangeListener;
import com.android.lib.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-10-18.
 */
public class BottomMenuView extends LinearLayout implements View.OnClickListener ,View.OnLongClickListener{

    ArrayList<View> tabViews = new ArrayList<>();
    OnAppItemSelectListener onAppItemClickListener;
    OnAppItemLongClickListener onAppItemLongClickListener;
    ViewPager viewPager;
    ViewGroup viewGroup;
    private Context context;
    private int index = 0;
    long time = 0;
    int id = R.id.ll_bed;

    public BottomMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bottommenus, null);
        addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void initItems(ArrayList<BottomMenuBean> been) {
        viewGroup = (ViewGroup) findViewById(R.id.ll_container);
        viewGroup.removeAllViews();
        for (int i = 0; i < been.size(); i++) {
            ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.item_bottommenu, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
            TextView textView = (TextView) view.findViewById(R.id.tv_name);
            imageView.setBackgroundResource(been.get(i).getDrawbleId());
            textView.setText(been.get(i).getName());
            textView.setTextColor(been.get(i).getTxtColor());
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            viewGroup.addView(view, params);
            tabViews.add(view.getChildAt(0));
            view.getChildAt(0).setOnClickListener(this);
            view.getChildAt(0).setOnLongClickListener(this);
            view.getChildAt(0).setTag(R.id.position, i);
        }

    }

    public void setIndex(int index){
        onClick(((ViewGroup)viewGroup.getChildAt(index)).getChildAt(0));
    }

    @Override
    public void onClick(View v) {
        long t = System.currentTimeMillis();
        long d = t - time;
        boolean b = ((Integer)v.getTag(R.id.position) == id);
        id = (int) v.getTag(R.id.position);
        time = t;
        if (d < 500 && b) {
            for (int i = 0; i < tabViews.size(); i++) {
                if (v.getTag(R.id.position) == tabViews.get(i).getTag(R.id.position)) {
                    tabViews.get(i).setSelected(true);
                    if(onAppItemLongClickListener!=null){
                        onAppItemLongClickListener.onAppItemLongClick(v, i);
                    }
                }else{
                    tabViews.get(i).setSelected(false);
                }
            }
        } else {
            for (int i = 0; i < tabViews.size(); i++) {
                if (v.getTag(R.id.position) == tabViews.get(i).getTag(R.id.position)) {
                    tabViews.get(i).setSelected(true);
                    index = i;
                    if(onAppItemClickListener!=null){
                        onAppItemClickListener.onAppItemSelect(this, v, i);
                    }
                } else {
                    tabViews.get(i).setSelected(false);
                }
            }
        }
    }

    public void setOnAppItemClickListener(OnAppItemSelectListener onAppItemClickListener) {
        this.onAppItemClickListener = onAppItemClickListener;
    }

    public BaseOnPagerChangeListener setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        BaseOnPagerChangeListener onPageChangeListener = null;
        if (viewPager != null) {
             onPageChangeListener = new BaseOnPagerChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    if (viewGroup == null) {
                        return;
                    }
                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                        if (i == position) {
                            index = position;
                            viewGroup.getChildAt(position).setSelected(true);
                            if(onAppItemClickListener!=null){
                                onAppItemClickListener.onAppItemSelect(viewGroup, viewGroup.getChildAt(i), position);
                            }
                        } else {
                            viewGroup.getChildAt(i).setSelected(false);
                        }
                    }
                }
            };
            viewPager.addOnPageChangeListener(onPageChangeListener);
        }
        return onPageChangeListener;
    }

    public ViewGroup getViewGroup() {
        return viewGroup;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean onLongClick(View v) {
        if(onAppItemLongClickListener!=null){
            onAppItemLongClickListener.onAppItemLongClick(v, (Integer) v.getTag(R.id.position));
        }
        return true;
    }

    public void setOnAppItemLongClickListener(OnAppItemLongClickListener onAppItemLongClickListener) {
        this.onAppItemLongClickListener = onAppItemLongClickListener;
    }

    public void setSelect(int pos){
        LogUtil.E(pos);
        for(int i=0;tabViews!=null&&i<tabViews.size();i++){
            LogUtil.E(i);
            if(i==pos){
                tabViews.get(i).setSelected(true);
            }else{
                tabViews.get(i).setSelected(false);
            }
        }
    }

    public ArrayList<View> getTabViews() {
        return tabViews;
    }
}
