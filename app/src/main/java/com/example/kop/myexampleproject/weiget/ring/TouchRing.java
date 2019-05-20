package com.example.kop.myexampleproject.weiget.ring;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.ConvertUtils;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2019/1/9 14:36
 */
public class TouchRing extends View {

    private static final String TAG = "TouchRing";

    private float mStart1 = 0;

    private float mStart2 = 0;

    private float mStartBtnX, mEndBtnX;

    private float mStartBtnY, mEndBtnY;

    private float mSweep1;

    private float mSweep2;

    private int mWidth;

    private int mHeight;

    private float mRedius;

    private Paint mRingPaint;

    private RectF mRectF;

    private float mX;

    private float mY;

    private float mDegrees, mDegrees2;

    private Paint mStartPaint;

    private Paint mEndPaint;

    private float mAtan2, mAtan2_2;

    private boolean mInBtn1, mInBtn2;

    public TouchRing(final Context context) {
        this(context, null);
    }

    public TouchRing(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchRing(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
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

        mRedius = Math.min(mWidth, mHeight) / 2 * 0.8f;
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(-mRedius, -mRedius, mRedius, mRedius);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        drawRing(canvas);
        deawStart(canvas);
        deawEnd(canvas);
    }

    private void initPaint() {
        mRingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRingPaint.setStyle(Style.STROKE);
        mRingPaint.setStrokeWidth(ConvertUtils.dp2px(20));
        mRingPaint.setColor(Color.parseColor("#f7ce46"));
        mRingPaint.setStrokeCap(Cap.ROUND);

        mStartPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStartPaint.setStyle(Style.FILL);
        mStartPaint.setColor(Color.RED);

        mEndPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mEndPaint.setStyle(Style.FILL);
        mEndPaint.setColor(Color.BLUE);
    }

    private void drawRing(Canvas canvas) {
        if (mSweep1 > mSweep2) {
            canvas.drawArc(mRectF, mStart1, mSweep1, false, mRingPaint);
        } else {
            canvas.drawArc(mRectF, mStart2, mSweep2, false, mRingPaint);
        }

        Log.i(TAG, "drawRing: " + mSweep1 + "---" + mSweep2);
    }

    private void deawStart(Canvas canvas) {
        mStartBtnX = (float) (Math.cos(Math.toDegrees(mAtan2) * (Math.PI / 180)) * mRedius);
        mStartBtnY = (float) (Math.sin(Math.toDegrees(mAtan2) * (Math.PI / 180)) * mRedius);
        canvas.drawCircle(mStartBtnX, mStartBtnY, mRedius / 8, mStartPaint);
    }

    private void deawEnd(Canvas canvas) {
        mEndBtnX = (float) (Math.cos(Math.toDegrees(mAtan2_2) * (Math.PI / 180)) * mRedius);
        mEndBtnY = (float) (Math.sin(Math.toDegrees(mAtan2_2) * (Math.PI / 180)) * mRedius);
        canvas.drawCircle(mEndBtnX, mEndBtnY, mRedius / 8, mEndPaint);
    }

    private void inCircleButton1(float x, float y) {
        float x2 = Math.abs(mStartBtnX - (x - mWidth / 2));
        float y2 = Math.abs(mStartBtnY - (y - mHeight / 2));
        mInBtn1 = (int) Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2)) < mRedius / 8;
        if (mInBtn1) {
            mInBtn2 = false;
        }
    }

    private void inCircleButton2(float x, float y) {
        float x2 = Math.abs(mEndBtnX - (x - mWidth / 2));
        float y2 = Math.abs(mEndBtnY - (y - mHeight / 2));
        mInBtn2 = (int) Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2)) < mRedius / 8;
        if (mInBtn2) {
            mInBtn1 = false;
        }
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        mX = event.getX();
        mY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                inCircleButton1(mX, mY);
                inCircleButton2(mX, mY);
                break;

            case MotionEvent.ACTION_MOVE:
                if (mInBtn1) {
                    mAtan2 = (float) Math.atan2(mY - mHeight / 2, mX - mWidth / 2);
                    mDegrees = (float) (Math.toDegrees(mAtan2) + 360) % 360;

                    mStart1 = mDegrees;
                    mSweep1 = (360 - mDegrees + mSweep2) % 360;

//                    Log.i("kop", "changeFirst111: " + mStart1 + "---" + mSweep1);
                }

                if (mInBtn2) {
                    mAtan2_2 = (float) Math.atan2(mY - mHeight / 2, mX - mWidth / 2);
                    mDegrees2 = (float) (Math.toDegrees(mAtan2_2) + 360) % 360;

                    mStart2 = mStart1;
                    mSweep2 = (360 - mDegrees + mDegrees2) % 360;

//                    Log.i("kop", "changeFirst222: " + mStart2 + "---" + mSweep2);
                }

                break;

            case MotionEvent.ACTION_UP:

                mInBtn1 = false;
                mInBtn2 = false;
                break;
        }

        invalidate();
        return true;
    }


}
