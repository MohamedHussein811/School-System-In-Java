package com.example.schoolsystem;

import javax.swing.*;

import org.springframework.context.ApplicationContext;

import com.example.schoolsystem.interfaces.ISchool;
import com.example.schoolsystem.models.Course;
import com.example.schoolsystem.models.Teacher;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnrollTeacher extends JPanel {

    public EnrollTeacher(JFrame frame, ISchool school, ApplicationContext ctx) {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2));
        JLabel teacherIdLabel = new JLabel("Teacher ID:");
        JTextField teacherIdField = new JTextField();
        inputPanel.add(teacherIdLabel);
        inputPanel.add(teacherIdField);

        JLabel courseIdLabel = new JLabel("Course ID:");
        JTextField courseIdField = new JTextField();
        inputPanel.add(courseIdLabel);
        inputPanel.add(courseIdField);

        JButton enrollButton = new JButton("Assign Course");
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
                    long teacherId = Long.parseLong(teacherIdField.getText());
                    long courseId = Long.parseLong(courseIdField.getText());

                    Teacher teacher = school.getTeacherById(teacherId);
                    Course course = school.getCourseById(courseId);

                    if (teacher != null && course != null) {
                        teacher.assignCourse(course);
                        outputArea.append("Teacher " + teacher.getName() + " enrolled in course "
                                + course.getTitle() + " successfully.\n");
                    } else {
                        outputArea.append("Student or course not found. Please make sure you have added both before enrolling.\n");
                    }
                    teacherIdField.setText("");
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
