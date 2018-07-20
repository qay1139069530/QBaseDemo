package com.qbase.auxilibrary.util;

import android.text.TextUtils;

import java.security.MessageDigest;

public class MD5Util {
    private static final String MD_PACKAGES    = "drision.xcslinspect";
    private static final char   MD_CODE_1      = '@';
    private static final char   MD_CODE_2      = 'W';
    private static final int    MD_CODE_LENGTH = 15;

    /**
     * MD5加密
     * @param s
     * @return
     */
    public final static String MD5(String s) {
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(val));

            }
            return sb.toString();
        } catch (Exception e) {
            return s;
        }
    }

    /**
     * 加密 str
     * @return
     */
    public static String encryptionStr(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String md5_code = MD5(MD_PACKAGES);
        String encry_code = md5_code.substring(0, MD_CODE_LENGTH);
        String encryption = encryption_first(str + encry_code);
        return encryption_second(encryption);

    }

    /**
     * 解密 str
     * @param str
     * @return
     */
    public static String decryptionStr(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String decry = decryption_second(str);
        String decryption = decryption_first(decry);
        return decryption.substring(0, decryption.length() - MD_CODE_LENGTH);
    }

    //可逆的加密算法   Encryption
    private static String encryption_first(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ MD_CODE_1);
        }
        String s = new String(a);
        return s;
    }

    //可逆的加密算法    Encryption
    private static String encryption_second(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ MD_CODE_2);
        }
        String s = new String(a);
        return s;
    }

    //加密后解密   Decryption
    private static String decryption_first(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ MD_CODE_1);
        }
        String k = new String(a);
        return k;
    }

    //加密后解密   Decryption
    private static String decryption_second(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ MD_CODE_2);
        }
        String k = new String(a);
        return k;
    }
}
