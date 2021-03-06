package com.example.user.sqltest;

        import android.content.Context;
        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
import android.widget.DatePicker;

        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextAdd;
    private Button btnAdd;
    private Button btnView;
    private Button btnList;


    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDatabase();

        final Calendar cal = Calendar.getInstance();
        int mYear= cal.get(Calendar.YEAR);
        int mMonth=cal.get(Calendar.MONTH);
        int mDay=cal.get(Calendar.DAY_OF_MONTH);



        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAdd = (EditText) findViewById(R.id.editTextAddress);

        String date = new SimpleDateFormat("dd/MMM/yyyy").format(new Date());


        editTextName.setText(date);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        btnList = (Button) findViewById(R.id.btnList);


        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnList.setOnClickListener(this);


    }


   protected void createDatabase(){
//        db=openOrCreateDatabase("PersonDB", Context.MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS persons(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR,address VARCHAR);");
      // / db=openOrCreateDatabase("PersonaDB", Context.MODE_PRIVATE, null);
      // db.execSQL("CREATE TABLE IF NOT EXISTS persons(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR,address VARCHAR);");

       db=openOrCreateDatabase("foodDB", Context.MODE_PRIVATE, null);
     //db=openOrCreateDatabase("foodEatenDB", Context.MODE_PRIVATE, null);
       db.execSQL("CREATE TABLE IF NOT EXISTS daily_entry(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, date VARCHAR,food VARCHAR);");
     // db.execSQL("CREATE TABLE IF NOT EXISTS daily_entry(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, date VARCHAR,food VARCHAR);");



    }

    protected void insertIntoDB(){
        String name = editTextName.getText().toString().trim();
        String add = editTextAdd.getText().toString().trim();
      //  if(name.equals("") || add.equals("")){
        if(name.equals("") || add.equals("")){



            Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }
        //Log.d("name", name);
        //String query = "INSERT INTO daily_entry (name,address) VALUES('" + name + "', '" + add + "');";
        String query = "INSERT INTO daily_entry (date,food) VALUES('" + name + "', '" + add + "');";
        db.execSQL(query);

        Toast.makeText(getApplicationContext(),"Saved Successfully", Toast.LENGTH_LONG).show();
    }

    private void showPeoples(){
        Intent intent = new Intent(this,ViewPeople.class);
        startActivity(intent);
        //finish();
    }

    private void showList(){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
       // finish();
    }



    @Override
    public void onClick(View v) {
        if(v == btnAdd){
            insertIntoDB();
        }
        if(v==btnView){
            showPeoples();
        }

        if(v==btnList){
            showList();
        }

    }



}