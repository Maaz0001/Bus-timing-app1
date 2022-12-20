package com.example.mad_project;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
//Edit timing

public class Add_Entry extends AppCompatActivity {
    SQLiteDatabase db;
    RadioGroup sources,destination,buses;
    RadioButton src,dst,bus;
    Button tim,sub;
    ImageButton ret_ent;
    TextView time_text;
    String bus_name,src_name,dst_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_entry);
        //Radio groups
        sources=findViewById(R.id.sources);
        destination=findViewById(R.id.destinations);
        buses=findViewById(R.id.busname);
        ret_ent=findViewById(R.id.gback3);

        sub=findViewById(R.id.submit_Entry);
        tim=findViewById(R.id.time);
        time_text=findViewById(R.id.tview);
        db=openOrCreateDatabase("BusTiming",MODE_PRIVATE,null);
        System.out.println("Hello");

        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tp=new TimePickerDialog(Add_Entry.this, new TimePickerDialog.OnTimeSetListener() {
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
                        time_text.setText(hours+":"+min+":"+"00 "+AM_PM);
                    }
                }, 0, 0, false);
                tp.show();
            }
        });



        //submitting
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //values to be inserted
                int sl;

                //Radio buttons
                int id1=buses.getCheckedRadioButtonId();
                int id2=destination.getCheckedRadioButtonId();
                int id3=sources.getCheckedRadioButtonId();

                bus=findViewById(id1);
                dst=findViewById(id2);
                src=findViewById(id3);
                //Bus name
                bus_name=bus.getText().toString();
                src_name=src.getText().toString();
                dst_name=dst.getText().toString();

                if(src_name.equals(dst_name))
                {
                    Toast.makeText(Add_Entry.this, "Source and destination cant be same", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String route="R1";
                    switch(dst_name)
                    {
                        case "Mangalore":
                            route="R2";
                            break;

                        case "Nitte":
                            route="R1";
                            break;

                        case "Udupi":
                            route="R3";
                            break;
                        default:
                            Toast.makeText(Add_Entry.this, "Select a Destination", Toast.LENGTH_SHORT).show();
                    }


                    //Inserting values
                    Cursor c=db.rawQuery("Select Slno from BusTiming",null);
                    c.moveToFirst();
                    do {
                    }while(c.moveToNext());
                    c.moveToPrevious();
                    c.moveToPrevious();
                    sl=c.getInt(0) +1; //SL number

                    ContentValues cv=new ContentValues();
                    cv.put("Slno",sl);
                    cv.put("BusName",bus_name);
                    cv.put("BusTime",time_text.getText().toString());
                    cv.put("Source",src_name);
                    cv.put("Route",route);
                    db.insert("BusTiming",null,cv);
                    Toast.makeText(Add_Entry.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });
        ret_ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


    }
}
