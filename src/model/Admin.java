/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author airi
 */
public abstract class Admin extends User {

    public Admin(String ID, String password, String username, String firstName, String lastName, String email, String phoneNumber, String dateOfBirth, String role) {
        super(ID, username, password, firstName, lastName, email, phoneNumber, dateOfBirth, role);
    }

}
