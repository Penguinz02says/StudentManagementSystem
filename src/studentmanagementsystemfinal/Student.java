/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystemfinal;

/**
 *
 * @author airi
 */
public abstract class Student extends User
{
    private String major; 
   

    public Student(String ID, String role, String firstName, String lastName, String password, String major, String email, String phoneNumber, String dateOfBirth){
        
        super(ID, password, role, firstName, lastName, email, phoneNumber, dateOfBirth);
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
