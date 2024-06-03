package com.example.schoolsystem.interfaces;
import com.example.schoolsystem.models.Course;
import java.util.List;

public interface IStudent {
    Long getId();
    String getName();
    void setName(String name);
    List<Course> getCourses();
    void enrollInCourse(Course course);
}