package com.example.kop.myexampleproject.ui.architecture;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.kop.myexampleproject.R;
import com.example.kop.myexampleproject.ui.architecture.viewmodel.LiveDataTimerViewModel;

public class ChronoActivity extends AppCompatActivity {

    private static final String TAG = "ChronoActivity";

    @BindView(R.id.tv_view)
    AppCompatTextView mTvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);
        ButterKnife.bind(this);

        LiveDataTimerViewModel timerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);
        timerViewModel.getElapsedTime().observe(this, aLong -> {
            mTvView.setText(String.valueOf(aLong));
            Log.d(TAG, "Updating timer" + aLong);
        });
    }
}
