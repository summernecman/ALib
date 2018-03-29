package com.siweisoft.app.app;

//by summer on 2018-03-29.

import com.android.lib.aplication.LibAplication;
import com.raizlabs.android.dbflow.config.FlowManager;

public class AppApp extends LibAplication {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }

    @Override
    public void initImagePicker() {

    }
}
