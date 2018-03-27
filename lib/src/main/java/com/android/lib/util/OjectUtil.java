package com.android.lib.util;

import java.util.TreeMap;

/**
 * Created by summer on 2018/2/6 22:35.
 */

public class OjectUtil {


    public static boolean equals(String str1,String str2){
        if(str1==null && str2==null){
            return true;
        }
        if(str1==null && str2!=null){
            return false;
        }
        if(str1!=null && str2==null){
            return false;
        }
        if(str1.equals(str2)){
            return true;
        }
        return false;
    }
    public static boolean equals(int int1,int int2){
        if(int1 == int2){
            return true;
        }
        return false;
    }
}
