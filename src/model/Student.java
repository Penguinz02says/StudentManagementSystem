/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author airi
 */
public class Student extends User
{
    private String major; 
    
    //no argument constructor for StudentData.getStudentById()
    
    public Student() 
    {
    super("", "", "", "", "", "", "", "", ""); 
    this.major = "";
    }
   

    public Student(String ID, String username, String role, String firstName, String lastName, String password, String major, String email, String phoneNumber, String dateOfBirth){
        
        super(ID, username, password, role, firstName, lastName, email, phoneNumber, dateOfBirth);
        this.major = major;
         
    }
    
    //Getters + setters---------------------------------------------------------------

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    
    

    @Override
    public String toString() {
        return "\nName: " + getFirstName() + " " + getLastName() + "\nStudent ID: " + ID +  "\nMajor: " + major + "\nDate Of Birth: " + dateOfBirth + "\nContact Email:  " + getEmail() + "\nMobile number: " + getPhoneNumber();
    }
}
