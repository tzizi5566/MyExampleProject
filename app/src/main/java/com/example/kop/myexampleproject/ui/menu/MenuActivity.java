package com.example.kop.myexampleproject.ui.menu;

import android.content.res.AssetManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.blankj.utilcode.util.FragmentUtils;
import com.example.kop.myexampleproject.R;
import com.example.kop.myexampleproject.ui.menu.MenuBean.GetAllgoodsRsBean;
import com.example.kop.myexampleproject.ui.menu.MenuBean.GetAllgoodsRsBean.ClassListBean;
import com.example.kop.myexampleproject.ui.menu.MenuBean.GetAllgoodsRsBean.ClassListBean.GoodsListBean;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private MenuListFragment mListFragment;

    private MenuDatailFragment mDatailFragment;

    private ArrayList<ClassListBean> mList = new ArrayList<>();

    private ArrayList<MySection> mDetail = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MenuBean menuBean = getDataFromJson();

        GetAllgoodsRsBean allgoodsRs = menuBean.getGetAllgoodsRs();
        List<ClassListBean> classList = allgoodsRs.getClassList();
        for (int i = 0; i < classList.size(); i++) {
            if (i == 0) {
                classList.get(0).setSelected(true);
            }

            String name = classList.get(i).getName();
            mDetail.add(new MySection(true, name));

            List<GoodsListBean> goodsList = classList.get(i).getGoodsList();
            for (int j = 0; j < goodsList.size(); j++) {
                GoodsListBean goodsListBean = goodsList.get(j);
                goodsListBean.setName(name);
                mDetail.add(new MySection(goodsListBean));
            }
        }
        mList.addAll(classList);

        initFragment();
    }

    private MenuBean getDataFromJson() {
        AssetManager assetManager = getAssets();
        StringBuilder sb = new StringBuilder();
        MenuBean menuBean = null;
        try {
            InputStream inputStream = assetManager.open("menu.json");
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bf.readLine()) != null) {
                sb.append(line);
            }

            Gson gson = new Gson();
            menuBean = gson.fromJson(sb.toString(), MenuBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menuBean;
    }

    private void initFragment() {
        mListFragment = MenuListFragment.newInstance(mList);
        mDatailFragment = MenuDatailFragment.newInstance(mDetail);
        FragmentUtils.add(getSupportFragmentManager(), mListFragment, R.id.fl_list);
        FragmentUtils.add(getSupportFragmentManager(), mDatailFragment, R.id.fl_detail);
    }
}
