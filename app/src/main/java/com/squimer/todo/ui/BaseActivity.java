package com.squimer.todo.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.squimer.todo.R;

public class BaseActivity extends AppCompatActivity {

    Dialog dialog;
    Firebase firebase;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissVisibleDialog();
    }

    public void setupFirebase() {
        firebase = new Firebase(getString(R.string.firebase_url));
    }

    public void showLoadingDialog() {
        if (dialog instanceof ProgressDialog && dialog.isShowing())
            return;

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.all_loading));
        progressDialog.setCancelable(false);
        showDialog(progressDialog);
    }

    public void showDialog(Dialog dialog) {
        dismissVisibleDialog();

        this.dialog = dialog;
        this.dialog.show();
    }

    public void dismissVisibleDialog() {
        if (dialog == null || !dialog.isShowing())
            return;

        dialog.dismiss();
        dialog = null;
    }

    public Firebase getFirebase() {
        return firebase;
    }

    public String getUserUid() {
        try {
            return getFirebase().getAuth().getUid();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
