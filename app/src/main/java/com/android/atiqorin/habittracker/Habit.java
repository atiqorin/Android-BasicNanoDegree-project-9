package com.android.atiqorin.habittracker;

/**
 * Created by atiqorin on 7/4/16.
 */
public class Habit {
    int id;
    String duration;
    String Action;
    public Habit(){

    }
    public Habit(String duration, String action) {

        this.duration = duration;
        Action = action;
    }
    public Habit(int id, String duration, String Action){
        this.id=id;
        this.duration = duration;
        this.Action=Action;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getAction() {
        return Action;
    }
    public void setAction(String action) {
        Action = action;
    }
}
