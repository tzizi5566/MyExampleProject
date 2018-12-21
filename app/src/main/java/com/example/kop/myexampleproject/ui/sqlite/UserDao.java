package com.example.kop.myexampleproject.ui.sqlite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.List;

/**
 * 功    能: Room内对应的表操作
 * 创 建 人: KOP
 * 创建日期: 2018/12/17 13:26
 */
@Dao
public interface UserDao {

    @Delete
    Maybe<Integer> delete(User user);

    @Delete
    Maybe<Integer> delete(List<User> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Maybe<Long> insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Maybe<List<Long>> insert(List<User> users);

    @Query("SELECT * FROM USER WHERE _ID = :id")
    Flowable<User> search(int id);

    @Query("SELECT * FROM USER WHERE NAME LIKE :name")
    Flowable<List<User>> search(String name);

    @Query("SELECT * FROM USER")
    Flowable<List<User>> search();

    @Update
    Maybe<Integer> update(List<User> users);

    @Update
    Maybe<Integer> update(User user);
}
