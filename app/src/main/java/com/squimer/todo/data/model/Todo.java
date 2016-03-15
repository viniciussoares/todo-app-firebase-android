package com.squimer.todo.data.model;

public class Todo {

    public static final String ROOT_NAME         = "todo";
    public static final String CHILD_TITLE       = "title";
    public static final String CHILD_DESCRIPTION = "description";
    public static final String CHILD_DONE        = "done";

    String title;
    String description;
    boolean done;

    public Todo() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
