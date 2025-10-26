/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Database.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kiandrasin
 */
public class EnrollmentManager {
    private final Connection conn;
    public EnrollmentManager(Connection conn) {
        this.conn = conn;
    }
    
    public void enrollStudent(String studentID, String courseID) throws SQLException {
        String query = "INSERT INTO enrollments (studentID, courseID) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentID);
            stmt.setString(2, courseID);
            stmt.executeUpdate();
        }
    }
    
    public void removeEnrollment(String studentID, String courseID) throws SQLException {
        String query = "DELETE FROM enrollments WHERE studentID = ? AND courseID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentID);
            stmt.setString(2, courseID);
            stmt.executeUpdate();
        }
    }
    
    public List<String> getCoursesForStudent(String studentID) throws SQLException {
        List<String> courses = new ArrayList<>();
        String query = "SELECT courseID FROM enrollments WHERE studentID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                courses.add(rs.getString("courseID"));
            }
        }
        return courses;
    }
}
