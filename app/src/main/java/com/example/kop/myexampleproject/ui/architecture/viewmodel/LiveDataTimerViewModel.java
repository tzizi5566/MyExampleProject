package com.example.kop.myexampleproject.ui.architecture.viewmodel;

import android.app.Application;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2018/12/24 14:34
 */
public class LiveDataTimerViewModel extends AndroidViewModel {

    private static final int ONE_SECOND = 1000;

    private MutableLiveData<Long> mElapsedTime = new MutableLiveData<>();

    private long mInitialTime;

    public LiveDataTimerViewModel(@NonNull final Application application) {
        super(application);
        mInitialTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;
                mElapsedTime.postValue(newValue);
            }
        }, ONE_SECOND, ONE_SECOND);
    }

    public LiveData<Long> getElapsedTime() {
        return mElapsedTime;
    }
}
