package com.android.lib.network.newsf;

import android.content.Context;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.NetGet;
import com.android.lib.network.news.NetI;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.NetWorkUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.SPUtil;
import com.android.lib.util.ToastUtil;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public  class NetFAdapter<A> implements NetI<A> {

    public static boolean cache = true;
    protected Context context;
    protected String url;
    protected BaseUIFrag frag;

    public NetFAdapter(BaseUIFrag frag) {
        this.context = frag.getContext();
        this.frag = frag;
        Type type = this.getClass().getGenericSuperclass();
        getClass().getSimpleName();
    }


    @Override
    public boolean onNetStart(String url, String gson) {
        this.url = url;
        boolean isNetOk = NetWorkUtil.getInstance().getNetisAvailable(context);
        if(NetGet.test){
            return true;
        }
        return isNetOk;
    }

    @Override
    public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
        if (!haveData) {
            if(cache){
                onResult(true,baseResBean.getMessage(), null);
            }else{
                onResult(false,baseResBean.getMessage(), null);
            }
        } else {
            if(cache){
                ToastUtil.getInstance().showShort(context,"当前为无网络测试环境");
                BaseResBean resBean = GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(url),BaseResBean.class);
                if(resBean ==null){
                    resBean = new BaseResBean();
                    resBean.setCode("000");
                }
                deal(haveData,url,resBean);
            }else{
                if(!NullUtil.isStrEmpty(baseResBean.getMessage())){
                    ToastUtil.getInstance().showShort(context,baseResBean.getMessage());
                }
                SPUtil.getInstance().saveStr(url,GsonUtil.getInstance().toJson(baseResBean));
                deal(haveData,url,baseResBean);
            }

        }
    }

    private void deal(boolean haveData, String url, BaseResBean baseResBean){
        Type type = getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType ){
            ParameterizedType parameterizedType = (ParameterizedType) type;
            A aa = null;
            try {
                Object o = new JSONTokener(GsonUtil.getInstance().toJson(baseResBean.getResult())).nextValue();
                if(o instanceof JSONObject){
                    Class<A> a = (Class<A>) parameterizedType.getActualTypeArguments()[0];
                     aa = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(baseResBean.getResult()),a);
                }else if(o instanceof JSONArray){
                    TypeToken<?> typeToken = TypeToken.get(parameterizedType.getActualTypeArguments()[0]);
                     aa = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(baseResBean.getResult()),typeToken.getType());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                if (!"200".equals(baseResBean.getCode())) {
                    onResult(false,baseResBean.getMessage(), aa);
                } else {
                    onResult(true,baseResBean.getMessage(), aa);
                }
            }
        }

    }

    @Override
    public void onResult(boolean success, String msg, A o) {

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