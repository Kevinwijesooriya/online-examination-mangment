package com.example.exapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context,"login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(username Text primary key,password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL(("drop table if exists users"));
    }


    public Boolean insertDate(String username,String password) {
        SQLiteDatabase myDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result=myDB.insert("users",null,contentValues);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public  Boolean checkusername(String username){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor coursor=myDB.rawQuery("select *from users where username=?",new String[]{username});
        if(coursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }
    public  Boolean checkusernamePassword(String username,String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor coursor=myDB.rawQuery("select *from users where username=? and password=?",new String[]{username,password});
        if(coursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }


}
