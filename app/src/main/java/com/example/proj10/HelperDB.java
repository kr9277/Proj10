package com.example.proj10;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.proj10.Student.TABLE_STUDENT;
import static com.example.proj10.Student.KEY_ID;
import static com.example.proj10.Student.NAME;
import static com.example.proj10.Student.ADDRESS;
import static com.example.proj10.Student.PHONE_NUMBER;
import static com.example.proj10.Student.HOME_NUMBER;
import static com.example.proj10.Student.DAD_NAME;
import static com.example.proj10.Student.MOM_NAME;
import static com.example.proj10.Student.DAD_NUMBER;
import static com.example.proj10.Student.MOM_NUMBER;
import static com.example.proj10.Student.ID;
import static com.example.proj10.Grades.TABLE_GRADES;
import static com.example.proj10.Grades.SUBJECT;
import static com.example.proj10.Grades.GRADE;
import static com.example.proj10.Grades.QUARTER;
import static com.example.proj10.Grades.ASSIGNMENT_TYPE;

import androidx.annotation.Nullable;

public class HelperDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 1;
    private String strCreate, strDelete;
    public HelperDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        strCreate="CREATE TABLE "+TABLE_STUDENT;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+NAME+" TEXT,";
        strCreate+=" "+ADDRESS+" TEXT,";
        strCreate+=" "+PHONE_NUMBER+" INTEGER,";
        strCreate+=" "+HOME_NUMBER+" INTEGER,";
        strCreate+=" "+DAD_NAME+" TEXT,";
        strCreate+=" "+MOM_NAME+" TEXT,";
        strCreate+=" "+DAD_NUMBER+" INTEGER,";
        strCreate+=" "+MOM_NUMBER+" INTEGER";
        strCreate+=" "+ID+" INTEGER";
        strCreate+=");";
        sqLiteDatabase.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_GRADES;
        strCreate+=" ("+KEY_ID+" INTEGER,";
        strCreate+=" "+SUBJECT+" TEXT,";
        strCreate+=" "+GRADE+" INTEGER,";
        strCreate+=" "+QUARTER+" INTEGER,";
        strCreate+=" "+ASSIGNMENT_TYPE+" TEXT";
        strCreate+=");";
        sqLiteDatabase.execSQL(strCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        strDelete="DROP TABLE IF EXISTS "+TABLE_STUDENT;
        sqLiteDatabase.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_GRADES;
        sqLiteDatabase.execSQL(strDelete);

        onCreate(sqLiteDatabase);
    }
}
