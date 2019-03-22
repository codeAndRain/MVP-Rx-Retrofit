package com.challenge.networkrxretrofit.model.service.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    private String url = "https://jsonplaceholder.typicode.com/";

    private static RetrofitProvider instance;

    private UserService userService;

    private RetrofitProvider() {
        initRetrofit();
    }

    public static RetrofitProvider getInstance() {
        if (instance == null) {
            instance = new RetrofitProvider();
        }
        return instance;
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
    }

    public UserService getUserService() {
        return userService;
    }

    public void destroy() {
        instance = null;
    }
}
