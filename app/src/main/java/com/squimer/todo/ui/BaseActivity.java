package com.squimer.todo.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.squimer.todo.R;
import com.squimer.todo.data.FirebaseManager;
import com.squimer.todo.util.Injector;

public class BaseActivity extends AppCompatActivity {

    Dialog dialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissVisibleDialog();
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

    @Override
    public Object getSystemService(String name) {
        if (Injector.matchesService(name, FirebaseManager.class))
            return getApplicationContext().getSystemService(name);

        return super.getSystemService(name);
    }
}
