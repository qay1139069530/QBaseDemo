package com.qbase.auxilibrary.util.image;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * bitmap工具类
 */
public class BitmapUtil {

    /**
     * 获取bitmap 大小
     *
     * @return
     */
    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            //API 12
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight(); //earlier version
    }

    /***
     * 固定bitmap大小
     *
     * @param bgimage：源图片资源
     * @param newWidth：缩放后宽度
     * @param newHeight：缩放后高度
     * @return
     */
    public static Bitmap decodeSizeBitmap(Bitmap bgimage, double newWidth, double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
        return bitmap;
    }

    /***
     * 固定bitmap大小
     *
     * @param bgimage：源图片资源
     * @param newWidth：缩放后宽度
     * @return
     */
    public static Bitmap decodeSizeBitmap(Bitmap bgimage, double newWidth) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleWidth);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
        return bitmap;
    }

    // 压缩图片 微信 小于32k
    public static byte[] compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 32) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        return (byte[]) baos.toByteArray();
    }

    /**
     * 保存bitmap到本地,控制大小
     *
     * @param bm
     * @return
     */
    public String saveBitmapToLocal(Bitmap bm, String path) {
        return this.saveBitmapToLocal(bm, path, 0);
    }

    /**
     * 保存bitmap到本地,控制大小
     *
     * @param bm
     * @return
     */
    public String saveBitmapToLocal(Bitmap bm, String path, int size) {
        FileOutputStream fos = null;
        try {
            if (size > 10) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                int bitmap_size = BitmapUtil.getBitmapSize(bm) / (1024 * 4);
                //判断bitmap占用空间是否大于允许最大空间  如果大于则压缩 小于则不压缩
                if (bitmap_size > size) {
                    //获取bitmap大小 是允许最大大小的多少倍
                    double i = bitmap_size / size;
                    //开始压缩  此处用到平方根 将宽带和高度压缩掉对应的平方根倍 （1.保持刻度和高度和原bitmap比率一致，压缩后也达到了最大大小占用空间的大小）
                    bm = BitmapUtil.decodeSizeBitmap(bm, bm.getWidth() / Math.sqrt(i), bm.getHeight() / Math.sqrt(i));
                }
            }
            fos = new FileOutputStream(path);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);// (0-100)压缩文件
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return path;
    }

    /***
     * 获取SD卡图片
     *
     * @param path
     * @return
     */
    public static Bitmap getBitmapFromPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        InputStream inputStream = null;
        Bitmap bitmap = null;
        try {
            URL url_Image = new URL(path);
            inputStream = url_Image.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    /**
     * bitmap == > base64
     *
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream bos = null;
        String str = "";
        try {
            bos = new ByteArrayOutputStream();
            //(0-100)压缩文件
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bos);
            byte[] bytes = bos.toByteArray();
            str = new String(Base64.encodeToString(bytes, Base64.NO_WRAP));
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return str;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }

    /**
     * bitmap == > base64
     *
     * @return
     */
    public static String bitmapToBase64(String path) {
        Bitmap bitmap = getBitmapFromPath(path);
        ByteArrayOutputStream bos = null;
        String str = "";
        try {
            bos = new ByteArrayOutputStream();
            //(0-100)压缩文件
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bos);
            byte[] bytes = bos.toByteArray();
            str = new String(Base64.encodeToString(bytes, Base64.NO_WRAP));
            bos.flush();
            bos.close();
            bitmap.recycle();
            bitmap = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return str;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bitmap != null) {
                bitmap.recycle();
                bitmap = null;
            }
        }
        return str;
    }

    /**
     * decode res图片
     */
    public static Bitmap decodeResources(int id) {
        InputStream inputStream = null;
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            //inputStream = AppVarManager.getInstance().getBaseContext().getResources().openRawResource(id);
            bitmap = BitmapFactory.decodeStream(inputStream, null, opt);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }


    private static Bitmap createWaterMaskImage(Bitmap src, Bitmap watermark) {

        if (src == null) {
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        int ww = watermark.getWidth();
        int wh = watermark.getHeight();
        // create the new blank bitmap
        Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(newb);
        // draw src into
        cv.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src
        // draw watermark into
        cv.drawBitmap(watermark, w / 2 - ww / 2, h / 2 - wh / 2, null);// 在src的右下角画入水印
        // save all clip
        cv.save(Canvas.ALL_SAVE_FLAG);// 保存
        // store
        cv.restore();// 存储
        return newb;
    }

    /**
     * 进行添加水印图片和文字
     *
     * @param bitmap  原图
     * @param content 水印内容
     * @return
     */
    public static Bitmap createBitmap(Bitmap bitmap, String content) {
        try {
            // 获取原始图片与水印图片的宽与高
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            Bitmap newBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas mCanvas = new Canvas(newBitmap);
            // 往位图中开始画入src原始图片
            Paint paint = new Paint();
            paint.setDither(true);
            mCanvas.drawBitmap(bitmap, 0, 0, paint);
            //content = "平度：" + content;
            // 开始加入文字
            if (!TextUtils.isEmpty(content)) {
                // 设置画笔
                Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);
                // 采用的颜色
                textPaint.setColor(Color.RED);
                textPaint.setAntiAlias(true);
                if(content.length()>15){
                    textPaint.setTextSize(10);
                }else{
                    textPaint.setTextSize(20);
                }
                String familyName = "宋体";
                Typeface typeface = Typeface.create(familyName, Typeface.ITALIC);
                textPaint.setTypeface(typeface);
                textPaint.setTextAlign(Paint.Align.CENTER);
                mCanvas.drawText(content, w / 2, h - getFontHeight(textPaint) / 2, textPaint);
            }
            mCanvas.save(Canvas.ALL_SAVE_FLAG);
            mCanvas.restore();
            return newBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 获取密度下的大小
     *
     * @return
     */
    private static float getSize(int size) {
        return size * 1.5f;
    }


    public static int getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 2;
    }

    /**
     * 压缩后保存图片
     *
     * @param originalPath 原图路径
     * @param nowPath      压缩后的图片路径
     * @param content      title 水印文字
     */
    public static boolean waterMarkImage(String originalPath, String nowPath,
                                         String content) {
        if (TextUtils.isEmpty(originalPath)) {
            return false;
        }
        if (originalPath.lastIndexOf(".jpg") > 0
                || originalPath.lastIndexOf(".jpeg") > 0
                || originalPath.lastIndexOf(".gif") > 0
                || originalPath.lastIndexOf(".png") > 0
                || originalPath.lastIndexOf(".bmp") > 0) {

        } else {
            return false;
        }
        // 对图片进行压缩
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 设为true，不分配内存空间
        // 获取这个图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeFile(originalPath, options);// 此时返回bm为空

        // System.out.println("原大小："+options.outWidth+"  "+options.outHeight);
        if (options.outWidth <= 1536 && options.outHeight <= 1536) {//无需压缩

        } else {//压缩处理
            options.inSampleSize = 1;
            int tempSampleSize = 1;
            int sampleIndex = 0;
            while (options.outHeight > 1536 || options.outWidth > 1536) {
                options.inSampleSize = tempSampleSize << sampleIndex++;
                bitmap = BitmapFactory.decodeFile(originalPath, options);// 此时返回bm为空
                if (sampleIndex == 3) {
                    break;
                }
            }
            // 重新读入图片，注意这次要把options.inJustDecodeBounds设为false哦
            options.inJustDecodeBounds = false;
        }

        try {

            if (!TextUtils.isEmpty(content)) {
                if (options.inJustDecodeBounds) {//压缩处理后的图片
                    bitmap = BitmapFactory.decodeFile(originalPath);
                } else {//未压缩处理的
                    bitmap = BitmapFactory.decodeFile(originalPath, options);
                }

                //增加水印文字效果
                //bitmap = createBitmap(bitmap, content);
            }

            // 保存入sdCard
            File file2 = new File(nowPath); // 压缩后存到sd卡中的路径
            FileOutputStream out = new FileOutputStream(file2);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 75, out)) {
                out.flush();
                out.close();
            }

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != bitmap && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
        return true;
    }

    /**
     * 压缩后保存图片
     *
     * @param largerImagePath 原图路径
     * @param smallImagePath  压缩后的图片路径
     * @param smallImagePath  title 水印文字
     */
    public static void watermarkZoomImage(String largerImagePath, String smallImagePath,
                                          Watermark entity) {
        if (TextUtils.isEmpty(largerImagePath)) {
            return;
        }
        if (largerImagePath.lastIndexOf(".jpg") > 0
                || largerImagePath.lastIndexOf(".jpeg") > 0
                || largerImagePath.lastIndexOf(".gif") > 0
                || largerImagePath.lastIndexOf(".png") > 0
                || largerImagePath.lastIndexOf(".bmp") > 0) {

        } else {
            return;
        }

        // 对图片进行压缩
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 设为true，不分配内存空间
        // 获取这个图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeFile(largerImagePath, options);// 此时返回bm为空

        // System.out.println("原大小："+options.outWidth+"  "+options.outHeight);
        if (options.outWidth <= 1024 && options.outHeight <= 1024) {//无需压缩

        } else {//压缩处理
            options.inSampleSize = 1;
            int tempSampleSize = 2;
            int sampleIndex = 0;
            while (options.outHeight > 1536 || options.outWidth > 1536) {
                options.inSampleSize = tempSampleSize << sampleIndex++;
                bitmap = BitmapFactory.decodeFile(largerImagePath, options);// 此时返回bm为空
                if (sampleIndex == 3) {
                    break;
                }
            }
            // 重新读入图片，注意这次要把options.inJustDecodeBounds设为false哦
            options.inJustDecodeBounds = false;
        }

        try {

            if (options.inJustDecodeBounds) {//压缩处理后的图片
                bitmap = BitmapFactory.decodeFile(largerImagePath);
            } else {//未压缩处理的
                bitmap = BitmapFactory.decodeFile(largerImagePath, options);
            }

            if (options.outWidth < options.outHeight) {
                bitmap = toTurnPic(bitmap, -90);
            }


            //增加水印文字效果
            bitmap = createBitmap(bitmap, entity.getWaterMaskTime());
            //增加水印图片效果
            bitmap = createWaterMaskImage(bitmap, entity.getWaterMaskimage());

            // 保存入sdCard
            File file2 = new File(smallImagePath); // 压缩后存到sd卡中的路径
            FileOutputStream out = new FileOutputStream(file2);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)) {
                out.flush();
                out.close();
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != bitmap && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }

    /**
     * 图片旋转
     */
    public static Bitmap toTurnPic(Bitmap img, int degree) {
        // int  degree= readPictureDegree(filePath);
        Matrix matrix = new Matrix();
        /*翻转90度*/
        matrix.postRotate(degree);
        int width = img.getWidth();
        int height = img.getHeight();
        img = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
        return img;
    }


    //****************************************根据Uri 获取图片路径******************************************************//

    /**
     * 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
     *
     * @param context
     * @param uri
     */
    @TargetApi(19)
    public static String getImageAbsolutePath(Context context, Uri uri) {
        if (uri == null)
            return null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                String id = DocumentsContract.getDocumentId(uri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("ic_audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * 获取图片地址
     *
     * @param context
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }










    /**
     * 将Bitmap转换为Base64字符串
     * @return
     */
    public static String BitmapToBase64String(Bitmap bitmap){
        if(bitmap == null){
            return "";
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return new String(Base64.encode(baos.toByteArray(),0));
    }
    /**
     * 将Base64字符串转换为Bitmap
     * @return
     */
    public static Bitmap Base64StringToBimap(String base64String){
        if(base64String == null){
            return null;
        }
        byte[] bytes = Base64.decode(base64String,1);
        if(bytes != null && bytes.length != 0){
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        return null;
    }


    /**
     * @param inStream
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }
}