package com.example.classscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        // slide in, slide out animation
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        // getting the data passed in from main
        Intent intent = getIntent();
        String courses = intent.getStringExtra("courses");

        // populating the text view
        TextView tv_courses = findViewById(R.id.tv_courses);
        tv_courses.setText(tv_courses.getText() + "\n" + courses);

    }


    static final String SAVE_COURSES = "courses";

    // saving the data in the activity
    @Override
    public void onSaveInstanceState( Bundle savedInstanceState ) {
        super.onSaveInstanceState( savedInstanceState );
        TextView courses = findViewById(R.id.tv_courses);

        // saving our text view
        savedInstanceState.putString(SAVE_COURSES, courses.getText().toString());

    }

    // restoring the data
    @Override
    public void onRestoreInstanceState( Bundle savedInstanceState ) {
        super.onRestoreInstanceState( savedInstanceState );
        TextView courses = findViewById(R.id.tv_courses);

        String strCourses = savedInstanceState.getString(SAVE_COURSES);
        // restoring our text view
        courses.setText(strCourses);

    }
}
