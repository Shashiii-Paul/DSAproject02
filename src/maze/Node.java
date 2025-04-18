/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package maze;

/**
 *
 * @shushmita_paul
 * id:23189444
 * 
 */

public class Node {
    // The unique identifier for the node (a single character or string)
    String characters;
    
    // The x and y coordinates of the node in the maze grid
    public int x;
    public int y;
    
    public Node(String characters, int x, int y) {
        this.characters = characters;  // Assign the node's character identifier
        this.x = x;  // Set the node's x-coordinate
        this.y = y;  // Set the node's y-coordinate
    }   
}
