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
}
