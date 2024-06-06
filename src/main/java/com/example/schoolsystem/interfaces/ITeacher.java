package com.example.schoolsystem.interfaces;
import com.example.schoolsystem.models.Course;

import java.util.List;

public interface ITeacher {
    Long getId();
    String getName();
    void setName(String name);
    List<Course> getCourses();
    void assignCourse(Course course);
    void removeCourse(Course course);
}
