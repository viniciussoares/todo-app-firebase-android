package com.squimer.todo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.squimer.todo.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
}
