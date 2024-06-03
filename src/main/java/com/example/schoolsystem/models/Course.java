
package com.example.schoolsystem.models;
import java.util.ArrayList;
import java.util.List;
import com.example.schoolsystem.interfaces.ICourse;

public class Course implements ICourse {
    private Long id;
    private String title;
    private Teacher teacher;
    private List<Student> students = new ArrayList<>();

    public Course(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }
}
