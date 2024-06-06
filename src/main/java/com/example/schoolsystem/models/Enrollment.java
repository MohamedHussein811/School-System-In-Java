package com.example.schoolsystem.models;
import com.example.schoolsystem.interfaces.ICourse;
import com.example.schoolsystem.interfaces.IEnrollment;
import com.example.schoolsystem.interfaces.IStudent;

import java.util.List;

public class Enrollment implements IEnrollment {
    
    @Override
    public void enroll(IStudent student, ICourse course) {
        student.enrollInCourse(course);
    }
    

    @Override
    public List<ICourse> getCoursesByStudent(IStudent student) {
        return student.getCourses();
    }

    @Override
    public List<IStudent> getStudentsByCourse(ICourse course) {
        return course.getStudents();
    }
}
