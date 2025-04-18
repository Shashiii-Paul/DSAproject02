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

public class BinaryTree<E, F extends Comparable<F>> {
    public Node<E, F> root;
    public int number_of_nodes;
    public Node<E, F>[] nodeList; // Array for storing sorted nodes
    private boolean isReversed = false;

    // Constructors
    public BinaryTree(Node<E, F> node) {
        this.root = node;
        this.number_of_nodes = (node != null) ? 1 : 0;
    }

    public BinaryTree(E element, F key) {
        this.root = new Node<>(element, key);
        this.number_of_nodes = 1;
    }

    public BinaryTree() {
        this.root = null;
        this.number_of_nodes = 0;
    }

    // Adds an element to the binary tree
    public void addElement(E element, F key) {
        Node<E, F> newNode = new Node<>(element, key);
        if (root == null) {
            root = newNode;
        } else {
            addNode(root, newNode);
        }
        number_of_nodes++;
    }

    // Adds a node to the tree based on its key
    public void addNode(Node<E, F> root, Node<E, F> node) {
        if (node.compareTo(root) < 0) {
            if (root.left == null) {
                root.left = node;
            } else {
                addNode(root.left, node);
            }
        } else {
            if (root.right == null) {
                root.right = node;
            } else {
                addNode(root.right, node);
            }
        }
    }

    // Traverses the binary tree
    public void traversal(Node<E, F> root) {
    if (root != null) {
        if (isReversed) {
            traversal(root.right); // Visit right first for reverse
            System.out.println(root.element.toString());
            traversal(root.left);
        } else {
            traversal(root.left); // Visit left first for normal order
            System.out.println(root.element.toString());
            traversal(root.right);
        }
    }
}

    // Converts the binary tree to a sorted list of nodes, respecting the reverse order
    public Node<E, F>[] toSortedList() {
        nodeList = new Node[number_of_nodes];
        toSortedListTraversal(root, 0);
        return nodeList;
    }

    private int toSortedListTraversal(Node<E, F> root, int index) {
        if (root != null) {
            if (isReversed) {
                // For reversed order, traverse right subtree first
                index = toSortedListTraversal(root.right, index);
                nodeList[index++] = root;
                index = toSortedListTraversal(root.left, index);
            } else {
                // For normal order, traverse left subtree first
                index = toSortedListTraversal(root.left, index);
                nodeList[index++] = root;
                index = toSortedListTraversal(root.right, index);
            }
        }
        return index;
    }

    // Searches for an element by its key
    public E searchElement(F key) {
        Node<E, F> resultNode = searchNode(root, new Node<>(null, key));
        return (resultNode != null) ? resultNode.element : null;
    }

    public Node<E, F> searchNode(Node<E, F> root, Node<E, F> node) {
        if (root == null) {
            return null;
        }
        int comparison = node.compareTo(root);
        if (comparison == 0) {
            return root;
        } else if (comparison < 0) {
            return searchNode(root.left, node);
        } else {
            return searchNode(root.right, node);
        }
    }

    // Reverses the order of traversal
    public void reverseOrder() {
        isReversed = !isReversed;
    }
}