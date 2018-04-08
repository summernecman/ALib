package com.android.lib.view.image.imagepager;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.databinding.ActivityImagesVpBinding;

import java.util.ArrayList;

public class ImagePagerUIOpe extends BaseUIOpe<ActivityImagesVpBinding> {


    public void initPager(FragmentManager fragmentManager, ArrayList<String> strs, int position) {
        getBind().vpVp.setAdapter(new ImagePagerAdaper(fragmentManager, getActivity(), strs));
        getBind().vpVp.setCurrentItem(position);

    }

}