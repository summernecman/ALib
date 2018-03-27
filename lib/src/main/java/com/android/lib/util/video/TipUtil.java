package com.android.lib.util.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.lib.R;

/**
 * Created by summer on 2018/2/6 21:54.
 */

public class TipUtil {

    View tipsView;

    public void showTips(Context context,ViewGroup viewGroup,String txt){
        if(viewGroup==null){
            return;
        }
        tipsView = LayoutInflater.from(context).inflate(R.layout.item_tips,null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        viewGroup.addView(tipsView,params);
        TextView textView = tipsView.findViewById(R.id.tv_txt);
        textView.setText(txt);
    }

    public void removeTips(ViewGroup viewGroup){
        if(tipsView !=null && tipsView.getParent()==viewGroup){
            viewGroup.removeView(tipsView);
        }
    }
}
