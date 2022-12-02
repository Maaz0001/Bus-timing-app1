package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button search,view;
    RadioGroup src,dst;
    RadioButton source,destination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        src=findViewById(R.id.src);
        dst=findViewById(R.id.dest);
        search=findViewById(R.id.Submit);
        view=findViewById(R.id.view);

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