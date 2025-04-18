/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @shushmita_paul
 * id:23189444
 * 
 */

public class Panel extends JPanel {

    // The instance of the Maze class to hold the maze structure
    Maze maze = new Maze();
    // The result of the search stored as a path
    String valueSearch;
    // Timer to control the pathfinding animation
    Timer animationTimer;
    // Array to store the nodes along the path
    String[] graphNodes;
    // Current node being traversed
    String currentVertex;
    // Index to keep track of the current position in the path
    int location = 0;
    // Set to keep track of visited nodes
    HashSet<String> vNodes = new HashSet<>();

    // Constructor that initializes the maze and starts the animation
    public Panel() {
        // Read the maze file
        maze.readMazeFile("Maze2.txt");
        // Perform a search and get the result path from the start to exit
        valueSearch = maze.search(maze.nodeList[1], "EXIT");
        // Split the search result into individual nodes
        graphNodes = valueSearch.split(" ");

        // Action listener for controlling the animation steps
        ActionListener action = (ActionEvent e) -> {
            if (location < graphNodes.length) {
                // Set the current vertex and mark it as visited
                currentVertex = graphNodes[location];
                vNodes.add(currentVertex);
                location++;
                // Redraw the panel after each step
                repaint();
            } else {
                // Stop the animation once all nodes have been visited
                animationTimer.stop();
            }
        };
        
        // Set the timer interval to 1000 milliseconds (1 second) for each animation step
        animationTimer = new Timer(1000, action);
        // Start the animation
        animationTimer.start();
    }

    // Method to load a new maze and restart the animation
    public void loadMaze(String fileName) {
        // Create a new instance of the Maze class
        maze = new Maze();
        // Read the maze file
        maze.readMazeFile(fileName);
        // Perform the search and get the path
        valueSearch = maze.search(maze.nodeList[1], "EXIT");
        // Split the search result into individual nodes
        graphNodes = valueSearch.split(" ");
        // Clear the set of visited nodes
        vNodes.clear();
        // Reset the current location in the path
        location = 0;
        // Restart the animation timer
        animationTimer.restart();
        // Redraw the panel to show the new maze and path
        repaint();
    }

    // Custom paint method to draw the maze and the animation
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set the background color to a soft pink
        g.setColor(new Color(255, 182, 193));  // Pink background
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw the lines between linked nodes
        for (Node nodeList : maze.nodeList) {
            for (Node nodeList1 : maze.nodeList) {
                // Draw the lines only between linked nodes
                if (maze.LinkedNodes(nodeList.characters, nodeList1.characters)) {
                    g.setColor(new Color(255, 255, 0));  // Yellow for links between nodes
                    g.drawLine(nodeList.x * 100 + 65, nodeList.y * 100 + 65, nodeList1.x * 100 + 65, nodeList1.y * 100 + 65);
                }
            }
        }
        
        // Draw the individual nodes
        for (int i = 1; i < maze.nodeList.length; i++) {
            // Check if the node has been visited and color it accordingly
            if (vNodes.contains(maze.nodeList[i].characters)) {
                g.setColor(Color.BLACK);  // Black for visited nodes
            } else {
                g.setColor(Color.WHITE);  // White for unvisited nodes
            }
            
            // Draw the characters of the nodes
            if (maze.nodeList[i].characters != null) {
                g.drawString(maze.nodeList[i].characters, maze.nodeList[i].x * 100 + 15, maze.nodeList[i].y * 100 + 25);
            }
            
            // Draw the circles representing the nodes
            g.fillOval(maze.nodeList[i].x * 100 + 50, maze.nodeList[i].y * 100 + 50, 30, 30);
        }

        // Highlight the start and end node connections
        for (Node nodeList : maze.nodeList) {
            // Draw the line from the start node to its connected nodes
            if (maze.LinkedNodes(maze.nodeList[1].characters, nodeList.characters)) {
                g.drawLine(maze.nodeList[1].x * 100 + 65, maze.nodeList[1].y * 100 + 65, nodeList.x * 100 + 65, nodeList.y * 100 + 65);
            }
            // Draw the line from the end node to its connected nodes
            if (maze.LinkedNodes(maze.nodeList[maze.nodeList.length - 1].characters, nodeList.characters)) {
                g.drawLine(maze.nodeList[maze.nodeList.length - 1].x * 100 + 65, maze.nodeList[maze.nodeList.length - 1].y * 100 + 65, nodeList.x * 100 + 65, nodeList.y * 100 + 65);
            }

            // Draw circles around nodes that match the search value (path nodes)
            for (Node nodeList1 : maze.nodeList) {
                if (valueSearch.contains(" " + nodeList.characters + " ")) {
                    g.drawOval(nodeList.x * 100 + 50, nodeList.y * 100 + 50, 30, 30);
                    if (valueSearch.contains(" " + nodeList1.characters + " ")) {
                        if (maze.LinkedNodes(nodeList.characters, nodeList1.characters)) {
                            g.drawLine(nodeList.x * 100 + 65, nodeList.y * 100 + 65, nodeList1.x * 100 + 65, nodeList1.y * 100 + 65);
                        }
                    }
                }
            }
        }
    }
}
