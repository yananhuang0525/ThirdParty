package com.panku.thirdparty;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * data 2019/4/28
 * time 10:59
 * Created by huangyanan.
 */
public class SelectImageActivity extends BaseActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {
    private Button btn;
    private ImageView iv;
    String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_image;
    }

    @Override
    protected void initView() {
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
        iv = findViewById(R.id.iv);
        if (!EasyPermissions.hasPermissions(this, permissions)) {
            EasyPermissions.requestPermissions(this, "", 101, permissions);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                PictureSelector.create(SelectImageActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .forResult(PictureConfig.CHOOSE_REQUEST);

//                PictureSelector.create(SelectImageActivity.this)
//                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                        .imageSpanCount(4)// 每行显示个数 int
//                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
//                        .previewImage(true)// 是否可预览图片 true or false
//                        .isCamera(true)// 是否显示拍照按钮 true or false
//                        .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
//                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                        .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
////                        .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
////                        .enableCrop(true)// 是否裁剪 true or false
//                        .compress(true)// 是否压缩 true or false
////                        .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
////                        .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示 true or false
////                        .isGif(false)// 是否显示gif图片 true or false
//                        .compressSavePath(getCompressPath())//压缩图片保存地址
////                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
////                        .circleDimmedLayer(true)// 是否圆形裁剪 true or false
////                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
////                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
////                        .openClickSound(false)// 是否开启点击声音 true or false
////                        .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
//                        .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                        .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
//                        .minimumCompressSize(100)// 小于100kb的图片不压缩
////                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
////                        .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
////                        .rotateEnabled() // 裁剪是否可旋转图片 true or false
////                        .scaleEnabled()// 裁剪是否可放大缩小图片 true or false
////                        .videoQuality()// 视频录制质量 0 or 1 int
////                        .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
////                        .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
////                        .recordVideoSecond()//视频秒数录制 默认60s int
////                        .isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    String path = "";
                    for (LocalMedia media : localMedia) {
                        if (media.isCut() && !media.isCompressed()) {
                            // 裁剪过
                            path = media.getCutPath();
                        } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                            path = media.getCompressPath();
                        } else {
                            // 原图地址
                            path = media.getPath();
                        }
                        Glide.with(SelectImageActivity.this).load(path).into(iv);
                    }
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        }
    }

    // 压缩后图片文件存储位置
    private String getCompressPath() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PictureSelector/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        toastMessage("用户未授权");
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            new AppSettingsDialog(null).show();
            toastMessage("去设置");
            new AppSettingsDialog.Builder(this)
                    .setTitle("提醒")
                    .setRationale("此app需要这些权限才能正常使用")
                    .build()
                    .show();
        }
    }
}
