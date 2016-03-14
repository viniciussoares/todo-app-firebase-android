package com.squimer.todo;

import android.app.Application;

import com.firebase.client.Firebase;

public class TodoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
