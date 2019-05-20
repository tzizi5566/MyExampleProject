package com.example.kop.myexampleproject.weiget.path;

import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.kop.myexampleproject.R;

@RequiresApi(api = VERSION_CODES.LOLLIPOP)
public class PathActivity extends AppCompatActivity {

    @BindView(R.id.path_view1)
    PathView mPathView;

    @BindView(R.id.path_view5)
    PathMeasureView4 mPathView5;

    @BindView(R.id.seek_bar)
    SeekBar mSeekBar;

    @BindView(R.id.seek_bar2)
    SeekBar mSeekBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        ButterKnife.bind(this);

        mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                mPathView.setFraction(progress / 100.0f);
            }

            @Override
            public void onStartTrackingTouch(final SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {

            }
        });

        mSeekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                mPathView5.setPercent(progress / 100.0f);
            }

            @Override
            public void onStartTrackingTouch(final SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {

            }
        });
    }
}
