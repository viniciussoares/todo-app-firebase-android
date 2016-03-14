package com.squimer.todo.ui.newtodo;

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
import com.squimer.todo.model.Todo;
import com.squimer.todo.ui.BaseActivity;
import com.squimer.todo.util.Injector;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTodoActivity extends BaseActivity implements Firebase.CompletionListener {

    @Bind(R.id.edittext_newtodo_title)
    EditText titleEditText;

    @Bind(R.id.edittext_newtodo_decription)
    EditText descriptionEditText;

    FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtodo);
        ButterKnife.bind(this);

        firebaseManager = Injector.obtain(this, FirebaseManager.class);
    }

    @OnClick(R.id.button_newtodo_save)
    void saveClick() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
            return;

        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);

        firebaseManager.saveTodo(todo, this);
    }

    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
        if (firebaseError == null) {
            finish();
            return;
        }

        Toast.makeText(this, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, NewTodoActivity.class);
    }
}
