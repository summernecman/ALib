package com.android.lib.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.android.lib.constant.ValueConstant;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

//import com.summer.imga.constant.ValueConstant;

/**
 * 跳转
 * Created by SWSD on 2016/4/18.
 */
public class IntentUtil {

    private static IntentUtil instance;

    private static Uri uri;

    public static IntentUtil getInstance() {
        if (instance == null) {
            instance = new IntentUtil();
        }
        return instance;
    }

    public static Uri getUri() {
        return uri;
    }

    /**
     * 拍照返回图片
     *
     * @param activity
     */
    public Uri takeGetPhoto(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File f = getPhotoFileFolder();
        if (f == null) {
            return null;
        }
        File file = new File(f, System.currentTimeMillis() + ".jpg");
        if (!f.exists()) {
            f.mkdirs();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
        activity.startActivityForResult(intent, ValueConstant.CODE_REQUSET_TAKE_PHOTO);
        uri = Uri.fromFile(file);
        return uri;
    }

    public File getPhotoFileFolder() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        File f = new File(Environment.getExternalStorageDirectory(), "/IMGA/");
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    public File getPhotoShortFileFolder() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        File f = new File(getPhotoFileFolder(), "/Thumbnails/");
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    public void IntentTo(Context context, String pkg) {
        if (context == null || pkg == null) {
            return;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkg, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo != null) {
            Intent intent = new Intent();
            intent = context.getPackageManager().getLaunchIntentForPackage(pkg);
            if (intent != null) {
                context.startActivity(intent);
            }
        }
    }

    public void uninstall(Context context, String pkg) {
        Uri packageURI = Uri.parse("package:" + pkg);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        context.startActivity(uninstallIntent);
    }

    public void photoShowFromphone(Activity activity, int requstCode) {
        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
        getImage.addCategory(Intent.CATEGORY_OPENABLE);
        getImage.setType("image/*");
        activity.startActivityForResult(getImage, requstCode);
    }

    public void photoShowFromphone(Fragment fragment, int requstCode) {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            fragment.startActivityForResult(intent, requstCode);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            fragment.startActivityForResult(intent, requstCode);
        }

    }

    public void photosShowFromphone(Fragment fragment, int requstCode) {
        Intent intent = new Intent(fragment.getContext(), ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, false); // 是否是直接打开相机
        fragment.startActivityForResult(intent, requstCode);
    }


    public void pickImage(Fragment fragment,int res){
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                //.theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .previewVideo(true)// 是否可预览视频 true or false
                .enablePreviewAudio(false) // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                //.glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                //.withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                //.hideBottomControls()// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
                //.compressSavePath(getPath())//压缩图片保存地址
                //.freeStyleCropEnabled()// 裁剪框是否可拖拽 true or false
                //.circleDimmedLayer()// 是否圆形裁剪 true or false
               // .showCropFrame()// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                //.showCropGrid()// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                //.openClickSound()// 是否开启点击声音 true or false
                //.selectionMedia()// 是否传入已选图片 List<LocalMedia> list
                //.previewEggs()// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                //.cropCompressQuality()// 裁剪压缩质量 默认90 int
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                //.rotateEnabled() // 裁剪是否可旋转图片 true or false
                //.scaleEnabled()// 裁剪是否可放大缩小图片 true or false
                //.videoQuality()// 视频录制质量 0 or 1 int
                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                //.recordVideoSecond()//视频秒数录制 默认60s int
                .forResult(res);//结果回调onActivityResult code
    }

    public void share(Fragment fragment, ArrayList<String> str) {
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        for (int i = 0; i < imageUris.size(); i++) {
            Uri imageUri = Uri.fromFile(new File(str.get(i)));
            imageUris.add(imageUri);
        }

        Intent intent3 = new Intent();
        intent3.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent3.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        intent3.setType("image/*");
        fragment.startActivity(Intent.createChooser(intent3, "share"));
    }

    public void shareImage(Fragment fragment, String str) {
        Intent intent2 = new Intent(Intent.ACTION_SEND);
        Uri uri = Uri.fromFile(new File(str));
        intent2.putExtra(Intent.EXTRA_STREAM, uri);
        intent2.setType("image/*");
        fragment.startActivity(Intent.createChooser(intent2, "share"));
    }

    public static void startActivity(Activity activity, Class c, Serializable s){
        Intent intent = new Intent(activity,c);
        if(s!=null){
            intent.putExtra(ValueConstant.DATA_DATA,s);
        }
        activity.startActivity(intent);
    }

    public static void startActivityWithFinish(Activity activity, Class c, Serializable s){
        Intent intent = new Intent(activity,c);
        if(s!=null){
            intent.putExtra(ValueConstant.DATA_DATA,s);
        }
        activity.startActivity(intent);
        activity.finish();
    }


}
