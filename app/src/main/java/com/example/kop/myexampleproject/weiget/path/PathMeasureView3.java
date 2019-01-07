package com.example.kop.myexampleproject.weiget.path;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.ConvertUtils;

/**
 * 功    能: PathMeasure练习
 * 创 建 人: KOP
 * 创建日期: 2018/12/21 09:46
 */
public class PathMeasureView3 extends View {

    private int mHeight;

    private Paint mPaint;

    private Path mPath1;

    private float mPath1Value;

    private Path mPath2;

    private float mPath2Value;

    private Path mPath3;

    private float mPath3Value;

    private PathMeasure mPathMeasure;

    private int mWidth;

    public PathMeasureView3(final Context context) {
        this(context, null);
    }

    public PathMeasureView3(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView3(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        mPathMeasure = new PathMeasure();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawPath(getPath1Segment(), mPaint);
        canvas.drawPath(getPath2Segment(), mPaint);
        canvas.drawPath(getPath3Segment(), mPaint);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);

        int minWidth = ConvertUtils.dp2px(20);

        if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = minWidth;
        } else if (widthMode == MeasureSpec.EXACTLY && mWidth < minWidth) {
            mWidth = minWidth;
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        int minHeight = ConvertUtils.dp2px(20);

        if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = minHeight;
        } else if (heightMode == MeasureSpec.EXACTLY && mHeight < minHeight) {
            mHeight = minHeight;
        }


    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initPath();

        initAnim();
    }

    private Path getPath1Segment() {
        mPathMeasure.setPath(mPath1, false);
        float stopD = mPathMeasure.getLength() * Math.abs(0.5f - mPath1Value) + mPathMeasure.getLength() / 4;
        Path path = new Path();
        mPathMeasure.getSegment(0, stopD, path, true);
        return path;
    }

    private Path getPath2Segment() {
        mPathMeasure.setPath(mPath2, false);
        float stopD = mPathMeasure.getLength() * Math.abs(0.5f - mPath2Value) + mPathMeasure.getLength() / 4;
        Path path = new Path();
        mPathMeasure.getSegment(0, stopD, path, true);
        return path;
    }

    private Path getPath3Segment() {
        mPathMeasure.setPath(mPath3, false);
        float stopD = mPathMeasure.getLength() * Math.abs(0.5f - mPath3Value) + mPathMeasure.getLength() / 4;
        Path path = new Path();
        mPathMeasure.getSegment(0, stopD, path, true);
        return path;
    }

    private void initAnim() {
        ValueAnimator animator1 = ValueAnimator.ofFloat(0, 1);
        animator1.setDuration(800);
        animator1.setRepeatCount(ValueAnimator.INFINITE);
        animator1.addUpdateListener(animation -> {
            mPath1Value = (float) animation.getAnimatedValue();
            invalidate();
        });
        animator1.start();

        ValueAnimator animator2 = ValueAnimator.ofFloat(0, 1);
        animator2.setDuration(800);
        animator2.setStartDelay(200);
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        animator2.addUpdateListener(animation -> {
            mPath2Value = (float) animation.getAnimatedValue();
            invalidate();
        });
        animator2.start();

        ValueAnimator animator3 = ValueAnimator.ofFloat(0, 1);
        animator3.setDuration(800);
        animator3.setStartDelay(400);
        animator3.setRepeatCount(ValueAnimator.INFINITE);
        animator3.addUpdateListener(animation -> {
            mPath3Value = (float) animation.getAnimatedValue();
            invalidate();
        });
        animator3.start();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Cap.ROUND);
        mPaint.setColor(Color.WHITE);
    }

    private void initPath() {
        mPath1 = new Path();
        mPath1.moveTo(-30, mHeight / 2);
        mPath1.lineTo(-30, -mHeight / 2 + 30);

        mPath2 = new Path();
        mPath2.moveTo(0, mHeight / 2);
        mPath2.lineTo(0, -mHeight / 2 + 30);

        mPath3 = new Path();
        mPath3.moveTo(30, mHeight / 2);
        mPath3.lineTo(30, -mHeight / 2 + 30);
    }
}
