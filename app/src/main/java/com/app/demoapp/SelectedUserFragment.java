package com.app.demoapp;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.demoapp.databinding.FragmentSelectedUserBinding;

public class SelectedUserFragment extends Fragment {

    FragmentSelectedUserBinding binding;

    public SelectedUserFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selected_user, container, false);

        Bundle bundle=getArguments();
        if(bundle!=null)
        {
            Log.e("Data",""+String.valueOf(bundle.getString("email")));
            Log.e("Data",""+String.valueOf(bundle.getString("user_name")));
            Log.e("Data",""+String.valueOf(bundle.getString("password")));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    binding.tvEmail.setText(String.valueOf(bundle.getString("email")));
                    binding.tvName.setText(String.valueOf(bundle.getString("user_name")));
                    binding.tvPassword.setText(String.valueOf(bundle.getString("password")));

                }
            });


        }
        return binding.getRoot();
    }
}