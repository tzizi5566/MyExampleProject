package com.example.kop.myexampleproject.weiget.point;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.ConvertUtils;
import com.example.kop.myexampleproject.entity.Point;
import java.util.LinkedList;

/**
 * 功    能: 手势解锁
 * 创 建 人: KOP
 * 创建日期: 2019-05-27 15:00
 */
public class PointView extends View {

    private static final String TAG = "PointView";

    private static final int ROW_COUNT = 3;

    private int mWidth, mHeight;

    private Paint mPaint, mFullPaint, mLinePaint;

    /**
     * 每个圆的半径
     */
    private float mRadius;

    /**
     * 所有圆圈的中心坐标点
     */
    private LinkedList<Point> mCirclePoints = new LinkedList<>();

    /**
     * 所有经过的圆圈中心坐标点
     */
    private LinkedList<Point> mLastPoints = new LinkedList<>();

    /**
     * 初始为所有圆圈的中心坐标点，每经过一个圆删除该圆的坐标点，保留没有经过的圆的坐标点
     */
    private LinkedList<Point> mRestPoints = new LinkedList<>();

    /**
     * 手指点击的x y坐标点
     */
    private float mX, mY;

    private Path mPath = new Path();

    private Path mLastPath = new Path();

    private Path mLinePath = new Path();

    private boolean mCanMove = false;

    private String mSavePassword;

    private Runnable mRunnable = this::resetCanvas;


    public PointView(final Context context) {
        this(context, null);
    }

    public PointView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPath();
        mSavePassword = "369";
    }

    private void initPath() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(ConvertUtils.dp2px(2));
        mPaint.setColor(Color.BLUE);

        mFullPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFullPaint.setStyle(Style.FILL);
        mFullPaint.setColor(Color.BLUE);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Style.STROKE);
        mLinePaint.setStrokeWidth(ConvertUtils.dp2px(2));
        mLinePaint.setColor(Color.BLUE);
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
        mRadius = mWidth / (ROW_COUNT * 4f + 2f);
        initCircle();
    }

    /**
     * 初始化9个圆圈中心点
     */
    private void initCircle() {
        int index = 0;
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < ROW_COUNT; j++) {
                index++;
                float x = (j * 4 + 3) * mRadius;
                float y = (i * 4 + 3) * mRadius;
                Point point = new Point(x, y, String.valueOf(index));
                mCirclePoints.add(point);
                mRestPoints.add(point);

                mPath.addCircle(point.getX(), point.getY(), mRadius, Direction.CW);
            }
        }
    }

    /**
     * 手指抬起后重置View
     */
    private void resetCanvas() {
        mFullPaint.setColor(Color.BLUE);
        mLinePaint.setColor(Color.BLUE);

        mCirclePoints.clear();
        mLastPoints.clear();
        mRestPoints.clear();

        mPath.reset();
        mLastPath.reset();
        mLinePath.reset();

        initCircle();
        invalidate();
    }

    private void passwordSuccess() {
        mFullPaint.setColor(Color.GREEN);
        mLinePaint.setColor(Color.GREEN);
        invalidate();
    }

    private void passwordFail() {
        mFullPaint.setColor(Color.RED);
        mLinePaint.setColor(Color.RED);
        invalidate();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
        canvas.drawPath(mLastPath, mFullPaint);
        canvas.drawPath(mLinePath, mLinePaint);

        //根据手指坐标画直线路径
        if (mLastPoints.size() > 0 && mCanMove) {
            Point lastPoint = mLastPoints.get(mLastPoints.size() - 1);
            canvas.drawLine(lastPoint.getX(), lastPoint.getY(), mX, mY, mLinePaint);
        }
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                removeCallbacks(mRunnable);

                resetCanvas();

                mX = event.getX();
                mY = event.getY();

                for (int i = 0; i < mCirclePoints.size(); i++) {
                    Point point = mCirclePoints.get(i);
                    if (Math.abs(mX - point.getX()) <= mRadius && Math.abs(mY - point.getY()) <= mRadius) {
                        mLastPoints.add(point);
                        mRestPoints.remove(point);
                        mCanMove = true;

                        Log.i(TAG, "onTouchEvent: " + point.getX());
                        mLinePath.moveTo(point.getX(), point.getY());
                        mLastPath.addCircle(point.getX(), point.getY(), mRadius * 0.8f, Direction.CW);
                        break;
                    }
                }

                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                mX = event.getX();
                mY = event.getY();

                if (mCanMove) {
                    for (int i = 0; i < mRestPoints.size(); i++) {
                        Point point = mRestPoints.get(i);
                        if (Math.abs(mX - point.getX()) <= mRadius && Math.abs(mY - point.getY()) <= mRadius) {
                            mLastPoints.add(point);
                            mRestPoints.remove(point);

                            mLinePath.lineTo(point.getX(), point.getY());
                            mLastPath.addCircle(point.getX(), point.getY(), mRadius * 0.8f, Direction.CW);
                            break;
                        }
                    }
                }

                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                if (mCanMove) {
                    mCanMove = false;

                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mLastPoints.size(); i++) {
                        Point point = mLastPoints.get(i);
                        sb.append(point.getIndex());
                    }
                    if (sb.toString().equals(mSavePassword)) {
                        passwordSuccess();
                    } else {
                        passwordFail();
                    }

                    postDelayed(mRunnable, 500);
                }
                break;
        }
        return true;
    }
}
