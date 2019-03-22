package com.challenge.networkrxretrofit.presenter;

import com.challenge.networkrxretrofit.model.User;

import java.util.List;

public interface UserPresenterContract {

    interface View {

        void setProgressVisibility(boolean isVisible);

        void setUserItems(List<User> userList);

        void showToastError();
    }

    interface Presenter {

        void onRequestButtonClicked();

        void onDestroy();

        void showLocalUserList();
    }
}
