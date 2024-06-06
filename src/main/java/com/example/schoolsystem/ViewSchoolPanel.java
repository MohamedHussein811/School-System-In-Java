package com.example.schoolsystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.example.schoolsystem.models.Student;
import com.example.schoolsystem.models.Teacher;
import com.example.schoolsystem.interfaces.ISchool;
import com.example.schoolsystem.models.Course;

public class ViewSchoolPanel extends JPanel {

    private ISchool school;
    private JPanel displayPanel;

    public ViewSchoolPanel(JFrame frame, ISchool school) {
        this.school = school;
        setLayout(new BorderLayout());

        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(displayPanel), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> switchPanel(frame, "Main"));
        add(backButton, BorderLayout.SOUTH);
    }

    public void refreshData() {
        displayPanel.removeAll();

        // Display Students
        List<Student> students = school.getAllStudents();
        displayPanel.add(new JLabel("Students:"));
        for (Student student : students) {
            displayPanel.add(createEntryPanel(student.getId(), student.getName(), "Student", student.getCourses()));
        }

        // Display Teachers
        List<Teacher> teachers = school.getAllTeachers();
        displayPanel.add(new JLabel("Teachers:"));
        for (Teacher teacher : teachers) {
            displayPanel.add(createEntryPanel(teacher.getId(), teacher.getName(), "Teacher", teacher.getCourses()));
        }

        // Display Courses
        List<Course> courses = school.getAllCourses();
        displayPanel.add(new JLabel("Courses:"));
        for (Course course : courses) {
            displayPanel.add(createEntryPanel(course.getId(), course.getTitle(), "Course", null));
        }

        displayPanel.revalidate();
        displayPanel.repaint();
    }

    private JPanel createEntryPanel(long id, String name, String type, List<Course> courses) {
        JPanel entryPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding between components
    
        JLabel idLabel = new JLabel("ID: " + id);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        entryPanel.add(idLabel, gbc);
    
        JLabel nameLabel = new JLabel(name);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        entryPanel.add(nameLabel, gbc);
    
        JButton editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(80, 30));
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        entryPanel.add(editButton, gbc);
    
        JButton removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(80, 30));
        removeButton.setBackground(Color.RED); // Set background color to red
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        entryPanel.add(removeButton, gbc);
    
        JTextField nameField = new JTextField(name);
        nameField.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        entryPanel.add(nameField, gbc);
        nameField.setVisible(false);
    
        JButton updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(80, 30));
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        entryPanel.add(updateButton, gbc);
        updateButton.setVisible(false);
    
        JLabel coursesLabel = new JLabel();
        if (courses != null) {
            StringBuilder courseNames = new StringBuilder("Enrolled in: ");
            for (Course course : courses) {
                courseNames.append(course.getTitle() + course.getId()).append(", ");
            }
            if (courseNames.length() > 12) {
                courseNames.setLength(courseNames.length() - 2); // remove the trailing comma and space
            }
            coursesLabel.setText(courseNames.toString());
        }
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        entryPanel.add(coursesLabel, gbc);
    
        editButton.addActionListener(e -> {
            nameField.setVisible(true);
            updateButton.setVisible(true);
            nameLabel.setVisible(false);
            editButton.setVisible(false);
        });
    
        removeButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this " + type + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                switch (type) {
                    case "Student":
                        school.removeStudent(id);
                        break;
                    case "Teacher":
                        school.removeTeacher(id);
                        break;
                    case "Course":
                        school.removeCourse(id);
                        break;
                }
                JOptionPane.showMessageDialog(this, type + " removed successfully.");
                refreshData();
            }
        });
    
        updateButton.addActionListener(e -> {
            String newName = nameField.getText();
            if (!newName.isEmpty()) {
                switch (type) {
                    case "Student":
                        school.updateStudentName(id, newName);
                        break;
                    case "Teacher":
                        school.updateTeacherName(id, newName);
                        break;
                    case "Course":
                        school.updateCourseTitle(id, newName);
                        break;
                }
                JOptionPane.showMessageDialog(this, type + " name updated successfully.");
                refreshData();
            } else {
                JOptionPane.showMessageDialog(this, "Name cannot be empty.");
            }
        });
    
        // Set preferred size for entryPanel
        entryPanel.setPreferredSize(new Dimension(500, 100));
    
        return entryPanel;
    }
    
    

    private void switchPanel(JFrame frame, String panelName) {
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), panelName);
    }
}
