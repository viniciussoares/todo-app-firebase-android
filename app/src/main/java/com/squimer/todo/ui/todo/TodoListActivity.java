package com.squimer.todo.ui.todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.squimer.todo.R;
import com.squimer.todo.ui.BaseActivity;
import com.squimer.todo.ui.newtodo.NewTodoActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodoListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab_todolist_newtodo)
    void newTodoClick() {
        startActivity(NewTodoActivity.newIntent(this));
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, TodoListActivity.class);
    }
}
