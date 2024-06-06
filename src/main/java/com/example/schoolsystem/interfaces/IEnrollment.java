package com.example.schoolsystem.interfaces;
import java.util.List;

public interface IEnrollment {
    void enroll(IStudent student, ICourse course);
    List<ICourse> getCoursesByStudent(IStudent student);
    List<IStudent> getStudentsByCourse(ICourse course);
}
