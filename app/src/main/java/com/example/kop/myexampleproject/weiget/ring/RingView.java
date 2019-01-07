package com.example.kop.myexampleproject.weiget.ring;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.ConvertUtils;
import com.example.kop.myexampleproject.R;

/**
 * 功    能: 环形进度
 * 创 建 人: KOP
 * 创建日期: 2019/1/4 13:45
 */
public class RingView extends View {

    private static final String TAG = "RingView";

    private Context mContext;

    private int mHeight;

    private RectF mInnerRectF;

    private RectF mOutRectF;

    private Paint mPaint1;

    private Paint mPaint2;

    private Paint mPaint3;

    private RectF mRectF;

    private float mSweepPercent;

    private Paint mTextPaint;

    private int mWidth;

    public RingView(final Context context) {
        this(context, null);
    }

    public RingView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPaint();
    }

    public void setValue(float percent) {
        mSweepPercent = percent;
        invalidate();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        drawOutRing(canvas);
        drawInnerRing(canvas);
        drawRing(canvas);
        drawRingValue(canvas);
        drawText(canvas);
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
        } else if (heightMode == MeasureSpec.EXACTLY && mWidth < minHeight) {
            mHeight = minHeight;
        }
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int min = Math.min(mWidth, mHeight);
        final float outRadius = min / 2 * 0.8f;
        final float radius = outRadius - ConvertUtils.dp2px(20);
        final float innerRadius = radius - ConvertUtils.dp2px(20);

        mOutRectF = new RectF(-outRadius, -outRadius, outRadius, outRadius);
        mInnerRectF = new RectF(-innerRadius, -innerRadius, innerRadius, innerRadius);
        mRectF = new RectF(
                -innerRadius - (outRadius - innerRadius) / 2,
                -innerRadius - (outRadius - innerRadius) / 2,
                innerRadius + (outRadius - innerRadius) / 2,
                innerRadius + (outRadius - innerRadius) / 2);
    }

    private void drawInnerRing(Canvas canvas) {
        canvas.drawArc(mInnerRectF, 135, 270, false, mPaint1);
    }

    private void drawOutRing(Canvas canvas) {
        canvas.drawArc(mOutRectF, 150, 240, false, mPaint1);
    }

    private void drawRing(Canvas canvas) {
        canvas.drawArc(mRectF, 150, 240, false, mPaint2);
    }

    private void drawRingValue(Canvas canvas) {
        canvas.drawArc(mRectF, 150, 240 * mSweepPercent, false, mPaint3);
    }

    private void drawText(Canvas canvas) {
        String value = mContext.getString(R.string.point_two, 500 * mSweepPercent);
        float width = mTextPaint.measureText(value);
        float height = (mTextPaint.ascent() + mTextPaint.descent()) / 2;
        canvas.drawText(value, -width / 2, -height / 2, mTextPaint);
    }

    private void initPaint() {
        mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint1.setColor(Color.parseColor("#a5d5f7"));
        mPaint1.setStyle(Style.STROKE);
        mPaint1.setStrokeWidth(ConvertUtils.dp2px(2));

        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setColor(Color.parseColor("#e5e5e5"));
        mPaint2.setStyle(Style.STROKE);
        mPaint2.setStrokeCap(Cap.ROUND);
        mPaint2.setStrokeWidth(ConvertUtils.dp2px(14));

        mPaint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint3.setColor(Color.parseColor("#a5d5f7"));
        mPaint3.setStyle(Style.STROKE);
        mPaint3.setStrokeCap(Cap.ROUND);
        mPaint3.setStrokeWidth(ConvertUtils.dp2px(14));

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(ConvertUtils.sp2px(16));
    }
}
