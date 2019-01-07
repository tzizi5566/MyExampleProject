package com.example.kop.myexampleproject.weiget.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.blankj.utilcode.util.ConvertUtils;

/**
 * 功    能: PathMeasure练习
 * 创 建 人: KOP
 * 创建日期: 2018/12/18 09:37
 */
@RequiresApi(api = VERSION_CODES.LOLLIPOP)
public class PathView extends View {

    //绘制不能从  坐标0 开始 会有 stroke*1 的偏移量
    private int contentWidth, contentHeight;  //内容宽度 内容高度

    private float fraction;  //绘制的比例

    private Paint mPaint;

    private float roundCorner;  //外层 圆角矩形 圆角半径

    private Path roundPath; //最外层 圆形Path

    private int strokeWidth;  //线宽

    public PathView(final Context context) {
        this(context, null);
    }

    public PathView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void onDrawPath(Canvas canvas, Paint paint) {
        if (fraction <= 0) {
            return;
        }

        Path dst = new Path();
        PathMeasure measure = new PathMeasure(roundPath, false);// 将 Path 与 PathMeasure 关联

        float length = measure.getLength();

        // 截取一部分 并使用 moveTo 保持截取得到的 Path 第一个点的位置不变
        measure.getSegment(0, length * fraction, dst, true);

        canvas.drawPath(dst, paint);
    }

    public void setFraction(final float fraction) {
        this.fraction = fraction;
        postInvalidate();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        onDrawPath(canvas, mPaint);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);

        //设定最小值时，增加 stoke 的偏移保证 边界绘制完整
        int minWidth = ConvertUtils.dp2px(30) + strokeWidth * 2;

        int minHeight = ConvertUtils.dp2px(35) + strokeWidth * 2;

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
        contentWidth = width - strokeWidth * 2;

        contentHeight = height - strokeWidth * 2;

        setMeasuredDimension(width, height);

        initNeedParamn();

        initPath();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    /**
     * 初始化绘制所需要的参数
     */
    private void initNeedParamn() {
        roundCorner = contentHeight / 10f;
    }

    private void initPath() {
        roundPath = new Path();
        roundPath.addArc(contentWidth / 2 - roundCorner,
                contentHeight / 2 - roundCorner,
                contentWidth / 2 + roundCorner,
                contentHeight / 2 + roundCorner,
                45,
                359.9f);

        Path outRoundPath = new Path();
        outRoundPath.addArc(contentWidth / 2 - roundCorner - 50,
                contentHeight / 2 - roundCorner - 50,
                contentWidth / 2 + roundCorner + 50,
                contentHeight / 2 + roundCorner + 50,
                45,
                359.9f);

        float[] pos = new float[2];
        PathMeasure measure = new PathMeasure();
        measure.setPath(outRoundPath, false);
        measure.getPosTan(0, pos, null);

        roundPath.lineTo(pos[0], pos[1]);
    }
}
