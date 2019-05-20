package com.example.kop.myexampleproject.ui.menu;

import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kop.myexampleproject.R;
import com.example.kop.myexampleproject.base.MyApplication;
import com.example.kop.myexampleproject.ui.menu.MenuBean.GetAllgoodsRsBean.ClassListBean.GoodsListBean;
import java.util.List;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2019-04-26 14:44
 */
public class DetailAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public DetailAdapter(final int layoutResId, final int sectionHeadResId, final List<MySection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MySection item) {
        GoodsListBean goodsListBean = item.t;
        helper.setText(R.id.tv_detail, goodsListBean.getGoodsName());
        AppCompatImageView imageView = helper.getView(R.id.iv_detail);
        Glide.with(MyApplication.getApplication()).load(goodsListBean.getGoodsPic()).into(imageView);
    }

    @Override
    protected void convertHead(final BaseViewHolder helper, final MySection item) {
        helper.itemView.setVisibility(View.GONE);
    }
}
