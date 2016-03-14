package com.squimer.todo.data;

import com.firebase.client.Firebase;
import com.squimer.todo.model.Todo;

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
        firebase.child(Todo.CHILD_NAME)
                .child(getUserUid())
                .setValue(todo, completionListener);
    }
}
