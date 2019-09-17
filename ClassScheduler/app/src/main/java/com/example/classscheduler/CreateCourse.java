package com.example.classscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class CreateCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        // radio buttons for the days of the week
        Switch sw_monday = findViewById(R.id.sw_monday);
        Switch sw_tuesday = findViewById(R.id.sw_tuesday);
        Switch sw_wednesday = findViewById(R.id.sw_wednesday);
        Switch sw_thursday = findViewById(R.id.sw_thursday);
        Switch sw_friday = findViewById(R.id.sw_friday);
        List<Switch> swList = new ArrayList<Switch>();
        swList.add(sw_monday);
        swList.add(sw_tuesday);
        swList.add(sw_wednesday);
        swList.add(sw_thursday);
        swList.add(sw_thursday);

        // buttons for selecting the time frame
        Button btn_start_time = findViewById(R.id.btn_start_time);
        Button btn_end_time = findViewById(R.id.btn_end_time);

        btn_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // submit your changes button
        Button btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
