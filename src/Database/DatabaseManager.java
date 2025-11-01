/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import dataAccess.CourseData;

/**
 *
 * @author airi
 */

//handles connection and table creation for User, Student, Admin
public class DatabaseManager 
{

    private static Connection conn;

    // connect to derby
    public DatabaseManager() throws SQLException 
    {
        String URL = "jdbc:derby:StudentManagementDB;create=true";
        String user = "app";
        String password = "password";

        conn = DriverManager.getConnection(URL, user, password);
        System.out.println("Connected to StudentManagementDB");

        createTables();
        insertDefaultAdmin();
        
        CourseData courseData = new CourseData(conn);
        courseData.insertCourses();
    }
    
    


    // Create all tables 
    private void createTables() throws SQLException 
    {
        createUsersTable();
        createStudentsTable();
        createAdminsTable();
        createCoursesTable();
        createEnrollmentsTable();
    }
    
    //users table
    private void createUsersTable() 
    {
        String sql = """
            CREATE TABLE users (
                id VARCHAR(8) PRIMARY KEY,
                username VARCHAR(50) UNIQUE,    
                password VARCHAR(255),
                firstName VARCHAR(50),
                lastName VARCHAR(50),
                email VARCHAR(100),
                dateOfBirth VARCHAR(20),
                phoneNumber VARCHAR(20),
                role VARCHAR(20)
            )
        """;

        executeTableCreation(sql, "users");
    }
    
    //students table
    private void createStudentsTable() 
    {
        String sql = """
            CREATE TABLE students (
                user_id VARCHAR(8) PRIMARY KEY,
                studentUsername VARCHAR(50) UNIQUE,   
                major VARCHAR(100),
                CONSTRAINT fk_user_student FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
            )
        """;

        executeTableCreation(sql, "students");
    }
    
    //admins table
    private void createAdminsTable() 
    {
        String sql = """
            CREATE TABLE admins (
                user_id VARCHAR(8) PRIMARY KEY,
                CONSTRAINT fk_user_admin FOREIGN KEY (user_id) REFERENCES users(id)
            )
        """;

        executeTableCreation(sql, "admins");
    }
    
     
        
    //Courses Table
     private void createCoursesTable() {
        String sql = """
            CREATE TABLE COURSES_TABLE (
                COURSE_ID VARCHAR(10) PRIMARY KEY,
                COURSE_NAME VARCHAR(100) NOT NULL,
                CREDITS INTEGER NOT NULL
            )
        """;
        executeTableCreation(sql, "COURSES_TABLE");
    }
    
    //Enrollments
   private void createEnrollmentsTable() {
        String sql = """
            CREATE TABLE ENROLLMENTS (
                STUDENT_ID VARCHAR(8) NOT NULL,
                COURSE_ID VARCHAR(10) NOT NULL,
                PRIMARY KEY (STUDENT_ID, COURSE_ID),
                FOREIGN KEY (STUDENT_ID) REFERENCES STUDENTS(USER_ID),
                FOREIGN KEY (COURSE_ID) REFERENCES COURSES_TABLE(COURSE_ID)
            )
        """;
        executeTableCreation(sql, "ENROLLMENTS");
    }
    
    // Helper method for table creation exception handling
    private void executeTableCreation(String sql, String tableName) {
        try (Statement stmt = conn.createStatement()) 
        {
            stmt.executeUpdate(sql);
            System.out.println("Table '" + tableName + "' created");
        } catch (SQLException e) 
        {
            if ("X0Y32".equals(e.getSQLState())) 
            {
                System.out.println("Table '" + tableName + "' already exists");
            } else 
            
            {
                System.out.println("Error creating table '" + tableName + "': " + e.getMessage());
            }
            
            
        }
        
        
    }
    
    //insert default Admin
    
    private void insertDefaultAdmin() 
    {
    String sql = """
        INSERT INTO users (id, username, password, firstName, lastName, email, dateOfBirth, phoneNumber, role)
        VALUES ('00000001', 'admin', 'adminpass', 'John', 'Smith', 'john.Smith@school.nz', '08/02/1988', '021234567', 'admin')
    """;
    try (Statement stmt = conn.createStatement()) 
    {
        stmt.executeUpdate(sql);
        System.out.println("Default admin account created.");
    } 
    catch (SQLException e) 
    {
        if ("23505".equals(e.getSQLState())) 
        {
            System.out.println("Default admin already exists.");
        } else {
            System.out.println("Error inserting default admin: " + e.getMessage());
        }
    }
    
    
}

    public static Connection getConnection() 
    {
        return conn;
    }

    //close connection
    public static void closeConnection() 
    {
        try 
        {
            if (conn != null) 
            {
                conn.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
}