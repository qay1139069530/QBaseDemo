package com.qbase.auxilibrary.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Created by qay
 */
public class FileUtil {
    //存在文件个数
    private static int FILE_SIZE = 30;


    /**
     * 判断文件大小
     */
    public static long getFileSize(String path) {
        FileInputStream fis = null;
        try {
            File file = new File(path);
            long size = 0;
            if (file.exists()) {
                fis = new FileInputStream(file);
                size = fis.available();
                fis.close();
            }
            return size;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return 0;
    }


    /**
     * 在SD卡上创建一个文件夹
     */
    public static void createFilePath(String path) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 创建一个文件夹对象，赋值为外部存储器的目录
            File filePath = new File(path);
            if (!filePath.exists()) {
                // 若不存在，创建目录，可以在应用启动的时候创建
                filePath.mkdirs();
            }
        } else {
            return;
        }
    }

    /**
     * 在SD卡上清空文件夹
     */
    public static void clearFilePath(String path) {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 创建一个文件夹对象，赋值为外部存储器的目录
            File file = new File(path);
            if (file.exists()) {
                if (file.isDirectory()) {
                    File[] fileList = file.listFiles();
                    if (fileList == null) {
                        return;
                    }
                    for (int i = 0; i < fileList.length; i++) {
                        fileList[i].delete();
                    }
                }
            }
        } else {
            return;
        }
    }

    /**
     * 在SD卡上固定一个文件夹大小
     */
    public static void fixSizeFile(String path) {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 创建一个文件夹对象，赋值为外部存储器的目录
            File file = new File(path);
            if (file.exists()) {
                if (file.isDirectory()) {
                    File[] fileList = file.listFiles();
                    if (fileList == null || fileList.length <= FILE_SIZE) {
                        return;
                    }
                    for (int i = 0; i < fileList.length - FILE_SIZE; i++) {
                        fileList[i].delete();
                    }
                }
            }
        } else {
            return;
        }
    }

    /**
     * 删除指定文件
     */
    public static void deleteFile(String fileName) {
        // 创建一个文件夹对象，赋值为外部存储器的目录
        File file = new File(fileName);
        if (file.exists()) {
            // 若不存在，则删除
            file.delete();
        }
    }

    /**
     * 获取SD卡剩余大小
     */
    public static long getSDFreeSize() {
        // 取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        // 获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        // 空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocks();
        // 返回SD卡空闲大小
        // return freeBlocks * blockSize; //单位Byte
        // return (freeBlocks * blockSize)/1024; //单位KB
        return (freeBlocks * blockSize) / 1024 / 1024; // 单位MB
    }

    /**
     * 读取文本数据中键值对
     *
     * @param filePath 文件名
     * @return String, 读取到的文本内容，失败返回null
     */
    public static String readFileKeyValue(String filePath, String key) {
        if (filePath == null || !new File(filePath).exists()) {
            return null;
        }
        FileInputStream fis = null;
        Properties pt = new Properties();
        String value = null;
        try {
            fis = new FileInputStream(filePath);
            if (fis != null) {
                pt.load(fis);
                value = pt.getProperty(key);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            value = null;
        } catch (IOException e) {
            e.printStackTrace();
            value = null;
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return value;
    }

    /**
     * 读取文本数据
     *
     * @param context  程序上下文
     * @param fileName 文件名
     * @return String, 读取到的文本内容，失败返回null
     */
    public static String readAssets(Context context, String fileName) {
        InputStream is = null;
        String content = null;
        try {
            is = context.getAssets().open(fileName);
            if (is != null) {

                byte[] buffer = new byte[1024];
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int readLength = is.read(buffer);
                    if (readLength == -1)
                        break;
                    arrayOutputStream.write(buffer, 0, readLength);
                }
                is.close();
                arrayOutputStream.close();
                content = new String(arrayOutputStream.toByteArray());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            content = null;
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return content;
    }


    public static String getAssetString(Context context, String fileName) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String line = null;
            StringBuilder builder = new StringBuilder();
            while (null != (line = bufferedReader.readLine())) {
                builder.append(line);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bufferedReader = null;
        }
        return "";
    }

    public static int readFileFirstLine(String fileName) {
        File file = new File(fileName);
        int first_line = 0;
        if (file.exists()) {
            BufferedReader reader = null;

            try {
                reader = new BufferedReader(new FileReader(file));
                // 一次读入一行，直到读入null为文件结束
                String tempString = reader.readLine();
                if (!TextUtils.isEmpty(tempString) && isAllNumer(tempString)) {
                    first_line = Integer.parseInt(tempString);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return first_line;
    }

    /**
     * 是否全是数字
     */
    public static boolean isAllNumer(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 获取文件路径
     */
    public static File getAssetFileToCacheDir(Context context, String fileName) {
        try {
            File cacheDir = PathUtil.getInstance().getFilePath();
            final String cachePath = cacheDir.getAbsolutePath() + File.separator + fileName;
            InputStream is = context.getAssets().open(fileName);
            File file = new File(cachePath);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }


    /**
     * 获取Audio路径
     *
     * @param ctx
     * @param uri
     */
    public static String getAudioFilePathFromUri(Context ctx, Uri uri) {
        String path = "";
        Cursor cursor = null;
        try {
            cursor = ctx.getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
            path = cursor.getString(index);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }


    /**
     * 检查文件是否存在
     */
    public static boolean checkFileExit(String path_name) {
        if (TextUtils.isEmpty(path_name)) {
            return false;
        }

        File tempFile = new File(path_name);
        if (tempFile.exists()) {
            //存在
            return true;
        } else {
            // 不存在
            return false;
        }
    }

}
