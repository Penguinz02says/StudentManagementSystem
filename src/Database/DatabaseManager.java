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
        insertDefaultAdmin();
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
            CREATE TABLE courses (
                courseID VARCHAR(10) PRIMARY KEY,
                courseName VARCHAR(100) NOT NULL,
                credits INTEGER
            )
        """;
        
        executeTableCreation(sql, "courses");
        
    }
    
    //Enrollments
    private void createEnrollmentsTable() {
    String sql = """
        CREATE TABLE enrollments (
            studentID VARCHAR(8) NOT NULL,
            courseID VARCHAR(10) NOT NULL,
            CONSTRAINT fk_student FOREIGN KEY (studentID) REFERENCES students(user_id),
            CONSTRAINT fk_course FOREIGN KEY (courseID) REFERENCES courses(courseID)
        )
    """;
    
    try (Statement stmt = conn.createStatement()) {
        stmt.execute(sql);
        System.out.println("Enrollments table created successfully.");
    } catch (SQLException e) {
        System.out.println("Error creating enrollments table: " + e.getMessage());
    }


        executeTableCreation(sql, "enrollments");
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