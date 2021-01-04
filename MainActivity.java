package com.patkim.virtualhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewlogo;
    private TextView textViewWelcome;
    private ImageView doctorImageView;
    private ImageView patientImageView;
    private Button doctorbtn;
    private Button patintbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        textViewlogo= findViewById(R.id.schedulelogo);
        textViewWelcome= findViewById(R.id.textViewWelcome);
        doctorImageView= findViewById(R.id.doctorimageView);
        patientImageView= findViewById(R.id.patientimageView);
        doctorbtn= findViewById(R.id.doctorbtn);
        patintbtn = findViewById(R.id.patientbtn);

        doctorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent= new Intent(getApplicationContext(), Doctor.class);
                startActivity(startIntent);

            }
        });
        patintbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=  new Intent(getApplicationContext(), Register.class);
                startActivity(intent);

            }
        });

    }
}