package com.example.kop.myexampleproject.weiget.degree;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.ConvertUtils;

/**
 * 功    能: 角度弧度、坐标点练习
 * 创 建 人: KOP
 * 创建日期: 2019-05-30 13:52
 */
public class DegreeView extends View {

    private static final String TAG = "DegreeView";

    private int mWidth, mHeight;

    private Paint mCellPaint, mCoordinatesPaint, mPointPaint;

    private Path mCellPath, mCoordinatesPath;

    private static final int CELL_WIDTH = ConvertUtils.dp2px(20);

    private float mRadius;

    private float mPointX, mPointY;


    public DegreeView(final Context context) {
        this(context, null);
    }

    public DegreeView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DegreeView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mCellPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCellPaint.setStyle(Style.STROKE);
        mCellPaint.setStrokeWidth(ConvertUtils.dp2px(1));
        mCellPaint.setColor(Color.parseColor("#DDDDDD"));

        mCoordinatesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCoordinatesPaint.setStyle(Style.STROKE);
        mCoordinatesPaint.setStrokeWidth(ConvertUtils.dp2px(1.5f));
        mCoordinatesPaint.setColor(Color.parseColor("#000000"));

        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setStyle(Style.FILL);
        mPointPaint.setStrokeWidth(ConvertUtils.dp2px(10f));
        mPointPaint.setColor(Color.RED);
    }

    private void initCell() {
        mCellPath = new Path();

        int column = mWidth / CELL_WIDTH;
        int row = mHeight / CELL_WIDTH;

        for (int i = 0; i < column; i++) {
            mCellPath.moveTo(CELL_WIDTH * (1 + i), 0);
            mCellPath.lineTo(CELL_WIDTH * (1 + i), mHeight);
        }

        for (int i = 0; i < row; i++) {
            mCellPath.moveTo(0, CELL_WIDTH * (1 + i));
            mCellPath.lineTo(mWidth, CELL_WIDTH * (1 + i));
        }
    }

    private void initCoordinates() {
        mCoordinatesPath = new Path();
        mCoordinatesPath.moveTo(mWidth / 2f, 0);
        mCoordinatesPath.lineTo(mWidth / 2f, mHeight);
        mCoordinatesPath.moveTo(0, mHeight / 2f);
        mCoordinatesPath.lineTo(mWidth, mHeight / 2f);

        int column = mWidth / CELL_WIDTH;
        int row = mHeight / CELL_WIDTH;

        for (int i = 0; i < column; i++) {
            mCoordinatesPath.moveTo(CELL_WIDTH * (1 + i), mHeight / 2f);
            mCoordinatesPath.lineTo(CELL_WIDTH * (1 + i), mHeight / 2f - 10);
        }

        for (int i = 0; i < row; i++) {
            mCoordinatesPath.moveTo(mWidth / 2f, CELL_WIDTH * (1 + i));
            mCoordinatesPath.lineTo(mWidth / 2f + 10, CELL_WIDTH * (1 + i));
        }
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

        mRadius = Math.min(mWidth, mHeight) / 2f * 0.6f;
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initCell();
        initCoordinates();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mCellPath, mCellPaint);
        canvas.drawPath(mCoordinatesPath, mCoordinatesPaint);
        canvas.drawCircle(mWidth / 2f, mHeight / 2f, mRadius, mCoordinatesPaint);
        canvas.drawPoint(mPointX, mPointY, mPointPaint);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        //已知x、y坐标点求出弧度
        double atan2 = Math.atan2(y - mHeight / 2f, x - mWidth / 2f);

        //已知弧度求出角度
        double degrees = (Math.toDegrees(atan2) + 360) % 360;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //已知圆心、半径求出x、y坐标点
                //Math.sin和Math.cos内非角度而是弧度
                mPointX = (float) ((Math.cos(atan2) * mRadius) + mWidth / 2f);
                mPointY = (float) ((Math.sin(atan2) * mRadius) + mHeight / 2f);
                break;

            case MotionEvent.ACTION_MOVE:
                mPointX = (float) ((Math.cos(atan2) * mRadius) + mWidth / 2f);
                mPointY = (float) ((Math.sin(atan2) * mRadius) + mHeight / 2f);
                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        invalidate();
        return true;
    }
}
