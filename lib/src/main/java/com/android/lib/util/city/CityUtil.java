package com.android.lib.util.city;

import android.content.Context;

import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ${viwmox} on 2016-08-02.
 */
public class CityUtil {
    private static CityUtil instance;

    public static CityUtil getInstance() {
        if (instance == null) {
            instance = new CityUtil();
        }
        return instance;
    }

    private String cityStr = "";

    public String getCityJson(Context context) {
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = context.getResources().getAssets().open("city.json");
            StringBuffer stringBuffer = new StringBuffer();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] chars = new char[1024];
            int length = bufferedReader.read(chars);
            stringBuffer.append(chars, 0, length);
            while (length != -1) {
                length = bufferedReader.read(chars);
                LogUtil.E(length);
                if (length != -1) {
                    stringBuffer.append(chars, 0, length);
                }
            }
            cityStr = stringBuffer.toString();
            return cityStr;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public CityList getCityList(Context context){
        if(NullUtil.isStrEmpty(cityStr)){
            getCityJson(context);
        }
        CityList cityList = GsonUtil.getInstance().fromJson(cityStr,CityList.class);
        return cityList;
    }

}
