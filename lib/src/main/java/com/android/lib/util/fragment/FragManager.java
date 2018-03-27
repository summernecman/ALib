package com.android.lib.util.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.android.lib.R;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ${viwmox} on 2016-11-10.
 */
public class FragManager {


    private static FragManager instance;

    private int index = 0;

    private HashMap<Integer, ArrayList<Fragment>> fragMaps = new HashMap<>();

    private ArrayList<Integer> viewIds = new ArrayList<>();


    public static FragManager getInstance() {
        if (instance == null) {
            instance = new FragManager();

        }
        return instance;
    }

    public void initIds(ArrayList<Integer> ints) {
        LogUtil.E(viewIds.size() + "--" + fragMaps);
        viewIds.clear();
        for (int i = 0; i < ints.size(); i++) {
            viewIds.add(ints.get(i));
        }

        fragMaps.clear();
        for (int i = 0; i < viewIds.size(); i++) {
            fragMaps.put(i, new ArrayList<Fragment>());
        }
    }


    public int addId(int id){
        viewIds.add(id);
        fragMaps.put(fragMaps.keySet().size(),new ArrayList<Fragment>());
        return fragMaps.keySet().size()-1;
    }

    public void clear() {
        viewIds.clear();
        fragMaps.clear();
    }


    public void clearAll(FragmentManager manager) {
        for(int i=0;i<fragMaps.size();i++){
            clearAll(manager,i);
        }
        viewIds.clear();
        fragMaps.clear();
    }

    public void finish(FragmentManager manager, int index) {
        if (fragMaps.get(index) != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            Bundle b = null;
            if (fragMaps.get(index).size() > 0) {
                Fragment fragment = fragMaps.get(index).get(fragMaps.get(index).size() - 1);
                b = fragment.getArguments();
                transaction.remove(fragment);
                fragMaps.get(index).remove(fragMaps.get(index).size() - 1);
                fragment = null;
                if ((fragMaps.get(index).size() - 1) >= 0) {
                    if (fragMaps.get(index).get(fragMaps.get(index).size() - 1) instanceof BaseUIFrag) {
                        BaseUIFrag fragment2 = (BaseUIFrag) fragMaps.get(index).get(fragMaps.get(index).size() - 1);
                        transaction.show(fragment2);
                        if (fragment2.getArguments() != null && fragment2.getArguments().getInt(ValueConstant.FARG_REQ) != 0) {
                            fragment2.onResult(fragment2.getArguments().getInt(ValueConstant.FARG_REQ), b);
                        }
                        fragment2.onResult(fragment2.getArguments().getInt(ValueConstant.FARG_REQ),b);
                    }
                }
                transaction.commitAllowingStateLoss();
            }
        }
    }

    public void finish(FragmentManager manager, int index, Bundle bundle) {
        Bundle b = null;
        if (fragMaps.get(index) != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            Fragment fragment = fragMaps.get(index).get(fragMaps.get(index).size() - 1);
            b = fragment.getArguments();
            if (fragment != null) {
                transaction.remove(fragment);
                fragMaps.get(index).remove(fragMaps.get(index).size() - 1);
                fragment = null;
                if ((fragMaps.get(index).size() - 1) >= 0) {
                    if (fragMaps.get(index).get(fragMaps.get(index).size() - 1) instanceof BaseUIFrag) {
                        BaseUIFrag fragment2 = (BaseUIFrag) fragMaps.get(index).get(fragMaps.get(index).size() - 1);
                        transaction.show(fragment2);
                        if (fragment2.getArguments() != null) {
                            fragment2.getArguments().putAll(bundle);
                        }
                        fragment2.onResult(fragment2.getArguments().getInt(ValueConstant.FARG_REQ),b);
                    }

                }
                transaction.commitAllowingStateLoss();
            }
        }
    }

    public void finish(Activity activity) {
        fragMaps.clear();
    }

