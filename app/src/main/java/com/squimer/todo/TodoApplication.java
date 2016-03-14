package com.squimer.todo;

import android.app.Application;

import com.firebase.client.Firebase;
import com.squimer.todo.data.FirebaseManager;
import com.squimer.todo.util.Injector;

public class TodoApplication extends Application {

    Firebase firebase;
    FirebaseManager firebaseManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        firebase = new Firebase(getString(R.string.firebase_url));
        firebaseManager = new FirebaseManager(firebase);
    }

    @Override
    public Object getSystemService(String name) {
        if (Injector.matchesService(name, FirebaseManager.class))
            return firebaseManager;

        return super.getSystemService(name);
    }
}
