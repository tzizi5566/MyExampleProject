package com.example.kop.myexampleproject.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.kop.myexampleproject.R;
import java.util.ArrayList;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2019-04-26 09:34
 */
public class MenuDatailFragment extends Fragment {

    private static final String TAG = "MenuDatailFragment";

    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private Unbinder mUnbinder;

    private ArrayList<MySection> mDetail;

    public static MenuDatailFragment newInstance(ArrayList<MySection> list) {
        Bundle args = new Bundle();
        args.putSerializable("detail", list);
        MenuDatailFragment fragment = new MenuDatailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mDetail = (ArrayList<MySection>) bundle.getSerializable("detail");
        }

        initAdapter();
    }

    private void initAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRvList.setLayoutManager(gridLayoutManager);
        mRvList.addItemDecoration(new MyItemDecoration(mDetail));
        DetailAdapter adapter = new DetailAdapter(R.layout.item_menu_detail, R.layout.item_menu_detail_header,
                mDetail);
        mRvList.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
