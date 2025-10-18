/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
        String URL = "jdbc:derby://localhost:1527/StudentManagementDB;create=true";
        String user = "app";
        String password = "password";

        conn = DriverManager.getConnection(URL, user, password);
        System.out.println("Connected to StudentManagementDB");

        createTables();
    }

    // Create all tables 
    private void createTables() throws SQLException 
    {
        createUsersTable();
        createStudentsTable();
        createAdminsTable();
        createCoursesTable();
        createStudentCoursesTable();
    }
    
    //users table
    private void createUsersTable() 
    {
        String sql = """
            CREATE TABLE users (
                id VARCHAR(8) PRIMARY KEY,
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
                id VARCHAR(8) PRIMARY KEY,
                major VARCHAR(100),
                CONSTRAINT fk_user FOREIGN KEY (id) REFERENCES users(id)
            )
        """;

        executeTableCreation(sql, "students");
    }
    
    //admins table
    private void createAdminsTable() 
    {
        String sql = """
            CREATE TABLE admins (
                adminID VARCHAR(8) PRIMARY KEY,
                userID VARCHAR(8) UNIQUE,
                CONSTRAINT fk_user_admin FOREIGN KEY (userID) REFERENCES users(id)
            )
        """;

        executeTableCreation(sql, "admins");
    }

    //courses table
    private void createCoursesTable() 
    {
        String sql = """
            CREATE TABLE courses (
                courseCode VARCHAR(20) PRIMARY KEY,
                courseName VARCHAR(100),
                credits DOUBLE,
                level INT
            )
        """;

        executeTableCreation(sql, "courses");
    }

    //studentcourses table
    private void createStudentCoursesTable() 
    {
        String sql = """
            CREATE TABLE studentCourses (
                studentID VARCHAR(8) REFERENCES students(id),
                courseCode VARCHAR(20) REFERENCES courses(courseCode),
                PRIMARY KEY (studentID, courseCode)
            )
        """;

        executeTableCreation(sql, "studentCourses");
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