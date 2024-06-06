package com.example.schoolsystem.interfaces;
import java.util.List;

public interface ISchool {
    List<IStudent> getAllStudents();
    List<ITeacher> getAllTeachers();
    List<ICourse> getAllCourses();
    IStudent getStudentById(long studentId);
    ITeacher getTeacherById(long teacherId);
    ICourse getCourseById(long courseId);
    void addStudent(IStudent student);
    void addTeacher(ITeacher teacher);
    void addCourse(ICourse course);
    void updateStudentName(long id, String name);
    void updateTeacherName(long id, String name);
    void updateCourseTitle(long id, String title);
    void removeStudent(long id);
    void removeTeacher(long id);
    void removeCourse(long id);
}
