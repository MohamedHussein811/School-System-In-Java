package com.example.schoolsystem.interfaces;

import java.util.List;

public interface ITeacher {
    Long getId();
    String getName();
    void setName(String name);
    void setId(Long id);
    List<ICourse> getCourses();
    void assignCourse(ICourse course);
    void removeCourse(ICourse course);
}
