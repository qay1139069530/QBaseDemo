package com.qbase.auxilibrary.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qbase.auxilibrary.R;

public class QCirclePoint extends LinearLayout {


    private GradientDrawable selected_point, unselected_point;

    /**
     * 圆大小
     */
    private int pointSize;
    /**
     * 圆数量
     */
    private int pointCount;

    /**
     * 圆左右间距
     */
    private int pointMargin;

    /**
     * 选中 未选中颜色
     */
    private int point_unselected_color, point_selected_color;

    /**
     * 每个点的样式
     */
    private LayoutParams layoutParams;


    private ImageView motionPoint;
    private int mPointMargin;


    public QCirclePoint(Context context) {
        super(context);
    }

    public QCirclePoint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public QCirclePoint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QCirclePoint);
            pointSize = typedArray.getInteger(R.styleable.QCirclePoint_pointSize, 8);
            pointCount = typedArray.getInteger(R.styleable.QCirclePoint_pointCount, 0);
            pointMargin = typedArray.getInteger(R.styleable.QCirclePoint_pointMargin, 0);
            point_selected_color = typedArray.getColor(R.styleable.QCirclePoint_point_selected_color, 0);
            point_unselected_color = typedArray.getColor(R.styleable.QCirclePoint_point_unselected_color, 0);
            typedArray.recycle();
        }

        if (Build.VERSION.SDK_INT < 21) {
            onPointDrawable();
        } else {
            onPointDrawable21();
        }

        if (point_selected_color != 0) {
            selected_point.setColor(point_selected_color);
        }
        if (point_unselected_color != 0) {
            unselected_point.setColor(point_unselected_color);
        }

        layoutParams = new LayoutParams(pointSize, pointSize);
        layoutParams.bottomMargin = pointMargin;
        layoutParams.topMargin = pointMargin;
        layoutParams.leftMargin = pointMargin;
        layoutParams.rightMargin = pointMargin;

        removeAllViews();
        initUnSelectPoint(context);
    }

    @TargetApi(21)
    private void onPointDrawable21() {
        selected_point = (GradientDrawable) getResources().getDrawable(R.drawable.shape_point_selected, null);
        unselected_point = (GradientDrawable) getResources().getDrawable(R.drawable.shape_point_normal, null);
    }

    private void onPointDrawable() {
        selected_point = (GradientDrawable) getResources().getDrawable(R.drawable.shape_point_selected);
        unselected_point = (GradientDrawable) getResources().getDrawable(R.drawable.shape_point_normal);
    }

    /**
     * 所有的点
     */
    public void initUnSelectPoint(Context context) {
        //移动的点
        motionPoint = new ImageView(context);
//        LayoutParams lp = new LayoutParams(pointSize+5, pointSize+5);
//        lp.leftMargin = pointMargin;
//        lp.rightMargin = pointMargin;

        motionPoint.setImageDrawable(selected_point);
        motionPoint.setLayoutParams(layoutParams);
        addView(motionPoint);
        //清除所有View
        for (int i = 0; i < pointCount - 1; i++) {
            // 设置底部小圆点(灰色)
            ImageView point = new ImageView(context);
            point.setImageDrawable(unselected_point);
            // 设置灰色点的布局参数
            point.setLayoutParams(layoutParams);
            addView(point);
        }
    }


    /**
     * 移动到某个位置
     */
    public void onPointScroll(final int toPosition) {
        int index = indexOfChild(motionPoint);
        if (index == -1) {
            addView(motionPoint, 0);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    removeView(motionPoint);
                    addView(motionPoint, toPosition);
                }
            });
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public int getPointSize() {
        return pointSize;
    }

    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
    }

    public int getPointCount() {
        return pointCount;
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
        removeAllViews();
        initUnSelectPoint(getContext());
    }
}