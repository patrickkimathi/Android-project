package com.patkim.virtualhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private TextView textViewlogo3;
    private ImageView imageViewpatient;
    private TextView pleasetextView;
    private EditText email;
    private  EditText password;
    private TextView acounttextView;
    private Button loginbtn;
    private Button buttonreg;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("((?=.*[a-z])" +
                    "(?=.*\\d)(?=.*[A-Z])(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&/!]).{8,20})");
    
    SqliteHelper sqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        
        sqliteHelper= new SqliteHelper(this);
        creatAccountTextView();
        initViews();
        loginbtn= findViewById(R.id.loginbtn);
        //login listner
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent= new Intent(getApplicationContext(), BookAppointment.class);
                startActivity(loginIntent);
                //validate user input
                if (validate())
                {
                    String Email= email.getText().toString();
                    String Password= password.getText().toString();
                    //user authentication
                    User currentUser= sqliteHelper.Authenticate(new User(null,
                            null,Email,Password));
                    //check if Authentication is successful or not
                    if (currentUser !=null)
                    {
                        Snackbar.make(loginbtn,
                                "Logged in Successfully!", Snackbar.LENGTH_LONG).show();

                    }else
                    {
                        // User logging failed
                        Snackbar.make(loginbtn,"Failed to Log in", Snackbar.LENGTH_LONG).show();

                    }

                }
            }
        });

    }

    private boolean validate() {
        boolean valid= false;
        //Get EditText Values
        String Email= email.getText().toString();
        String Password= password.getText().toString();
        //validating the email entered
        if (Email.isEmpty()) {
            email.setError("Field can't be empty");
            valid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Please enter a valid email");
            return false;
        } else {
            email.setError(null);
            valid = true;
        }
        // validating password
        if (Password.isEmpty()) {
            valid= false;
            password.setError("Field Cannot be Empty");

        } else if (PASSWORD_PATTERN.matcher(Password).matches()) {
            password.setError("Password too weak");
            valid = false;
        } else {
            password.setError(null);
            valid = true;
        }
        return valid;
    }
    private void initViews() {
        textViewlogo3 = findViewById(R.id.textViewlogo3);
        imageViewpatient= findViewById(R.id.imageViewpatient);
        pleasetextView= findViewById(R.id.pleasetextView);
        email = findViewById(R.id.loginemail);
        password = findViewById(R.id.loginpassword);
        acounttextView = findViewById(R.id.accounttextView);
        loginbtn= findViewById(R.id.loginbtn);
        buttonreg= findViewById(R.id.buttonreg);
    }
    private void creatAccountTextView() {
        buttonreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}