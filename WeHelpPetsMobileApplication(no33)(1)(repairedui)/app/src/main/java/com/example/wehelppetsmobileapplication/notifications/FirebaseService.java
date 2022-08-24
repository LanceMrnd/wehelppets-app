package com.example.wehelppetsmobileapplication.notifications;


import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;


public class FirebaseService extends FirebaseMessagingService {
    @Override

    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.e("Refreshed token:",token);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Task<String> tokenRefresh = FirebaseMessaging.getInstance().getToken();
        if(user != null){
            updateToken(tokenRefresh);
        }
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        //sendRegistrationToServer(token);
    }

    private void updateToken(Task<String> tokenRefresh) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token = new Token(tokenRefresh);
        ref.child(user.getUid()).setValue(token);
    }

}





