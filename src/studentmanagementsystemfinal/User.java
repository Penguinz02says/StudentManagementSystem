/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystemfinal;

/**
 *
 * @author airi
 */
public abstract class User 
{
    protected String ID; 
    protected String password; 
    protected String firstName; 
    protected String lastName; 
    protected String email; 
    protected String phoneNumber; 
    protected String dateOfBirth;
    

    public User(String ID, String password, String role, String firstName, String lastName, String email, String phoneNumber, String dateOfBirth) {
       
        this.ID = ID; 
        this.password = password; 
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        
    }
    
  
    //Getters + setters

    
    public String getID() {
        return ID;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
   
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}


