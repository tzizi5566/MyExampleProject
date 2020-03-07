package com.example.kop.myexampleproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.kop.myexampleproject.entity.MainBean;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_view)
    RecyclerView mRvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initAdapter();
    }

    private void initAdapter() {
        MainAdapter mainAdapter = new MainAdapter(DataGenerator.generateData());
        mRvView.setLayoutManager(new LinearLayoutManager(this));
        mRvView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvView.setAdapter(mainAdapter);

        mainAdapter.setOnItemClickListener((adapter, view, position) -> {
            MainBean bean = (MainBean) adapter.getData().get(position);
            Intent intent = new Intent(MainActivity.this, bean.getClazz());
            startActivity(intent);
        });
    }
}
