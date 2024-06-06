package com.example.schoolsystem.services;
import com.example.schoolsystem.interfaces.ISchool;
import com.example.schoolsystem.models.Course;
import com.example.schoolsystem.models.Student;
import com.example.schoolsystem.models.Teacher;

import java.util.ArrayList;
import java.util.List;

public class School implements ISchool {
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teachers;
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }

    @Override
    public Student getStudentById(long studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    @Override
    public Teacher getTeacherById(long teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == teacherId) {
                return teacher;
            }
        }
        return null;
    }

    @Override
    public Course getCourseById(long courseId) {
        for (Course course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }
        return null;
    }

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public void updateStudentName(long id, String name) {
        Student student = getStudentById(id);
        if (student != null) {
            student.setName(name);
        }
    }

    @Override
    public void updateTeacherName(long id, String name) {
        Teacher teacher = getTeacherById(id);
        if (teacher != null) {
            teacher.setName(name);
        }
    }

    @Override
    public void updateCourseTitle(long id, String title) {
        Course course = getCourseById(id);
        if (course != null) {
            course.setTitle(title);
        }
    }

    @Override
    public void removeStudent(long id) {
        Student student = getStudentById(id);
        if (student != null) {
            students.remove(student);
        }
    }

    @Override
    public void removeTeacher(long id) {
        Teacher teacher = getTeacherById(id);
        if (teacher != null) {
            teachers.remove(teacher);
        }
    }

    @Override
    public void removeCourse(long id) {
        Course course = getCourseById(id);
    
        if (course != null) {
            List<Student> allStudents = new ArrayList<>(course.getStudents());
    
            for (Student student : allStudents) {
                student.dropCourse(course);
            }
    
            courses.remove(course);
        }
    }
    
}

