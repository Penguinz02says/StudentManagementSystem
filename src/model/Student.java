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
   

    public Student(String ID, String username, String password, String firstName, String lastName, String email, String phoneNumber, String dateOfBirth, String role, String major) 
    {
    super(ID, username, password, firstName, lastName, email, phoneNumber, dateOfBirth, role);
    this.major = major;
    }
    
    //Getters + setters---------------------------------------------------------------

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    

    @Override
    public String toString() {
        return "\nName: " + getFirstName() + " " + getLastName() + "\nStudent ID: " + ID +  "\nMajor: " + major + "\nDate Of Birth: " + dateOfBirth + "\nContact Email:  " + getEmail() + "\nMobile number: " + getPhoneNumber();
    }
}
