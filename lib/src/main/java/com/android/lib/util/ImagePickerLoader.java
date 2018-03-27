package com.android.lib.util;

//by summer on 2017-06-12.

import android.app.Activity;
import android.widget.ImageView;

import com.android.lib.GlideApp;
import com.lzy.imagepicker.loader.ImageLoader;

public class ImagePickerLoader implements ImageLoader {


    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        GlideApp.with(activity).asBitmap().load(path).centerCrop().into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        GlideApp.with(activity).asBitmap().load(path).centerCrop().into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
