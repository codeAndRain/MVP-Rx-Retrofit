package com.challenge.networkrxretrofit.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.challenge.networkrxretrofit.R;
import com.challenge.networkrxretrofit.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapterViewHolder> {

    private List<User> userItems = new ArrayList<>();

    @NonNull
    @Override
    public UserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new UserAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterViewHolder holder, int position) {
        User user = userItems.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }

    public void setUserItems(List<User> items) {
        if (!userItems.isEmpty()) {
            userItems.clear();
        }
        userItems.addAll(items);
        notifyDataSetChanged();
    }
}
