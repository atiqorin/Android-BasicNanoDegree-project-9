package com.android.atiqorin.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db =new DBHelper(MainActivity.this);

        // Inserting activities
        Log.i("Insert: ", "Inserting ..");
        String time=getTime();
        db.addActivity(new Habit(time,"getting up from bed"));
        db.addActivity(new Habit(time,"eating breakfast"));
        db.addActivity(new Habit(time,"Commuting to work"));
        db.addActivity(new Habit(time,"Returning home"));
        //Updating  User Activity of id = 2
        Habit updateobj = new Habit(2, time,"Beakfast finish");
        db.updateUserActivity(updateobj);


        // Reading activities
        Log.i("Reading: ", "Reading all User Activites..");
        List<Habit> contents = db.getUserActivites();

        for (Habit cn : contents) {
            String log = "Id: "+cn.getId()+" ,Actions: " + cn.getAction() + " ,getDuration: " + cn.getDuration();
            // Writing User Activity to log
            Log.i("Activity: ", log);
        }
        db.deleteAllEntries();
        db.deleteDatabase();
    }
    public String getTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
}
