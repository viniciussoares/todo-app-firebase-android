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

    void setupFirebase() {
        firebase = new Firebase(getString(R.string.firebase_url));
    }

    void showLoadingDialog() {
        if (dialog instanceof ProgressDialog && dialog.isShowing())
            return;

        if (dialog != null && dialog.isShowing())
            dismissVisibleDialog();

        dialog = new ProgressDialog(this);
        ((ProgressDialog) dialog).setMessage(getString(R.string.all_loading));
        dialog.setCancelable(false);
        dialog.show();
    }

    void showDialog(Dialog dialog) {
        dismissVisibleDialog();

        this.dialog = dialog;
        this.dialog.show();
    }

    void dismissVisibleDialog() {
        if (dialog == null || !dialog.isShowing())
            return;

        dialog.dismiss();
        dialog = null;
    }
}
