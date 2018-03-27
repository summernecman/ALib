package com.android.lib.view.line.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.android.lib.R;


/**
 * Created by ${viwmox} on 2016-06-02.
 */
public class HorizonDashView extends View {

    int dashColor;

    float dashGap;

    float dashWidth;

    int bgcolor;

    Paint paint = new Paint();


    public HorizonDashView(Context context) {
        super(context);
    }

    public HorizonDashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DashView);
        dashColor = typedArray.getColor(R.styleable.DashView_dashcolor, Color.BLACK);
        dashGap = typedArray.getDimension(R.styleable.DashView_dashgap, 10f);
        dashWidth = typedArray.getDimension(R.styleable.DashView_hashwidth, 10f);
        bgcolor = typedArray.getResourceId(R.styleable.DashView_dashbg, Color.WHITE);
        paint.setColor(dashColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStrokeWidth(getHeight());
        paint.setColor(dashColor);
        paint.setStrokeWidth(dashGap);
        for (int i = 0; i < getWidth() / (dashGap + dashWidth); i++) {
            canvas.drawRect((dashGap + dashWidth) * i,0,dashWidth + (dashGap + dashWidth) * i,getHeight(),paint);
        }
    }
}
