# 🚀 Assignment 2 – Data Structures and Algorithms

**Name:** Shushmita Paul  
**Student ID:** 23189444  
**Course:** Data Structures and Algorithms  
**IDE:** NetBeans  

---

## 📌 Overview

This project dives into advanced data structures and algorithms with real-world applications, focusing on:

- 🌳 A **Binary Tree** for student management and sorting
- 🧼 A **Noise Removal Tool** using median filters and QuickSort
- 🧭 A **Maze Solver** with animation and shortest path tracking

---

## 🧩 Project Components

### 1. Binary Tree – Student Manager
- Generic Binary Search Tree with Comparable key support
- Sorts students by name or score
- Allows reverse-order traversal without rebuilding the tree
- Uses `Student`, `Node<T>`, and `BinaryTree<T>` classes
- `StudentManager` handles user interaction and data display

### 2. Noise Removal – Median Filter App
- Applies a 3x3 median filter to remove salt-and-pepper noise
- Implements custom `ArraySort<T>` using QuickSort
- Includes GUI to open, clean, and save images
- Output saved as `noise_removed.jpg`
- Justifies the use of QuickSort for median finding

### 3. Maze Solver
- Reads maze and nodes from `.txt` file
- Builds internal representation of graph structure
- Animates traversal from START to EXIT
- Shows the correct path and complete trail of visited nodes
- GUI for dynamic visualization

---

## ▶️ How to Run

1. Open the project folders in **NetBeans**
2. Go to `Run > Clean and Build Project` for each section
3. Execute via NetBeans or run the `.jar` files inside `dist/` if available

---

## 📁 Project Structure

- Assignment_2_S2_Release/   <- Main project folder
- studentapp/                <- Binary Tree and Student Manager
- noiseremoving/             <- Median Filter for noise removal
- maze/                      <- Maze solving with GUI and animation
- dist/                      <- Contains compiled .jar files
- README.md                  <- This file (your instructions)

---

## ⚠️ Notes

- All GUI components are manually developed in Java Swing.
- Algorithms have been tested with a range of input files and edge cases.
- This is an individual submission and complies with AUT’s academic integrity policies.

---