    public void startFragment(FragmentManager manager, int index, Fragment fragment) {
        this.index = index;
        if (fragMaps.get(index) == null) {
            fragMaps.put(index, new ArrayList<Fragment>());
        }
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragment != null) {
            if (fragMaps.get(index).size() - 1 >= 0) {
                transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out, R.anim.anim_push_right_in, R.anim.anim_push_left_out);
                transaction.hide(fragMaps.get(index).get(fragMaps.get(index).size() - 1));
            }
            Bundle bundle = fragment.getArguments();
            if (bundle == null) {
                fragment.setArguments(new Bundle());
            }
            fragment.getArguments().putInt(ValueConstant.FRAG_POSITION, index);

            transaction.add(viewIds.get(index), fragment, fragment.getClass().getSimpleName());
            fragMaps.get(index).add(fragment);
        }
        transaction.commitAllowingStateLoss();
    }

    public void startFragment(FragmentManager manager, BaseUIFrag fragment) {
        if (fragMaps.get(index) == null) {
            fragMaps.put(index, new ArrayList<Fragment>());
        }
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragment != null) {
            if (fragMaps.get(index).size() - 1 >= 0) {
                transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out, R.anim.anim_push_right_in, R.anim.anim_push_left_out);
                transaction.hide(fragMaps.get(index).get(fragMaps.get(index).size() - 1));
            }
            Bundle bundle = fragment.getArguments();
            if (bundle == null) {
                fragment.setArguments(new Bundle());
            }
            fragment.getArguments().putInt(ValueConstant.FRAG_POSITION, index);

            transaction.add(viewIds.get(index), fragment, fragment.getClass().getSimpleName());
            fragMaps.get(index).add(fragment);
        }
        transaction.commitAllowingStateLoss();
    }


    public void startFragment(FragmentManager manager, int index, Fragment fragment, Bundle bundle) {
        this.index = index;
        if (fragMaps.get(index) == null) {
            fragMaps.put(index, new ArrayList<Fragment>());
        }
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragment != null) {
            if (fragMaps.get(index).size() - 1 >= 0) {
                transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out, R.anim.anim_push_right_in, R.anim.anim_push_left_out);
                transaction.hide(fragMaps.get(index).get(fragMaps.get(index).size() - 1));
            }
            Bundle b = fragment.getArguments();
            if (b == null) {
                fragment.setArguments(new Bundle());
            }
            fragment.getArguments().putInt(ValueConstant.FRAG_POSITION, index);
            if (bundle != null) {
                fragment.getArguments().putAll(bundle);
            }
            transaction.add(viewIds.get(index), fragment, fragment.getClass().getSimpleName());
            fragMaps.get(index).add(fragment);
        }
        transaction.commitAllowingStateLoss();
    }

    @SuppressLint("Range")
    public void startFragment(FragmentManager manager, int index, Fragment fragment, Bundle bundle, View view, int id) {
        this.index = index;
        if (fragMaps.get(index) == null) {
            fragMaps.put(index, new ArrayList<Fragment>());
        }
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragment != null) {
            if (fragMaps.get(index).size() - 1 >= 0) {
                transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out, R.anim.anim_push_right_in, R.anim.anim_push_left_out);
                transaction.hide(fragMaps.get(index).get(fragMaps.get(index).size() - 1));
            }
            Bundle b = fragment.getArguments();
            if (b == null) {
                fragment.setArguments(new Bundle());
            }
            fragment.getArguments().putInt(ValueConstant.FRAG_POSITION, index);
            if (bundle != null) {
                int[] ints = new int[3];
                ints[3] = id;
                view.getLocationInWindow(ints);
                bundle.putIntArray(ValueConstant.DATA_INTENT3, ints);
                fragment.getArguments().putAll(bundle);
            }
            transaction.add(viewIds.get(index), fragment, fragment.getClass().getSimpleName());
            fragMaps.get(index).add(fragment);
        }
        transaction.commitAllowingStateLoss();
    }


    public void startFragmentForResult(FragmentManager manager, int index, Fragment fragment, Bundle bundle, int req) {
        this.index = index;
        if (fragMaps.get(index) == null) {
            fragMaps.put(index, new ArrayList<Fragment>());
        }
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragment != null) {
            if (fragMaps.get(index).size() - 1 >= 0) {
                transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out, R.anim.anim_push_right_in, R.anim.anim_push_left_out);
                transaction.hide(fragMaps.get(index).get(fragMaps.get(index).size() - 1));
                if (fragMaps.get(index).get(fragMaps.get(index).size() - 1).getArguments() == null) {
                    fragMaps.get(index).get(fragMaps.get(index).size() - 1).setArguments(new Bundle());
                }
                fragMaps.get(index).get(fragMaps.get(index).size() - 1).getArguments().putInt(ValueConstant.FARG_REQ, req);
            }
            Bundle b = fragment.getArguments();
            if (b == null) {
                fragment.setArguments(new Bundle());
            }
            fragment.getArguments().putInt(ValueConstant.FRAG_POSITION, index);
            if (bundle != null) {
                fragment.getArguments().putAll(bundle);
            }
            transaction.add(viewIds.get(index), fragment, fragment.getClass().getSimpleName());
            fragMaps.get(index).add(fragment);
        }
        transaction.commitAllowingStateLoss();
    }


    public void clearTop(FragmentManager manager, int positon) {
        this.index = positon;
        ArrayList<Fragment> fragments = fragMaps.get(positon);
        if (fragments.size() > 1) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            for (int j = fragments.size() - 1; j > 0; j--) {
                transaction.remove(fragments.get(j));
                fragments.remove(j);
                if (fragments.get(j - 1) != null) {
                    transaction.show(fragments.get(j - 1));
                }
            }
            transaction.commitAllowingStateLoss();
        }
    }


    public void clearTopWith(FragmentManager manager, int positon, OnFinishListener onFinishListener) {
        this.index = positon;
        ArrayList<Fragment> fragments = fragMaps.get(positon);
        if (fragments.size() > 1) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            for (int j = fragments.size() - 1; j > 0; j--) {
                transaction.remove(fragments.get(j));
                fragments.remove(j);
                if (fragments.get(j - 1) != null) {
                    transaction.show(fragments.get(j - 1));
                }
            }
            transaction.commitAllowingStateLoss();
        }else{
            onFinishListener.onFinish(null);
        }
    }


    public void clear(FragmentManager manager, int positon) {
        this.index = positon;
        ArrayList<Fragment> fragments = fragMaps.get(positon);
        if (fragments.size() > 0) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            for (int j = fragments.size() - 1; j >= 0; j--) {
                transaction.remove(fragments.get(j));
                fragments.remove(j);
                if (j >= 1 && fragments.get(j - 1) != null) {
                    transaction.show(fragments.get(j - 1));
                }
            }
            transaction.commitAllowingStateLoss();
        }
    }

    public void clearAll(FragmentManager manager, int positon) {
        ArrayList<Fragment> fragments = fragMaps.get(positon);
        if (fragments.size() > 0) {
            FragmentTransaction transaction = manager.beginTransaction();
            for (int j = fragments.size() - 1; j >= 0; j--) {
                transaction.remove(fragments.get(j));
                fragments.remove(j);
            }
            transaction.commitAllowingStateLoss();
        }
    }


    public ArrayList<Integer> getViewIds() {
        return viewIds;
    }

    public HashMap<Integer, ArrayList<Fragment>> getFragMaps() {
        return fragMaps;
    }

    public Fragment getCurrentClass(int index) {
        return fragMaps.get(index).get(fragMaps.get(index).size() - 1);
    }


    public void showAndHidden(FragmentActivity activity, ArrayList<Fragment> fragments, int position){
        for(int i=0;i<fragments.size();i++){
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            //transaction.setCustomAnimations(R.anim.anim_push_right_in_200, R.anim.anim_push_left_out_200);
            if(position==i){
                transaction.show(fragments.get(i));
            }else{
                transaction.hide(fragments.get(i));
            }
            transaction.commitAllowingStateLoss();
        }
    }

    public void showAndHiddenWithAnim(FragmentActivity activity,ArrayList<Fragment> fragments,int position){
        for(int i=0;i<fragments.size();i++){
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            if(position==i){
                transaction.show(fragments.get(i));
            }else{
                transaction.hide(fragments.get(i));
            }
            transaction.commitAllowingStateLoss();
        }
    }


    public Fragment getFragment(Class c) {
        Iterator<Integer> keys = fragMaps.keySet().iterator();
        while (keys.hasNext()) {
            int k = keys.next();
            for (int i = 0; fragMaps.get(k) != null && i < fragMaps.get(k).size(); i++) {
                if (fragMaps.get(k).get(i).getClass().getName().equals(c.getName())) {
                    return fragMaps.get(k).get(i);
                }
            }
        }
        return null;
    }


    public void add(final FragmentActivity fragmentActivity, int id, Fragment now) {
        if (fragMaps.get(id) == null) {
            fragMaps.put(id, new ArrayList<Fragment>());
        }
        if (fragMaps.get(id) != null && fragMaps.get(id).size() > 0) {
            Fragment fragment = fragMaps.get(id).get(fragMaps.get(id).size() - 1);
            fragMaps.get(id).add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.hide(fragment);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        } else {
            fragMaps.get(id).add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        }
    }


    public void add(final FragmentActivity fragmentActivity, int id, Fragment now,int anim1,int anim2) {
        if (fragMaps.get(id) == null) {
            fragMaps.put(id, new ArrayList<Fragment>());
        }
        if (fragMaps.get(id) != null && fragMaps.get(id).size() > 0) {
            Fragment fragment = fragMaps.get(id).get(fragMaps.get(id).size() - 1);
            fragMaps.get(id).add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(anim1, anim2);
            transaction.hide(fragment);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        } else {
            fragMaps.get(id).add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(anim1, anim2);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        }
    }

    public void addNoAnim(final FragmentActivity fragmentActivity, int id, Fragment now) {
        if (fragMaps.get(id) == null) {
            fragMaps.put(id, new ArrayList<Fragment>());
        }
        if (fragMaps.get(id) != null && fragMaps.get(id).size() > 0) {
            Fragment fragment = fragMaps.get(id).get(fragMaps.get(id).size() - 1);
            fragMaps.get(id).add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            //transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.hide(fragment);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        } else {
            fragMaps.get(id).add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            //transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        }
    }


    public void cover(final FragmentActivity fragmentActivity, int id, Fragment now,boolean anim) {
        if (fragMaps.get(id) == null) {
            fragMaps.put(id, new ArrayList<Fragment>());
        }
        fragMaps.get(id).add(now);
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        if(anim){
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
        }
        transaction.add(id, now);
        transaction.commitAllowingStateLoss();
    }

    public void cover(final FragmentActivity fragmentActivity, int id, Fragment now,int anim1,int anim2) {
        if (fragMaps.get(id) == null) {
            fragMaps.put(id, new ArrayList<Fragment>());
        }
        fragMaps.get(id).add(now);
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(anim1, anim2);
        transaction.add(id, now);
        transaction.commitAllowingStateLoss();
    }



    public void addsNoAnim(final FragmentActivity fragmentActivity, int id,ArrayList<Fragment> fragments) {
        for(int i=0;i<fragments.size();i++){
            addNoAnim(fragmentActivity,id,fragments.get(i));
        }
    }



    public void addJustInNormal(final FragmentActivity fragmentActivity, int resid, Fragment now) {
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
        transaction.add(resid, now);
        transaction.commitAllowingStateLoss();
    }


    public void addJustInNormal(final FragmentActivity fragmentActivity, int resid, Fragment now,int anim1,int anim2) {
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(anim1, anim2);
        transaction.add(resid, now);
        transaction.commitAllowingStateLoss();
    }


    public void removeJustInNormal(final FragmentActivity fragmentActivity,  Fragment now) {
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
        transaction.remove(now);
        transaction.commitAllowingStateLoss();
    }

    public void removeJustInNormal(final FragmentActivity fragmentActivity,  Fragment now,int anim1,int anim2) {
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(anim1, anim2);
        transaction.remove(now);
        transaction.commitAllowingStateLoss();
    }
}
