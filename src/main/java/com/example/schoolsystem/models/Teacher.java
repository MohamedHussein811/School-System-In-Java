package com.example.schoolsystem.models;
import com.example.schoolsystem.interfaces.ITeacher;
import java.util.ArrayList;
import java.util.List;

public class Teacher implements ITeacher {
    private Long id;
    private String name;
    private List<Course> courses = new ArrayList<>();

    public Teacher(Long id, String name) {
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
    public void assignCourse(Course course) {
        courses.add(course);
        course.setTeacher(this);
    }

    @Override
    public void removeCourse(Course course) {
        courses.remove(course);
        course.setTeacher(null);
    }
}
