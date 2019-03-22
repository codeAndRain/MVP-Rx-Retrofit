package com.challenge.networkrxretrofit.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.challenge.networkrxretrofit.R;
import com.challenge.networkrxretrofit.model.User;
import com.challenge.networkrxretrofit.model.repo.Repository;
import com.challenge.networkrxretrofit.presenter.UserPresenter;
import com.challenge.networkrxretrofit.presenter.UserPresenterContract;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, UserPresenterContract.View {

    private Button requestButton;
    private ProgressBar progressBar;
    private RecyclerView userRecyclerView;
    private UserAdapter adapter;

    private UserPresenter presenter;

    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new UserPresenter(this, this);
        repository = new Repository(this);

        initViews();
    }

    private void initViews() {
        requestButton = findViewById(R.id.request_button);
        requestButton.setOnClickListener(this);
        userRecyclerView = findViewById(R.id.recyclerview);
        progressBar  = findViewById(R.id.progress_circular);

        adapter = new UserAdapter();
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        userRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        presenter.onRequestButtonClicked();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.showLocalUserList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void setProgressVisibility(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setUserItems(List<User> userList) {
        adapter.setUserItems(userList);
    }

    @Override
    public void showToastError() {
        Toast.makeText(this, "Empty Database", Toast.LENGTH_SHORT).show();
    }
}
