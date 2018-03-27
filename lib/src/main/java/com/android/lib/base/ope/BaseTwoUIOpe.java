package com.android.lib.base.ope;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.bean.AppViewHolder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * ui处理操作者 处理对象 uibean fragment view
 */
public class BaseTwoUIOpe<A extends ViewDataBinding> {

    public AppViewHolder viewHolder;
    public int[] variableId;
    public A bind;
    protected Context context;
    protected  BaseUIFrag frag;




    public BaseTwoUIOpe(){

    }


    public BaseTwoUIOpe(Context context) {
        this.context = context;
        bind = initViewDataBinding();
        viewHolder = new AppViewHolder(bind);
        bind.executePendingBindings();
    }

    public BaseUIActivity getActivity(){
        if(frag!=null ){
            return frag.getBaseUIAct();
        }else{
            return (BaseUIActivity) context;
        }
    }

    public void copy(BaseTwoUIOpe baseUIOpe){
        this.context = baseUIOpe.context;
        this.frag = baseUIOpe.frag;
        this.bind = (A) baseUIOpe.bind;
        this.viewHolder = new AppViewHolder(this.bind);
        initUI();
    }


    public void initUI(){

    }


    public A initViewDataBinding() {
        A viewDataBinding = null;
        if (viewDataBinding == null) {
            if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
                Class<A> a = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                Method method = null;
                try {
                    method = a.getMethod("inflate", LayoutInflater.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    viewDataBinding = (A) method.invoke(null, LayoutInflater.from(context));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                viewDataBinding = getBind();
            }
        }
        return viewDataBinding;
    }



    public A getBind() {
        return bind;
    }

    public int[] getVariableId() {
        return variableId;
    }

    public void setVariableId(int[] variableId) {
        this.variableId = variableId;
    }

    public BaseUIFrag getFrag() {
        return frag;
    }

    public void setFrag(BaseUIFrag frag) {
        this.frag = frag;
        this.context = frag.getActivity();
        if(bind==null){
            bind = initViewDataBinding();
            viewHolder = new AppViewHolder(bind);
            bind.executePendingBindings();
        }
    }

    public void setContext(Context context) {
        this.context = context;
        if(bind==null){
            bind = initViewDataBinding();
            viewHolder = new AppViewHolder(bind);
            bind.executePendingBindings();
        }
    }
}
