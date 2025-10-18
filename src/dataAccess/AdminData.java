/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import Database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author airi
 */
public class AdminData 
{
    private final Connection conn; 
    
    public AdminData()
    {
        this.conn = DatabaseManager.getConnection();
    }

    public boolean insertAdmin (String userID)
    {
        String sql = "INSERT INTO admins (userID) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
