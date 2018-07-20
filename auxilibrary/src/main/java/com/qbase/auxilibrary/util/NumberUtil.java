package com.qbase.auxilibrary.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * 关于数字的工具类
 */
public class NumberUtil {

    /**
     * 保留小数
     *
     * @param d   需要处理的数
     * @param num 保留位数
     * @return
     */
    public static double saveDataDecimal(double d, int num) {
        BigDecimal bigDecimal = new BigDecimal(d);
        return bigDecimal.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 保留小数
     *
     * @param d   需要处理的数
     * @param num 保留位数
     * @return
     */
    public static float saveDataDecimal(float d, int num) {
        BigDecimal bigDecimal = new BigDecimal(d);
        return bigDecimal.setScale(num, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * float ==> string 保留两位小数
     *
     * @param value
     * @return
     */
    public static String decimalValueOf(float value) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format((double) value);
    }

    /**
     * float ==> string 保留两位小数
     *
     * @param value
     * @return
     */
    public static String decimalValueOf(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format((double) value);
    }

    /**
     * double ==> string 不保留小数
     *
     * @param value
     * @return
     */
    public static String decimalNo(double value) {
        DecimalFormat df = new DecimalFormat("0");
        return df.format(value);
    }

    /**
     * double ==> string 格式化价格
     *
     * @param value
     * @return
     */
    public static String formatMoney(double value) {
        try {
            double d = saveDataDecimal(value, 2);
            String e = String.valueOf(d);
            while (e.contains(".")) {
                String s1 = e.substring(e.length() - 1, e.length());
                if (s1.equals("0") || s1.equals(".")) {
                    e = e.substring(0, e.length() - 1);
                } else {
                    break;
                }
            }
            return e;
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(value);
        }
    }

    /**
     * 保留小数位数
     *
     * @param a
     * @return
     */
    public static String getFormatDouble(double a) {
        if (a > 0 && a < 1) {
            return String.valueOf(a);
        } else {
            double b = Math.floor(a); //不取小数,向下取整
            if (b <= a) {
                return getFormatData(a, 0);
            } else {
                return String.valueOf(a);
            }
        }
    }

    /**
     * 保留小数位数
     *
     * @param a
     * @return
     */
    public static String getFormatFloat(float a) {
        if (a > 0 && a < 1) {
            return String.valueOf(a);
        } else {
            double b = Math.floor(a); //不取小数,向下取整
            if (b <= a) {
                return getFormatData(a, 0);
            } else {
                return String.valueOf(a);
            }
        }
    }

    /**
     * 保留小数位数
     *
     * @param a
     * @return
     */
    public static String getFormatData(double a, int num) {
        try {
            String pattern = "#";
            if (num > 0) {
                pattern = pattern + ".";
                for (int i = 0; i < num; i++) {
                    pattern = pattern + "0";
                }
            }
            DecimalFormat df = new DecimalFormat(pattern);
            return df.format(a);
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(a);
        }
    }

    /**
     * 保留小数位数
     *
     * @param a
     * @return
     */
    public static String getFormatData(float a, int num) {
        try {
            String pattern = "#";
            if (num > 0) {
                pattern = pattern + ".";
                for (int i = 0; i < num; i++) {
                    pattern = pattern + "0";
                }
            }
            DecimalFormat df = new DecimalFormat(pattern);
            return df.format(a);
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(a);
        }
    }

    /**
     * 范围内的随机数
     *
     * @param low  最低位
     * @param high 最高位
     */
    public static int randomData(int low, int high) {
        int x;//定义两变量
        Random ne = new Random();//实例化一个random的对象ne
        x = ne.nextInt(high - low + 1) + low;//为变量赋随机值1000-9999
        return x;
    }
}