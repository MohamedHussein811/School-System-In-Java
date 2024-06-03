package com.example.schoolsystem.interfaces;
import com.example.schoolsystem.models.Course;
import com.example.schoolsystem.models.Student;
import java.util.List;

public interface IEnrollment {
    void enroll(Student student, Course course);
    List<Course> getCoursesByStudent(Student student);
    List<Student> getStudentsByCourse(Course course);
}
