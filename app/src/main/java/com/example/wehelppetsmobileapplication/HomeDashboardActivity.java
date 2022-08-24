package com.example.wehelppetsmobileapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.wehelppetsmobileapplication.adapters.HomeFragmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class HomeDashboardActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    String mUID;
    ActionBar actionBar;

    TabLayout tabLayout;
    ViewPager2 pager2;
    HomeFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dashboard);


        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        getSupportActionBar().setElevation(0);

        firebaseAuth = FirebaseAuth.getInstance();

        // bottom navigation
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setSelectedItemId(R.id.nav_home);
        navigationView.setSelectedItemId(R.id.nav_home);

        tabLayout = findViewById(R.id.home_tab);
        pager2 = findViewById(R.id.home_view_pager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new HomeFragmentAdapter(fragmentManager, getLifecycle());
        pager2.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.addTab(tabLayout.newTab().setText("Users"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });





        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId())
                {
                    case R.id.nav_home:

                        return true;
                    case R.id.nav_profile:
                        actionBar.setTitle("Profile");
                        ProfileFragment fragment2 = new ProfileFragment();
                        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                        ft2.replace(R.id.content, fragment2, "");
                        ft2.commit();
                        return true;
                    case R.id.nav_chat:
                        actionBar.setTitle("Chats");
                        ChatListFragment fragment4 = new ChatListFragment();
                        FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                        ft4.replace(R.id.content, fragment4, "");
                        ft4.commit();
                        return true;
                    case R.id.nav_notification:
                        actionBar.setTitle("Notifications");
                        NotificationsFragment fragment5 = new NotificationsFragment();
                        FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                        ft5.replace(R.id.content, fragment5, "");
                        ft5.commit();
                        return true;

                    case R.id.action_add_post:

                        Intent intent = new Intent(HomeDashboardActivity.this,AddPostActivity.class);
                        startActivity(intent);
                        return false;
                }

                return false;
            }
        });
    }
}