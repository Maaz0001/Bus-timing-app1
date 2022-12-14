package com.example.mad_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Filtered_view extends AppCompatActivity {
    SQLiteDatabase db;
    TableLayout tl;
    Button ret;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtered_view);
        ret=findViewById(R.id.gback1);
        db=openOrCreateDatabase("BusTiming",MODE_PRIVATE,null);
        Intent i=getIntent();
        String src=i.getStringExtra("src");
        String route=i.getStringExtra("route");
        Cursor c=db.rawQuery("Select * from BusTiming Where Source=? and Route=? Order by datetime(BusTime) DESC",new String[]{src,route});
        if(c.getCount()==0)
        {
            Toast.makeText(this, "Does not exist", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            c.moveToFirst();
            String result="";
            do {
                String v1 = c.getString(1);
                String v2 = c.getString(2);
                String v3 = c.getString(3);
                result = result + "BusName:" + v1 + "\nBus Time:" + v2 + "\nSource:" + v3 + "\n\n";


            } while (c.moveToNext());
            AlertDialog.Builder ad = new AlertDialog.Builder(Filtered_view.this);
            ad.setTitle("Timings");
            ad.setMessage(result);
            //ad.show();
            //Showing in Table format
            addtable(c);
        }


    }
    public void addtable(Cursor c)
    {

        tl=findViewById(R.id.mainTable);
        TableRow tr0=new TableRow(this);
        //Headers
        TextView h1=new TextView(this);


        h1.setText("Bus Name \t\t\t\t");
        h1.setGravity(Gravity.CENTER);
        h1.setTextSize(25);
        h1.setTextColor(Color.RED);
        h1.setTypeface(Typeface.DEFAULT_BOLD);
        tr0.addView(h1);


        TextView h2=new TextView(this);
        h2.setText("Bus Time \t\t\t\t");
        h2.setGravity(Gravity.CENTER);
        h2.setTextSize(25);
        h2.setTypeface(Typeface.DEFAULT_BOLD);
        h2.setTextColor(Color.RED);
        tr0.addView(h2);

        TextView h3=new TextView(this);
        h3.setText("Source \t\t\t\t");
        h3.setGravity(Gravity.CENTER);
        h3.setTextSize(25);
        h3.setTypeface(Typeface.DEFAULT_BOLD);
        h3.setTextColor(Color.RED);
        tr0.addView(h3);
        //end of Header
        tl.addView(tr0);

        c.moveToFirst();
        do {
            String v1 = c.getString(1);
            String v2 = c.getString(2);
            String v3 = c.getString(3);
            TableRow tr=new TableRow(this);
            TextView tv1=new TextView(this);
            tv1.setText(v1+"\t\t\t\t");
            tv1.setGravity(Gravity.CENTER);
            tv1.setTextColor(Color.BLACK);
            tr.addView(tv1);

            TextView tv2=new TextView(this);
            tv2.setText(v2+"\t\t\t\t");
            tv2.setGravity(Gravity.CENTER);
            tv2.setTextColor(Color.BLACK);
            tr.addView(tv2);

            TextView tv3=new TextView(this);
            tv3.setText(v3+"\t\t\t\t");
            tv3.setGravity(Gravity.CENTER);
            tv3.setTextColor(Color.BLACK);
            tr.addView(tv3);

            tl.addView(tr);
        } while (c.moveToNext());
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
