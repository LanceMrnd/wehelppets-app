
package com.example.wehelppetsmobileapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.wehelppetsmobileapplication.notifications.Token;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class DashboardActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    String mUID;
    ActionBar actionBar;

    FrameLayout frameLayout;
    SlidrInterface slidr;
    //SwipeListener swipelistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");
        getSupportActionBar().setElevation(0);

        firebaseAuth = FirebaseAuth.getInstance();

        // bottom navigation
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(onItemSelectedListener);


        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        overridePendingTransition(0, 0);









        checkUserStatus();



    }



        @Override
        protected void onResume() {
            checkUserStatus();
            super.onResume();
        }

        public void updateToken(Task<String> token){
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Tokens");
            Token mToken = new Token(token);
            ref.child(mUID).setValue(mToken);
        }

        private BottomNavigationView.OnItemSelectedListener onItemSelectedListener =
            new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId())
                    {
                        case R.id.nav_home:
                            actionBar.setTitle("Home");
                           startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                           //overridePendingTransition(0, 0);
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

                        Intent intent = new Intent(DashboardActivity.this,AddPostActivity.class);
                            startActivity(intent);
                            return false;
                    }
                    return false;
                }
            };


    private void checkUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            //user is signed in stay here
            //mProfileTv.setText(user.getEmail());
             mUID = user.getUid();

             //save uid of currently signed in user in shared preferences
             SharedPreferences sp = getSharedPreferences("SP_USER",MODE_PRIVATE);
             SharedPreferences.Editor editor = sp.edit();
             editor.putString("Current_USERID", mUID);
             editor.apply();

            //update token
            updateToken(FirebaseMessaging.getInstance().getToken());

        } else{
            //user is not signed in, go to main activity
            startActivity(new Intent(DashboardActivity.this,MainActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStart() {
        //check on start of app
        checkUserStatus();
        super.onStart();
    }


}