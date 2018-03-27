package com.android.lib.aplication;

import android.support.multidex.MultiDexApplication;
import android.support.v4.app.FragmentActivity;

import com.android.lib.R;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.SPUtil;
import com.android.lib.util.ScreenUtil;
import com.android.lib.util.activity.ActivityUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;

import io.paperdb.Paper;
import org.xutils.x;

import java.util.List;


/**
 * 用于一些跟应用程序生命周期一致的处理
 */
public  abstract  class LibAplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
    }

    /**
     * 初始化application设置
     */
    protected void initApplication() {
        initSysConfig();
        initImagePicker();
        initXutils();
        //ZXingLibrary.initDisplayOpinion(this);
    }


    protected void initDB(){
        Paper.init(this);
    }

    /**
     * 初始化系统常量
     */
    public void initSysConfig() {
        ScreenUtil.getInstance().getScreenSize(getApplicationContext());
        ScreenUtil.getInstance().getStatusBarHeight(getApplicationContext());
        ValueConstant.DIMEN_1 = (int) getResources().getDimension(R.dimen.dimen_1);
        SPUtil.getInstance().init(this);
    }

    /**
     * 初始化图片加载了imagepicker图片选择器
     */
    public abstract  void initImagePicker();

    public void initXutils() {
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
    }

    /**
     * 退出结束所有界面
     */
    public void exit() {
        List<FragmentActivity> list = ActivityUtil.getInstance().getActList();
        for (int i = 0; i < list.size(); i++) {
            //FragmentUtil2.getInstance().initClear(list.get(i));
            FragmentUtil2.getInstance().clear();
        }
        ActivityUtil.getInstance().destoryActs();
        //System.gc();
    }

}
