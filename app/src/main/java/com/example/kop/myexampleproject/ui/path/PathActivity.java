package com.example.kop.myexampleproject.ui.path;

import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
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

    @BindView(R.id.seek_bar)
    SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        ButterKnife.bind(this);

        mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                Log.i("kop", "onProgressChanged: " + (float) (progress / 100.0));
                mPathView.setFraction((float) (progress / 100.0));
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
