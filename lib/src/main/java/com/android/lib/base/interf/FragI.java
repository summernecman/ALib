package com.android.lib.base.interf;

//by summer on 2018-01-10.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface FragI {

    public void onCreate(Bundle savedInstanceState);

    public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState);

    public void onDestroyView() ;

    public void onDestroy();
}
