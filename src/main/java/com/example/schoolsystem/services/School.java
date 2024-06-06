package com.example.schoolsystem.services;
import com.example.schoolsystem.interfaces.ICourse;
import com.example.schoolsystem.interfaces.ISchool;
import com.example.schoolsystem.interfaces.IStudent;
import com.example.schoolsystem.interfaces.ITeacher;


import java.util.ArrayList;
import java.util.List;

public class School implements ISchool {
    private List<IStudent> students = new ArrayList<>();
    private List<ITeacher> teachers = new ArrayList<>();
    private List<ICourse> courses = new ArrayList<>();

    @Override
    public List<IStudent> getAllStudents() {
        return students;
    }

    @Override
    public List<ITeacher> getAllTeachers() {
        return teachers;
    }

    @Override
    public List<ICourse> getAllCourses() {
        return courses;
    }

    @Override
    public IStudent getStudentById(long studentId) {
        for (IStudent student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    @Override
    public ITeacher getTeacherById(long teacherId) {
        for (ITeacher teacher : teachers) {
            if (teacher.getId() == teacherId) {
                return teacher;
            }
        }
        return null;
    }

    @Override
    public ICourse getCourseById(long courseId) {
        for (ICourse course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }
        return null;
    }

    @Override
    public void addStudent(IStudent student) {
        students.add(student);
    }

    @Override
    public void addTeacher(ITeacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public void addCourse(ICourse course) {
        courses.add(course);
    }

    @Override
    public void updateStudentName(long id, String name) {
        IStudent student = getStudentById(id);
        if (student != null) {
            student.setName(name);
        }
    }

    @Override
    public void updateTeacherName(long id, String name) {
        ITeacher teacher = getTeacherById(id);
        if (teacher != null) {
            teacher.setName(name);
        }
    }

    @Override
    public void updateCourseTitle(long id, String title) {
        ICourse course = getCourseById(id);
        if (course != null) {
            course.setTitle(title);
        }
    }

    @Override
    public void removeStudent(long id) {
        IStudent student = getStudentById(id);
        if (student != null) {
            students.remove(student);
        }
    }

    @Override
    public void removeTeacher(long id) {
        ITeacher teacher = getTeacherById(id);
        if (teacher != null) {
            teachers.remove(teacher);
        }
    }

    @Override
    public void removeCourse(long id) {
        ICourse course = getCourseById(id);
    
        if (course != null) {
            List<IStudent> allStudents = new ArrayList<>(course.getStudents());
            ITeacher teacher = course.getTeacher();

            if (teacher != null) {
                teacher.removeCourse(course);
            }

            for (IStudent student : allStudents) {
                student.dropCourse(course);
            }
    
            courses.remove(course);
        }
    }
    
}

