package com.example.mad_project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
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


    }
}
