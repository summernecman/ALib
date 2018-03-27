package com.android.lib.network.news;

import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.res.BaseResBean;

public interface NetI<A> {


    boolean onNetStart(String url, String gson);

    void onNetFinish(boolean haveData, String url, BaseResBean baseResBean);

    void onResult(boolean success, String msg, A o);

    void onProgress(long total, long current);

    void onSuccess(A o);

    void onFail(boolean haveData,String msg);


}