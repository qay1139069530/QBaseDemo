package com.qbase.auxilibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * 圆点 view
 *
 * @author Qian
 */
public class QCircleView extends View {
    private Context context;

    /**
     * view 的位置
     */
    private int viewWidth, viewHeight;
    /**
     * 圆中内容
     */
    private String text;
    /**
     * 圆颜色
     */
    private int circleColor = Color.RED;
    /**
     * 字体颜色
     */
    private int textColor = Color.WHITE;
    /**
     * 描边颜色
     */
    private int sideColor = Color.WHITE;
    /**
     * 描边半径
     */
    private int sideRadius = 8;
    /**
     * 圆半径
     */
    private int radius = 8;

    /**
     * 单独的圆
     */
    private int radius_alone =9;
    /**
     * 圆中字体大小
     */
    private int textSize = 12;

    /**
     * 密度
     */
    private float density = 1.5f;


    @Override
    public boolean isInEditMode() {
        return super.isInEditMode();
    }

    public QCircleView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public QCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public QCircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    private void init() {
        this.density = getDesity();
    }

    /**
     * 获取密度
     *
     * @return
     */
    private float getDesity() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画元
        if (!isInEditMode()) {
            if (!TextUtils.isEmpty(text)) {
                drawCircleText(canvas);
            } else {
                drawCircle(canvas);
            }
        }
        super.onDraw(canvas);
    }

    /**
     * 圆
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        //画圆
        Paint cirlce = new Paint();
        cirlce.setColor(circleColor);
        cirlce.setStyle(Style.FILL);
        cirlce.setAntiAlias(true);
        canvas.drawCircle(viewWidth - getSize(radius_alone), getSize(radius_alone), getSize(radius_alone), cirlce);
    }

    /**
     * 圆+中心字
     *
     * @param canvas
     */
    private void drawCircleText(Canvas canvas) {
        //画描边圆
        Paint cirlceSide = new Paint();
        cirlceSide.setColor(sideColor);
        cirlceSide.setStyle(Style.FILL);
        cirlceSide.setAntiAlias(true);
        canvas.drawCircle(viewWidth - getSize(radius), getSize(radius), getSize(sideRadius), cirlceSide);

        //画圆
        Paint cirlce = new Paint();
        cirlce.setColor(circleColor);
        cirlce.setStyle(Style.FILL);
        cirlce.setAntiAlias(true);
        canvas.drawCircle(viewWidth - getSize(radius), getSize(radius), getSize(radius), cirlce);

        //画字体，获取字体区域的大小，没有颜色显示
        Paint textRect = new Paint();
        textRect.setColor(Color.TRANSPARENT);
        textRect.setStyle(Style.FILL);
        textRect.setAntiAlias(true);
        textRect.setTextSize(getSize(textSize));
        canvas.drawText(text, viewWidth - getSize(radius), getSize(radius), textRect);

        //根据字体区域再重新调整画内容的位置
        Rect textR = new Rect();
        textRect.getTextBounds(text, 0, text.length(), textR);
        textRect.setColor(textColor);

        if (text.substring(0, 1).equals("1")) {
            //如果是“1”,则空余太多，字体看起来偏右
            canvas.drawText(text, viewWidth - getSize(radius) - textR.width() / 2 - getSize(1), getSize(radius) + textR.height() / 2, textRect);
        } else {
            canvas.drawText(text, viewWidth - getSize(radius) - textR.width() / 2, getSize(radius) + textR.height() / 2, textRect);
        }

    }

    /**
     * 获取密度下的大小
     *
     * @return
     */
    private float getSize(int size) {
        return size * density;
    }

    /**
     * 获取圆中的内容
     */
    public void setText(String textStr) {
        if (textStr.length() > 2) {
            textStr = textStr.substring(0, 2) + ".";
        }
        this.text = textStr;
        invalidate();
    }

    /**
     * 设置字体大小
     *
     * @param textColor
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    /**
     * 设置描边圆的颜色
     *
     * @return
     */
    public void setSideColor(int sideColor) {
        this.sideColor = sideColor;
    }

    /**
     * 设置圆的颜色
     *
     * @return
     */
    public int getCircleColor() {
        return circleColor;
    }

    /**
     * 设置圆的颜色
     *
     * @param circleColor
     */
    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }

    /**
     * 获取圆半径
     *
     * @return
     */
    public int getRadius() {
        return radius;
    }

    /**
     * 设置圆半径
     *
     * @param radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * 设置描边圆半径
     *
     * @param sideRadius
     */
    public void setSideRadius(int sideRadius) {
        this.sideRadius = sideRadius;
    }

    /**
     * 设置字体大小
     *
     * @return
     */
    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
}
