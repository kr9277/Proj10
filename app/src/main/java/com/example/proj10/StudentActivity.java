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
    EditText name, address, phoneNumber, homeNumber, ID, dadName, momName, dadNumber, momNumber;
    Button btn1;
    ContentValues cv = new ContentValues();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        initAll();
    }
    private void initAll() {
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        phoneNumber = findViewById(R.id.phoneNumber);
        homeNumber = findViewById(R.id.homeNumber);
        dadName = findViewById(R.id.dadName);
        momName = findViewById(R.id.momName);
        dadNumber = findViewById(R.id.dadPhone);
        momNumber = findViewById(R.id.momPhone);
        ID = findViewById(R.id.ID);
        btn1 = findViewById(R.id.btn1);

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
        if(!(ID.getText().toString().equals(""))){
            cv.put(Student.ID, ID.getText().toString());
            if (!name.getText().equals("")) {
                cv.put(Student.NAME, name.getText().toString());
            }
            if (!address.getText().equals("")) {
                cv.put(Student.ADDRESS, address.getText().toString());
            }
            if (!(phoneNumber.getText().toString().equals(""))) {
                cv.put(Student.PHONE_NUMBER, Integer.parseInt(phoneNumber.getText().toString()));
            }
            if (!(homeNumber.getText().toString().equals(""))) {
                cv.put(Student.HOME_NUMBER, Integer.parseInt(homeNumber.getText().toString()));
            }
            if (!dadName.getText().equals("")) {
                cv.put(Student.DAD_NAME, dadName.getText().toString());
            }
            if (!momName.getText().equals("")) {
                cv.put(Student.MOM_NAME, momName.getText().toString());
            }
            if (!(dadNumber.getText().toString().equals(""))) {
                cv.put(Student.DAD_NUMBER, Integer.parseInt(dadNumber.getText().toString()));
            }
            if (!(momNumber.getText().toString().equals(""))) {
                cv.put(Student.MOM_NUMBER, Integer.parseInt(momNumber.getText().toString()));
            }
            db = hlp.getWritableDatabase();
            db.insert(Student.TABLE_STUDENT, null, cv);
            db.close();

            name.setText("");
            address.setText("");
            phoneNumber.setText("");
            homeNumber.setText("");
            dadName.setText("");
            momName.setText("");
            dadNumber.setText("");
            momNumber.setText("");
            ID.setText("");
            Toast.makeText(this, "Data pushed to Students table", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "You did not entered the student's ID", Toast.LENGTH_LONG).show();
        }
    }
}


    
