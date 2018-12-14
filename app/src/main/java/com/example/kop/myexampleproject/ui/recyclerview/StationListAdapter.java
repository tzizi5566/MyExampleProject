package com.example.kop.myexampleproject.ui.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kop.myexampleproject.R;
import java.util.List;

/**
 * Created by houjt
 * on 2018/8/17.
 */
public class StationListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int selectPos;

    private List<String> mDatas;

    public StationListAdapter(List<String> data) {
        super(R.layout.item_station_list, data);
        mDatas = data;
    }

    public void selectStation(int pos) {
        selectPos = pos;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(final BaseViewHolder helper, final String item) {
        TextView tvStation = helper.getView(R.id.tv_station);
        tvStation.setText(item);
        ImageView ivStationStatus = helper.getView(R.id.iv_station_status);
        ImageView ivLocation = helper.getView(R.id.iv_location);
        ImageView imgLine1 = helper.getView(R.id.img_line1);
        ImageView imgLine2 = helper.getView(R.id.img_line2);

        if (selectPos == helper.getAdapterPosition()) {
            ivStationStatus.setImageResource(R.drawable.ic_station_select);
            ivLocation.setVisibility(View.VISIBLE);
            tvStation.setTextColor(mContext.getResources().getColor(R.color.app_basic_color));
        } else {
            ivStationStatus.setImageResource(R.drawable.ic_station_normal);
            ivLocation.setVisibility(View.INVISIBLE);
            tvStation.setTextColor(mContext.getResources().getColor(R.color.black));
        }

//        if (helper.getAdapterPosition() == 0) {
//            imgLine1.setVisibility(View.GONE);
//        } else {
//            imgLine1.setVisibility(View.VISIBLE);
//        }

//        if (mDatas.size() - 1 == helper.getAdapterPosition()) {
//            imgLine2.setVisibility(View.GONE);
//        } else {
//            imgLine2.setVisibility(View.VISIBLE);
//        }

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.selectStation(helper.getAdapterPosition(), item);
                }
            }
        });
    }

    public interface OnStationSelectListener {

        void selectStation(int pos, String code);
    }

    private OnStationSelectListener mListener;

    public void setOnStationSelectListener(OnStationSelectListener listener) {
        mListener = listener;
    }
}
