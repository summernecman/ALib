package com.android.lib.view.item;

//by summer on 2017-12-15.

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lib.R;
import com.android.lib.R2;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;

import butterknife.BindView;



/*   <com.android.lib.view.item.ItemView
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           style_item:txt_left="123"
           style_item:txt_right="5"
           style_item:iv_left="@drawable/icon_arraw"
           style_item:iv_right="@drawable/icon_arraw"
           style_item:txt_right_bg="@color/white"
           style_item:bg_divider="@color/color_item_content_gap"
           />*/


public class ItemView extends RelativeLayout{

    ImageView leftIV;

    TextView leftTV;

    TextView rightTV;

    ImageView rightIV;

    View divider;

    private String textleftStr;

    private String txtrightStr;


    private int ivleftInt;

    private int ivrightInt;

    private int txtrightBg;

    private int bgDivider;

    private int color_txt_right;

    private TextView midTV;



    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(getContext()).inflate(R.layout.item_content,this,true);
        leftIV = (ImageView) findViewById(R.id.iv_left_icon);
        leftTV = (TextView) findViewById(R.id.tv_left_content);
        rightTV = (TextView) findViewById(R.id.iv_right_content);
        rightIV = (ImageView) findViewById(R.id.iv_right_icon);
        midTV = (TextView) findViewById(R.id.tv_mid);
        divider = findViewById(R.id.divide);
        TypedArray t = context.obtainStyledAttributes(attrs,R.styleable.style_common);
        textleftStr= t.getString(R.styleable.style_common_txt_left);
        txtrightStr= t.getString(R.styleable.style_common_txt_right);
        ivleftInt= t.getResourceId(R.styleable.style_common_iv_left,R.color.white);
        ivrightInt= t.getResourceId(R.styleable.style_common_iv_right,R.color.white);
        txtrightBg= t.getResourceId(R.styleable.style_common_txt_right_bg,R.color.white);
        bgDivider= t.getResourceId(R.styleable.style_common_bg_divider,R.color.color_item_content_gap);
        color_txt_right = t.getColor(R.styleable.style_common_color_txt_right,getResources().getColor(R.color.white));

        t.recycle();
        leftTV.setText(StringUtil.getStr(textleftStr));
        leftIV.setImageResource(ivleftInt);
        rightTV.setText(StringUtil.getStr(txtrightStr));
        rightTV.setTextColor(color_txt_right);
        rightIV.setImageResource(ivrightInt);
        rightTV.setBackgroundResource(txtrightBg);
       // divider.setBackgroundColor(bgDivider);
    }

    public TextView getRightTV() {
        return rightTV;
    }

    public TextView getLeftTV() {
        return leftTV;
    }

    public TextView getMidTV() {
        return midTV;
    }

    public void setRightTxt(int o){
        if(o==0){
            getRightTV().setVisibility(View.GONE);
        }else{
            getRightTV().setVisibility(View.VISIBLE);
            getRightTV().setText(StringUtil.getStr(o));
        }

    }

    public void setRightTxt(String o){
        if(NullUtil.isStrEmpty(o)){
            getRightTV().setVisibility(View.GONE);
        }else{
            getRightTV().setVisibility(View.VISIBLE);
            getRightTV().setText(o);
        }

    }

}

