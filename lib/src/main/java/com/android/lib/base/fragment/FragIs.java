package com.android.lib.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.lib.base.interf.FragI;

import java.util.ArrayList;

/**
 * Created by summer on 2018/2/6 22:21.
 */

public class FragIs {

    private ArrayList<FragI> fragIs = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        for(int i=0;i<fragIs.size();i++){
            fragIs.get(i).onCreate(savedInstanceState);
        }
    }


    public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        for(int i=0;i<fragIs.size();i++){
            fragIs.get(i).onCreateView(inflater,container,savedInstanceState);
        }
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        for(int i=0;i<fragIs.size();i++){
            fragIs.get(i).onViewCreated(view,savedInstanceState);
        }
    }


    public void onDestroyView() {
        for(int i=0;i<fragIs.size();i++){
            fragIs.get(i).onDestroyView();
        }
    }

    public void onDestroy() {
        for(int i=0;i<fragIs.size();i++){
            fragIs.get(i).onDestroy();
        }
    }

    public ArrayList<FragI> getFragIs() {
        return fragIs;
    }
}
