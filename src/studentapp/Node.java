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

public class Node<E, F extends Comparable<F>> implements Comparable<Node<E, F>> {
    public E element; // Stores the element, in this case, a Student object
    public F key; // Comparable key used for sorting
    public Node<E, F> left; // Reference to the left child
    public Node<E, F> right; // Reference to the right child

    // Constructor to initialize a new node with element and key
    public Node(E element, F key) {
        this.element = element;
        this.key = key;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(Node<E, F> otherNode) {
        // Compare this node's key with the other node's key
        return this.key.compareTo(otherNode.key);
    }
}
