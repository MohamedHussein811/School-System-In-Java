package com.example.schoolsystem;

import javax.swing.*;
import java.awt.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.schoolsystem.interfaces.ISchool;
import com.example.schoolsystem.models.Student;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false"); // Set headless property to false
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            ISchool school = ctx.getBean(ISchool.class);
            Student student = ctx.getBean(Student.class);

            JFrame frame = new JFrame("School Management System");
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new CardLayout());

            MainPanel mainPanel = new MainPanel(frame, school, ctx);
            AddEntityPanel addEntityPanel = new AddEntityPanel(frame, school,student,ctx);
            EnrollPanel enrollPanel = new EnrollPanel(frame, school, ctx);
            EnrollTeacher enrollTeacher = new EnrollTeacher(frame, school, ctx);
            ViewSchoolPanel viewSchool = new ViewSchoolPanel(frame, school);
            

            frame.add(mainPanel, "Main");
            frame.add(addEntityPanel, "AddEntity");
            frame.add(enrollPanel, "Enroll");
            frame.add(enrollTeacher, "EnrollTeacher");
            frame.add(viewSchool, "ViewSchool");

            frame.setVisible(true);
        };
    }
}
