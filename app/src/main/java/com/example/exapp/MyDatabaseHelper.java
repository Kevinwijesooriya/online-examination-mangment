package com.example.exapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    //database
    private static final String DATABASE_NAME = "examination.db";
    private static final int DATABASE_VERSION = 1;

    //table names
    private static final String TABLE_ENROLLMENT = "enrollment";
//    private static final String TABLE_sUSER = "sUser";



    //Common column names
    private static final String COLUMN_ID = "_id";

    //enrollment table column names
    private static final String COLUMN_eYEAR = "eYear";
    private static final String COLUMN_eSEMESTER = "eSemester";
    private static final String COLUMN_eMODULE = "eModule";
//    //student user table column names
//    private static final String COLUMN_sUSERNAME = "sUsername";
//    private static final String COLUMN_sPASSWORD = "sPassword";


    //enrollment table create statement
    private static final String CREATE_TABLE_ENROLLMENT = "CREATE TABLE " + TABLE_ENROLLMENT +
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_eYEAR + " INTEGER, " +
            COLUMN_eSEMESTER + " INTEGER, " +
            COLUMN_eMODULE + " TEXT);";
//    //sUser table create statement
//    private static final String CREATE_TABLE_sUSER = "CREATE TABLE " + TABLE_sUSER +
//            " (" + COLUMN_sUSERNAME + " TEXT PRIMARY KEY, " +
//            COLUMN_sPASSWORD + " TEXT);";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating tables
        db.execSQL(CREATE_TABLE_ENROLLMENT);
        //db.execSQL(CREATE_TABLE_sUSER);
        db.execSQL("create Table sUser(sUsername Text primary key,sPassword Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENROLLMENT);
        db.execSQL(("drop table if exists sUser"));


        //create new tables
        onCreate(db);

    }

    //Enrollment table operations

    void addEnrollment(int eYear, int eSemester, String eModule) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_eYEAR, eYear);
        values.put(COLUMN_eSEMESTER, eSemester);
        values.put(COLUMN_eMODULE, eModule);
        long result = db.insert(TABLE_ENROLLMENT, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllEnrollmentData() {
        String query = "SELECT * FROM " + TABLE_ENROLLMENT;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateEnrollmentData(String e_id, int eYear, int eSemester, String eModule) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_eYEAR, eYear);
        cv.put(COLUMN_eSEMESTER, eSemester);
        cv.put(COLUMN_eMODULE, eModule);

        long result = db.update(TABLE_ENROLLMENT, cv, "_id=?", new String[]{e_id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneEnrollmentRow(String e_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_ENROLLMENT, "_id=?", new String[]{e_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllEnrollmentData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ENROLLMENT);
    }

    //Student authentication operations

    public Boolean insertStudentDate(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sUsername", username);
        contentValues.put("sPassword", password);
        long result = db.insert("sUser", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkStudentUsername(String sUsername) {
        //String query = "SELECT * FROM " + TABLE_sUSER + " WHERE " + COLUMN_sUSERNAME + " = ? "  ;
        //"select *from sUsers where sUsername=?" + "WHERE " + COLUMN_sUSERNAME + "=?"
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from sUser where sUsername=?", new String[]{sUsername});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkStudentUsernamePassword(String sUsername, String sPassword) {
        //String query = "SELECT * FROM " + TABLE_sUSER + " WHERE (" + COLUMN_sUSERNAME + " = ? AND " + COLUMN_sPASSWORD + " = ? ) " ;
        //"select *from sUsers where sUsername=? and sPassword=?"
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select *from sUser where sUsername=? and sPassword=?", new String[]{sUsername, sPassword});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }
}
