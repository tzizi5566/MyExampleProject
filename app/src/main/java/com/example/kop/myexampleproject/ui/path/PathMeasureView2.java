package com.example.kop.myexampleproject.ui.path;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.ConvertUtils;

/**
 * 功    能: PathMeasure练习
 * 创 建 人: KOP
 * 创建日期: 2018/12/20 09:41
 */
public class PathMeasureView2 extends View {

    private int mHeight;

    private Paint mPaint;

    private PathMeasure mPathMeasure;

    private float mRadius;

    private Path mRoundPath;

    private float mRoundValue;

    private Path mSearchPath;

    private float mSearchValue;

    private int mWidth;

    public PathMeasureView2(final Context context) {
        this(context, null);
    }

    public PathMeasureView2(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView2(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        mPathMeasure = new PathMeasure();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawPath(getRoundSegment(), mPaint);
        canvas.drawPath(getSearchSegment(), mPaint);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        int minWidth = ConvertUtils.dp2px(50f);
        int minHeight = ConvertUtils.dp2px(50f);

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

        mRadius = Math.min(mWidth, mHeight) / 2 * 0.8f;
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initRoundPath();

        initSearchPath();

        initAnim();
    }

    private Path getRoundSegment() {
        mPathMeasure.setPath(mRoundPath, false);
        float stopD = mPathMeasure.getLength() * mRoundValue;
        float startD = stopD - (0.5f - Math.abs(0.5f - mRoundValue)) * 200;
        Path path = new Path();
        mPathMeasure.getSegment(startD, stopD, path, true);
        return path;
    }

    private Path getSearchSegment() {
        mPathMeasure.setPath(mSearchPath, false);
        Path path = new Path();
        mPathMeasure.getSegment(0, mPathMeasure.getLength() * mSearchValue, path, true);
        return path;
    }

    private void initAnim() {
        ValueAnimator roundAnimator = ValueAnimator.ofFloat(0, 1);
        roundAnimator.setRepeatCount(2);
        roundAnimator.setDuration(2000);
        roundAnimator.addUpdateListener(animation -> {
            mRoundValue = (float) animation.getAnimatedValue();
            invalidate();
        });
        roundAnimator.start();

        ValueAnimator searchAnimator = ValueAnimator.ofFloat(0, 1);
        searchAnimator.setDuration(2000);
        searchAnimator.addUpdateListener(animation -> {
            mSearchValue = (float) animation.getAnimatedValue();
            invalidate();
        });

        roundAnimator.addListener(new AnimatorListener() {
            @Override
            public void onAnimationCancel(final Animator animation) {

            }

            @Override
            public void onAnimationEnd(final Animator animation) {
                searchAnimator.start();
            }

            @Override
            public void onAnimationRepeat(final Animator animation) {

            }

            @Override
            public void onAnimationStart(final Animator animation) {

            }
        });
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(10f);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeCap(Cap.ROUND);
    }

    private void initRoundPath() {
        mRoundPath = new Path();
        RectF rectF = new RectF(-mRadius, -mRadius, mRadius, mRadius);
        mRoundPath.addArc(rectF, 45, -359.9f);
    }

    private void initSearchPath() {
        mSearchPath = new Path();
        mPathMeasure.setPath(mRoundPath, false);
        float[] pos = new float[2];
        mPathMeasure.getPosTan(0, pos, null);

        RectF rectF = new RectF(-mRadius + 50, -mRadius + 50, mRadius - 50, mRadius - 50);
        Path path = new Path();
        path.addArc(rectF, 45, 359.9f);

        mPathMeasure.setPath(path, false);
        float[] pos2 = new float[2];
        mPathMeasure.getPosTan(0, pos2, null);

        mSearchPath.moveTo(pos[0], pos[1]);
        mSearchPath.lineTo(pos2[0], pos2[1]);
        mSearchPath.arcTo(rectF, 45, -359.9f);
    }
}
