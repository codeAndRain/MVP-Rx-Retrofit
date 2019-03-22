package com.challenge.networkrxretrofit.view;

import android.view.View;
import android.widget.TextView;

import com.challenge.networkrxretrofit.R;
import com.challenge.networkrxretrofit.model.User;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class UserAdapterViewHolder extends RecyclerView.ViewHolder {

    TextView userItemTextView;

    public UserAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        userItemTextView = itemView.findViewById(R.id.user_item_text);
    }

    public void bind(User user) {
        userItemTextView.setText(user.getTitle()
                .concat(", ")
                .concat(Integer.toString(user.getId_())));
    }
}
