package com.android.lib.network.news;

import android.content.Context;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.*;
import com.android.lib.view.bottommenu.MessageEvent;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public  class NetAdapter<A> implements NetI<A> {

    public static boolean cache = false;
    protected Context context;
    protected String url;

    protected boolean showtoast = false;

    protected BaseUIFrag baseUIFrag;

    public NetAdapter(Context context) {
        this.context = context;
    }


    public NetAdapter(BaseUIFrag baseUIFrag) {
        this.baseUIFrag = baseUIFrag;
        this.context = baseUIFrag.getActivity();
    }

    public NetAdapter(BaseUIFrag baseUIFrag,boolean isshow) {
        this.baseUIFrag = baseUIFrag;
        this.context = baseUIFrag.getActivity();
        showtoast = isshow;
    }

    public NetAdapter(Context context,boolean isshow) {
        this.context = context;
        showtoast = isshow;
    }


    @Override
    public boolean onNetStart(String url, String gson) {
        this.url = url;
        boolean isNetOk = NetWorkUtil.getInstance().getNetisAvailable(context);
        if(NetGet.test){
            return true;
        }
        if(!isNetOk){
            ToastUtil.getInstance().showShort(context,"无网络");
        }
        return isNetOk;
    }

    @Override
    public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
        if (!haveData) {
            if(cache){
                onResult(true,baseResBean.getErrorMessage(), null);
            }else{
                onResult(false,baseResBean.getErrorMessage(), null);
                if(!NullUtil.isStrEmpty(baseResBean.getMessage())&& showtoast){
                    ToastUtil.getInstance().showShort(context.getApplicationContext(),StringUtil.getStr(baseResBean.getMessage())+StringUtil.getStr(baseResBean.getErrorMessage()));
                }
            }
        } else {
            if(cache){
                if(showtoast){
                    ToastUtil.getInstance().showShort(context,"当前为无网络测试环境");
                }
                BaseResBean resBean = GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(url),BaseResBean.class);
                if(resBean ==null){
                    resBean = new BaseResBean();
                    resBean.setCode("000");
                }
                deal(haveData,url,resBean);
            }else{
                if(!NullUtil.isStrEmpty(baseResBean.getMessage())&& showtoast){
                    ToastUtil.getInstance().showShort(context.getApplicationContext(),StringUtil.getStr(baseResBean.getMessage())+StringUtil.getStr(baseResBean.getErrorMessage()));
                }
                SPUtil.getInstance().saveStr(url,GsonUtil.getInstance().toJson(baseResBean));
                deal(haveData,url,baseResBean);
            }

        }
    }


    private void deal(boolean haveData, String url, BaseResBean baseResBean){
        boolean isobject = false;
        boolean isarray = false;
        Type type = getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType ){
            ParameterizedType parameterizedType = (ParameterizedType) type;
            A aa = null;
            try {
                Object o = new JSONTokener(GsonUtil.getInstance().toJson(baseResBean.getResult())).nextValue();
                try {
                    isobject = o instanceof JSONObject;
                    if(!isobject){
                        try {
                            isarray = o instanceof JSONArray;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(isobject){
                    Class<A> a = (Class<A>) parameterizedType.getActualTypeArguments()[0];
                     aa = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(baseResBean.getResult()),a);
                }else if(isarray){
                    TypeToken<?> typeToken = TypeToken.get(parameterizedType.getActualTypeArguments()[0]);
                     aa = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(baseResBean.getResult()),typeToken.getType());
                }else{
                    LogUtil.E(baseResBean.getResult());
                }
            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                if (!"200".equals(baseResBean.getCode())) {
                    onResult(false,StringUtil.getStr(baseResBean.getMessage())+StringUtil.getStr(baseResBean.getErrorMessage()), aa);
                } else {
                    onResult(true,StringUtil.getStr(baseResBean.getMessage())+StringUtil.getStr(baseResBean.getErrorMessage()), aa);
                }
            }
        }

    }

    @Override
    public void onResult(boolean success, String msg, A o) {
        if(msg!=null&&msg.toLowerCase().contains("Unauthorized".toLowerCase())){
            MessageEvent messageEvent = new MessageEvent();
            messageEvent.sender = "net";
            messageEvent.dealer = "main";
            EventBus.getDefault().post(messageEvent);
        }
        if(success){
            onSuccess(o);
        }else{
            onFail(o==null,msg);
        }
    }

    @Override
    public void onProgress(long total, long current) {

    }

    @Override
    public void onSuccess(A o) {

    }

    @Override
    public void onFail(boolean haveData, String msg) {

    }



}