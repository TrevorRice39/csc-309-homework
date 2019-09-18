package com.example.classscheduler;

import java.sql.Time;


public class Course {
    boolean[] days;
    String courseName, courseStartTime, courseEndTime;

    public Course(boolean[] days, String courseName, String courseStartTime, String courseEndTime) {
        this.days = days;
        this.courseName = courseName;
        this.courseStartTime = courseStartTime;
        this.courseStartTime = courseEndTime;
    }

    @Override
    public String toString() {
        String days = "";
        String dayNames = "MWTRF";
        for (int i = 0; i < 5; i++) {
            if (this.days[i]) {
                days += dayNames.charAt(i) + ", ";
            }
        }
        days = days.substring(0, days.length()-1);

        return this.courseName + "      " + days + "     " + this.courseStartTime + "       " + this.courseEndTime;
    }
}
