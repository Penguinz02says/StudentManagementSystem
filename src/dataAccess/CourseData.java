/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import Database.DatabaseManager; 

/**
 *
 * @author kiandrasin
 */
public class CourseData {
    private final Connection conn;
    
    public CourseData(Connection conn) {
        this.conn = conn;
    }
    
    //geting student course by ID
    public List<Course> getCoursesByStudentId(String studentId) {
    List<Course> courses = new ArrayList<>();
    String sql = "SELECT c.course_id, c.course_name, c.credits " +
                 "FROM courses c " +
                 "JOIN enrollment e ON c.course_id = e.course_id " +
                 "WHERE e.student_id = ?";

    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, studentId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Course course = new Course(
                rs.getString("course_id"),
                rs.getString("course_name"),
                rs.getInt("credits")
            );
            courses.add(course);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return courses;
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
