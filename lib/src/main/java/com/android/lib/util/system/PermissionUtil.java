package com.android.lib.util.system;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Trace;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.android.lib.util.ToastUtil;

/**
 * Created by ${viwmox} on 2017-02-16.
 */

public class PermissionUtil {

    public static final int reqcode = 12345;


    public boolean go检查权限(Activity context, String[] permissions){
        if (checkPermissionAllGranted(context,permissions)) {
            return true;
        }
        ActivityCompat.requestPermissions(context, permissions, reqcode);
        return false;
    }


    public boolean onRequestPermissionsResult(Activity activity,int requestCode, int[] grantResults) {
        if (requestCode == reqcode) {
            boolean isallgranted = true;
            for (int grnat : grantResults) {
                if (grnat != PackageManager.PERMISSION_GRANTED) {
                    isallgranted = false;
                    break;
                }
            }
            if (isallgranted) {
                return true;
            } else {
                ToastUtil.getInstance().showLong(activity, "请到应用权限管理中找到此应用并手动打开权限");
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                activity.startActivity(intent);
                activity.finish();
            }
        }
        return false;
    }

    private static  boolean checkPermissionAllGranted(Context context,String[] permissions) {
        for (String s : permissions) {
            if (ContextCompat.checkSelfPermission(context, s) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}
