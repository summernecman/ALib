package com.android.lib.network.newsf;

import android.content.Context;

import android.view.View;
import com.android.lib.R;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.util.LoadUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public abstract class UIFNetAdapter<A> extends NetFAdapter<A> {

    private boolean isload = false;

    public UIFNetAdapter(BaseUIFrag frag) {
        super(frag);
    }

    public UIFNetAdapter(BaseUIFrag frag,boolean isload) {
        super(frag);
        this.isload = isload;
    }

    @Override
    public boolean onNetStart(String url, String gson) {
        if(isload){
            frag.startLoading();
        }
        return super.onNetStart(url, gson);
    }

    @Override
    public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
        super.onNetFinish(haveData, url, baseResBean);
    }

    @Override
    public void onResult(boolean success, String msg, A o) {
        stopRefreshORLoadMore();
        super.onResult(success, msg, o);
    }

    public void stopRefreshORLoadMore(){
        if(frag!=null&&frag.getView()!=null){
            View v = frag.getView().findViewById(R.id.refresh);
            if(v!=null&&v instanceof SmartRefreshLayout){
                SmartRefreshLayout refreshLayout = (SmartRefreshLayout) v;
                refreshLayout.finishLoadmore();
                refreshLayout.finishRefresh();
            }
            frag.stopLoading();
        }
    }
}