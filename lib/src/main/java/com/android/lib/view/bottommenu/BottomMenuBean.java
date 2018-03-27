package com.android.lib.view.bottommenu;

import android.content.res.ColorStateList;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;

public class BottomMenuBean {


    private String name;

    private int drawbleId;

    private BaseUIFrag fragment;

    private View containerView;

    private ColorStateList txtColor;

    private int bgcolor;

    public BottomMenuBean(String name, int drawbleId) {
        this.name = name;
        this.drawbleId = drawbleId;
    }

    public BottomMenuBean(String name, int drawbleId, BaseUIFrag fragment, View containerView) {
        this.name = name;
        this.drawbleId = drawbleId;
        this.fragment = fragment;
        this.containerView = containerView;
    }

    public BottomMenuBean(String name, int drawbleId, BaseUIFrag fragment, View containerView, ColorStateList txtColor) {
        this.name = name;
        this.drawbleId = drawbleId;
        this.fragment = fragment;
        this.containerView = containerView;
        this.txtColor = txtColor;
    }


    public BottomMenuBean(String name, int drawbleId, BaseUIFrag fragment, View containerView, ColorStateList txtColor,int bgcolor) {
        this.name = name;
        this.drawbleId = drawbleId;
        this.fragment = fragment;
        this.containerView = containerView;
        this.txtColor = txtColor;
        this.bgcolor = bgcolor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawbleId() {
        return drawbleId;
    }

    public void setDrawbleId(int drawbleId) {
        this.drawbleId = drawbleId;
    }

    public BaseUIFrag getFragment() {
        return fragment;
    }

    public void setFragment(BaseUIFrag fragment) {
        this.fragment = fragment;
    }

    public View getContainerView() {
        return containerView;
    }

    public void setContainerView(View containerView) {
        this.containerView = containerView;
    }

    public ColorStateList getTxtColor() {
        return txtColor;
    }

    public void setTxtColor(ColorStateList txtColor) {
        this.txtColor = txtColor;
    }

    public int getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(int bgcolor) {
        this.bgcolor = bgcolor;
    }
}