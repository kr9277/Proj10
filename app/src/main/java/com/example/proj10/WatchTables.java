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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import static com.example.proj10.Grades.TABLE_GRADES;
import static com.example.proj10.Student.TABLE_STUDENT;
import static com.example.proj10.Student.KEY_ID;

public class WatchTables extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private SQLiteDatabase db;
    private HelperDB hlp;
    private Cursor crsr;

    private ListView lv1, lv2;
    private ArrayList<String> tbl = new ArrayList<>();
    private ArrayAdapter adp;
    private int tablechoise;
    private AlertDialog.Builder adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_tables);
        lv1=(ListView)findViewById(R.id.lv1);
        lv2=(ListView)findViewById(R.id.lv2);

        hlp=new HelperDB(this);
        db=hlp.getWritableDatabase();
        db.close();

        lv1.setOnItemClickListener(this);
        lv1.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lv2.setOnItemClickListener(this);
        lv2.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        tablechoise=0;

        String[] tables={TABLE_STUDENT,TABLE_GRADES};
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tables);
        lv1.setAdapter(adp);
    }
    /**
     * onItemClick
     * <p>
     * This method react to the selected option in the listViews
     * @param parent the listView selected
     * @param view the view
     * @param position the position selected
     * @param id the id selected
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        if (parent == lv1) {
            tbl = new ArrayList<>();
            tablechoise = position + 1;
            // table to display
            if (tablechoise != 0) {
                db = hlp.getReadableDatabase();
                // read the table
                if (tablechoise == 1) {
                    crsr = db.query(TABLE_STUDENT, null, null, null, null, null, null);
                    int colKEY_ID = crsr.getColumnIndex(Student.KEY_ID);
                    int colNAME = crsr.getColumnIndex(Student.NAME);
                    int colADDRESS = crsr.getColumnIndex(Student.ADDRESS);
                    int colPHONE_NUMBER = crsr.getColumnIndex(Student.PHONE_NUMBER);
                    int colHOME_NUMBER = crsr.getColumnIndex(Student.HOME_NUMBER);
                    int colDAD_NAME = crsr.getColumnIndex(Student.DAD_NAME);
                    int colMOM_NAME = crsr.getColumnIndex(Student.MOM_NAME);
                    int colDAD_NUMBER = crsr.getColumnIndex(Student.DAD_NUMBER);
                    int colMOM_NUMBER = crsr.getColumnIndex(Student.MOM_NUMBER);

                    crsr.moveToFirst();
                    while (!crsr.isAfterLast()) {
                        int key = crsr.getInt(colKEY_ID);
                        String name = crsr.getString(colNAME);
                        String address = crsr.getString(colADDRESS);
                        int phone_number = crsr.getInt(colPHONE_NUMBER);
                        int home_number = crsr.getInt(colHOME_NUMBER);
                        String dad_name = crsr.getString(colDAD_NAME);
                        String mom_name = crsr.getString(colMOM_NAME);
                        int dad_number = crsr.getInt(colDAD_NUMBER);
                        int mom_number = crsr.getInt(colMOM_NUMBER);
                        String tmp = "" + key + ", " + name + ", " + address + ", " + phone_number + home_number + ", " + dad_name + ", " + mom_name + ", " + dad_number + ", " + mom_number;
                        tbl.add(tmp);
                        crsr.moveToNext();
                    }
                } else {
                    crsr = db.query(TABLE_GRADES, null, null, null, null, null, null);
                    int colKEY_ID = crsr.getColumnIndex(Student.KEY_ID);
                    int colSUBJECT = crsr.getColumnIndex(Grades.SUBJECT);
                    int colGRADE = crsr.getColumnIndex(Grades.GRADE);
                    int colQUARTER = crsr.getColumnIndex(Grades.QUARTER);
                    int colASSIGNMENT_TYPE = crsr.getColumnIndex(Grades.ASSIGNMENT_TYPE);

                    crsr.moveToFirst();
                    while (!crsr.isAfterLast()) {
                        int key = crsr.getInt(colKEY_ID);
                        String subject = crsr.getString(colSUBJECT);
                        int grade = crsr.getInt(colGRADE);
                        int quarter = crsr.getInt(colQUARTER);
                        String assignment_type = crsr.getString(colASSIGNMENT_TYPE);
                        String tmp = "" + key + ", " + subject + ", " + grade + ", " + quarter + ", " + assignment_type;
                        tbl.add(tmp);
                        crsr.moveToNext();
                    }
                }
                crsr.close();
                db.close();
                // display the table
                adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
                lv2.setAdapter(adp);
            } else {
                Toast.makeText(this, "Choose table first!", Toast.LENGTH_LONG).show();
            }
        } else {
            String strtmp = tbl.get(position);
            // alert to ensure delete of record & delete
            adb = new AlertDialog.Builder(this);
            adb.setTitle("Are you sure ?");
            adb.setMessage("Are you sure you want to delete " + strtmp);
            adb.setPositiveButton("Yes !", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db = hlp.getWritableDatabase();
                    if (tablechoise == 1) {
                        db.delete(TABLE_STUDENT, KEY_ID+"=?", new String[]{Integer.toString(position + 1)});
                    } else {
                        db.delete(TABLE_GRADES, KEY_ID+"=?", new String[]{Integer.toString(position + 1)});
                    }
                    db.close();
                    tbl.remove(position);
                    adp.notifyDataSetChanged();
                }
            });
            adb.setNeutralButton("Cancel !", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog ad = adb.create();
            ad.show();
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
}