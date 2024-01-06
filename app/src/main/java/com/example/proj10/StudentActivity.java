package com.example.proj10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.Toast;

public class StudentActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {
    private SQLiteDatabase db;
    private HelperDB hlp;
    EditText et1, et2, et3, et4, et5, et6, et7, et8;
    Button btn1;
    ContentValues cv = new ContentValues();
    String name, address, phone_number, home_number, dad_name, mom_name, dad_number, mom_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        initAll();
    }
    private void initAll() {
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
        et7 = findViewById(R.id.et7);
        et8 = findViewById(R.id.et8);
        btn1 = findViewById(R.id.btn1);
        name = et1.getText().toString();
        address = et2.getText().toString();
        phone_number = et3.getText().toString();
        home_number = et4.getText().toString();
        dad_name = et5.getText().toString();
        mom_name = et6.getText().toString();
        dad_number = et7.getText().toString();
        mom_number = et8.getText().toString();

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
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
        return true;
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
        if(str.equals("watch tables")){
            Intent intent = new Intent(this, WatchTables.class);
            startActivity(intent);
        }
        if(str.equals("credits activity")){
            Intent intent = new Intent(this, Credits_Activity.class);
            startActivity(intent);
        }
        if(str.equals("input grades")){
            Intent intent = new Intent(this, Grades_Activity.class);
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
    public void save_student(View view) {
        if (!et1.getText().equals("")) {
            cv.put(Student.NAME, name);
        }
        if (!et2.getText().equals("")) {
            cv.put(Student.ADDRESS, address);
        }
        if (!(et3.getText().toString().equals(""))) {
            cv.put(Student.PHONE_NUMBER, phone_number);
        }
        if (!(et4.getText().toString().equals(""))) {
            cv.put(Student.HOME_NUMBER, home_number);
        }
        if (!et5.getText().equals("")) {
            cv.put(Student.DAD_NAME, dad_name);
        }
        if (!et6.getText().equals("")) {
            cv.put(Student.MOM_NAME, mom_name);
        }
        if (!(et7.getText().toString().equals(""))) {
            cv.put(Student.DAD_NUMBER, dad_number);
        }
        if (!(et8.getText().toString().equals(""))) {
            cv.put(Student.MOM_NUMBER, mom_number);
        }
        db = hlp.getWritableDatabase();
        db.insert(Student.TABLE_STUDENT, null, cv);
        db.close();

        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
        et7.setText("");
        et8.setText("");
        Toast.makeText(this, "Data pushed to Students table", Toast.LENGTH_LONG).show();
    }
}


    
