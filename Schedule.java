package com.patkim.virtualhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

public class Schedule extends AppCompatActivity {
    private TextView schedulelogo;
    private  TextView textViewSchedule;
    private TextView patientRecords;
    private TextView feedback;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        schedulelogo= findViewById(R.id.schedulelogo);
        textViewSchedule = findViewById(R.id.tesxtViewSchedule);
        patientRecords = findViewById(R.id.patientRecords);
        feedback = findViewById(R.id.feedback);
        calendarView = findViewById(R.id.calendarView);

        //setting listeners, SCHEDULE LISTENER
        textViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent scheduleIntent = new Intent(getApplicationContext(), UpdateSchedule.class);
                startActivity(scheduleIntent);

            }
        });
        //patient records listener
        patientRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //feedback listner
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}