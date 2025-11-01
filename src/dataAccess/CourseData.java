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
    
    public void insertCourses() {
    try {
        String checkSql = "SELECT COUNT(*) FROM COURSES_TABLE";
        try (PreparedStatement stmt = conn.prepareStatement(checkSql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next() && rs.getInt(1) == 0) {
                
                insertCourse(new Course("COMP603", "Program Design and Construction - Software Construction", 15));
                insertCourse(new Course("COMP604", "Algorithms and Data Structures", 15));
                insertCourse(new Course("COMP605", "Database Systems", 15));
                insertCourse(new Course("COMP606", "Human-Computer Interaction", 15));
                System.out.println("Courses inserted successfully.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public void insertCourse(Course course) throws SQLException {
        String sql = "INSERT INTO COURSES_TABLE (COURSE_ID, COURSE_NAME, CREDITS) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseID());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getCredits());
            stmt.executeUpdate();
        }
    }

    // Get all courses a student is enrolled in
     public List<Course> getCoursesByStudentId(String studentId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = """
            SELECT c.COURSE_ID, c.COURSE_NAME, c.CREDITS
            FROM COURSES_TABLE c
            JOIN ENROLLMENTS e ON c.COURSE_ID = e.COURSE_ID
            WHERE e.STUDENT_ID = ?
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(new Course(
                            rs.getString("COURSE_ID"),
                            rs.getString("COURSE_NAME"),
                            rs.getInt("CREDITS")
                    ));
                }
            }
        }
        return courses;
    }
     
        // Remove a student's enrollment
    public void removeEnrollment(String studentId, String courseId) throws SQLException {
        String sql = "DELETE FROM ENROLLMENTS WHERE STUDENT_ID = ? AND COURSE_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            stmt.setString(2, courseId);
            stmt.executeUpdate();
        }
    }


    // Get all courses
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT COURSE_ID, COURSE_NAME, CREDITS FROM COURSES_TABLE";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                courses.add(new Course(
                        rs.getString("COURSE_ID"),
                        rs.getString("COURSE_NAME"),
                        rs.getInt("CREDITS")
                ));
            }
        }
        return courses;
    }
    
     // Enroll a student in a course
    public void enrollStudentInCourse(String studentId, String courseId) throws SQLException {
        String sql = "INSERT INTO ENROLLMENTS (STUDENT_ID, COURSE_ID) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            stmt.setString(2, courseId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                System.out.println("Student already enrolled in this course.");
            } else {
                throw e;
            }
        }
    }
}