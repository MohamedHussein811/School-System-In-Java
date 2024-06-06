package com.example.schoolsystem.interfaces;
import java.util.List;

public interface IStudent {
    Long getId();
    String getName();
    void setId(Long id);
    void setName(String name);
    List<ICourse> getCourses();
    void enrollInCourse(ICourse course);
    void dropCourse(ICourse course);
}