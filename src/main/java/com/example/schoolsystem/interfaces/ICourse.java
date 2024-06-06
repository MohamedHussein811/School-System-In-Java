package com.example.schoolsystem.interfaces;
import java.util.List;


public interface ICourse {
    Long getId();
    String getTitle();
    void setId(Long id);
    void setTitle(String title);
    ITeacher getTeacher();
    void setTeacher(ITeacher teacher);
    List<IStudent> getStudents();
    void addStudent(IStudent student);
    void removeStudent(IStudent student);
}
