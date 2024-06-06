package com.example.schoolsystem.models;
import com.example.schoolsystem.interfaces.IStudent;
import java.util.ArrayList;
import java.util.List;

public class Student implements IStudent {
    private Long id;
    private String name;
    private List<Course> courses = new ArrayList<>();

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
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
    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public void enrollInCourse(Course course) {
        courses.add(course);
        course.addStudent(this);
    }

    @Override
    public void dropCourse(Course course) {
        courses.remove(course);
        course.removeStudent(this);
    }

}
