package com.example.proj10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Adapter;

import static com.example.proj10.Student.ID;
import static com.example.proj10.Student.NAME;
import java.util.ArrayList;
import static com.example.proj10.Student.TABLE_STUDENT;

public class Grades_Activity extends AppCompatActivity implements View.OnCreateContextMenuListener,
        AdapterView.OnItemSelectedListener {
    private SQLiteDatabase db;
    private HelperDB hlp;
    Button btn2;
    Spinner sp1, sp2, students;
    private Cursor crsr;
    EditText Subject, Grade;
    String[] assignment_type = {"Choose a assignment", "task","exam","test"};
    String[] quarter = {"Choose a quarter", "first", "second", "third", "fourth"};
    int Quarter;
    ContentValues cv = new ContentValues();
    String subject, grade, AssignmentType;
    private ArrayList<String> studentsList;
    private ArrayList<String> idsList;
    private ArrayAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        initAll();
    }
    @Override
    protected void onResume() {
        super.onResume();
        students_now();
    }
    private void students_now() {
        studentsList = new ArrayList<>();
        idsList = new ArrayList<>();
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();
        studentsList.add("Choose a student");

        String[] columns = {NAME, ID};

        crsr = db.query(TABLE_STUDENT, columns, null, null, null, null, null);
        int name = crsr.getColumnIndex(NAME);
        int id = crsr.getColumnIndex(ID);

        crsr.moveToFirst();
        while(!crsr.isAfterLast()){
            idsList.add(crsr.getString(id));
            studentsList.add(crsr.getString(name)+" ( "+crsr.getString(id));
            crsr.moveToNext();
        }
        crsr.close();
        db.close();
        initAll();
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
    private void initAll() {
        btn2 = findViewById(R.id.btn2);
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        students = findViewById(R.id.students);
        Subject = findViewById(R.id.Subject);
        Grade = findViewById(R.id.Grade);
        ArrayAdapter<String> adp_sp1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, assignment_type);
        ArrayAdapter<String> adp_sp2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, quarter);
        ArrayAdapter<String> adp_students=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, assignment_type);
        sp1.setAdapter(adp_sp1);
        sp2.setAdapter(adp_sp2);
        sp2.setAdapter(adp_students);
        sp1.setOnItemSelectedListener(this);
        sp2.setOnItemSelectedListener(this);
        students.setOnItemSelectedListener(this);
        subject = Subject.getText().toString();
        grade = Grade.getText().toString();

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        students_now();
        db.close();
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i!=0){
            if(adapterView.equals(sp1)){
                AssignmentType = assignment_type[i];
            }
            else{
                Quarter = i;
            }
        }
    }
    public void save_grades(View view){
        if(!Subject.getText().equals("")){
            cv.put(Grades.SUBJECT, Subject.getText().toString());
        }
        if(!(Grade.getText().toString().equals(""))) {
            cv.put(Grades.GRADE, Grade.getText().toString());
        }
        cv.put(Grades.ASSIGNMENT_TYPE, AssignmentType);
        cv.put(Grades.QUARTER, Quarter);

        db = hlp.getWritableDatabase();
        db.insert(Grades.TABLE_GRADES, null, cv);
        db.close();

        Subject.setText("");
        Grade.setText("");
        Toast.makeText(this, "Data pushed to Grades table", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(Grades_Activity.this, "you have to choose something", Toast.LENGTH_SHORT).show();
    }
}