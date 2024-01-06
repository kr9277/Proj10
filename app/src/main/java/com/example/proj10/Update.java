package com.example.proj10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.proj10.Grades.TABLE_GRADES;
import static com.example.proj10.Student.TABLE_STUDENT;

public class Update extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView tvtitle, tvtables, tvrecord, tvfield, tvadd;
    Button btn_update;
    EditText etadd;
    ListView lvtables, lvrecord, lvfield;
    private SQLiteDatabase db;
    private HelperDB hlp;
    private Cursor crsr;
    private String strtmp, olddata, newdata;

    private ArrayList<String> tblRec, tblFiled;
    private ArrayAdapter<String> adpRecord, adpField;
    private int table, record, field;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        tvtitle = findViewById(R.id.tvtitle);
        tvtables = findViewById(R.id.tvtables);
        tvrecord = findViewById(R.id.tvrecord);
        tvfield = findViewById(R.id.tvfield);
        tvadd = findViewById(R.id.tvadd);
        btn_update = findViewById(R.id.btn_update);
        etadd = findViewById(R.id.etadd);
        lvtables = findViewById(R.id.lvtables);
        lvrecord = findViewById(R.id.lvrecord);
        lvfield = findViewById(R.id.lvfield);

        hlp=new HelperDB(this);
        db=hlp.getWritableDatabase();
        db.close();

        lvtables.setOnItemClickListener(this);
        lvtables.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lvrecord.setOnItemClickListener(this);
        lvrecord.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lvfield.setOnItemClickListener(this);
        lvfield.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        table=-1;
        record=-1;

        String[] tables={TABLE_STUDENT,TABLE_GRADES};
        ArrayAdapter<String> adpTable=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tables);
        lvtables.setAdapter(adpTable);
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
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (lvtables.equals(parent)) {
//            table = position;
//            // table to update
//            tblRec = new ArrayList<>();
//            db = hlp.getReadableDatabase();
//            // read the data
//            switch (table) {
//                case (0): {
//                    crsr = db.query(TABLE_USERS, null, null, null, null, null, null);
//                    int colKEY_ID = crsr.getColumnIndex(Users.KEY_ID);
//                    int colNAME = crsr.getColumnIndex(Users.NAME);
//                    int colPASSWORD = crsr.getColumnIndex(Users.PASSWORD);
//                    int colAGE = crsr.getColumnIndex(Users.AGE);
//
//                    crsr.moveToFirst();
//                    while (!crsr.isAfterLast()) {
//                        int key = crsr.getInt(colKEY_ID);
//                        String name = crsr.getString(colNAME);
//                        String pass = crsr.getString(colPASSWORD);
//                        int age = crsr.getInt(colAGE);
//                        String tmp = "" + key + ", " + name + ", " + pass + ", " + age;
//                        tblRec.add(tmp);
//                        crsr.moveToNext();
//                    }
//                    break;
//                }
//                case (1): {
//                    crsr = db.query(TABLE_GRADES, null, null, null, null, null, null);
//                    int colKEY_ID = crsr.getColumnIndex(Users.KEY_ID);
//                    int colSUBJECT = crsr.getColumnIndex(Grades.SUBJECT);
//                    int colGRADE = crsr.getColumnIndex(Grades.GRADE);
//
//                    crsr.moveToFirst();
//                    while (!crsr.isAfterLast()) {
//                        int key = crsr.getInt(colKEY_ID);
//                        String sub = crsr.getString(colSUBJECT);
//                        int gra = crsr.getInt(colGRADE);
//                        String tmp = "" + key + ", " + sub + ", " + gra;
//                        tblRec.add(tmp);
//                        crsr.moveToNext();
//                    }
//                    break;
//                }
//            }
//            crsr.close();
//            db.close();
//            // display the records
//            adpRecord= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tblRec);
//            lVrecord.setAdapter(adpRecord);
//        } else if (lVrecord.equals(parent)) {
//            if (table != -1) {
//                record = position;
//                // record to update
//                strtmp=tblRec.get(record);
//                strtmp=strtmp.substring(0,strtmp.indexOf(","));
//                tblFiled = new ArrayList<>();
//                db = hlp.getReadableDatabase();
//                // read the data
//                switch (table) {
//                    case (0): {
//                        crsr = db.query(TABLE_USERS, null, KEY_ID+"=?", new String[]{strtmp}, null, null, null);
//                        crsr.moveToFirst();
//                        int colKEY_ID = crsr.getColumnIndex(Users.KEY_ID);
//                        int colNAME = crsr.getColumnIndex(Users.NAME);
//                        int colPASSWORD = crsr.getColumnIndex(Users.PASSWORD);
//                        int colAGE = crsr.getColumnIndex(Users.AGE);
//
//                        tblFiled.add(String.valueOf(crsr.getInt(colKEY_ID)));
//                        tblFiled.add(crsr.getString(colNAME));
//                        tblFiled.add(crsr.getString(colPASSWORD));
//                        tblFiled.add(String.valueOf(crsr.getInt(colAGE)));
//                        break;
//                    }
//                    case (1): {
//                        crsr = db.query(TABLE_GRADES, null, KEY_ID+"=?", new String[]{strtmp}, null, null, null);
//                        crsr.moveToFirst();
//                        int colKEY_ID = crsr.getColumnIndex(Users.KEY_ID);
//                        int colSUBJECT = crsr.getColumnIndex(Grades.SUBJECT);
//                        int colGRADE = crsr.getColumnIndex(Grades.GRADE);
//
//                        tblFiled.add(String.valueOf(crsr.getInt(colKEY_ID)));
//                        tblFiled.add(crsr.getString(colSUBJECT));
//                        tblFiled.add(String.valueOf(crsr.getInt(colGRADE)));
//                        break;
//                    }
//                }
//                crsr.close();
//                db.close();
//                // display the data
//                adpField= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tblFiled);
//                lVfield.setAdapter(adpField);
//            } else {
//                Toast.makeText(this, "You have to choose a table first", Toast.LENGTH_LONG).show();
//            }
//        } else if (lVfield.equals(parent)) {
//            if (table != -1 && record != -1) {
//                field = position;
//                olddata=tblFiled.get(field);
//                eTdata.setHint(olddata);
//            } else {
//                Toast.makeText(this, "You have to choose a table & record first", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}