/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

/**
 *
 * @author kiandrasin
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Enrollment;

public class EnrollmentData 
{
    private final Connection conn;

    public EnrollmentData(Connection conn) 
    {
        this.conn = conn;
    }

    public void addEnrollment(Enrollment enrollment) throws SQLException {
        String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, enrollment.getStudent().getID());
            stmt.setString(2, enrollment.getCourse().getCourseID());
            stmt.executeUpdate();
        }
    }
    
    public void removeEnrollment(String studentId, String courseId) throws SQLException {
    String sql = "DELETE FROM enrollments WHERE student_id = ? AND course_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, studentId);
        stmt.setString(2, courseId);
        stmt.executeUpdate();
    }

}
    
}
