/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @shushmita_paul
 * id:23189444
 * 
 */

public class FileManager {

    public String name;  // The name of the file being managed
    public int lineCount;  // Number of lines in the file
    
    // Arrays to store node data and their connections
    public String[] lineContents;
    public String[] nextCoordinateFirst;
    public String[] nextCoordinateSecond;
    public String[] texCoordinatetLines;    
    public String[] characters;  // Node names
    public int[] xCoordinate;  // Node x-coordinates
    public int[] yCoordinate;  // Node y-coordinates
    
    public String content = "";  // Stores file content as a string
    
    // Metadata from the file, including linkers, rows, and columns
    public int numberOfLinkers;
    public int numberOfColumn;
    public int numberOfRows;
   
    // Constructor that initializes the file and prepares to read data
    public FileManager(String fileName) {
        this.name = fileName;
        lineCount = 0;
        numberOfLinkers = 0;
        numberOfColumn = 0;
        numberOfRows = 0;
        
        File f = new File(name);  // Create a file object using the provided filename
        try {
            Scanner myCoordinateScanner = new Scanner(f);
            
            // Count the number of lines in the file
            while (myCoordinateScanner.hasNextLine()) {
                myCoordinateScanner.nextLine();
                lineCount++;
            }  
            myCoordinateScanner.close();
        } catch (IOException e) {
            // Handle any errors when reading the file
            System.out.println("Unable to read the file: ERROR!!!!!!" + e.getMessage());
        }
        
        // Initialize arrays based on the number of lines
        lineContents = new String[lineCount];
        characters = new String[lineCount];
        texCoordinatetLines = new String[lineCount];
        
        xCoordinate = new int[lineCount];
        yCoordinate = new int[lineCount];
        
        nextCoordinateFirst = new String[lineCount];
        nextCoordinateSecond = new String[lineCount];
    }
    
    // Reads the file and populates the necessary arrays with node data
    public void readFile(String fileName) {
        File f;
        
        // Use the provided filename or the original file if no name is provided
        if (fileName == null) {
            f = new File(this.name);
        } else {
            f = new File(fileName);
        }
        
        try {
            Scanner myCoordinateScanner = new Scanner(f);
            int lineNum = 0;
            
            // Read each line from the file and split the contents
            while (myCoordinateScanner.hasNextLine()) {
                String line = myCoordinateScanner.nextLine();            
                texCoordinatetLines[lineNum] = line;
                lineContents = line.split(",");  // Split the line by commas
                
                // First line contains metadata about the maze
                if (lineNum == 0) {
                    numberOfLinkers = Integer.parseInt(lineContents[0]);
                    numberOfColumn = Integer.parseInt(lineContents[1]);
                    numberOfRows = Integer.parseInt(lineContents[2]);
                } else {
                    // Subsequent lines contain node and connection data
                    characters[lineNum] = lineContents[0];  // Node name
                    xCoordinate[lineNum] = Integer.parseInt(lineContents[1]);  // X-coordinate
                    yCoordinate[lineNum] = Integer.parseInt(lineContents[2]);  // Y-coordinate
                    nextCoordinateFirst[lineNum] = lineContents[3];  // First link
                    nextCoordinateSecond[lineNum] = lineContents[4];  // Second link
                }
                
                content += line;  // Append the line to the content string
                lineNum++;   
            }  
            myCoordinateScanner.close(); 
        } catch (IOException e) {
            // Handle any errors when reading the file
            System.out.println("Unable to read the file: ERROR!!!!!!" + e.getMessage());
        }
    }
    
    // Writes content to a file, creating it if it doesn't exist
    public void writeFile(String fileName, String c) {
        File f;
        
        // Use the provided filename or the original file if no name is provided
        if (fileName == null) {
            f = new File(this.name);
        } else {
            f = new File(fileName);
        }
        
        try {
            f.createNewFile();  // Create a new file if it doesn't already exist
            FileWriter writer = new FileWriter(f);
            writer.write(c);  // Write the content to the file
            writer.flush();  // Flush the writer to ensure everything is written
            writer.close();  // Close the writer after writing
        } catch (IOException e) {
            // Handle any errors when writing to the file
            System.out.println("Unable to read the file: ERROR!!!!!!" + e.getMessage());
        }
    }

    // Placeholder method for unsupported functionality
    void readFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated method body
    }
    
}
