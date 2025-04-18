/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noiseremoving;

/**
 *
 * @shushmita_paul
 * id:23189444
 * 
 * Question 1: Is quick sort the best way of finding the median? Why?
 * 
 * Answer: No, QuickSort is not the best way to find the median because
 * QuickSort sorts the entire array, which is unnecessary for finding a
 * single median value. Sorting the whole array has a time complexity
 * of O(n log n), which is more than needed for median-finding purposes.
 * 
 * Question 2: What is another good way of finding the median? Explain.
 * 
 * Answer: A better approach to find the median is the Median of Medians
 * algorithm. This algorithm finds the median in O(n) time by recursively
 * dividing the array into smaller groups, finding the median of each group,
 * and then using these medians to find the overall median. This approach
 * is more efficient than QuickSort for finding a single median value.
 */

public class SortArray<E extends Comparable<E>> {

    private E[] array;

    public SortArray(E[] array) {
        this.array = array;
    }

    public void setArray(E[] array) {
        this.array = array;
    }

    // Public method to start QuickSort
    public void quickSort() {
        quickSort(0, array.length - 1);
    }

    // Internal QuickSort method with recursion
    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    // Partition method that organizes the array around the pivot
    private int partition(int low, int high) {
        E pivot = array[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        
        swap(i + 1, high);
        return i + 1;
    }

    // Swap elements at two indices
    private void swap(int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}