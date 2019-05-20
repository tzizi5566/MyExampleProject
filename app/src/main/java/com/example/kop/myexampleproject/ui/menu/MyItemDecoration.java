package com.example.kop.myexampleproject.ui.menu;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.State;
import java.util.ArrayList;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2019-04-26 15:24
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "MyItemDecoration";

    private Paint mPaint;

    private int mDividerHeight = 100;

    private ArrayList<MySection> mList;

    private TextPaint mTextPaint;

    private Rect mTextBounds;

    private int mSpanCount;

    public MyItemDecoration(ArrayList<MySection> list) {
        super();
        mList = list;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4f);

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.parseColor("#FF000000"));
        mTextPaint.setTextSize(48f);
        mTextBounds = new Rect();

        mSpanCount = 2;
    }

    @Override
    public void onDraw(@NonNull final Canvas c, @NonNull final RecyclerView parent, @NonNull final State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(@NonNull final Canvas c, @NonNull final RecyclerView parent, @NonNull final State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View currentView = parent.getChildAt(i);
            int currentPosition = parent.getChildAdapterPosition(currentView);

            String currentName = getTitleName(mList.get(currentPosition));

            if (TextUtils.isEmpty(currentName)) {
                continue;
            }

            if (currentPosition < mSpanCount || i < mSpanCount) {
                int bottom = Math.max(currentView.getTop(), mDividerHeight);
                View nextChildView = parent.getChildAt(i + mSpanCount);
                String nextName = getTitleName(mList.get(currentPosition + mSpanCount));
                if (nextChildView != null && !TextUtils.equals(currentName, nextName) && bottom > (
                        nextChildView.getTop() - mDividerHeight)) {
                    bottom = nextChildView.getTop() - mDividerHeight;
                }
                int top = bottom - mDividerHeight;
                c.drawRect(parent.getLeft(), top, parent.getRight(), bottom, mPaint);
                mTextPaint.getTextBounds(currentName, 0, currentName.length(), mTextBounds);
                c.drawText(currentName, parent.getRight() / 2 - mTextBounds.width() / 2,
                        bottom - mDividerHeight / 2 + mTextBounds.height() / 2, mTextPaint);
                continue;
            }

            String previousName = getTitleName(mList.get(currentPosition - mSpanCount));
            if (!TextUtils.equals(currentName, previousName)) {
                int bottom = Math.max(currentView.getTop(), mDividerHeight);
                View nextChildView = parent.getChildAt(i + mSpanCount);
                String nextName = getTitleName(mList.get(currentPosition + mSpanCount));
                if (nextChildView != null && !TextUtils.equals(currentName, nextName) && bottom > (
                        nextChildView.getTop() - mDividerHeight)) {
                    bottom = nextChildView.getTop() - mDividerHeight;
                }
                int top = bottom - mDividerHeight;
                c.drawRect(parent.getLeft(), top, parent.getRight(), bottom, mPaint);
                mTextPaint.getTextBounds(currentName, 0, currentName.length(), mTextBounds);
                c.drawText(currentName, parent.getRight() / 2 - mTextBounds.width() / 2,
                        bottom - mDividerHeight / 2 + mTextBounds.height() / 2, mTextPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull final Rect outRect, @NonNull final View view,
            @NonNull final RecyclerView parent, @NonNull final State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    private String getTitleName(MySection mySection) {
        if (mySection.isHeader) {
            return null;
        } else {
            return mySection.t.getName();
        }
    }
}
