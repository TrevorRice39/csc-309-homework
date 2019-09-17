package com.example.classscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    protected static final int SETTINGS_REQUEST_CODE = 100;
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
    }
}
