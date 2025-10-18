/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystemfinal;

/**
 *
 * @author airi
 */
public abstract class Admin extends User {

    public Admin(String ID, String password, String firstName, String lastName, String email, String phoneNumber, String dateOfBirth) {
        super(ID, password, "Admin", firstName, lastName, email, phoneNumber, dateOfBirth);
    }

}
