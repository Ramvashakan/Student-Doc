package com.valaithalam.studoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "login.db";
    public static final String TABLE_NAME = "login";


    public DatabaseHelper(Context context) {
        super(context,"login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table login(username text, email text primary key, password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" drop table if exists "+ TABLE_NAME);
        onCreate(db);
    }

    public long adduser(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("login", null, contentValues);
        db.close();
        return ins;
    }
    public Boolean checkuser(String mail,String password){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" select * from login where email =? and password =?  ", new String[]{mail,password});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }

    }
}
