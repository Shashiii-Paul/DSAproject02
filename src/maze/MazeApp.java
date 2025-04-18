/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.BorderLayout;
import java.awt.Color;  // Import for color
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @shushmita_paul
 * id:23189444
 * 
 */

public class MazeApp {

    private static Panel panel;

    public static void main(String[] args) {
        //Create a JFrame for the maze GUI
        JFrame frame = new JFrame("Maze Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        
        //Create a panel for the maze
        panel = new Panel();
        frame.add(panel);
        
        //Create a button panel
        JPanel buttonPanel = new JPanel();
        
        //Create a button for the maze 1
        JButton maze1Button = new JButton("Maze 1");
        maze1Button.setBackground(new Color(255, 182, 193));  // Set the button color to pink (Light Pink)
        maze1Button.setOpaque(true);  // Ensure the background color is applied
        maze1Button.setBorderPainted(false);  // Remove border if desired
        maze1Button.addActionListener((ActionEvent e) -> {
            //Load the maze 1 when the button is clicked
            panel.loadMaze("Maze1.txt");
        });
        buttonPanel.add(maze1Button);
        
        //Create a button for maze 2
        JButton maze2Button = new JButton("Maze 2");
        maze2Button.setBackground(new Color(255, 182, 193));  // Set the button color to pink (Light Pink)
        maze2Button.setOpaque(true);  // Ensure the background color is applied
        maze2Button.setBorderPainted(false);  // Remove border if desired
        maze2Button.addActionListener((ActionEvent e) -> {
            //Load Maze 2 when the button is clicked
            panel.loadMaze("Maze2.txt");
        });
        buttonPanel.add(maze2Button);
        
        //Add the button panel to the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);
        //Make the frame visible
        frame.setVisible(true);
    }
}
