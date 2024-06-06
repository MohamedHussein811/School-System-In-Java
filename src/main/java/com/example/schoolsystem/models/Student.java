package com.example.schoolsystem.models;
import com.example.schoolsystem.interfaces.ICourse;
import com.example.schoolsystem.interfaces.IStudent;
import java.util.ArrayList;
import java.util.List;

public class Student implements IStudent {
    private Long id;
    private String name;
    private List<ICourse> courses = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override 
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<ICourse> getCourses() {
        return courses;
    }

    @Override
    public void enrollInCourse(ICourse course) {
        courses.add(course);
        course.addStudent(this);
    }

    @Override
    public void dropCourse(ICourse course) {
        courses.remove(course);
        course.removeStudent(this);
    }

}
