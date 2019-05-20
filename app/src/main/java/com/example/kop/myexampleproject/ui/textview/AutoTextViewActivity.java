package com.example.kop.myexampleproject.ui.textview;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.kop.myexampleproject.R;

public class AutoTextViewActivity extends AppCompatActivity {

    @BindView(R.id.seek_bar)
    SeekBar mSeekBar;

    @BindView(R.id.tv_view)
    TextView mTvView;

    private String[] array = new String[]{" ", "这", "是", "测", "试", "文", "字"};

    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_text_view);
        ButterKnife.bind(this);

        mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                String s = array[progress % array.length];
                sb.append(s);
                mTvView.setText(sb.toString());
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
