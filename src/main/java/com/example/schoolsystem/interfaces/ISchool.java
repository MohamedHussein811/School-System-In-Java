package com.example.schoolsystem.interfaces;
import com.example.schoolsystem.models.Course;
import com.example.schoolsystem.models.Teacher;
import com.example.schoolsystem.models.Student;
import java.util.List;

public interface ISchool {
    List<Student> getAllStudents();
    List<Teacher> getAllTeachers();
    List<Course> getAllCourses();
    Student getStudentById(long studentId);
    Teacher getTeacherById(long teacherId);
    Course getCourseById(long courseId);
    void addStudent(Student student);
    void addTeacher(Teacher teacher);
    void addCourse(Course course);
}
