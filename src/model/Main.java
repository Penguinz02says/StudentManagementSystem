/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;
import gui.AdminLoginGUI;

import Database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author airi
 */
public class Main {
    public static void main(String[] args) 
    {
        
        
       try {
        new DatabaseManager(); // creates tables + default admin automatically

        java.awt.EventQueue.invokeLater(() -> {
            new AdminLoginGUI().setVisible(true);
        });

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}