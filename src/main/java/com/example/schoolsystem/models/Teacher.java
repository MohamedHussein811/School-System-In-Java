package com.example.schoolsystem.models;
import com.example.schoolsystem.interfaces.ICourse;
import com.example.schoolsystem.interfaces.ITeacher;
import java.util.ArrayList;
import java.util.List;

public class Teacher implements ITeacher {
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
    public void assignCourse(ICourse course) {
        courses.add(course);
        course.setTeacher(this);
    }

    @Override
    public void removeCourse(ICourse course) {
        courses.remove(course);
        course.setTeacher(null);
    }
}
