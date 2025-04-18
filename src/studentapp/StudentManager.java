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

public class StudentManager<E, F extends Comparable<F>> {
    private final BinaryTree<Student, Float> bTreeScore;
    private final BinaryTree<Student, String> bTreeName;

    // Constructor to initialize binary trees for name and score sorting
    public StudentManager() {
        this.bTreeScore = new BinaryTree<>();
        this.bTreeName = new BinaryTree<>();
    }

    // Method to add a student to both trees
    public void addStudent(float score, String name, String comments) {
        Student student = new Student();
        student.name = name;
        student.score = score;
        student.comments = comments;
        
        addToTreeByName(student, name);  // Add to name tree
        addToTreeByScore(student, score); // Add to score tree
    }

    // Adds a student to the tree based on the name (String key)
    public void addToTreeByName(Student student, String name) {
        bTreeName.addElement(student, name);
    }

    // Adds a student to the tree based on the score (Float key)
    public void addToTreeByScore(Student student, Float score) {
        bTreeScore.addElement(student, score);
    }

    // Finds a student by name or score
    public Student findStudent(E key) {
        if (key instanceof String) {
            return bTreeName.searchElement((String) key);
        } else if (key instanceof Float) {
            return bTreeScore.searchElement((Float) key);
        }
        return null;
    }

    // Returns a sorted list of students by name or score
    public Student[] getSortedStudentList(E key) {
        Node<Student, F>[] nodeList;
        if (key instanceof String) {
            nodeList = (Node<Student, F>[]) bTreeName.toSortedList();
        } else if (key instanceof Float) {
            nodeList = (Node<Student, F>[]) bTreeScore.toSortedList();
        } else {
            return null;
        }

        // Convert Node array to Student array
        Student[] sortedList = new Student[nodeList.length];
        for (int i = 0; i < nodeList.length; i++) {
            sortedList[i] = nodeList[i].element;
        }
        return sortedList;
    }

    // Reverses the order of both name and score trees
    public void reverseOrder() {
        bTreeName.reverseOrder();
        bTreeScore.reverseOrder();
    }
}
