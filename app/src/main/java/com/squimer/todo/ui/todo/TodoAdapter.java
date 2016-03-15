package com.squimer.todo.ui.todo;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.squimer.todo.R;
import com.squimer.todo.data.FirebaseManager;
import com.squimer.todo.data.model.Todo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodoAdapter extends FirebaseRecyclerAdapter<Todo, TodoAdapter.TodoViewHolder> {

    public TodoAdapter(FirebaseManager firebaseManager) {
        super(Todo.class, R.layout.item_todo, TodoViewHolder.class, firebaseManager.getTodoList());
    }

    @Override
    protected void populateViewHolder(TodoViewHolder viewHolder, Todo todo, int i) {
        viewHolder.bind(todo, getRef(i));
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.textview_todo_title)
        TextView titleTextView;

        @Bind(R.id.textview_todo_description)
        TextView descriptionTextView;

        Todo todo;
        Firebase objectReference;

        public TodoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Todo todo, Firebase objectReference) {
            this.todo = todo;
            this.objectReference = objectReference;
            titleTextView.setText(todo.getTitle());
            descriptionTextView.setText(todo.getDescription());

            updateStatus(titleTextView, todo.isDone());
            updateStatus(descriptionTextView, todo.isDone());
        }

        void updateStatus(TextView textView, boolean done) {
            if (done) {
                textView.setTextColor(Color.GRAY);
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                textView.setTextColor(Color.BLACK);
                textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        }

        @OnClick(R.id.view_todo)
        void onClick() {
            objectReference.child(Todo.CHILD_DONE)
                    .setValue(!todo.isDone());
            objectReference.push();
        }
    }
}
