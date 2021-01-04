package com.patkim.virtualhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Doctor extends AppCompatActivity {
    private TextView textViewlogo5;
    private ImageView imageView2;
    private EditText doctoremail;
    private  EditText doctorPassword;
    private Button doctorlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        textViewlogo5 = findViewById(R.id.textViewlogo5);
        imageView2 = findViewById(R.id.imageView2);
        doctoremail = findViewById(R.id.doctoremail);
        doctorPassword= findViewById(R.id.doctorPassword);
        doctorlogin= findViewById(R.id.doctorlogin);

        doctorlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctIntent= new Intent(getApplicationContext(), Schedule.class);
                startActivity(doctIntent);
            }
        });
    }
}