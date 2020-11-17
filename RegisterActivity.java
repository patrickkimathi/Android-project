package com.patkim.virtualhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    TextView textViewlogo;
    EditText Name;
    EditText myPhone;
    EditText Email;
    EditText Password;
    Button Register;
    TextView ReadyAccount;
    Button Login;
    String emailPattern = "[ a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textViewlogo = findViewById(R.id.textViewlogo);
        Name = findViewById(R.id.name);
        myPhone = findViewById(R.id.myPhone);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.password);
        Register = findViewById(R.id.registerbtn);
        ReadyAccount = findViewById(R.id.textView2);
        Login = findViewById(R.id.button2);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Password Validation
                /*if(Password.getText().toString())
                //Email validation
                    if (Email.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter email address",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        if (Email.getText().toString().trim().matches(emailPattern)) {
                            Toast.makeText(getApplicationContext(),
                                    "valid email address",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    //Password Validation

**/


            }

        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}