package com.squimer.todo.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.squimer.todo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements Firebase.AuthResultHandler {

    @Bind(R.id.edittext_login_email)
    EditText emailEditText;

    @Bind(R.id.edittext_login_password)
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setupFirebase();

        if (firebase.getAuth() != null)
            startActivity(MainActivity.newIntent(this));
    }

    @OnClick(R.id.button_login)
    void loginClick() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
            return;

        showLoadingDialog();
        firebase.authWithPassword(email, password, this);
    }

    @Override
    public void onAuthenticated(AuthData authData) {
        if (authData == null)
            return;

        dismissVisibleDialog();
        startActivity(MainActivity.newIntent(this));
        finish();
    }

    @Override
    public void onAuthenticationError(FirebaseError firebaseError) {
        dismissVisibleDialog();
        Toast.makeText(this, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
