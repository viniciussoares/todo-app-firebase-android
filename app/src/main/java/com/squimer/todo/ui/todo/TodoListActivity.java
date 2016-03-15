package com.squimer.todo.ui.todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.FirebaseRecyclerAdapter;
import com.squimer.todo.R;
import com.squimer.todo.data.FirebaseManager;
import com.squimer.todo.data.model.Todo;
import com.squimer.todo.ui.BaseActivity;
import com.squimer.todo.ui.newtodo.NewTodoActivity;
import com.squimer.todo.util.Injector;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodoListActivity extends BaseActivity {

    @Bind(R.id.recyclerview_all)
    RecyclerView recyclerView;

    FirebaseRecyclerAdapter<Todo, TodoAdapter.TodoViewHolder> adapter;

    FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);
        ButterKnife.bind(this);

        firebaseManager = Injector.obtain(this, FirebaseManager.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodoAdapter(firebaseManager);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.fab_todolist_newtodo)
    void newTodoClick() {
        startActivity(NewTodoActivity.newIntent(this));
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, TodoListActivity.class);
    }
}
