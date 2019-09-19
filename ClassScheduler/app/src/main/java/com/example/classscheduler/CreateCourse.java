package com.example.classscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateCourse extends AppCompatActivity {
    String startTime = "";
    String endTime = "";

    // list to keep track of our switches
    final List<Switch> swList = new ArrayList<Switch>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        // slide in, slide out animation
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        // switches for the days of the week
        Switch sw_monday = findViewById(R.id.sw_monday);
        Switch sw_tuesday = findViewById(R.id.sw_tuesday);
        Switch sw_wednesday = findViewById(R.id.sw_wednesday);
        Switch sw_thursday = findViewById(R.id.sw_thursday);
        Switch sw_friday = findViewById(R.id.sw_friday);

        // adding the buttons to our list
        swList.add(0, sw_monday);
        swList.add(1, sw_tuesday);
        swList.add(2, sw_wednesday);
        swList.add(3, sw_thursday);
        swList.add(4, sw_friday);

        // buttons for selecting the time frame
        Button btn_start_time = findViewById(R.id.btn_start_time);
        Button btn_end_time = findViewById(R.id.btn_end_time);

        btn_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // displays the time picker so user can select an hour and minute for the start of their class
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateCourse.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        startTime = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute) + ":00";
                    }
                }, hour, minute, false);

                timePickerDialog.show();

            }
        });

        btn_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // displays the time picker so user can select an hour and minute for the end of their class
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateCourse.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        endTime = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute) + ":00";
                    }
                }, hour, minute, false);

                timePickerDialog.show();

            }
        });


        // submit your changes button
        Button btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView courseName = findViewById(R.id.et_course_name);

                // must enter a course name
                if (courseName.getText().toString().length() == 0) {
                    sendToast("You must enter a course name");
                    return;
                }

                // cant make the course name TOO long
                if (courseName.getText().length() > 20) {
                    sendToast("Course name must be 20 characters or less");
                    return;
                }

                // gathering the days user entered
                boolean days[] = new boolean[5];
                int numDays = 0;
                for (int i = 0; i < 5; i++) {
                    if (swList.get(i).isChecked()) {
                        days[i] = true;
                        numDays++;
                    }
                }

                // they must select at least one day
                if (numDays == 0) {
                    sendToast("You must select at least one day");
                    return;
                }

                // must have a start time
                if (startTime.length() == 0) {
                    sendToast("You must set a start time");
                    return;
                }

                // must have an end time
                if (endTime.length() == 0) {
                    sendToast("You must set an end time");
                    return;
                }

                // pass the data back to main
                Intent main = getIntent();
                main.putExtra("CourseName", courseName.getText().toString());
                main.putExtra("StartTime", startTime);
                main.putExtra("EndTime", endTime);
                main.putExtra("Days", days);
                setResult(RESULT_OK, main);
                finish();

            }
        });

    }

    // function for sending toasts
    protected void sendToast(String text) {
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();;

    }
    static final String SAVE_COURSE_NAME = "courseName";
    static final String SAVE_DAYS = "days";

    // saving the data in the activity
    @Override
    public void onSaveInstanceState( Bundle savedInstanceState ) {
        super.onSaveInstanceState( savedInstanceState );
        TextView courseName = findViewById(R.id.et_course_name);

        // saving the course name being entered
        savedInstanceState.putString(SAVE_COURSE_NAME, courseName.getText().toString());

        // saving the days they have selected
        boolean days[] = new boolean[5];
        for (int i = 0; i < 5; i++) {
            days[i] = swList.get(i).isChecked();
        }
        savedInstanceState.putBooleanArray(SAVE_DAYS, days);
    }

    // restoring the data
    @Override
    public void onRestoreInstanceState( Bundle savedInstanceState ) {
        super.onRestoreInstanceState( savedInstanceState );
        TextView courseName = findViewById(R.id.et_course_name);

        // restoring the course name
        String strCourseName = savedInstanceState.getString(SAVE_COURSE_NAME);

        boolean days[] = savedInstanceState.getBooleanArray(SAVE_DAYS);
        // checking the appropriate switches
        for (int i = 0; i < 5; i++) {
            swList.get(i).setChecked(days[i]);
        }
        courseName.setText(strCourseName);
    }
}
