package com.android.lib.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.android.lib.util.activity.ActivityUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

// android:fitsSystemWindows="true"
//android:clipToPadding="true"

/**
 * 状态栏工具
 */
public class StatusBarUtil {

    private static StatusBarUtil instance;

    public static StatusBarUtil getInstance() {
        if (instance == null) {
            instance = new StatusBarUtil();
        }
        return instance;
    }

    /**
     * 从resid设置状态栏的颜色
     *
     * @param activity
     * @param resId
     */
    public void setStatusBarColorResId(Activity activity, int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = activity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setStatusBarTintResource(resId);
        tintManager.setStatusBarAlpha(1);
    }

    public void enableStatusBarColorResId(Activity activity) {
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        tintManager.setStatusBarAlpha(0);
    }

    /**
     * 从颜色资源设置状态栏的颜色
     *
     * @param activity
     * @param color
     */
    public void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = activity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(false);
        tintManager.setStatusBarTintColor(color);
    }

    public void hideNavigationBar(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }


//    public void setStatusBarColor(Activity activity, int color) {
//        ViewGroup decorViewGroup = (ViewGroup) activity.getWindow().getDecorView();
//        View statusBarView = new View(activity.getWindow().getContext());
//        int statusBarHeight = (int) ScreenUtil.getInstance().getStatusBarHeight(activity);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
//        params.gravity = Gravity.TOP;
//        statusBarView.setLayoutParams(params);
//        statusBarView.setBackgroundColor(color);
//        decorViewGroup.addView(statusBarView);
//    }

    public void setStatusBarUpper(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarUpperAPI21(activity);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setStatusBarUpperAPI19(activity);
        }
    }

    // 5.0版本以上
    private void setStatusBarUpperAPI21(Activity activity) {
        Window window = activity.getWindow();
        //设置透明状态栏,这样才能让 ContentView 向上
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }

    // 4.4 - 5.0版本
    private void setStatusBarUpperAPI19(Activity activity) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View statusBarView = mContentView.getChildAt(0);
        //移除假的 View
        if (statusBarView != null && statusBarView.getLayoutParams() != null &&
                statusBarView.getLayoutParams().height == getStatusBarHeight(activity)) {
            mContentView.removeView(statusBarView);
        }
        //不预留空间
        if (mContentView.getChildAt(0) != null) {
            ViewCompat.setFitsSystemWindows(mContentView.getChildAt(0), false);
        }
    }

    private int getStatusBarHeight(Activity activity){
        int result = 0;
        int resId = activity.getResources().getIdentifier("status_bar_height","dimen","android");
        if(resId>0){
            result = activity.getResources().getDimensionPixelSize(resId);
        }
        return result;
    }


    public void setStatusBarTRANSPARENT(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
