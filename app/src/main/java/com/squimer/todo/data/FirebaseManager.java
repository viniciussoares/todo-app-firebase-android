package com.squimer.todo.data;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.squimer.todo.data.model.Todo;

public class FirebaseManager {

    Firebase firebase;

    public FirebaseManager(Firebase firebase) {
        this.firebase = firebase;
    }

    public Firebase getFirebase() {
        return firebase;
    }

    public boolean isAuthenticated() {
        return firebase.getAuth() != null;
    }

    public String getUserUid() {
        try {
            return firebase.getAuth().getUid();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void saveTodo(Todo todo, Firebase.CompletionListener completionListener) {
        Firebase todoFirebase = firebase
                .child(Todo.ROOT_NAME)
                .child(getUserUid())
                .push();

        todoFirebase.setValue(todo, completionListener);
    }

    public Query getTodoList() {
        return firebase.child(Todo.ROOT_NAME)
                .child(getUserUid())
                .orderByKey();
    }
}
