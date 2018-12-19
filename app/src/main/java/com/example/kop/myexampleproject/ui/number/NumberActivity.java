package com.example.kop.myexampleproject.ui.number;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.kop.myexampleproject.R;
import java.util.Random;

public class NumberActivity extends AppCompatActivity {

    @BindView(R.id.num_view)
    NumberView mNumView;

    private Random mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        ButterKnife.bind(this);
        mRandom = new Random();
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        String num = getString(R.string.point_two, 100 * mRandom.nextFloat());
        mNumView.setNum(Float.valueOf(num));
        mNumView.startAnim();
    }
}
