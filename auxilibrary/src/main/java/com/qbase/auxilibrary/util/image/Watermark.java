package com.qbase.auxilibrary.util.image;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Watermark implements Serializable {

    private String WaterMaskTime;
    private String WaterMaskLongitude;
    private String WaterMaskLatitude;
    private Bitmap WaterMaskimage;

    public String getWaterMaskTime() {
        return WaterMaskTime;
    }

    public void setWaterMaskTime(String waterMaskTime) {
        WaterMaskTime = waterMaskTime;
    }

    public String getWaterMaskLongitude() {
        return WaterMaskLongitude;
    }

    public void setWaterMaskLongitude(String waterMaskLongitude) {
        WaterMaskLongitude = waterMaskLongitude;
    }

    public String getWaterMaskLatitude() {
        return WaterMaskLatitude;
    }

    public void setWaterMaskLatitude(String waterMaskLatitude) {
        WaterMaskLatitude = waterMaskLatitude;
    }

    public Bitmap getWaterMaskimage() {
        return WaterMaskimage;
    }

    public void setWaterMaskimage(Bitmap waterMaskimage) {
        WaterMaskimage = waterMaskimage;
    }
}
