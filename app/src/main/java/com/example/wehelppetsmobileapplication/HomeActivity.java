package com.example.wehelppetsmobileapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wehelppetsmobileapplication.adapters.HomeFragmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class HomeActivity extends AppCompatActivity {



    FirebaseAuth firebaseAuth;

    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ImageView avatarIv,coverIv;
    TextView nameTv, emailTv, phoneTv;
    FloatingActionButton fab;
    RecyclerView postsRecyclerView;

    String mUID;
    ActionBar actionBar;

    TabLayout tabLayout;
    ViewPager2 pager2;
    HomeFragmentAdapter adapter;
    LinearLayout hometoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        actionBar = getSupportActionBar();
        //actionBar.setTitle("Home");
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        getSupportActionBar().setElevation(0);

        firebaseAuth = FirebaseAuth.getInstance();


        // bottom navigation
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setSelectedItemId(R.id.nav_home);
        navigationView.setSelectedItemId(R.id.nav_home);

        hometoolbar = findViewById(R.id.hometoolbar);

        tabLayout = findViewById(R.id.home_tab);
        pager2 = findViewById(R.id.home_view_pager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new HomeFragmentAdapter(fragmentManager, getLifecycle());
        pager2.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.addTab(tabLayout.newTab().setText("Users"));


        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");









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
                        actionBar.setTitle("Home");
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0, 0);
                        getSupportActionBar().hide();

                        return true;
                    case R.id.nav_profile:

                        tabLayout.setVisibility(View.GONE);
                        hometoolbar.setVisibility(View.GONE);
                        actionBar.setTitle("Profile");
                        ProfileFragment fragment2 = new ProfileFragment();
                        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                        ft2.replace(R.id.content, fragment2, "");
                        ft2.commit();
                        getSupportActionBar().show();

                        return true;

                    case R.id.nav_chat:

                        tabLayout.setVisibility(View.GONE);
                        hometoolbar.setVisibility(View.GONE);
                        actionBar.setTitle("Chats");
                        ChatListFragment fragment4 = new ChatListFragment();
                        FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                        ft4.replace(R.id.content, fragment4, "");
                        getSupportActionBar().show();
                        ft4.commit();
                        return true;
                    case R.id.nav_notification:

                        tabLayout.setVisibility(View.GONE);
                        hometoolbar.setVisibility(View.GONE);
                        actionBar.setTitle("Notifications");
                        NotificationsFragment fragment5 = new NotificationsFragment();
                        FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                        ft5.replace(R.id.content, fragment5, "");
                        ft5.commit();
                        getSupportActionBar().show();
                        return true;

                    case R.id.action_add_post:

                        tabLayout.setVisibility(View.GONE);
                        hometoolbar.setVisibility(View.GONE);
                        Intent intent = new Intent(HomeActivity.this,AddPostActivity.class);
                        startActivity(intent);
                        getSupportActionBar().show();
                        return false;
                }

                return false;
            }
        });












    }


}