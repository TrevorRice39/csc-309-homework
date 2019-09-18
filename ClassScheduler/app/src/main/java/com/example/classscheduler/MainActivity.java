package com.example.classscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected static final int SETTINGS_REQUEST_CODE = 100;
    public static List<Course> courses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // buttons to add a class or view your current schedule
        Button btn_add_class = findViewById(R.id.btn_add_class);
        Button btn_view_schedule = findViewById(R.id.btn_view_schedule);

        btn_add_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createCourseIntent = new Intent(
                        getApplicationContext(), CreateCourse.class );
                // settingsIntent.putExtra();
                // start the activity, getting a response
                startActivityForResult( createCourseIntent, SETTINGS_REQUEST_CODE );
            }
        });

        btn_view_schedule.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent scheduleIntent = new Intent(getApplicationContext(), Schedule.class);
                TextView tv_courses = findViewById(R.id.tv_courses);
                String str_courses = "";
                for (int i = 0; i < courses.size(); i++) {
                    str_courses += courses.get(i).toString() + " ";
                }
                tv_courses.setText(str_courses);
                startActivity(scheduleIntent);
            }
        });
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {

        // did they approve new settings?
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String courseName = data.getStringExtra("CourseName");
            String startTime = data.getStringExtra("StartTime");
            String endTime = data.getStringExtra("EndTime");
            boolean days[] = data.getBooleanArrayExtra("Days");
            Course course = new Course(days, courseName, startTime, endTime);
            courses.add(course);
        }
    }
}
