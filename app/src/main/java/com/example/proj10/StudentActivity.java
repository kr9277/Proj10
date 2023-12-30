package com.example.proj10;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.Toast;

public class StudentActivity extends AppCompatActivity {
    EditText et1, et2, et3, et4, et5, et6, et7, et8;
    Button btn1;
    String Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
        et7 = findViewById(R.id.et7);
        et8 = findViewById(R.id.et8);
        btn1 = findViewById(R.id.btn1);

    }
//    public void btn1(View view){
//        if(et1.getText()!=null && et2.getText()!=null && et3.getText()!=null && et4.getText()!=null && et5.getText()!=null && et6.getText()!=null && et7.getText()!=null &&et8.getText()!=null){
//
//        }
//    }


    
}