/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import java.sql.Connection;
import Database.DatabaseManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author airi
 */
public class StudentData 
{

    private final Connection conn;

    public StudentData() 
    {
        this.conn = DatabaseManager.getConnection();
    }

    public boolean insertStudent(String id, String major) 
    {
        String sql = "INSERT INTO students (user_id, major) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, major);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("e.getMessage()");
            return false;
        }

    }
    
    public ResultSet getAllStudents()
    {
        String sql = """
        SELECT s.user_id, u.username, u.firstName, u.lastName, s.major, u.email, u.phoneNumber, u.dateOfBirth
        FROM students s
        INNER JOIN users u ON s.user_id = u.id
        ORDER BY u.lastName ASC
    """;

    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        return pstmt.executeQuery();  
    } 
    
    catch (SQLException e) 
    {
        System.out.println(e.getMessage());
        return null;
    }
    
    }
           
}
