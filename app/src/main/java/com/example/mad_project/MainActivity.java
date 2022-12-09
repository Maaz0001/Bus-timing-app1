package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button search,view;
    RadioGroup src,dst;
    RadioButton source,destination;
    TableLayout t;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        src=findViewById(R.id.src);
        dst=findViewById(R.id.dest);
        search=findViewById(R.id.Submit);
        view=findViewById(R.id.view);
        db=openOrCreateDatabase("BusTiming",MODE_PRIVATE,null);
        db.execSQL("create table if not Exists BusTiming(Slno int primary key,BusName varchar(15),BusTime time,Source varchar(15),Route varchar(2))");
        String mCSVfile = "file.csv";
        AssetManager manager = this.getAssets();
        InputStream inStream = null;
        try {
            inStream = manager.open(mCSVfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //importing the csv
        BufferedReader buffer= new BufferedReader(new InputStreamReader(inStream));
        String line = "";
        db.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length != 5) {
                    continue;
                }
                ContentValues cv = new ContentValues();
                cv.put("dbCol0", colums[0].trim());
                cv.put("dbCol1", colums[1].trim());
                cv.put("dbCol2", colums[2].trim());
                cv.put("dbCol3", colums[3].trim());
                cv.put("dbCol4", colums[4].trim());
                db.insert("BusTiming", null, cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        //

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i1=src.getCheckedRadioButtonId();
                int i2=dst.getCheckedRadioButtonId();
                if(i1==i2)
                {
                    Toast.makeText(MainActivity.this, "Source and Destination cannot be same", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    source=findViewById(i1);
                    destination=findViewById(i2);
                    Intent tab =new Intent(MainActivity.this,table11.class);
                    tab.putExtra("src",source.getText().toString());
                    tab.putExtra("dst",destination.getText().toString());
                    startActivity(tab);
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}