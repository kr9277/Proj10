package com.example.proj10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
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
        if(str.equals("input grades")){
            Intent intent = new Intent(this, Grades_Activity.class);
            startActivity(intent);
        }
        if(str.equals("sort")){
            Intent intent = new Intent(this, Sort.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}