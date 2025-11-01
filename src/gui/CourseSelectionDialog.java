/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;


import Database.DatabaseManager;
import java.sql.Connection;
import java.sql.SQLException;

import model.Course;
import dataAccess.EnrollmentData;
import model.Student;
import model.Enrollment;
import dataAccess.CourseData;
import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 *
 * @author airi
 */
public class CourseSelectionDialog extends JDialog {

    private JComboBox<Course> courseCombo;
    private JButton addButton;
    private JButton cancelButton;
    private Student student;

    public CourseSelectionDialog(JFrame parent, Student student) {
        super(parent, "Add Course", true);
        this.student = student;

        setLayout(new BorderLayout());
        setSize(600, 150);
        setLocationRelativeTo(parent);

        try {
            // Get database connection
            Connection conn = DatabaseManager.getConnection();

            // Fetch all available courses
            CourseData courseData = new CourseData(conn);
            List<Course> courses = courseData.getAllCourses();

            // Populate combo box with courses
            courseCombo = new JComboBox<>(courses.toArray(new Course[0]));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading courses from database:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buttons
        addButton = new JButton("Add Course");
        cancelButton = new JButton("Cancel");

        // Layout setup
        JPanel comboPanel = new JPanel();
        comboPanel.add(new JLabel("Select a course:"));
        comboPanel.add(courseCombo);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        add(comboPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addCourseToDatabase());
        cancelButton.addActionListener(e -> dispose());
    }

    private void addCourseToDatabase() {
        Course selectedCourse = (Course) courseCombo.getSelectedItem();
        if (selectedCourse == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a course!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection conn = DatabaseManager.getConnection();
            EnrollmentData enrollmentData = new EnrollmentData(conn);

            // Create enrollment
            Enrollment enrollment = new Enrollment(student, selectedCourse);
            enrollmentData.addEnrollment(enrollment);

            JOptionPane.showMessageDialog(this,
                    selectedCourse.getCourseID() + " added to " + student.getFirstName() + " " + student.getLastName(),
                    "Course Added",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Failed to add course:\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}