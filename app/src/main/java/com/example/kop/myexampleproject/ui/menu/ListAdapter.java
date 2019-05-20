package com.example.kop.myexampleproject.ui.menu;

import android.graphics.Color;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kop.myexampleproject.R;
import com.example.kop.myexampleproject.ui.menu.MenuBean.GetAllgoodsRsBean.ClassListBean;
import java.util.List;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2019-04-26 10:38
 */
public class ListAdapter extends BaseQuickAdapter<ClassListBean, BaseViewHolder> {

    public ListAdapter(final int layoutResId, @Nullable final List<ClassListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ClassListBean item) {
        helper.setText(R.id.tv_list, item.getName());

        if (item.isSelected()) {
            helper.setBackgroundColor(R.id.tv_list, Color.GRAY);
        } else {
            helper.setBackgroundColor(R.id.tv_list, Color.WHITE);
        }
    }
}
