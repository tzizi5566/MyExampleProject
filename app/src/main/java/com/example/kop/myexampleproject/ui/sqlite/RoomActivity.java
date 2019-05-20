package com.example.kop.myexampleproject.ui.sqlite;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.example.kop.myexampleproject.R;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {

    private static final String TAG = "RoomActivity";

    @BindView(R.id.rv)
    RecyclerView mRv;

    private UserAdapter mAdapter;

    private User mDeleteUser;

    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

        }

        @Override
        public void onItemSwipeMoving(final Canvas canvas, final ViewHolder viewHolder, final float v, final float v1,
                final boolean b) {
            canvas.drawColor(ContextCompat.getColor(RoomActivity.this, R.color.colorAccent));
        }

        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
            mDeleteUser = mAdapter.getItem(pos);
        }

        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
//            if (pos == RESULT_OK) {
//                delete(mDeleteUser);
//            }
        }
    };

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;

    private UserDao mUserDao;

    private RoomViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ButterKnife.bind(this);
        mUserDao = UserDatabase.getInstance(this).getUserDao();
        initAdapter();

        mViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);
        mViewModel.getDatas().observe(this, users -> mAdapter.setNewData(users));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }

    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_edit, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
//                insert();
                insertData();
                break;

            case R.id.btn_delete:

                break;

            case R.id.btn_edit:

                break;

            case R.id.btn_search:
                search();
                break;
        }
    }

    private void initAdapter() {
        mAdapter = new UserAdapter(R.layout.item_room, new ArrayList<>());

        mRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRv.setAdapter(mAdapter);

        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(mRv);
//        //开启滑动删除
//        mAdapter.enableSwipeItem();
//        mAdapter.setOnItemSwipeListener(onItemSwipeListener);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                User user = mAdapter.getData().get(position);
                deleteData(user);
            }
        });
    }

    private void insert() {
        User user = new User();
        user.setName("小明" + (int) ((Math.random() * 9 + 1) * 10));
        user.setAge((int) ((Math.random() * 9 + 1) * 10));

        mDisposable.add(mUserDao.insert(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(aLong -> mAdapter.addData(user),
                        throwable -> Log.e("kop", "insert", throwable)));
    }

    private void delete(User user) {
        mDisposable.add(mUserDao.delete(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(aLong -> {
                        },
                        throwable -> Log.e("kop", "insert", throwable)));
    }

    private void search() {
        mDisposable.add(mUserDao.search()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(users -> mAdapter.setNewData(users),
                        throwable -> Log.e("kop", "search", throwable)));
    }

    @SuppressLint("AutoDispose")
    private void insertData() {
        User user = new User();
        user.setName("小明" + (int) ((Math.random() * 9 + 1) * 10));
        user.setAge((int) ((Math.random() * 9 + 1) * 10));

        mDisposable.add(mViewModel.insertData(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                }));
    }

    @SuppressLint("AutoDispose")
    private void deleteData(User user) {
        mDisposable.add(mViewModel.deleteData(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                }));
    }
}
