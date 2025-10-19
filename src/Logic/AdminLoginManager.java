/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author airi
 */
public class AdminLoginManager {

    private final Connection conn;

    public AdminLoginManager() {
        this.conn = DatabaseManager.getConnection();
    }

    public boolean validateAdminLogin(String username, String password) {
        String sql = """
            SELECT * FROM users
            WHERE username = ? AND password = ? AND role = 'admin'       
                   """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
