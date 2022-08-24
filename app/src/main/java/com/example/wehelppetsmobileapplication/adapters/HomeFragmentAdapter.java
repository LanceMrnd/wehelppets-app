package com.example.wehelppetsmobileapplication.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wehelppetsmobileapplication.NewsFragment;
import com.example.wehelppetsmobileapplication.UsersFragment;

public class HomeFragmentAdapter extends FragmentStateAdapter {
    public HomeFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){


            case 1 :
                return new UsersFragment();
            case 2 :
                return new NewsFragment();
        }


        return new NewsFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
