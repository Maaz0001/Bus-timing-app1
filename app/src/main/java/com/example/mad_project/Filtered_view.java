package com.example.mad_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Filtered_view extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table11);
        db=openOrCreateDatabase("BusTiming",MODE_PRIVATE,null);
        Intent i=getIntent();
        String src=i.getStringExtra("src");
        String route=i.getStringExtra("route");
        Cursor c=db.rawQuery("Select * from BusTiming Where Source=? and Route=?",new String[]{src,route});
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
            ad.show();
        }

    }
}
