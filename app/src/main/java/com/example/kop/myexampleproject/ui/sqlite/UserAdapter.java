package com.example.kop.myexampleproject.ui.sqlite;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kop.myexampleproject.R;
import java.util.List;

/**
 * 功    能: Room案例Adapter
 * 创 建 人: KOP
 * 创建日期: 2018/12/17 16:54
 */
public class UserAdapter extends BaseItemDraggableAdapter<User, BaseViewHolder> {

    public UserAdapter(final int layoutResId, @Nullable final List<User> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final User item) {
        helper.setText(R.id.tv_id, String.valueOf(item.getId()));
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_age, String.valueOf(item.getAge()));
    }
}
