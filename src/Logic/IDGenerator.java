/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

/**
 *
 * @author airi
 */

import java.util.Random;

public class IDGenerator 
{
    public static String generateID()
    {
        Random random = new Random();
        String id;  

        //4 numbers
        int uniqueNumber = (int) (Math.random() * 9000) + 1000;
        
        id = "2025" + uniqueNumber; 
            
        return id;
    }
    
    
    public static String generateUsername()
    {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        
        //random letter prefix for username
        for(int i=0; i <3; i++)
        {
            int index = (int) (Math.random() * letters.length());
            sb.append(letters.charAt(index));
        }
        
        //4 random numbers
        int numbers = (int) (Math.random() * 900) + 1000;
        
        //combining letters + digits for Student username
        String username = sb.toString() + numbers; 
        
       return username;
    
    }
        
}
