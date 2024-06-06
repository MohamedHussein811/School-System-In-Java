
package com.example.schoolsystem.models;
import java.util.ArrayList;
import java.util.List;
import com.example.schoolsystem.interfaces.ICourse;
import com.example.schoolsystem.interfaces.IStudent;
import com.example.schoolsystem.interfaces.ITeacher;

public class Course implements ICourse {
    private Long id;
    private String title;
    private ITeacher teacher;
    private List<IStudent> students = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
    public ITeacher getTeacher() {
        return teacher;
    }

    @Override
    public void setTeacher(ITeacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public List<IStudent> getStudents() {
        return students;
    }

    @Override
    public void addStudent(IStudent student) {
        students.add(student);
    }

    @Override
    public void removeStudent(IStudent student) {
        students.remove(student);
    }
}
