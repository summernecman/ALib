package com.android.lib.util.fragment.two;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.bean.BaseBean;

import java.util.ArrayList;

/**
 * Created by summer on 2018/1/13 15:40.
 */

public class Container extends BaseBean{

    private String name;

    private int viewid = -1;

    private ArrayList<BaseUIFrag> uiUnit = new ArrayList<>();

    private Container() {
    }

    public Container(String name, int viewid) {
        this.name = name;
        this.viewid = viewid;
    }

    public String getName() {
        return name;
    }

    public int getViewid() {
        return viewid;
    }

    public ArrayList<BaseUIFrag> getUiUnit() {
        return uiUnit;
    }

    public int getUIUnitSize(){
        return getUiUnit().size();
    }

    public boolean haveLast(){
        return getUiUnit().size()>0?true:false;
    }

    public boolean haveLastBefore(){
        return getUiUnit().size()>1?true:false;
    }

    public BaseUIFrag getLast(){
        return getUiUnit().get(getUiUnit().size()-1);
    }

    public BaseUIFrag getLastBefore(){
        return getUiUnit().get(getUiUnit().size()-2);
    }

    public void addFrag(BaseUIFrag baseUIFrag){
        getUiUnit().add(baseUIFrag);
    }

    public void removeLast(){
        if(haveLast()){
            getUiUnit().remove(getUiUnit().size()-1);
        }
    }
}
