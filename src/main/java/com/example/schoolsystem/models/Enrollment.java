package com.example.schoolsystem.models;
import com.example.schoolsystem.interfaces.IEnrollment;
import java.util.List;

public class Enrollment implements IEnrollment {
    
    @Override
    public void enroll(Student student, Course course) {
        student.enrollInCourse(course);
    }
    

    @Override
    public List<Course> getCoursesByStudent(Student student) {
        return student.getCourses();
    }

    @Override
    public List<Student> getStudentsByCourse(Course course) {
        return course.getStudents();
    }
}
