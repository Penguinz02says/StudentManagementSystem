/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Course;

/**
 *
 * @author kiandrasin
 */
public class CourseData {
    private final Connection conn;
    
    public CourseData(Connection conn) {
        this.conn = conn;
    }
    
    public void insertCourse(Course course) throws SQLException {
        String query = "INSERT INTO courses (courseID, courseName, credits) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, course.getCourseID());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getCredits());
            stmt.executeUpdate();
        }
    }
    
    public List<Course> getAllCourse() throws SQLException {
        List<Course> list = new ArrayList<>();
        String query = "SELECT * FROM courses";
        try (PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Course(
                rs.getString("courseID"),
                rs.getString("courseName"),
                rs.getInt("credits")
                ));
            }
        }
        return list;
    }
}
