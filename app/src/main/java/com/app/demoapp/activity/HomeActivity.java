package com.app.demoapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.app.demoapp.AllUserFragment;
import com.app.demoapp.R;
import com.app.demoapp.SelectedUserFragment;
import com.app.demoapp.adapter.TabPagerAdapter;
import com.app.demoapp.adapter.UserAdapter;
import com.app.demoapp.database.DatabaseClient;
import com.app.demoapp.databinding.ActivityHomeBinding;
import com.app.demoapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    LinearLayoutManager linearLayoutManager;
    UserAdapter adapter;
    TabPagerAdapter tabPagerAdapter;
    //ArrayList<User> userArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);

        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), this);
        //tabPagerAdapter.addFragment(new VideosFragment(), getResources().getString(R.string.videos));
        tabPagerAdapter.addFragment(new AllUserFragment(), getResources().getString(R.string.all_user));
        tabPagerAdapter.addFragment(new SelectedUserFragment(), getResources().getString(R.string.selected_user));
        binding.viewPager.setAdapter(tabPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        /*List<User> userList = DatabaseClient
                .getInstance(getApplicationContext())
                .getAppDatabase()
                .userDao()
                .getAll();


        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.rcUser.setLayoutManager(linearLayoutManager);
        adapter = new UserAdapter(this, userList);
        binding.rcUser.setAdapter(adapter);*/

    }
}