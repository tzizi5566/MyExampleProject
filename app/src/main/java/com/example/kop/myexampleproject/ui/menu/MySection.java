package com.example.kop.myexampleproject.ui.menu;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.example.kop.myexampleproject.ui.menu.MenuBean.GetAllgoodsRsBean.ClassListBean.GoodsListBean;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2019-04-26 14:33
 */
public class MySection extends SectionEntity<GoodsListBean> {

    public MySection(final boolean isHeader, final String header) {
        super(isHeader, header);
    }

    public MySection(final GoodsListBean goodsListBean) {
        super(goodsListBean);
    }
}
