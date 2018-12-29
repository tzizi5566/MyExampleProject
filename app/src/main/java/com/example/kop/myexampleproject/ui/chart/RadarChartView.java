package com.example.kop.myexampleproject.ui.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.ConvertUtils;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2018/12/29 11:29
 */
public class RadarChartView extends View {

    private static final String TAG = "RadarChartView";

    private static final int LINE_COUNT = 4;

    private static final int ANGLE_COUNT = 5;

    private static final float MAX_DATA = 10.0f;

    private Paint mContentPaint;

    private Path mContentPath;

    private int[] mDatas = new int[]{9, 5, 2, 7, 5};

    private int mHeight;

    private Paint mLinePaint;

    private Path mLinePath;

    private float mRadius;

    private int mWidth;

    public RadarChartView(final Context context) {
        this(context, null);
    }

    public RadarChartView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarChartView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawPath(mLinePath, mLinePaint);
        canvas.drawPath(mContentPath, mContentPaint);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int minWidth = ConvertUtils.dp2px(50);

        if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = minWidth;
        } else if (widthMode == MeasureSpec.EXACTLY && mWidth < minWidth) {
            mWidth = minWidth;
        }

        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int minHeight = ConvertUtils.dp2px(50);

        if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = minHeight;
        } else if (heightMode == MeasureSpec.EXACTLY && mHeight < minHeight) {
            mHeight = minHeight;
        }

        mRadius = Math.min(mWidth, mHeight) / 2 * 0.8f;
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initLinePath1();
        initLinePath2();
        initContentPath();
    }

    private void initContentPath() {
        mContentPath = new Path();
        float angle = (float) (2 * Math.PI / ANGLE_COUNT);
        for (int i = 0; i < ANGLE_COUNT; i++) {
            if (i == 0) {
                mContentPath.moveTo(0, -mRadius * (mDatas[i] / MAX_DATA));
            } else {
                float x = (float) ((mRadius * (mDatas[i] / MAX_DATA)) * Math.sin(angle * i));
                float y = (float) ((-mRadius * (mDatas[i] / MAX_DATA)) * Math.cos(angle * i));
                mContentPath.lineTo(x, y);
            }
        }
    }

    private void initLinePath1() {
        mLinePath = new Path();
        float angle = (float) (2 * Math.PI / ANGLE_COUNT);
        for (int i = 0; i < LINE_COUNT; i++) {
            float radius = (float) ((i + 1.0) / LINE_COUNT * mRadius);
            for (int j = 0; j < ANGLE_COUNT; j++) {
                if (j == 0) {
                    mLinePath.moveTo(0, -radius);
                } else {
                    float x = (float) (radius * Math.sin(angle * j));
                    float y = (float) (-radius * Math.cos(angle * j));
                    mLinePath.lineTo(x, y);
                }
            }
            mLinePath.close();
        }
    }

    private void initLinePath2() {
        float angle = (float) (2 * Math.PI / ANGLE_COUNT);
        for (int i = 0; i <= LINE_COUNT; i++) {
            mLinePath.moveTo(0, 0);
            float x = (float) (mRadius * Math.sin(angle * i));
            float y = (float) (-mRadius * Math.cos(angle * i));
            mLinePath.lineTo(x, y);
        }
    }

    private void initPaint() {
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Style.STROKE);
        mLinePaint.setStrokeWidth(3);
        mLinePaint.setColor(Color.BLACK);

        mContentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mContentPaint.setStyle(Style.FILL);
        mContentPaint.setColor(Color.RED);
        mContentPaint.setAlpha(200);
    }
}
