/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @shushmita_paul
 * id:23189444
 * 
 */

public class Maze {

    public Stack<String> stack = new Stack<>();  // Stack used for depth-first search (DFS)
    public boolean[][] matrix;  // Adjacency matrix to represent the links between nodes
    public Node[] nodeList;  // Array to store all nodes in the maze
    public int location = 0;  // Keeps track of the current node location in the nodeList
    public Map<String, Integer> map = new HashMap<>();  // Map to store node names and their indices in nodeList
    public HashSet<String> visited = new HashSet<>();  // Set to track visited nodes during search
    public String[] prev;  // Array to track the previous node for backtracking during search
    public String path = "";  // String to store the final path from start to end

    // Constructor for initializing the maze structure
    public Maze() {
        this.matrix = new boolean[24][24];  // Initialize adjacency matrix to store up to 24 nodes
        nodeList = new Node[24];  // Array to hold up to 24 nodes
        prev = new String[matrix.length];  // Array to track previous nodes during pathfinding

        // Initialize the prev array to empty strings
        for (int i = 0; i < prev.length; i++) {
            prev[i] = "";
        }
    }

    // Attaches a node to the nodeList and updates the map
    private void attachNode(String letter, int x, int y) {
        nodeList[this.location] = new Node(letter, x, y);  // Create a new node and add it to nodeList
        map.put(letter, this.location);  // Map the node name to its index in nodeList
        this.location++;  // Increment the location index
    }

    // Links two nodes together by updating the adjacency matrix
    private void linkEdge(String firstChar, String secondChar) {
        Integer firstPosition = map.get(firstChar);  // Get the index of the first node
        Integer secondPosition = map.get(secondChar);  // Get the index of the second node

        // Check if both positions are valid and update the matrix to link the nodes
        if (firstPosition != null && secondPosition != null) {
            this.matrix[firstPosition][secondPosition] = true;
            this.matrix[secondPosition][firstPosition] = true;  // Since it's an undirected graph
        }
    }

    // Reads the maze file and populates the nodeList and adjacency matrix
    public void readMazeFile(String fileName) {
        FileManager fileManager = new FileManager(fileName);  // Create a FileManager to read the file
        fileManager.readFile(fileName);  // Read the maze file

        // Attach nodes to the nodeList using data from the file
        for (int i = 0; i < fileManager.lineCount; i++) {
            attachNode(fileManager.characters[i], fileManager.xCoordinate[i], fileManager.yCoordinate[i]);
        }

        // Link nodes together based on the information from the file
        for (int i = 1; i < fileManager.numberOfLinkers; i++) {
            if (!fileManager.nextCoordinateFirst[i].equalsIgnoreCase("A")) {
                if (fileManager.nextCoordinateFirst[i].equals("W")) {
                    fileManager.nextCoordinateFirst[i] = "EXIT";  // Handle special case where 'W' means 'EXIT'
                }
                linkEdge(fileManager.characters[i], fileManager.nextCoordinateFirst[i]);
            }
            if (!fileManager.nextCoordinateSecond[i].equalsIgnoreCase("A")) {
                if (fileManager.nextCoordinateSecond[i].equals("W")) {
                    fileManager.nextCoordinateSecond[i] = "EXIT";  // Handle special case where 'W' means 'EXIT'
                }
                linkEdge(fileManager.characters[i], fileManager.nextCoordinateSecond[i]);
            }
        }
    }

    // Checks if two nodes are linked in the adjacency matrix
    public boolean LinkedNodes(String firstChar, String secondChar) {
        boolean linked = false;

        Integer positionOne = map.get(firstChar);  // Get the index of the first node
        Integer positionTwo = map.get(secondChar);  // Get the index of the second node

        // Check if the nodes are linked by checking their positions in the matrix
        if (this.matrix[positionOne][positionTwo] == true || this.matrix[positionTwo][positionOne] == true) {
            linked = true;
        }

        return linked;
    }

    // Performs a depth-first search (DFS) in the maze from startNode to endNode and returns the path
    public String search(Node startNode, String end) {

        // Mark the start node as visited and push it onto the stack
        visited.add(startNode.characters);
        stack.push(startNode.characters);

        // Continue the search while the stack is not empty
        while (!stack.isEmpty()) {
            String currentVertex = stack.pop();  // Get the current node from the stack

            // If the current node is null, the search has reached the end
            if (currentVertex == null) {
                System.out.println("Reached end of maze!");
            }

            // Iterate through all nodes in the nodeList
            for (int i = 0; i < nodeList.length; i++) {
                // If the current node is linked to another and not visited, add it to the stack
                if (LinkedNodes(currentVertex, nodeList[i].characters) && !visited.contains(nodeList[i].characters)) {
                    stack.push(nodeList[i].characters);
                    visited.add(nodeList[i].characters);  // Mark the node as visited
                    if (prev[i].equalsIgnoreCase("")) {  // Track the previous node for backtracking
                        prev[i] = currentVertex;
                    }
                }
            }
        }

        // Backtrack from the end node to the start node to build the path
        String currentVertex = end;
        String reverse = "";

        while (!currentVertex.isEmpty()) {
            reverse += currentVertex + " ";  // Build the path in reverse order
            int currentIndex = map.get(currentVertex);
            currentVertex = prev[currentIndex];  // Move to the previous node
        }

        // Reverse the path to get the correct order from start to end
        String[] pathNodes = reverse.split(" ");
        for (int i = pathNodes.length - 1; i >= 0; i--) {
            path += pathNodes[i] + " ";  // Build the final path
        }

        return path;  // Return the path from start to exit
    }
}
