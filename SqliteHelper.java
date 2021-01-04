package com.patkim.virtualhealthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteHelper extends SQLiteOpenHelper {
    //Database variables
    public static  final String DATABASE_NAME= "vhcare";
    public static  final int DATABASE_VERSION=1;
    public static  final String TABLE_USERS= "USERS";
    //TABLES COLUMNS
    public static  final String EMAILID= "email";
    public static  final String USERNAME= "fulName";
    public static  final String PHONE= "Phone";
    public static  final String PASSWORD= "password";

    //CREATING USERS TABLE
    public static  final String SQL_TABLE_USERS= "CREATE TABLE " + TABLE_USERS + "("
            + EMAILID + "TEXT PRIMARY KEY NOT NULL,"
            + USERNAME + "TEXT NOT NULL,"
            + PHONE + "TEXT NOT NULL,"
            + PASSWORD + "TEXT NOT NULL"
            + ")";
    private static final String TAG = SqliteHelper.class.getSimpleName();
    public  SqliteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create Table  when onCreate is called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
    }

    public void addUser(User user) {
        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();
        //create content values to insert
        ContentValues values= new ContentValues();
        values.put(EMAILID, user.email);
        values.put(USERNAME, user.fullName);
        values.put(PHONE, user.Phone);
        values.put(PASSWORD, user.password);

        //insert a row in the database
        long todo_id = db.insert(TABLE_USERS,null, values);
        db.close();

        Log.d(TAG, "New user value:" + values.toString());
        Log.d(TAG, "New user inserted into sqlite" +todo_id);

            }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int  i, int i1)
    {
        // DROP TABLE TO CREATE NEW ONE IF DB VERSION CHANGED
        String query= " DROP TABLE IF EXISTS " +TABLE_USERS;
        sqLiteDatabase.execSQL(query);
        //create tables agaain
        this.onCreate(sqLiteDatabase);

    }
    //Add users to user table
    public User Authenticate(User user) {
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.query(TABLE_USERS, //Select Table
                new  String[] { EMAILID, USERNAME, PHONE,PASSWORD},
                EMAILID + "=?",
                new  String[] {user.email},
                null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            //if cursor has value then in user database there is user
            // associated with this given email
            User user1 =new User(cursor.getString(0),cursor.getString(1),
                    cursor.getString(2), cursor.getString(3));
            //if cursor has value then in user database
            // there is user associated with this given email
            if (user.password.equals(user1.password))
            {
                return  user1;
            }
        }
        return null;

    }

    
   /* public SqliteHelper(Register register) {
        super();
        this.register = register;
    }

    public SqliteHelper(Login login) { super();
    }

    */




    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.query(TABLE_USERS,
                new String[] {EMAILID, USERNAME, PHONE, PASSWORD},
                EMAILID +"=?",
                new String[]{email},
                null,null,null);
        if (cursor != null && cursor.moveToFirst()){
            //if cursor has value then in user database there
            // is user associated with this given
            return true;
        }
        return false;
    }

}
