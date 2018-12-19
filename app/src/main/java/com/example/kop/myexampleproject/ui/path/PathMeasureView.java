package com.example.kop.myexampleproject.ui.path;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2018/12/19 15:20
 */
public class PathMeasureView extends View {

    private boolean isAnimTwoFinish = false;

    private int mHeight;

    private Path mInPath;

    private int mInRadius;

    private Path mOutPath;

    private int mOutRadius;

    private Paint mPaint;

    private PathMeasure mPathMeasure;

    private float mRoundValue;

    private Path mTriangleOnePath;

    private Path mTriangleTwoPath;

    private float mTriangleValue;

    private int mWidth;

    public PathMeasureView(final Context context) {
        this(context, null);
    }

    public PathMeasureView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawPath(getOutSegment(), mPaint);
        canvas.drawPath(getInSegment(), mPaint);

        canvas.save();
        canvas.rotate(-90);
        canvas.drawPath(getTriangleOneSegment(), mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(90);
        canvas.drawPath(getTriangleTwoSegment(), mPaint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        int minWidth = 200;
        int minHeight = 200;

        if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = minWidth;
        } else if (widthMode == MeasureSpec.EXACTLY && mWidth < minWidth) {
            mWidth = minWidth;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = minHeight;
        } else if (heightMode == MeasureSpec.EXACTLY && mHeight < minHeight) {
            mHeight = minHeight;
        }

        mOutRadius = (int) (Math.min(mWidth, mHeight) * 0.4f);
        mInRadius = (int) (Math.min(mWidth, mHeight) * 0.32f);

        setMeasuredDimension(mWidth, mHeight);

        initPath();
        initAnim();
    }

    private Path getInSegment() {
        mPathMeasure.setPath(mInPath, false);
        Path path = new Path();
        mPathMeasure.getSegment(0, mPathMeasure.getLength() * mRoundValue, path, true);
        return path;
    }

    private Path getOutSegment() {
        mPathMeasure.setPath(mOutPath, false);
        Path path = new Path();
        mPathMeasure.getSegment(0, mPathMeasure.getLength() * mRoundValue, path, true);
        return path;
    }

    private Path getTriangleOneSegment() {
        mPathMeasure.setPath(mTriangleOnePath, false);
        float startD;
        float stopD;
        if (isAnimTwoFinish) {
            stopD = mTriangleValue * mPathMeasure.getLength();
            startD = 0;
        } else {
            stopD = mTriangleValue * mPathMeasure.getLength();
            startD = stopD - (0.5f - Math.abs(0.5f - mTriangleValue)) * 200;
        }
        Path path = new Path();
        mPathMeasure.getSegment(startD, stopD, path, true);
        return path;
    }

    private Path getTriangleTwoSegment() {
        mPathMeasure.setPath(mTriangleTwoPath, false);
        float startD;
        float stopD;
        if (isAnimTwoFinish) {
            stopD = mTriangleValue * mPathMeasure.getLength();
            startD = 0;
        } else {
            stopD = mTriangleValue * mPathMeasure.getLength();
            startD = stopD - (0.5f - Math.abs(0.5f - mTriangleValue)) * 200;
        }
        Path path = new Path();
        mPathMeasure.getSegment(startD, stopD, path, true);
        return path;
    }

    private void initAnim() {
        ValueAnimator roundAnim = ValueAnimator.ofFloat(0, 1);
        roundAnim.setDuration(2000);
        roundAnim.addUpdateListener(animation -> {
            mRoundValue = (float) animation.getAnimatedValue();
            invalidate();
        });

        ValueAnimator triangleAnim = ValueAnimator.ofFloat(0, 1);
        triangleAnim.setDuration(1000);
        triangleAnim.addUpdateListener(animation -> {
            mTriangleValue = (float) animation.getAnimatedValue();
            invalidate();
        });
        triangleAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animator animation) {
                super.onAnimationEnd(animation);
                if (!isAnimTwoFinish) {
                    isAnimTwoFinish = true;
                    triangleAnim.setDuration(2000);
                }
            }
        });

        ValueAnimator triangleAnim2 = ValueAnimator.ofFloat(0, 1);
        triangleAnim2.setDuration(2000);
        triangleAnim2.addUpdateListener(animation -> {
            mTriangleValue = (float) animation.getAnimatedValue();
            invalidate();
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(roundAnim, triangleAnim, triangleAnim2);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animator animation) {
                super.onAnimationEnd(animation);
                isAnimTwoFinish = false;
                animatorSet.start();
            }
        });
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        mPaint.setShadowLayer(15, 0, 0, Color.WHITE);//白色光影效果
    }

    private void initPath() {
        mOutPath = new Path();
        RectF rect1 = new RectF(-mOutRadius, -mOutRadius, mOutRadius, mOutRadius);
        mOutPath.addArc(rect1, -90, -359.9f);

        mInPath = new Path();
        RectF rect2 = new RectF(-mInRadius, -mInRadius, mInRadius, mInRadius);
        mInPath.addArc(rect2, 120, -359.9f);

        float[] pos1 = new float[2];
        float[] pos2 = new float[2];
        float[] pos3 = new float[2];
        mPathMeasure = new PathMeasure(mInPath, false);
        float length = mPathMeasure.getLength();
        mPathMeasure.getPosTan(0, pos1, null);
        mPathMeasure.getPosTan(length / 3, pos2, null);
        mPathMeasure.getPosTan(length / 3 * 2, pos3, null);

        mTriangleOnePath = new Path();
        mTriangleOnePath.moveTo(pos1[0], pos1[1]);
        mTriangleOnePath.lineTo(pos2[0], pos2[1]);
        mTriangleOnePath.lineTo(pos3[0], pos3[1]);
        mTriangleOnePath.close();

        mTriangleTwoPath = new Path();
        mTriangleTwoPath.moveTo(pos1[0], pos1[1]);
        mTriangleTwoPath.lineTo(pos2[0], pos2[1]);
        mTriangleTwoPath.lineTo(pos3[0], pos3[1]);
        mTriangleTwoPath.close();
    }
}
