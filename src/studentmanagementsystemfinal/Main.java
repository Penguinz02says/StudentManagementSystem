/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentmanagementsystemfinal;

import Database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author airi
 */
public class Main {
    public static void main(String[] args) {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            Connection conn = DatabaseManager.getConnection();

            String insertUser = "INSERT INTO users (id, password, firstName, lastName, email, dateOfBirth, phoneNumber, role) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = conn.prepareStatement(insertUser)) {
                pstmt.setString(1, "12345678");
                pstmt.setString(2, "Password123");
                pstmt.setString(3, "Airi");
                pstmt.setString(4, "Ishida");
                pstmt.setString(5, "airi@example.com");
                pstmt.setString(6, "2000-01-01");
                pstmt.setString(7, "0211234567");
                pstmt.setString(8, "student");
                
                pstmt.executeUpdate();
                System.out.println("Test user inserted!");
            } catch (SQLException e) {
                if ("23505".equals(e.getSQLState()))
                    System.out.println("Test user already exists");
                else
                    throw e;
            }

            String selectUsers = "SELECT * FROM users";
            try (PreparedStatement pstmt = conn.prepareStatement(selectUsers);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    System.out.printf("ID: %s | Name: %s %s | Email: %s | Role: %s%n",
                            rs.getString("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("email"),
                            rs.getString("role"));
                }
            }

            DatabaseManager.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}