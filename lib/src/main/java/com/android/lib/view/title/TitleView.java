package com.android.lib.view.title;

//by summer on 2017-12-21.

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lib.R;


public class TitleView extends RelativeLayout {

    private View ftitle_bar;

    private TextView midTV;

    private ImageView leftIV;

    private ImageView rightIV;

    private ImageView rightIV2;

    private String txt_mid = "";

    private int leftivid;

    private int rightivid;

    private int rightiv2id;

    private int bg;

    private ImageView midIconIV;



    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    @SuppressLint("WrongViewCast")
    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.item_title5,this,true);
        midTV = (TextView) findViewById(R.id.ftv_title);
        leftIV = (ImageView) findViewById(R.id.ftv_back);
        rightIV = (ImageView) findViewById(R.id.ftv_right);
        rightIV2 = (ImageView) findViewById(R.id.ftv_right2);
        ftitle_bar = findViewById(R.id.ftitle_bar);
        midIconIV = findViewById(R.id.ftv_midicon);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.style_common);
        txt_mid =  a.getString(R.styleable.style_common_txt_mid);
        leftivid = a.getResourceId(R.styleable.style_common_iv_left,android.R.color.transparent);
        rightivid = a.getResourceId(R.styleable.style_common_iv_right,android.R.color.transparent);
        rightiv2id = a.getResourceId(R.styleable.style_common_iv_right2,android.R.color.transparent);
        leftIV.setImageResource(leftivid);
        rightIV.setImageResource(rightivid);
        rightIV2.setImageResource(rightiv2id);
        bg = a.getColor(R.styleable.style_common_color_bg,getResources().getColor(R.color.color_base_statusbar));
        ftitle_bar.setBackgroundColor(bg);
        midTV.setText(txt_mid);

    }



    public TextView getMidTV() {
        return midTV;
    }

    public ImageView getLeftIV() {
        return leftIV;
    }

    public ImageView getRightIV() {
        return rightIV;
    }

    public ImageView getRightIV2() {
        return rightIV2;
    }

    public String getTxt_mid() {
        return txt_mid;
    }

    public void setTxt_mid(String txt_mid) {
        this.txt_mid = txt_mid;
        midTV.setText(txt_mid);
    }

    public int getLeftivid() {
        return leftivid;
    }

    public int getRightivid() {
        return rightivid;
    }

    public int getRightiv2id() {
        return rightiv2id;
    }

    public ImageView getMidIconIV() {
        return midIconIV;
    }
}
