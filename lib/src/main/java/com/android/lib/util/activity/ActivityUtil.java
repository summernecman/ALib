package com.android.lib.util.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.android.lib.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * activity 管理
 * Created by ${viwmox} on 2016-05-12.
 */
public class ActivityUtil {

    private static ActivityUtil instance;

    /**
     * 键值对存储activity
     */
    private HashMap<String, FragmentActivity> actMap = new HashMap<>();

    /**
     * 列表存储activity
     */
    private ArrayList<FragmentActivity> actList = new ArrayList<>();

    public static ActivityUtil getInstance() {
        if (instance == null) {
            instance = new ActivityUtil();
        }
        return instance;
    }

    public HashMap<String, FragmentActivity> getActMap() {
        return actMap;
    }

    public ArrayList<FragmentActivity> getActList() {
        return actList;
    }

    public void removeAct(Activity activity) {
        actMap.remove(activity.getClass().getSimpleName());
        for (int i = 0; i < actList.size(); i++) {
            actList.remove(activity);
        }
        activity.finish();
    }

    /**
     * 销毁所有的activity
     */
    public void destoryActs() {
        for (int i = 0; i < actList.size(); i++) {
            actList.get(i).finish();
        }
        actList.clear();
        actMap.clear();
    }

    public static void 被后台清理了就重启(Activity nowact, Bundle savedInstanceState, Class<? extends BaseActivity> toact){
        if(savedInstanceState!=null){
            nowact.startActivity(new Intent(nowact,toact));
            nowact.finish();
        }
    }
}
