package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button search,view,addentry;
    RadioGroup src,dst;
    RadioButton source,destination;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        src=findViewById(R.id.src);
        dst=findViewById(R.id.dest);
        search=findViewById(R.id.Submit);
        view=findViewById(R.id.view);
        addentry=findViewById(R.id.addentry);
        db=openOrCreateDatabase("BusTiming",MODE_PRIVATE,null);
        db.execSQL("create table if not Exists BusTiming(Slno int primary key,BusName varchar(15),BusTime time,Source varchar(15),Route varchar(2))");
        //importing the csv
        InputStream inStream=this.getResources().openRawResource(R.raw.bus_csv);
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
                cv.put("Slno", colums[0].trim());
                cv.put("BusName", colums[1].trim());
                cv.put("BusTime", colums[2].trim());
                cv.put("Source", colums[3].trim());
                cv.put("Route", colums[4].trim());
                db.insert("BusTiming", null, cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        //end of importing

        //Filtering
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i1=src.getCheckedRadioButtonId();
                int i2=dst.getCheckedRadioButtonId();
                String route;
                source=findViewById(i1);
                destination=findViewById(i2);
                if(source.getText().toString()==destination.getText().toString())
                {
                    Toast.makeText(MainActivity.this, "Source and Destination cannot be same", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(destination.getText().toString().equals("Mangalore"))
                    {
                        route="R2";
                    }
                    else if(destination.getText().toString().equals("Nitte"))
                    {
                        route="R1";
                    }
                    else
                    {
                        route="R3";
                    }
                    Intent tab =new Intent(MainActivity.this, Filtered_view.class);
                    tab.putExtra("src",source.getText().toString());
                    System.out.println("route"+destination.getText().toString());
                    tab.putExtra("route",route);
                    startActivity(tab);
                }
            }
        });


        //View All
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent va=new Intent(MainActivity.this,ViewAll.class);
                startActivity(va);

            }
        });

        //adding Entry
        addentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ae=new Intent(MainActivity.this,Add_Entry.class);
                startActivity(ae);
            }
        });
    }
}