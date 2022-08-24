package com.example.wehelppetsmobileapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wehelppetsmobileapplication.adapters.AdapterPosts;
import com.example.wehelppetsmobileapplication.adapters.HomeFragmentAdapter;
import com.example.wehelppetsmobileapplication.models.ModelPost;
import com.example.wehelppetsmobileapplication.notifications.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {



    FirebaseAuth firebaseAuth;
    FirebaseAuth firebaseAuth1;

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

    Button search;


    String email;
    EditText searchfield;
    RecyclerView seacheduser;


    RecyclerView recyclerView;
    List<ModelPost> postList;
    AdapterPosts adapterPosts;


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
        tabLayout.addTab(tabLayout.newTab().setText("People"));
        tabLayout.addTab(tabLayout.newTab().setText("Shelter"));


        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");


        search = findViewById(R.id.search_user_button);

        RecyclerView searched_post;
        EditText searchField;















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


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* hometoolbar.setVisibility(View.GONE);
                SearchFragment fragment6 = new SearchFragment();
                FragmentTransaction ft6 = getSupportFragmentManager().beginTransaction();
                ft6.replace(R.id.content, fragment6, "");
                ft6.commit();*/


                tabLayout.setVisibility(View.GONE);
                hometoolbar.setVisibility(View.GONE);
                //actionBar.setTitle("Search");
                SearchFragment fragment5 = new SearchFragment();
                FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                ft5.replace(R.id.content, fragment5, "");
                ft5.commit();


                UsersFragment usersFragment = new UsersFragment();
                NewsFragment newsFragment = new NewsFragment();
                FragmentTransaction news = getSupportFragmentManager().beginTransaction();
                news.hide(newsFragment);
                news.hide(usersFragment);

            }
        });











    }


}