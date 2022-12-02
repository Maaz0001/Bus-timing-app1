package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button filter,view_all;
    EditText b_src,b_dst,b_time;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_src=findViewById(R.id.bus_src);
        b_dst=findViewById(R.id.bus_dst);
        b_time=findViewById(R.id.bus_time);
        filter=findViewById(R.id.Submit);
        view_all=findViewById(R.id.view);
        db = openOrCreateDatabase("BusTiming",MODE_PRIVATE,null);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }
}