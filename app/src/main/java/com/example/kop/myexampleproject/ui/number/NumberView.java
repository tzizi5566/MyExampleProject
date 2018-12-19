package com.example.kop.myexampleproject.ui.number;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.ConvertUtils;
import com.example.kop.myexampleproject.R;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2018/12/18 14:01
 */
public class NumberView extends View {

    //绘制不能从  坐标0 开始 会有 stroke*1 的偏移量
    private int contentWidth, contentHeight;  //内容宽度 内容高度

    private ValueAnimator mAnimator;

    private Context mContext;

    private float mNum;

    private Paint mPaint;

    private float mValue;

    public NumberView(final Context context) {
        this(context, null);
    }

    public NumberView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        anim();
        mContext = context;
    }

    public void setNum(float num) {
        mNum = num;
        invalidate();
    }

    public void startAnim() {
        if (mAnimator != null) {
            mAnimator.start();
        }
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mContext.getString(R.string.point_two, mNum * mValue), contentWidth / 2, contentHeight
                / 2, mPaint);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);

        //设定最小值时，增加 stoke 的偏移保证 边界绘制完整
        int minWidth = ConvertUtils.dp2px(30);

        int minHeight = ConvertUtils.dp2px(35);

        //判断 测量模式  如果是  wrap_content 需要对 宽高进行限定
        //同时确定 高度 也对 最小值进行限定

        if (widthMode == MeasureSpec.AT_MOST) {
            width = minWidth;
        } else if (widthMode == MeasureSpec.EXACTLY && width < minWidth) {
            width = minWidth;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            height = minHeight;
        } else if (heightMode == MeasureSpec.EXACTLY && height < minHeight) {
            height = minHeight;
        }

        // 在确定宽高之后 对内容 宽高再次进行计算，留出 stroke 的偏移
        contentWidth = width;

        contentHeight = height;

        setMeasuredDimension(width, height);
    }

    private void anim() {
        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setDuration(500);
        mAnimator.addUpdateListener(animation -> {
            mValue = (float) animation.getAnimatedValue();
            invalidate();
        });
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(ConvertUtils.sp2px(20));
    }
}
