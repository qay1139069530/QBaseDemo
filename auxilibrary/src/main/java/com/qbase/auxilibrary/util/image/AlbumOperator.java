package com.qbase.auxilibrary.util.image;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.qbase.auxilibrary.params.AuxiliaryParams;
import com.qbase.auxilibrary.util.FileUtil;

import java.io.File;
import java.io.IOException;

/**
 * 对系统自带照相机操作
 */
public class AlbumOperator {

    private static AlbumOperator mInstace;
    public static final int OPERATOR_ALBUM = 751;
    public static final int OPERATOR_CAMERA = 752;
    public static final int OPERATOR_CUT = 753;
    public static final int OPERATOR_ALL = 754;
    public static final int OPERATOR_VIDEO = 755;
    public static final int OPERATOR_AUDIO = 756;

    private AlbumOperator() {
        //如果文件夹不存在则新建
        FileUtil.createFilePath(AuxiliaryParams.SD_PHOTOS);
        //保存文件夹下10个文件
        FileUtil.fixSizeFile(AuxiliaryParams.SD_PHOTOS);
    }

    public synchronized static AlbumOperator getInstance() {
        if (mInstace == null) {
            mInstace = new AlbumOperator();
        }
        return mInstace;
    }

    /**
     * 清理照片
     */
    public void clearData() {
        //删除文件夹
        FileUtil.deleteFile(AuxiliaryParams.SD_PHOTOS);
        //如果文件夹不存在则新建
        FileUtil.createFilePath(AuxiliaryParams.SD_PHOTOS);
    }

    /**
     * 拍照
     */
    public void takeCamera(Activity act, String fileName) {
        takeCamera(act, fileName, OPERATOR_CAMERA);
    }

    /**
     * 拍照
     */
    public void takeCamera(Activity act, String fileName, int requestCode) {
        try {
            if (act == null) {
                return;
            }
            Intent intent = new Intent();
            // 指定存放拍摄照片的位置
            Uri imageUri;
            File file = new File(AuxiliaryParams.SD_PHOTOS + fileName);
            if (file.exists()) {
                file.delete();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                Toast.makeText(act, "文件创建失败，请查看权限", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                String author = getProviderAuth(act);
                imageUri = FileProvider.getUriForFile(act.getApplicationContext(), author, file);
            } else {
                imageUri = Uri.fromFile(file);
            }
            //设置Action为拍照
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            //将拍取的照片保存到指定URI
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            if (requestCode == 0) {
                requestCode = OPERATOR_CAMERA;
            }
            act.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拍照
     */
    public void takeCamera(Fragment fragment, String fileName) {
        takeCamera(fragment, fileName, OPERATOR_CAMERA);
    }

    /**
     * 拍照
     */
    public void takeCamera(Fragment fragment, String fileName, int requestCode) {
        try {
            if (fragment == null || fragment.getActivity() == null) {
                return;
            }
            Intent intent = new Intent();
            // 指定存放拍摄照片的位置
            Uri imageUri;
            File file = new File(AuxiliaryParams.SD_PHOTOS + fileName);
            if (file.exists()) {
                file.delete();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                Toast.makeText(fragment.getActivity(), "文件创建失败，请查看权限", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                String author = getProviderAuth(fragment.getActivity());
                imageUri = FileProvider.getUriForFile(fragment.getActivity().getApplicationContext(), author, file);
            } else {
                imageUri = Uri.fromFile(file);
            }
            //设置Action为拍照
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            //将拍取的照片保存到指定URI
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            if (requestCode == 0) {
                requestCode = OPERATOR_CAMERA;
            }
            fragment.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 裁剪
     */
    private void cropPhoto(Activity act, String path, Uri uri) {
        File file = new File(path);
        Uri outputUri = Uri.fromFile(file);
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("outputFormat",
                Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        act.startActivityForResult(intent, OPERATOR_CUT);
    }

    /**
     * 获取provider auth
     */
    private String getProviderAuth(Context context) {
        try {
            if (context != null) {
                ComponentName cn = new ComponentName(context, "android.support.v4.content.FileProvider");
                ProviderInfo appInfo = context.getPackageManager().getProviderInfo(cn, PackageManager.MATCH_ALL);
                return appInfo.authority;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
