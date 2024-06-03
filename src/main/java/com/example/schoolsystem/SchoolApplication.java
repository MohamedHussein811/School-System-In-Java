package com.example.schoolsystem;
import java.util.List;
import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import com.example.schoolsystem.interfaces.ISchool;
import com.example.schoolsystem.models.Course;
import com.example.schoolsystem.models.Enrollment;
import com.example.schoolsystem.models.Student;
import com.example.schoolsystem.models.Teacher;

@SpringBootApplication
public class SchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            ISchool school = ctx.getBean(ISchool.class);
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
    
            while (true) {
                System.out.println("Choose what you want to add (student/teacher/course), or type 'enroll' to enroll students, or 'end' to finish:");
                String choice = scanner.nextLine().trim().toLowerCase();
    
                if (choice.equals("end")) {
                    break;
                }
    
                switch (choice) {
                    case "student":
                        System.out.print("Enter student ID: ");
                        long studentId = scanner.nextLong();
                        System.out.print("Enter student name: ");
                        scanner.nextLine();
                        String studentName = scanner.nextLine();
                        Student student = new Student(studentId, studentName);
                        school.addStudent(student);
                        break;
                    case "teacher":
                        System.out.print("Enter teacher ID: ");
                        long teacherId = scanner.nextLong();
                        System.out.print("Enter teacher name: ");
                        scanner.nextLine();
                        String teacherName = scanner.nextLine();
                        Teacher teacher = new Teacher(teacherId, teacherName);
                        school.addTeacher(teacher);
                        break;
                    case "course":
                        System.out.print("Enter course ID: ");
                        long courseId = scanner.nextLong();
                        System.out.print("Enter course title: ");
                        scanner.nextLine(); 
                        String courseTitle = scanner.nextLine();
                        Course course = new Course(courseId, courseTitle);
                        school.addCourse(course);
                        break;
                    case "enroll":
                        System.out.print("Enter student ID to enroll: ");
                        long enrollStudentId = scanner.nextLong();
                        System.out.print("Enter course ID to enroll the student in: ");
                        long enrollCourseId = scanner.nextLong();
    
                        Student enrollStudent = school.getStudentById(enrollStudentId);
                        Course enrollCourse = school.getCourseById(enrollCourseId);
    
                        if (enrollStudent != null && enrollCourse != null) {
                            Enrollment enrollment = ctx.getBean(Enrollment.class);
                            enrollment.enroll(enrollStudent, enrollCourse);
                            System.out.println("Student " + enrollStudent.getName() + " enrolled in course " + enrollCourse.getTitle() + " successfully.");
                        } else {
                            System.out.println("Student or course not found. Please make sure you have added both before enrolling.");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose 'student', 'teacher', 'course', 'enroll', or 'end'.");
                        break;
                }
            }
    
            List<Student> students = school.getAllStudents();
            List<Teacher> teachers = school.getAllTeachers();
            List<Course> courses = school.getAllCourses();
    
            System.out.println("List of students:");
            for (Student studentz : students) {
                System.out.println(studentz.getName());
            }
    
            System.out.println("List of teachers:");
            for (Teacher teacherz : teachers) {
                System.out.println(teacherz.getName());
            }
    
            System.out.println("List of courses:");
            for (Course coursez : courses) {
                System.out.println(coursez.getTitle());
            }
    
            System.out.println("School setup is complete.");
        };
    }
    
}