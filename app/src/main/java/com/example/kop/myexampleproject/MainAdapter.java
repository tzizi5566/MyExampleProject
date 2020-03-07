package com.example.kop.myexampleproject;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kop.myexampleproject.entity.MainBean;
import java.util.List;

/**
 * 功    能: 首页适配器
 * 创 建 人: KOP
 * 创建日期: 2019-05-24 17:01
 */
public class MainAdapter extends BaseQuickAdapter<MainBean, BaseViewHolder> {

    public MainAdapter(@Nullable final List<MainBean> data) {
        super(R.layout.item_main, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MainBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_content, item.getContent());
    }
}
