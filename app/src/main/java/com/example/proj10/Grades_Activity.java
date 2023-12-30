package com.example.proj10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Adapter;

public class Grades_Activity extends AppCompatActivity implements View.OnCreateContextMenuListener,
        AdapterView.OnItemSelectedListener {
    Button btn2;
    Spinner sp1, sp2;
    EditText Subject, Grade;
    String[] assignment_type = {"Choose a assignment", "task","exam","test"};
    String[] quarter = {"Choose a quarter", "first", "second", "third", "fourth"};
    String AssignmentType;
    int Quarter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        btn2 = findViewById(R.id.btn2);
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        Subject = findViewById(R.id.Subject);
        Grade = findViewById(R.id.Grade);
        ArrayAdapter<String> adp_sp1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, assignment_type);
        ArrayAdapter<String> adp_sp2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, quarter);
        sp1.setAdapter(adp_sp1);
        sp2.setAdapter(adp_sp2);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i!=0){
            if(adapterView.equals(sp1)){
                AssignmentType = assignment_type[i+1];
            }
            else{
                Quarter = (int)Integer.parseInt(quarter[i+1]);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(Grades_Activity.this, "you have to choose something", Toast.LENGTH_SHORT).show();
    }
}