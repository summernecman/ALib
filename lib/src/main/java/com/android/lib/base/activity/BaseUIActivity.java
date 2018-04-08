package com.android.lib.base.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.ViewGroup;

import com.android.lib.R;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseOpes;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.android.lib.view.bottommenu.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by summer on 2016/4/16 0016 11:51.
 */
public abstract class BaseUIActivity<A extends BaseUIOpe, B extends BaseDAOpe> extends BaseActivity {

    protected ViewGroup baseUIRoot;

    protected BaseOpes<A, B> opes;

    private String moudle;

    private ArrayList<String> moudles = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getBaseUILayout());
        baseUIRoot = findViewById(R.id.act_base_root);
        if(getP().getU().getBind()!=null){
            baseUIRoot.addView(getP().getU().getBind().getRoot(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        getP().getD().initDA();
        getP().getU().initUI();
        initNow();
        ButterKnife.bind(getActivity());
        if(registerEventBus()){
            EventBus.getDefault().register(this);
        }
    }

    protected void initNow(){

    }

    protected int getBaseUILayout() {
        return R.layout.act_baseui;
    }


    public BaseOpes<A, B> getP() {
        if (opes == null) {
            opes= new BaseOpes<>(null,null);
            initaa(getClass());
            initbb(getClass());
        }
        return opes;
    }

    private void initbb(Class<?> c) {
        if (c == null) {
            opes.setDa((B)(new BaseDAOpe()));
            return;
        }
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            Class<B> b = (Class<B>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[1];
            try {
                Constructor<B> bc = b.getConstructor();
                B bb = bc.newInstance();
                opes.setDa(bb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            initbb(c.getSuperclass());
        }
    }


    private void initaa(Class<?> c) {
        if (c == null) {
            opes.setUi((A)(new BaseUIOpe<ViewDataBinding>()));
            opes.getU().init(getActivity());
            return;
        }
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            Class<A> a = (Class<A>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[0];
            try {
                Constructor<A> ac = a.getConstructor();
                A aa = ac.newInstance();
                aa.init(getActivity());
                opes.setUi(aa);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            initaa(c.getSuperclass());
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealMesage(MessageEvent event) {
        LogUtil.E(getClass().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moudles.clear();
        if(registerEventBus()){
            EventBus.getDefault().unregister(this);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected boolean registerEventBus(){
        return false;
    }

    public String getMoudle() {
        return moudle;
    }

    public void setMoudle(String moudle) {
        this.moudle = moudle;
        moudles.add(moudle);
    }

    public BaseUIActivity getActivity() {
        return this;
    }

    public ViewGroup getBaseUIRoot() {
        return baseUIRoot;
    }

    public ArrayList<String> getMoudles() {
        return moudles;
    }

}
