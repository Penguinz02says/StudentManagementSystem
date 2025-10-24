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
import javax.swing.table.DefaultTableModel;

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
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }

    }
    
    //function coded with help of chatgpt

    public DefaultTableModel getAllStudentsTable() 
    {
        String sql = """
            SELECT s.user_id, u.username, u.firstName, u.lastName, s.major, u.email, u.phoneNumber, u.dateOfBirth
            FROM students s
            INNER JOIN users u ON s.user_id = u.id
            WHERE u.role = 'student'         
            ORDER BY u.lastName ASC
        """;

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"User ID", "Username", "First Name", "Last Name", "Major", "Email", "Phone", "DOB"}, 
            0
        );

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) 
            {
                model.addRow(new Object[]{
                    rs.getString("user_id"),
                    rs.getString("username"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("major"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("dateOfBirth")
                });
            }

        } catch (SQLException e) 
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return model;
    }
    
    public boolean deleteStudent(String userID)
    {
        String deleteCourses = "DELETE FROM studentCourses WHERE student_id = ?";
        String deleteStudent = "DELETE FROM students WHERE user_id = ?";
        String deleteUser = "DELETE FROM users WHERE id = ?";
    
    try(PreparedStatement pstmt1 = conn.prepareStatement(deleteCourses);
        PreparedStatement pstmt2 = conn.prepareStatement(deleteStudent);
        PreparedStatement pstmt3 = conn.prepareStatement(deleteUser)) 
        {
        pstmt1.setString(1, userID);
        pstmt1.executeUpdate();
        
        
        pstmt2.setString(1, userID);
        int studentsDeleted = pstmt2.executeUpdate();
        
       
        pstmt3.setString(1, userID);
        int usersDeleted = pstmt3.executeUpdate();
        
        
        return studentsDeleted > 0;
        } 
    
    catch (SQLException e) 
    {
        e.printStackTrace();
        return false;
    }
   
}
}
