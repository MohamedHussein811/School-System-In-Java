package com.example.schoolsystem;

import javax.swing.*;

import org.springframework.context.ApplicationContext;

import com.example.schoolsystem.interfaces.ICourse;
import com.example.schoolsystem.interfaces.ISchool;
import com.example.schoolsystem.interfaces.IStudent;
import com.example.schoolsystem.models.Enrollment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnrollPanel extends JPanel {

    public EnrollPanel(JFrame frame, ISchool school, ApplicationContext ctx) {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2));
        JLabel studentIdLabel = new JLabel("Student ID:");
        JTextField studentIdField = new JTextField();
        inputPanel.add(studentIdLabel);
        inputPanel.add(studentIdField);

        JLabel courseIdLabel = new JLabel("Course ID:");
        JTextField courseIdField = new JTextField();
        inputPanel.add(courseIdLabel);
        inputPanel.add(courseIdField);

        JButton enrollButton = new JButton("Enroll");
        enrollButton.setBackground(Color.BLACK);
        enrollButton.setForeground(Color.WHITE);
        inputPanel.add(enrollButton);

        add(inputPanel, BorderLayout.NORTH);

        JTextArea outputArea = new JTextArea();
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long studentId = Long.parseLong(studentIdField.getText());
                    long courseId = Long.parseLong(courseIdField.getText());

                    IStudent student = school.getStudentById(studentId);
                    ICourse course = school.getCourseById(courseId);

                    if (student != null && course != null) {
                        Enrollment enrollment = ctx.getBean(Enrollment.class);
                        enrollment.enroll(student, course);
                        outputArea.append("Student " + student.getName() + " enrolled in course "
                                + course.getTitle() + " successfully.\n");
                    } else {
                        outputArea.append("Student or course not found. Please make sure you have added both before enrolling.\n");
                    }

                    studentIdField.setText("");
                    courseIdField.setText("");
                    
                } catch (NumberFormatException ex) {
                    outputArea.append("Invalid ID. Please enter a valid number.\n");
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> switchPanel(frame, "Main"));
        add(backButton, BorderLayout.SOUTH);
    }

    private void switchPanel(JFrame frame, String panelName) {
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), panelName);
    }
}
