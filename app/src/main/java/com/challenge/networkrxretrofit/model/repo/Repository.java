package com.challenge.networkrxretrofit.model.repo;

import android.annotation.SuppressLint;
import android.content.Context;

import com.challenge.networkrxretrofit.model.User;
import com.challenge.networkrxretrofit.model.service.local.UserDao;
import com.challenge.networkrxretrofit.model.service.local.UserDatabase;
import com.challenge.networkrxretrofit.model.service.remote.RetrofitProvider;
import com.challenge.networkrxretrofit.model.service.remote.UserService;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private RetrofitProvider retrofitProvider;
    private UserDatabase userDatabase;
    private UserDao userDao;
    private UserService userService;

    public Repository(Context context) {
        // initialize remote service
        retrofitProvider = RetrofitProvider.getInstance();
        userService = retrofitProvider.getUserService();

        // initialize local db service
        userDatabase = UserDatabase.getInstance(context);
        userDao = userDatabase.getUserDao();

    }

    public Single<List<User>> getUserList() {
        return userService.getUsers();
    }

    public Single<List<User>> getLocalUserList() {
        return userDao.getUsers();
    }

    @SuppressLint("CheckResult")
    public void insertUsers(List<User> users) {

        Completable.fromSingle(emitter -> userDao.insertUsers(users))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void onDestroy() {
        retrofitProvider.destroy();
        userDatabase.destroyDbInstance();
    }
}
