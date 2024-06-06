package com.example.schoolsystem;

import javax.swing.*;

import org.springframework.context.ApplicationContext;

import com.example.schoolsystem.interfaces.ISchool;

import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel(JFrame frame, ISchool school, ApplicationContext ctx) {
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("School Management System");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headerLabel, BorderLayout.NORTH);

        JButton addEntityButton = new JButton("Add Entity");
        addEntityButton.setForeground(Color.WHITE);
        addEntityButton.setBackground(Color.BLACK);
        addEntityButton.addActionListener(e -> switchPanel(frame, "AddEntity"));

        JButton enrollButton = new JButton("Enroll Student");
        enrollButton.setForeground(Color.WHITE);
        enrollButton.setBackground(Color.BLACK);
        enrollButton.addActionListener(e -> switchPanel(frame, "Enroll"));

        JButton enrollTeacherButton = new JButton("Assign Course");
        enrollTeacherButton.setForeground(Color.WHITE);
        enrollTeacherButton.setBackground(Color.BLACK);
        enrollTeacherButton.addActionListener(e -> switchPanel(frame, "EnrollTeacher"));

        JButton viewSchoolButton = new JButton("View School");
        viewSchoolButton.setForeground(Color.WHITE);
        viewSchoolButton.setBackground(Color.BLACK);
        viewSchoolButton.addActionListener(e -> switchPanel(frame, "ViewSchool"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.add(addEntityButton);
        buttonPanel.add(enrollButton);
        buttonPanel.add(enrollTeacherButton);
        buttonPanel.add(viewSchoolButton);

        add(buttonPanel, BorderLayout.CENTER);

        JTextArea infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setLineWrap(true);
        infoTextArea.setWrapStyleWord(true);
        infoTextArea.setText("This is the main panel of our school system. Here you can perform various actions such as adding entities, enrolling students, assigning courses, and viewing school information.");
        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void switchPanel(JFrame frame, String panelName) {
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), panelName);

        if (panelName.equals("ViewSchool")) {
            Component[] components = frame.getContentPane().getComponents();
            for (Component component : components) {
                if (component instanceof ViewSchoolPanel) {
                    ((ViewSchoolPanel) component).refreshData();
                }
            }
        }
    }
}
