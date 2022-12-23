package com.example.mad_project;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Mod_entry extends AppCompatActivity {
    TextView time_text2,sl;
    Button tim,mod,va;
    SQLiteDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifyentry);
        tim=findViewById(R.id.time2);
        time_text2=findViewById(R.id.tview4);
        mod=findViewById(R.id.modifyval);
        va=findViewById(R.id.vall);
        sl=findViewById(R.id.slno_given);
        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tp=new TimePickerDialog(Mod_entry.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String hours,min,AM_PM;
                        if(i < 12) {
                            AM_PM = "AM";
                        } else {
                            AM_PM = "PM";
                        }
                        if(AM_PM.equals("AM")) {
                            if(i>=10)
                                hours=i+"";
                            else
                                hours="0"+i;

                        }
                        else
                        {
                            i=i-12;
                            if(i==0)
                                hours="12";
                            else if(i>=10)
                            {
                                hours=i+"";
                            }
                            else
                            {
                                hours="0"+i;
                            }

                        }
                        if(i1<10)
                        {
                            min="0"+i1;
                        }
                        else
                        {
                            min=i1+"";
                        }
                        time_text2.setText(hours+":"+min+":"+"00 "+AM_PM);
                    }
                }, 0, 0, false);
                tp.show();
            }
        });

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=openOrCreateDatabase("BusTiming",MODE_PRIVATE,null);
                String slno = sl.getText().toString();
                Cursor c=db.rawQuery("Select Slno from BusTiming",null);
                c.moveToFirst();
                do {
                }while(c.moveToNext());
                c.moveToPrevious();
                c.moveToPrevious();
                if(Integer.parseInt(slno)>c.getInt(0))
                {
                    Toast.makeText(Mod_entry.this, "Invalid Slno", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    ContentValues cv = new ContentValues();

                    cv.put("Bustime", time_text2.getText().toString());
                    db.update("BusTiming", cv, "Slno=?", new String[]{slno});
                    Toast.makeText(Mod_entry.this, "Modified Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        va.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent va=new Intent(Mod_entry.this,ViewAll.class);
                startActivity(va);
            }
        });
    }
}
