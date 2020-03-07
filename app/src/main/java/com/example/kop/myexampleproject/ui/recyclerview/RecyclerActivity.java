package com.example.kop.myexampleproject.ui.recyclerview;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat.LayoutParams;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kop.myexampleproject.R;
import com.example.kop.myexampleproject.ui.recyclerview.StationListAdapter.OnStationSelectListener;
import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity implements OnStationSelectListener {

    @BindView(R.id.rv)
    RecyclerView mRv;

    private StationListAdapter mAdapter;

    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);

        initData();
        initAdapter();

        mAdapter.selectStation(0);
        mRv.smoothScrollToPosition(0);
    }

    private void initData() {
        mList.add("下麦西");
        mList.add("老湾塘");
        mList.add("阅山湖公园");
        mList.add("林城西路站");
        mList.add("观山湖公园站");
        mList.add("阳观站");
        mList.add("新寨站");
        mList.add("白鹭湖站");
        mList.add("贵阳北站");
        mList.add("雅关站");
        mList.add("南垭路站");
        mList.add("八鸽岩站");
        mList.add("北京路站");
        mList.add("喷水池站");
        mList.add("中山西路站");
        mList.add("河滨公园站");
        mList.add("国际生态会议中心站");
        mList.add("贵阳火车站");
        mList.add("沙冲路站");
        mList.add("望城坡站");
    }

    private void initAdapter() {
        mAdapter = new StationListAdapter(mList);
        mAdapter.setOnStationSelectListener(this);
        CenterLayoutManager manager = new CenterLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRv.setLayoutManager(manager);
        mRv.setAdapter(mAdapter);

        mRv.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                TextView tempTextView = new TextView(RecyclerActivity.this);
                tempTextView.setTextSize(16f);
                float viewHeight;
                View child = mRv.getChildAt(0);

                BaseViewHolder childViewHolder = (BaseViewHolder) mRv.getChildViewHolder(child);
                ImageView imageView1 = childViewHolder.getView(R.id.iv_location);
                ImageView imageView2 = childViewHolder.getView(R.id.iv_station_status);
                TextView textView = childViewHolder.getView(R.id.tv_station);
                LayoutParams params = (LayoutParams) textView.getLayoutParams();

                int height1 = imageView1.getHeight();
                int height2 = imageView2.getHeight();

                int maxLength = 0;
                String tempText = "";
                for (final String s : mList) {
                    int length = s.length();
                    if (maxLength < length) {
                        maxLength = length;
                        tempText = s;
                    }
                }

                tempTextView.setText(tempText);
                int lineCount = tempTextView.length();
                float top = tempTextView.getPaint().getFontMetrics().top;
                float bottom = tempTextView.getPaint().getFontMetrics().bottom;

                float textHeight = (Math.abs(top) + bottom) * lineCount;

                viewHeight = params.topMargin + height1 + height2;

                ViewGroup.LayoutParams layoutParams = mRv.getLayoutParams();
                layoutParams.height = (int) (viewHeight + textHeight);
                mRv.setLayoutParams(layoutParams);

                mRv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void selectStation(int pos, String code) {
        mAdapter.selectStation(pos);
        mRv.smoothScrollToPosition(pos);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
