package com.example.schoolsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.example.schoolsystem.interfaces.ISchool;
import com.example.schoolsystem.models.Course;
import com.example.schoolsystem.models.Enrollment;
import com.example.schoolsystem.models.Student;
import com.example.schoolsystem.models.Teacher;
import com.example.schoolsystem.models.ui.RoundedButtonUI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchoolApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false"); // Set headless property to false
        SpringApplication.run(SchoolApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            ISchool school = ctx.getBean(ISchool.class);

            JFrame frame = new JFrame("School System");
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

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

            JLabel CourseIDLabel = new JLabel("Course ID:");
            JTextField CourseIDField = new JTextField();
            inputPanel.add(CourseIDLabel);
            inputPanel.add(CourseIDField);
            CourseIDField.setVisible(false);
            CourseIDLabel.setVisible(false);

            JButton addButton = new JButton("Add");
            addButton.setBackground(Color.BLACK);
            addButton.setForeground(Color.WHITE);
            addButton.setUI(new RoundedButtonUI());

            inputPanel.add(addButton);

            JButton enrollButton = new JButton("Enroll");
            enrollButton.setBackground(Color.BLACK);
            enrollButton.setForeground(Color.WHITE);
            enrollButton.setUI(new RoundedButtonUI());

            inputPanel.add(enrollButton);

            frame.add(inputPanel, BorderLayout.NORTH);

            JTextArea outputArea = new JTextArea();
            frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

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
                                }
                                break;
                            case "Teacher":
                                idExists = school.getAllTeachers().stream().anyMatch(teacher -> teacher.getId() == id);
                                if (!idExists) {
                                    school.addTeacher(new Teacher(id, name));
                                }
                                break;
                            case "Course":
                                idExists = school.getAllCourses().stream().anyMatch(course -> course.getId() == id);
                                if (!idExists) {
                                    school.addCourse(new Course(id, name));
                                }
                                break;
                        }
                        if (!idExists) {
                            outputArea.append(name + " added successfully.\n");
                        } else {
                            outputArea.append("ID already exists. Please enter a unique ID.\n");
                        }
                    } catch (NumberFormatException ex) {
                        outputArea.append("Invalid ID. Please enter a valid number.\n");
                    }
                }
            });

            enrollButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    idLabel.setVisible(false);
                    idField.setVisible(false);
                    nameLabel.setVisible(false);
                    nameField.setVisible(false);
                    addButton.setVisible(false);
                    typeComboBox.setVisible(false);

                    typeLabel.setText("Student ID:");
                    idField.setText("");
                    idField.setVisible(true);

                    CourseIDLabel.setText("Course ID:");
                    CourseIDField.setText("");
                    CourseIDField.setVisible(true);
                    CourseIDLabel.setVisible(true);

                    enrollButton.setVisible(false);

                    JButton backButton = new JButton("Back");
                    backButton.setBackground(Color.BLACK);
                    backButton.setForeground(Color.WHITE);
                    inputPanel.add(backButton);
                    frame.revalidate();

                    backButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            typeLabel.setText("Type:");
                            idField.setText("");

                            CourseIDLabel.setText("Course ID:");
                            CourseIDField.setText("");
                            CourseIDField.setVisible(false);
                            CourseIDLabel.setVisible(false);

                            backButton.setVisible(false);
                            typeComboBox.setVisible(true);

                            idLabel.setVisible(true);
                            idField.setVisible(true);
                            nameLabel.setVisible(true);
                            nameField.setVisible(true);
                            addButton.setVisible(true);

                            enrollButton.setVisible(true);

                            removeConfirmButton(inputPanel);

                            frame.revalidate();
                            frame.repaint();
                        }
                    });

                    removeConfirmButton(inputPanel);

                    JButton confirmButton = new JButton("Confirm");
                    confirmButton.setBackground(Color.BLACK);
                    confirmButton.setForeground(Color.WHITE);
                    inputPanel.add(confirmButton);

                    frame.revalidate();

                    confirmButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                long studentId = Long.parseLong(idField.getText());
                                long courseId = Long.parseLong(CourseIDField.getText());

                                Student student = school.getStudentById(studentId);
                                Course course = school.getCourseById(courseId);

                                if (student != null && course != null) {
                                    Enrollment enrollment = ctx.getBean(Enrollment.class);
                                    enrollment.enroll(student, course);
                                    outputArea.append("Student " + student.getName() + " enrolled in course "
                                            + course.getTitle() + " successfully.\n");
                                } else {
                                    outputArea.append(
                                            "Student or course not found. Please make sure you have added both before enrolling.\n");
                                }
                            } catch (NumberFormatException ex) {
                                outputArea.append("Invalid ID. Please enter a valid number.\n");
                            }
                        }
                    });
                }
            });

            frame.setVisible(true);
        };
    }

    private void removeConfirmButton(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton && ((JButton) component).getText().equals("Confirm")) {
                panel.remove(component);
                break; 
            }
        }
    }
}
