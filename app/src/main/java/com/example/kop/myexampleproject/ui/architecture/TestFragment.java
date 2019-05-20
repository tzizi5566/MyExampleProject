package com.example.kop.myexampleproject.ui.architecture;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2019/3/25 13:40
 */
public class TestFragment extends Fragment {

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LiveData<LifecycleOwner> viewLifecycleOwnerLiveData = getViewLifecycleOwnerLiveData();
    }
}
