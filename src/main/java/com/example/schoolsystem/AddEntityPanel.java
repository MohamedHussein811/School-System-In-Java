package com.example.schoolsystem;

import javax.swing.*;

import com.example.schoolsystem.interfaces.ISchool;
import com.example.schoolsystem.models.Course;
import com.example.schoolsystem.models.Student;
import com.example.schoolsystem.models.Teacher;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEntityPanel extends JPanel {

    public AddEntityPanel(JFrame frame, ISchool school) {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2));
        JLabel typeLabel = new JLabel("Type:");
        JComboBox<String> typeComboBox = new JComboBox<>(new String[] { "Student", "Teacher", "Course" });
        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        inputPanel.add(idLabel);
        inputPanel.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        JTextArea outputArea = new JTextArea();
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = (String) typeComboBox.getSelectedItem();
                String name = nameField.getText();
                try {
                    long id = Long.parseLong(idField.getText());
                    boolean idExists = false;
                    switch (type) {
                        case "Student":
                            idExists = school.getAllStudents().stream().anyMatch(student -> student.getId() == id);
                            if (!idExists) {
                                school.addStudent(new Student(id, name));
                                outputArea.append("Student ID: " + id + ", Name: " + name + " added successfully.\n");
                            }
                            break;
                        case "Teacher":
                            idExists = school.getAllTeachers().stream().anyMatch(teacher -> teacher.getId() == id);
                            if (!idExists) {
                                school.addTeacher(new Teacher(id, name));
                                outputArea.append("Teacher ID: " + id + ", Name: " + name + " added successfully.\n");
                            }
                            break;
                        case "Course":
                            idExists = school.getAllCourses().stream().anyMatch(course -> course.getId() == id);
                            if (!idExists) {
                                school.addCourse(new Course(id, name));
                                outputArea.append("Course ID: " + id + ", Name: " + name + " added successfully.\n");
                            }
                            break;
                    }
                    if (idExists) {
                        outputArea.append("ID already exists. Please enter a unique ID.\n");
                    }

                    nameField.setText("");
                    idField.setText("");


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
