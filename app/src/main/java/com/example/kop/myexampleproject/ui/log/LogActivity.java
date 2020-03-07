package com.example.kop.myexampleproject.ui.log;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ThreadUtils.Task;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.kop.myexampleproject.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        Exception exception = new NullPointerException();
        String stackTraceString = Log.getStackTraceString(exception);
        String nowString = TimeUtils.getNowString();
        String log = nowString + "\n" + stackTraceString;

        ThreadUtils.executeByIo(new Task<String>() {
            @Nullable
            @Override
            public String doInBackground() {
                File logFile = getLogFile(getApplicationContext());
                RandomAccessFile raf = null;
                try {
                    raf = new RandomAccessFile(logFile, "rw");
                    raf.seek(logFile.length());
                    raf.write(log.getBytes());
                    raf.write("\n".getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (raf != null) {
                        try {
                            raf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            public void onSuccess(@Nullable final String result) {
                ToastUtils.showShort("保存成功");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFail(final Throwable t) {
                ToastUtils.showShort("保存失败 " + t.getMessage());
            }
        });
    }

    /**
     * 获得储存路径
     */
    public File getLogFile(Context context) {
        final File file;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory == null) {
                return null;
            }
            file = externalStorageDirectory;
        } else {
            File cacheDirectory = context.getExternalCacheDir();
            if (cacheDirectory == null) {
                return null;
            }
            file = cacheDirectory;
        }

        File logFile = new File(file.getPath(), "TestLog.txt");
        if (!logFile.exists()) {
            try {
                if (!logFile.createNewFile()) {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return logFile;
    }
}
