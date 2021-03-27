package com.app.demoapp;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.demoapp.adapter.UserAdapter;
import com.app.demoapp.database.DatabaseClient;
import com.app.demoapp.databinding.FragmentAllUserBinding;
import com.app.demoapp.model.User;

import java.util.List;

public class AllUserFragment extends Fragment {


    FragmentAllUserBinding binding;
    LinearLayoutManager linearLayoutManager;
    UserAdapter adapter;
    ViewPager viewPager;

    public AllUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_user, container, false);
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewPager);

        List<User> userList = DatabaseClient
                .getInstance(getActivity())
                .getAppDatabase()
                .userDao()
                .getAll();

        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.rcUser.setLayoutManager(linearLayoutManager);
        adapter = new UserAdapter(getActivity(), userList) {
            @Override
            public void onClickPosition(int position) {

                FragmentTransaction transection=getFragmentManager().beginTransaction();
                SelectedUserFragment mfragment=new SelectedUserFragment();
                Bundle bundle=new Bundle();
                bundle.putString("email",userList.get(position).getUserEmail());
                bundle.putString("user_name",userList.get(position).getFirstName());
                bundle.putString("password",userList.get(position).getUserPassword());
                mfragment.setArguments(bundle); //data being send to SecondFragment
                transection.add(R.id.viewPager, mfragment);
                transection.commit();
                viewPager.setCurrentItem(1);
            }
        };
        binding.rcUser.setAdapter(adapter);

        return binding.getRoot();

    }
}