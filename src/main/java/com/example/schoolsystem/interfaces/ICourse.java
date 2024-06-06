package com.example.schoolsystem.interfaces;
import java.util.List;
import com.example.schoolsystem.models.Teacher;
import com.example.schoolsystem.models.Student;

public interface ICourse {
    Long getId();
    String getTitle();
    void setTitle(String title);
    Teacher getTeacher();
    void setTeacher(Teacher teacher);
    List<Student> getStudents();
    void addStudent(Student student);
    void removeStudent(Student student);
}
