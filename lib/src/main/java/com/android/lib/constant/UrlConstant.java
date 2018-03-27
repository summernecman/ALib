package com.android.lib.constant;

/**
 * 网络常量
 * Created by ${viwmox} on 2016-05-12.
 */
public class UrlConstant {

    protected static final String 奔溃日志 = "http://www.summernecro.com:8079/server/crash/sendCrash";

    protected static final String HTTP前缀 = "http://";

    protected static  String 项目名 = "/zhongxin";

    protected static String 正式域名 = "192.168.1.205:8080";

    protected static String 测试域名 = "192.168.1.205:8080";

    protected static String 域名 = 测试域名;

    protected static String 正式文件路径 = "";

    protected static String 测试文件路径 = "";

    protected static String 文件路径 = "";

    protected static boolean isOffice = false;


    public static String 获取地址(String module){
        return  HTTP前缀+域名+项目名+module;
    }


    public static String 获取文件路径(String module){
        return  HTTP前缀+域名+文件路径+module;
    }

    public static void setIsOffice(boolean isOffice) {
        UrlConstant.isOffice = isOffice;
        if(isOffice){
            域名 = 正式域名;
        }else{
            域名 = 测试域名;
        }
    }

    public static void set域名(String 域名){
        UrlConstant.域名= 域名;
    }
}
