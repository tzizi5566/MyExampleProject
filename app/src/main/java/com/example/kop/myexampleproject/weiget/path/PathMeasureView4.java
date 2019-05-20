package com.example.kop.myexampleproject.weiget.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.ConvertUtils;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2019/3/4 09:17
 */
public class PathMeasureView4 extends View {

    private Path mDstPath;

    private int mWidth;

    private int mHeight;

    private Path mPath;

    private Paint mPaint;

    private PathMeasure mPathMeasure;

    private float mRadius;

    private float mPercent;

    public PathMeasureView4(final Context context) {
        this(context, null);
    }

    public PathMeasureView4(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView4(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
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

        mRadius = Math.min(mWidth, mHeight) * 0.8f / 2;
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initPath();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(ConvertUtils.dp2px(6));
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeCap(Cap.ROUND);
        mPaint.setStrokeJoin(Join.ROUND);
    }

    private void initPath() {
        mPath = new Path();
        mPath.addCircle(mWidth / 2, mHeight / 2, mRadius, Direction.CCW);

        mPathMeasure = new PathMeasure(mPath, false);
        float[] pos = new float[2];
        float[] pos2 = new float[2];
        float[] pos3 = new float[2];
        mPathMeasure.getPosTan(mPathMeasure.getLength() / 2, pos, null);
        mPathMeasure.getPosTan(mPathMeasure.getLength() / 4 * 3, pos2, null);
        mPathMeasure.getPosTan(mPathMeasure.getLength() / 10, pos3, null);

        mPath.moveTo(pos[0] * 2.8f, pos[1] * 1.1f);
        mPath.lineTo(pos2[0], pos2[1] - pos2[1] * 0.2f);
        mPath.lineTo(pos3[0] - pos3[1] * 0.4f, pos3[1] + pos3[1] * 0.4f);

        mPathMeasure.setPath(mPath, false);

        mDstPath = new Path();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        mDstPath.reset();
        mPathMeasure.getSegment(0, mPathMeasure.getLength() * mPercent, mDstPath, true);
        canvas.drawPath(mDstPath, mPaint);
    }

    public void setPercent(final float percent) {
        mPercent = percent;
        postInvalidate();
    }
}
