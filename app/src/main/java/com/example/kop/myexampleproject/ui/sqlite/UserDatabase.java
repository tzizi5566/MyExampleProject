package com.example.kop.myexampleproject.ui.sqlite;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * 功    能: Room内对应的数据库创建
 * 创 建 人: KOP
 * 创建日期: 2018/12/17 13:35
 */
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static final String DB_NAME = "user.db";

    private static volatile UserDatabase INSTANCE;

    public static UserDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = create(context);
                }
            }
        }
        return INSTANCE;
    }

    public abstract UserDao getUserDao();

    private static UserDatabase create(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DB_NAME).build();
    }
}
