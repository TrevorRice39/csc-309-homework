package com.example.classscheduler;

import java.sql.Time;


public class Course {
    // days the user can select, MWTRF
    boolean[] days;

    // Name of course and the start and end time
    String courseName, courseStartTime, courseEndTime;

    // constructor
    public Course(boolean[] days, String courseName, String courseStartTime, String courseEndTime) {
        this.days = days;
        this.courseName = courseName;
        this.courseStartTime = courseStartTime;
        this.courseEndTime = courseEndTime;
    }

    // used for printing the object to the screen
    @Override
    public String toString() {
        String days = "";
        String dayNames = "MTWRF";

        // creating the days string (MWTRF)
        for (int i = 0; i < 5; i++) {
            if (this.days[i]) {
                days += dayNames.charAt(i);
            }
        }
        return courseName + ", " + days + ", " + this.courseStartTime + ", " + this.courseEndTime;
    }
}
