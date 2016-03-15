package com.squimer.todo.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.squimer.todo.R;
import com.squimer.todo.data.FirebaseManager;
import com.squimer.todo.ui.BaseActivity;
import com.squimer.todo.util.Injector;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends BaseActivity  implements Firebase.ResultHandler {

    @Bind(R.id.edittext_login_email)
    EditText emailEditText;

    @Bind(R.id.edittext_login_password)
    EditText passwordEditText;

    FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        firebaseManager = Injector.obtain(this, FirebaseManager.class);
    }

    @OnClick(R.id.button_signup)
    void signupClick() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
            return;

        showLoadingDialog();
        firebaseManager.getFirebase()
                .createUser(email, password, this);
    }

    @Override
    public void onSuccess() {
        dismissVisibleDialog();
        finish();
    }

    @Override
    public void onError(FirebaseError firebaseError) {
        dismissVisibleDialog();
        Toast.makeText(this, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, SignupActivity.class);
    }
}
