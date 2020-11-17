package com.patkim.virtualhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView textViewLogo;
    EditText myName;
    EditText Email;
    EditText Password;
    Button login;
    TextView textView;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        textViewLogo= findViewById(R.id.textViewlogo);
        myName= findViewById(R.id.myName);
        Email= findViewById(R.id.emaill);
        Password= findViewById(R.id.password);
        login= findViewById(R.id.loginBtn);
        textView= findViewById(R.id.textView);
        register = findViewById(R.id.btnregister);
        
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}