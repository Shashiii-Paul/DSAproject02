/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp;

/**
 *
 * @shushmita_paul
 * id:23189444
 * 
 */

public class Student {
    
    public String name;
    public Float score;
    public String comments;
    
    @Override
    public String toString() {
        // Format the string to display each detail on a separate line
        return name + "\nScore: " + score + "\nComments: " + comments;
    }
}
