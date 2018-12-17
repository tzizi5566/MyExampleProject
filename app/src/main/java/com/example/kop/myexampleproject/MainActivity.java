package com.example.kop.myexampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.kop.myexampleproject.ui.camera.CameraActivity;
import com.example.kop.myexampleproject.ui.recyclerview.RecyclerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_rv, R.id.btn_camera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_rv:
                startActivity(new Intent(this, RecyclerActivity.class));
                break;

            case R.id.btn_camera:
                startActivity(new Intent(this, CameraActivity.class));
                break;

        }
    }
}
