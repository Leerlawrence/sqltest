package com.example.user.sqltest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by user on 06/09/2016.
 */
public class ListActivity extends AppCompatActivity {

    private static final String SELECT_SQL = "SELECT * FROM daily_entry;";
    private SQLiteDatabase db;

    private Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        _openDatabase();


        c = db.rawQuery(SELECT_SQL, null);
        c.moveToFirst();
        ArrayList <String> allRecords = showAllRecords();

        ListView listView =  (ListView) findViewById(R.id.entryList);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,R.layout.list_row_text,R.id.entry, allRecords);

        listView.setAdapter(listAdapter);

    }

    protected void _openDatabase() {
        //db = openOrCreateDatabase("PersonDB", Context.MODE_PRIVATE, null);


        db = openOrCreateDatabase("foodDB", Context.MODE_PRIVATE, null);

    }

    protected ArrayList<String> showAllRecords() {

        ArrayList <String> fullList = new ArrayList <String> ();


        while (!c.isLast()) {
            String id = c.getString(0);
            String name = c.getString(1);
            String add = c.getString(2);
            String listString = id + " " + name + " " + add;
            fullList.add(listString);
            c.moveToNext();
        }
        return fullList;
    }

}


