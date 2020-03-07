package com.example.kop.myexampleproject.weiget.chart;

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
 * 功    能: 雷达图表
 * 创 建 人: KOP
 * 创建日期: 2019-06-03 15:27
 */
public class RadarChartTestView extends View {

    private static final String TAG = "RadarChartTestView";

    /**
     * 有几层
     */
    private static final int LINE_COUNT = 4;

    /**
     * 有几个角
     */
    private static final int ANG_COUNT = 5;

    private static final float MAX_DATA = 100.0f;

    private int mWidth, mHeight;

    private Path mLinePath, mLinePath2, mDataPath;

    private float mRadius;

    private Paint mLinePaint, mDataPaint;

    private float[] mData = new float[]{53.3f, 42.2f, 63.5f, 41, 79};


    public RadarChartTestView(final Context context) {
        this(context, null);
    }

    public RadarChartTestView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarChartTestView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);

        int minWidth = ConvertUtils.dp2px(100);

        if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = minWidth;
        } else if (widthMode == MeasureSpec.EXACTLY && mWidth < minWidth) {
            mWidth = minWidth;
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        int minHeight = ConvertUtils.dp2px(100);

        if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = minHeight;
        } else if (heightMode == MeasureSpec.EXACTLY && mHeight < minHeight) {
            mHeight = minHeight;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRadius = Math.min(mWidth, mHeight) / 2f * 0.8f;

        initPath();
        initLine();
        initData();
    }

    private void initPaint() {
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Style.STROKE);
        mLinePaint.setStrokeWidth(ConvertUtils.dp2px(1));
        mLinePaint.setColor(Color.BLACK);

        mDataPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDataPaint.setStyle(Style.FILL);
        mDataPaint.setColor(Color.RED);
        mDataPaint.setAlpha(155);
    }

    private void initPath() {
        mLinePath = new Path();
        float r = mRadius / LINE_COUNT;
        float angdeg = (float) (2 * Math.PI / ANG_COUNT);
        for (int i = 0; i <= LINE_COUNT; i++) {
            float r2 = r * i;
            for (int j = 0; j < ANG_COUNT; j++) {
                if (j == 0) {
                    mLinePath.moveTo(0, -r2);
                } else {
                    //个人猜想：为什么y轴半径为负数，有可能是按手机坐标象限来的。
                    float x = (float) (Math.sin(angdeg * j) * r2);
                    float y = (float) (Math.cos(angdeg * j) * -r2);
                    mLinePath.lineTo(x, y);
                }
            }
            mLinePath.close();
        }
    }

    private void initLine() {
        mLinePath2 = new Path();
        float angdeg = (float) (2 * Math.PI / ANG_COUNT);
        for (int i = 0; i < ANG_COUNT; i++) {
            mLinePath2.moveTo(0, 0);
            float x = (float) (Math.sin(angdeg * i) * mRadius);
            float y = (float) (Math.cos(angdeg * i) * -mRadius);
            mLinePath2.lineTo(x, y);
        }
    }

    private void initData() {
        mDataPath = new Path();
        float angdeg = (float) (2 * Math.PI / ANG_COUNT);
        for (int i = 0; i < mData.length; i++) {
            if (i == 0) {
                mDataPath.moveTo(0, mData[i] / MAX_DATA * -mRadius);
            } else {
                float x = (float) (Math.sin(angdeg * i) * (mData[i] / MAX_DATA * mRadius));
                float y = (float) (Math.cos(angdeg * i) * (mData[i] / MAX_DATA * -mRadius));
                mDataPath.lineTo(x, y);
            }
        }
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2f, mHeight / 2f);
        canvas.drawPath(mLinePath, mLinePaint);
        canvas.drawPath(mLinePath2, mLinePaint);
        canvas.drawPath(mDataPath, mDataPaint);
    }
}
