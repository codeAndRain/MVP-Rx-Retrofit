package com.challenge.networkrxretrofit.presenter;

import android.content.Context;
import android.util.Log;

import com.challenge.networkrxretrofit.model.repo.Repository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter implements UserPresenterContract.Presenter {

    private UserPresenterContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Repository repository;

    public UserPresenter(Context context, UserPresenterContract.View view) {
        this.view = view;
        repository = new Repository(context);
    }

    @Override
    public void onRequestButtonClicked() {
        view.setProgressVisibility(true);
        add(repository.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(todoList -> view.setProgressVisibility(false))
                .subscribe(userList -> {
                    view.setUserItems(userList);
                    repository.insertUsers(userList);
                }, throwable -> {
                    Log.d("TAG_1", throwable.getMessage());
                    view.setProgressVisibility(false);
                }));
    }

    private void add(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        repository.onDestroy();
    }

    @Override
    public void showLocalUserList() {
        add(repository.getLocalUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    if (!users.isEmpty()) {
                        view.setUserItems(users);
                    } else {
                        view.showToastError();
                    }
                }));
    }
}
