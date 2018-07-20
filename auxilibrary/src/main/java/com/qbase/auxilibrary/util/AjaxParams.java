package com.qbase.auxilibrary.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * <p>
 * 使用方法:
 * <p>
 * <pre>
 * AjaxParams params = new AjaxParams();
 * params.put("username", "michael");
 * params.put("password", "123456");
 * params.put("email", "test@tsz.net");
 * params.put("profile_picture", new File("/mnt/sdcard/pic.jpg")); // 上传文件
 * params.put("profile_picture2", inputStream); // 上传数据流
 * params.put("profile_picture3", new ByteArrayInputStream(bytes)); // 提交字节流
 *
 * FinalHttp fh = new FinalHttp();
 * fh.post("http://www.yangfuhai.com", params, new AjaxCallBack<String>(){
 * 		@Override
 *		public void onLoading(long count, long current) {
 *				textView.setText(current+"/"+count);
 *		}
 *
 *		@Override
 *		public void onSuccess(String t) {
 *			textView.setText(t==null?"null":t);
 *		}
 * });
 * </pre>
 */
public class AjaxParams {
    private static String ENCODING = "UTF-8";

    protected ConcurrentHashMap<String, String> urlParams;
    protected ConcurrentHashMap<String, FileWrapper> fileParams;

    public AjaxParams() {
        init();
    }

    public AjaxParams(Map<String, String> source) {
        init();

        for (Map.Entry<String, String> entry : source.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public AjaxParams(String key, String value) {
        init();
        put(key, value);
    }

    public AjaxParams(Object... keysAndValues) {
        init();
        int len = keysAndValues.length;
        if (len % 2 != 0)
            throw new IllegalArgumentException("Supplied arguments must be even");
        for (int i = 0; i < len; i += 2) {
            String key = String.valueOf(keysAndValues[i]);
            String val = String.valueOf(keysAndValues[i + 1]);
            put(key, val);
        }
    }

    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }

    //    public void put(String key, File file) throws FileNotFoundException {
    //        put(key, new FileInputStream(file), file.getName());
    //    }

    public void put(String key, File file) throws FileNotFoundException {
        put(key, file, file.getName(), null);
    }

    public void put(String key, InputStream stream) {
        put(key, stream, null);
    }

    public void put(String key, InputStream stream, String fileName) {
        put(key, stream, fileName, null);
    }

    /**
     * 添加 inputStream 到请求中.
     * @param key the key name for the new param.
     * @param fileName the name of the file.
     * @param contentType the content type of the file, eg. application/json
     */
    public void put(String key, File file, String fileName, String contentType) {
        if (key != null && file != null) {
            fileParams.put(key, new FileWrapper(file, fileName, contentType));
        }
    }

    /**
     * 添加 inputStream 到请求中.
     * @param key the key name for the new param.
     * @param stream the input stream to add.
     * @param fileName the name of the file.
     * @param contentType the content type of the file, eg. application/json
     */
    public void put(String key, InputStream stream, String fileName, String contentType) {
        if (key != null && stream != null) {
            fileParams.put(key, new FileWrapper(stream, fileName, contentType));
        }
    }

    public void remove(String key) {
        urlParams.remove(key);
        fileParams.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }

        for (ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append("FILE");
        }

        return result.toString();
    }

    private void init() {
        urlParams = new ConcurrentHashMap<String, String>();
        fileParams = new ConcurrentHashMap<String, FileWrapper>();
    }

    public ConcurrentHashMap<String, String> getStringParame() {
        return this.urlParams;
    }

    public ConcurrentHashMap<String, FileWrapper> getFileParame() {
        return this.fileParams;
    }


    public static class FileWrapper {
        public InputStream inputStream;
        public String fileName;
        public String contentType;
        public File file;

        public FileWrapper(File file, String fileName, String contentType) {
            this.file = file;
            this.fileName = fileName;
            this.contentType = contentType;
        }

        public FileWrapper(InputStream inputStream, String fileName, String contentType) {
            this.inputStream = inputStream;
            this.fileName = fileName;
            this.contentType = contentType;
        }

        public String getFileName() {
            if (fileName != null) {
                return fileName;
            }
            else {
                return "nofilename";
            }
        }
    }

}