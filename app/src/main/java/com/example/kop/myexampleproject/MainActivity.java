package com.example.kop.myexampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.kop.myexampleproject.ui.architecture.ChronoActivity;
import com.example.kop.myexampleproject.ui.camera.CameraActivity;
import com.example.kop.myexampleproject.ui.menu.MenuActivity;
import com.example.kop.myexampleproject.ui.textview.AutoTextViewActivity;
import com.example.kop.myexampleproject.weiget.chart.RadarChartActivity;
import com.example.kop.myexampleproject.weiget.number.NumberActivity;
import com.example.kop.myexampleproject.ui.paging.PagingActivity;
import com.example.kop.myexampleproject.weiget.path.PathActivity;
import com.example.kop.myexampleproject.ui.recyclerview.RecyclerActivity;
import com.example.kop.myexampleproject.ui.sqlite.RoomActivity;
import com.example.kop.myexampleproject.weiget.ring.RingViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_rv, R.id.btn_camera, R.id.btn_sqlite, R.id.btn_path, R.id.btn_num, R.id.btn_paging,
            R.id.btn_viewmodel, R.id.btn_chart, R.id.btn_ring, R.id.btn_text, R.id.btn_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_rv:
                startActivity(new Intent(this, RecyclerActivity.class));
                break;

            case R.id.btn_camera:
                startActivity(new Intent(this, CameraActivity.class));
                break;

            case R.id.btn_sqlite:
                startActivity(new Intent(this, RoomActivity.class));
                break;

            case R.id.btn_path:
                startActivity(new Intent(this, PathActivity.class));
                break;

            case R.id.btn_num:
                startActivity(new Intent(this, NumberActivity.class));
                break;

            case R.id.btn_paging:
                startActivity(new Intent(this, PagingActivity.class));
                break;

            case R.id.btn_viewmodel:
                startActivity(new Intent(this, ChronoActivity.class));
                break;

            case R.id.btn_chart:
                startActivity(new Intent(this, RadarChartActivity.class));
                break;

            case R.id.btn_ring:
                startActivity(new Intent(this, RingViewActivity.class));
                break;

            case R.id.btn_text:
                startActivity(new Intent(this, AutoTextViewActivity.class));
                break;

            case R.id.btn_menu:
                startActivity(new Intent(this, MenuActivity.class));
                break;
        }
    }
}
