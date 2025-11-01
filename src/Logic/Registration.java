/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import dataAccess.AdminData;
import dataAccess.UserData;
import dataAccess.StudentData;

/**
 *
 * @author airi
 */
public class Registration 
{

    private final AdminData adminData;
    private final UserData userData;
    private final StudentData studentData;

    public Registration() 
    
    {
        this.adminData = new AdminData();
        this.userData = new UserData();
        this.studentData = new StudentData();
    }

    /**
     * For GUI Usage -- input fields
     *
     * @param username
     * @param id
     * @param password
     * @param role
     * @param firstName
     * @param lastName
     * @param email
     * @param phoneNumber
     * @param dateOfBirth
     * @param major 
     * @return
     */
    //register a user 
    // Role can be "student" or "admin" 
    // returns true if registration is successful, false if not.
    // GUI can use boolean to show success or error message to user
    
    //leaves out major and password to be updated later -- create methods for this.
   
    public boolean createAccount(String id, String username, String password, String firstName, String lastName, String email, String dateOfBirth, String phoneNumber, String role) 
    {

        try 
        {
            boolean userInserted = userData.insertUser(id, username, password, firstName, lastName, email, dateOfBirth, phoneNumber, role);
            if (!userInserted) 
            {
                System.out.println("failed to insert user");
                return false;
            }

            if ("student".equalsIgnoreCase(role)) 
            {
                boolean studentInserted = studentData.insertStudent(id, "undeclared");
                if (!studentInserted) 
                {
                    System.out.println("failed to insert student");
                    return false;
                }
                return true;
            } 
            
            else if ("admin".equalsIgnoreCase(role)) 
            {
                boolean adminInserted = adminData.insertAdmin(id);
                if (!adminInserted) 
                {
                    System.out.println("failed to insert admin");
                    return false;
                }
                return true;
            }

            return false;

        } 
        
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }
}

    


