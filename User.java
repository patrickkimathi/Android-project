package com.patkim.virtualhealthcare;

public class User {
    public String email;
    public String fullName;
    public String password;
    public String Phone;

    public User(Object o, String fullName, String email, String password ) {
        this.password = password;
        this.email= email;
        this.fullName = fullName;


    }
}
