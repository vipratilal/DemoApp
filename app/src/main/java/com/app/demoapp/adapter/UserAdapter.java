package com.app.demoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.demoapp.R;
import com.app.demoapp.databinding.ItemUserListLayoutBinding;
import com.app.demoapp.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public abstract class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{


    Context mContext;
    List<User> arrayList;

    public UserAdapter(Context mContext, List<User> userArrayList) {
        this.mContext = mContext;
        this.arrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        ItemUserListLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_user_list_layout, viewGroup, false);
        return new MyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bind(arrayList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
        //return 15;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        ItemUserListLayoutBinding binding;

        public MyViewHolder(ItemUserListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(User obj) {
            binding.setUserItem(obj);
            binding.executePendingBindings();
        }
    }

    public abstract void onClickPosition(int position);

}
