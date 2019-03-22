package com.challenge.networkrxretrofit.model.service.local;

import com.challenge.networkrxretrofit.model.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface UserDao {

    @Query("SELECT * FROM USER_TABLE")
    Single<List<User>> getUsers();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<User> users);
}
