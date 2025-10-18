/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import java.sql.Connection;
import Database.DatabaseManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author airi
 */
public class UserData 
{

    private final Connection conn;

    public UserData() 
    {
        this.conn = DatabaseManager.getConnection();
    }

    public boolean insertUser(String id, String password, String firstName, String lastName, String email, String dob, String phone, String role)
    {
        String sql = """
            INSERT INTO usres (id, password, firstName lastName, email, dateOfBirth, phoneNumber, role)         
                     """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, email);
            pstmt.setString(6, dob);
            pstmt.setString(7, phone);
            pstmt.setString(8, role);
            pstmt.executeUpdate();
            return true;
        } 
        
        catch (SQLException e) 
        {
            System.out.println("e.getMessage()");
            return false;
        }
    }    

}
