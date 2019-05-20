package com.example.kop.myexampleproject.ui.sqlite;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import java.util.List;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2019-05-20 15:19
 */
public class RoomViewModel extends AndroidViewModel {

    private final UserDao mUserDao;

    public RoomViewModel(@NonNull final Application application) {
        super(application);
        mUserDao = UserDatabase.getInstance(application).getUserDao();

    }

    public LiveData<List<User>> getDatas() {
        return mUserDao.searchAll();
    }

    public Completable insertData(User user) {
        return Completable.fromAction(() -> mUserDao.insertData(user));
    }

    public Completable deleteData(User user) {
        return Completable.fromAction(() -> mUserDao.deleteData(user));
    }
}
