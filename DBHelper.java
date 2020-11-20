package com.patkim.virtualhealthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "VHdb";
    public static final String TABLE_NAME = "PATIENTS";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String PHONE = "myPhone";
    public static final String PASSWORD = "password";


    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase VHdb) {
        VHdb.execSQL("create Table PATIENTS(email TEXT PRIMARY KEY," +
                " name TEXT, myPhone TEXT, password TEXT)");
        //VHdb.execSQL(DBHelper.CreateQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase VHdb, int oldVersion, int newVersion) {
        VHdb.execSQL("drop Table if Exists PATIENTS");
        //   VHdb.execSQL(DBHelper.DeleteQuery);
        //onCreate(VHdb);
    }

    public Boolean insertData(String email, String name, String myPhone, String password) {
        SQLiteDatabase VHdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("myphone", myPhone);
        contentValues.put("password", password);

        long results = VHdb.insert("PATIENTS", null, contentValues);
        if (results == -1) {
            return false;
        } else {
            return true;
        }


    }

    public Boolean upDatePatientsData(String email, String name, String myPhone, String password) {
        SQLiteDatabase VHdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("myphone", myPhone);
        contentValues.put("password", password);
        Cursor cursor = VHdb.rawQuery("Select * from Patients where email= ?",
                new String[]{email});
        if (cursor.getCount() > 0) {
            long results = VHdb.update("PATIENTS", contentValues,
                    "name=?", new String[]{email});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;

        }
    }
    // delete patient data
        public Boolean DeletePatientsData(String email)
        {
            SQLiteDatabase VHdb = this.getWritableDatabase();
            Cursor cursor = VHdb.rawQuery("Select * from Patients where email= ?",
                    new String[]{email});
            if (cursor.getCount() > 0) {
                long results = VHdb.delete("PATIENTS",
                        "name=?", new String[]{email});
                if (results == -1) {
                    return false;
                } else {
                    return true;
                }
            }else
            {
                return  false;

            }
        }
    public Cursor getdata(String email)
    {
        SQLiteDatabase VHdb = this.getWritableDatabase();
        Cursor cursor = VHdb.rawQuery("Select * from Patients where email= ?",
                new String[]{email});
        return cursor;
    }


    }



