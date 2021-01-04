package com.patkim.virtualhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private TextView textViewlogo3;
    private EditText fullName;
    private EditText Phone;
    private EditText email;
    private EditText regpassword;
    private TextView textView2;
    private EditText confirmPassword;
    private Button regbutton;
    Toast t;
    SqliteHelper sqliteHelper;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("((?=.*[a-z])" +
                    "(?=.*\\d)(?=.*[A-Z])(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&/!]).{8,20})");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //create instance of sqlite
        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        intViews();
        //LISTENING TO THE REGISTRATION BUTTON CLICK
       regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checkDataEntered();
                if (validate()) {
                    String username = fullName.getText().toString();
                    String Email = email.getText().toString();
                    String Password = regpassword.getText().toString();
                    String password2= confirmPassword.getText().toString();

                    //Check in the database is there any user associated with  this email
                    if (!sqliteHelper.isEmailExists(Email)) {

                        //Email does not exist now add new user to database
                        sqliteHelper.addUser(new User(null, username, Email, Password));
                        Snackbar.make(regbutton, "Account created succefully! " +
                                "Please login", Snackbar.LENGTH_LONG).show();
                        //constructor
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                            //Show the Snackbar for a long period of time
                        }, Snackbar.LENGTH_LONG);
                    } else {

                        //Email exists with email input provided so show error user already exist
                        //make a Snackbar to display a message
                        Snackbar.make(regbutton, "Account already exists with same email ",
                                Snackbar.LENGTH_LONG).show();
                    }


                }

                Intent regIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(regIntent);

            }
        });
    }
    private void initTextViewLogin()
    {
        TextView textViewlogin= findViewById(R.id.textViewLogin);
        textViewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    // //this method is used to connect XML views to its Objects
    private void intViews() {
        textViewlogo3 = findViewById(R.id.textViewlogo3);
        fullName = findViewById(R.id.username);
        Phone = findViewById(R.id.Phone);
        email = findViewById(R.id.registerEmail);
        regpassword = findViewById(R.id.regpassword);
        textView2 = findViewById(R.id.textView2);
        confirmPassword = findViewById(R.id.confirmPassword);
        regbutton = findViewById(R.id.regbutton);
    }

    //method validate
    public boolean validate() {
        boolean valid = false;
        String username = fullName.getText().toString();
        String Email = email.getText().toString();
        String Password = regpassword.getText().toString();
        String password2 = confirmPassword.getText().toString();

        //Username validation
        if (username.isEmpty()) {
            valid = false;
            fullName.setError("Please enter a valid fullname");

        } else {
            if (username.length() > 8) {
                valid = true;
                fullName.setError(null);
            } else {
                valid = false;
                fullName.setError("fullname is too short");
            }

            // validating email
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

            if (Password.isEmpty()) {
                regpassword.setError("Field Cannot be Empty");
                valid = false;
            } else if (PASSWORD_PATTERN.matcher(Password).matches()) {
                regpassword.setError("Password too weak");
                valid = false;
            } else {
                regpassword.setError(null);
                valid = true;
            }
            if (!Password.equals(password2))
            {
                //pop up message
                Toast.makeText(Register.this, "Passwords did not match", Toast.LENGTH_SHORT).show();
            }



        }
        return valid;

    }
}

   /* private  boolean  validateEmail(){
        String email = registerEmail.getEditableText().toString().trim();
        if(email.isEmpty())
        {
            registerEmail.setError("Field can't be empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            registerEmail.setError("Please enter a valid email");
            return false;
        }else {
            registerEmail.setError(null);
            return true;
        }
    }
    private boolean fullNameValidation()
    {
        String fullnameInput=  fullName.getEditableText().toString().trim();
        if (fullnameInput.isEmpty())
        {
            fullName.setError("Field cannot be empty");
            return false;
        }else if (fullnameInput.length()>15)
        {
            fullName.setError("Username if too long");
            return false;
        }
        else{
            fullName.setError(null);
            return true;
        }


    }
    private  boolean validatePassword(){
        String passwordInput = regpassword.getEditableText().toString().trim();
        if (passwordInput.isEmpty()){
            regpassword.setError("Field Cannot be Empty");
            return false;
        }
        else if (PASSWORD_PATTERN.matcher(passwordInput).matches())
        {
            regpassword.setError("Password too weak");
            return false;
        }else {
            regpassword.setError(null);
            return true;
        }
    }
    public void confirmInput(View v){
        if (!validateEmail()|!validatePassword() | !fullNameValidation())
        {
            return;
        }
        String input ="Email:" + registerEmail.getEditableText().toString();
        input+= "\n";
        input += "fullName"+ fullName.getEditableText().toString();
        input+= "\n";
        input += "Password:" +regpassword.getEditableText().toString();
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}

    /*boolean isEmail(EditText text) {
        CharSequence registerEmail = text.getText().toString();
        return (!TextUtils.isEmpty(registerEmail) &&
                Patterns.EMAIL_ADDRESS.matcher(registerEmail).matches());

    }


    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(fullName))
            t = Toast.makeText(this,
                    "Your full Name is required", Toast.LENGTH_SHORT);
        t.show();
    }
       /* if(

    isEmpty(ContactsContract.CommonDataKinds.Phone))

    {
        Phone.setError("Phone number is required");

    }
        if(isEmpty(regpassword))

    {
        regpassword.setError("Password is required");

    }
        if(isEmail(registerEmail))

    {
        registerEmail.setError("Enter valid email");
    }
        boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])" +
                "(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,20})").matcher(password);
        return matcher.matches();
       // if (!isValidPassword(regpassword.getText().toString()))

    }*/


