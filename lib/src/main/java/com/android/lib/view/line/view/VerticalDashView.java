package com.android.lib.view.line.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.android.lib.R;
import com.android.lib.util.LogUtil;


/**
 * Created by ${viwmox} on 2016-06-02.
 */
public class VerticalDashView extends View {

    int dashColor;

    float dashGap;

    float dashWidth;

    int bgcolor;

    float w;

    Paint paint = new Paint();


    public VerticalDashView(Context context) {
        super(context);
    }

    public VerticalDashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DashView);
        dashColor = typedArray.getColor(R.styleable.DashView_dashcolor, Color.BLACK);
        dashGap = typedArray.getDimension(R.styleable.DashView_dashgap, 10f);
        dashWidth = typedArray.getDimension(R.styleable.DashView_hashwidth, 10f);
        bgcolor = typedArray.getColor(R.styleable.DashView_dashbg, Color.WHITE);
        w = typedArray.getDimension(R.styleable.DashView_w, 10f);
        paint.setColor(dashColor);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStrokeWidth(getHeight());
        paint.setColor(dashColor);
        for (int i = 0; i < getHeight() / (dashGap + dashWidth); i++) {
            canvas.drawRect(0, 0 + (dashGap + dashWidth) * i, w, dashWidth + (dashGap + dashWidth) * i, paint);
        }
    }
}
