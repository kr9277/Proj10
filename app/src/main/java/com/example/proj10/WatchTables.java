package com.example.proj10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import static com.example.proj10.Grades.TABLE_GRADES;
import static com.example.proj10.Student.TABLE_STUDENT;
import static com.example.proj10.Student.KEY_ID;

public class WatchTables extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnCreateContextMenuListener {

    private SQLiteDatabase db;
    private HelperDB hlp;
    private Cursor crsr;
    TextView tvstudent, tvgrades;
    Switch sw;
    Button add;
    ListView lvshow;

    private ArrayList<String> tbl = new ArrayList<>();
    private ArrayAdapter adp;
    private AlertDialog.Builder adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_tables);
        sw = findViewById(R.id.sw);
        add = findViewById(R.id.add);
        lvshow = findViewById(R.id.lvshow);
        tvstudent = findViewById(R.id.tvstudent);
        tvgrades = findViewById(R.id.tvgrades);


        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        lvshow.setOnItemClickListener(this);
        lvshow.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
    }


    public void onSwitch(View v) {
        if (!sw.isChecked()) {
            db = hlp.getWritableDatabase();
            crsr = db.query(TABLE_STUDENT, null, null, null, null, null, null);
            int col1 = crsr.getColumnIndex(Student.KEY_ID);
            int col2 = crsr.getColumnIndex(Student.NAME);
            int col3 = crsr.getColumnIndex(Student.ADDRESS);
            int col4 = crsr.getColumnIndex(Student.PHONE_NUMBER);
            int col5 = crsr.getColumnIndex(Student.HOME_NUMBER);
            int col6 = crsr.getColumnIndex(Student.DAD_NAME);
            int col7 = crsr.getColumnIndex(Student.MOM_NAME);
            int col8 = crsr.getColumnIndex(Student.DAD_NUMBER);
            int col9 = crsr.getColumnIndex(Student.MOM_NUMBER);
            int col10 = crsr.getColumnIndex(Student.ID);
            crsr.moveToFirst();
            while (!crsr.isAfterLast()) {
                int key = crsr.getInt(col1);
                String name = crsr.getString(col2);
                String address = crsr.getString(col3);
                int phone_number = crsr.getInt(col4);
                int home_number = crsr.getInt(col5);
                String dad_name = crsr.getString(col6);
                String mom_name = crsr.getString(col7);
                int dad_number = crsr.getInt(col8);
                int mom_number = crsr.getInt(col9);
                int Id = crsr.getInt(col10);
                String tmp = "" + key + ", " + name + ", " + address + ", " + phone_number + home_number + ", " + dad_name + ", " + mom_name + ", " + dad_number + ", " + mom_number + ", " + Id;
                tbl.add(tmp);
                crsr.moveToNext();
            }
            crsr.close();
            db.close();
            // display the table
            adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
            lvshow.setAdapter(adp);
        }
        else {
            db = hlp.getWritableDatabase();
            crsr = db.query(TABLE_GRADES, null, null, null, null, null, null);
            int col1 = crsr.getColumnIndex(Grades.SUBJECT);
            int col2 = crsr.getColumnIndex(Grades.GRADE);
            int col3 = crsr.getColumnIndex(Grades.QUARTER);
            int col4 = crsr.getColumnIndex(Grades.ASSIGNMENT_TYPE);
            crsr.moveToFirst();
            while (!crsr.isAfterLast()) {
                String subject = crsr.getString(col1);
                int grade = crsr.getInt(col2);
                int quarter = crsr.getInt(col3);
                String assignment_type = crsr.getString(col4);
                String tmp = "" + subject + ", " + grade + ", " + quarter + ", " + assignment_type;
                tbl.add(tmp);
                crsr.moveToNext();
            }
            crsr.close();
            db.close();
            // display the table
            adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
            lvshow.setAdapter(adp);
            }
        }
    /**
     * onCreateOptionsMenu method
     * <p> Creating the options menu
     * </p>
     *
     * @param menu the Menu object to pass to the inflater
     * @return true
     */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.tafrit, menu);
        return super.onCreateOptionsMenu(menu);
        }
    /**
     * onOptionsItemSelected method
     * <p> Reacting the options menu
     * </p>
     *
     * @param item the MenuItem object that triggered by the listener
     * @return super.onOptionsItemSelected(item)
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        String str = item.getTitle().toString();
        if(str.equals("input grades")){
            Intent intent = new Intent(this, Grades_Activity.class);
            startActivity(intent);
        }
        if(str.equals("credits activity")){
            Intent intent = new Intent(this, Credits_Activity.class);
            startActivity(intent);
        }
        if(str.equals("input student")){
            Intent intent = new Intent(this, StudentActivity.class);
            startActivity(intent);
        }
        if(str.equals("update tables")){
            Intent intent = new Intent(this, Update.class);
            startActivity(intent);
        }
        if(str.equals("sort")){
            Intent intent = new Intent(this, Sort.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}