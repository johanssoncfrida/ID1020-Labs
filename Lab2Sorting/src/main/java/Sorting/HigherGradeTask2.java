/*
 * Compare the execution times for sorting large arrays of integers with quicksort 
 * and merge sort. When should one select quicksort over mergesort?
 */
package Sorting;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Frida Johansson
 */
public class HigherGradeTask2 {

    /**
     * main, all calls goes from here.
     * The arrays is not printed, only time, due to the purpose of the task
     * is to measure the time it takes to sort the array.
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        int [] input = {2,3,5,1,6,8,7,9,0};
        
        long startTime = System.nanoTime();
        sort(input);
        long endTime = System.nanoTime();
        long interval = endTime - startTime;
        System.out.println("Execution time quicksort (nano = 10E-9): \n" + interval + "ns");
        System.out.println(Arrays.toString(input));
    }
    /**
     * quicksort divide the array recusively in subarrays by the partitionIndex and 
     * sorts each subarray by quickSort.
     * @param arr represents a part of array that is being sorted in partition
     * @param begin represents the start of the subarray
     * @param end represents the end of the subarray
     */
    public static void quickSort(int arr[], int begin, int end) 
    {
        if(begin >= end)
        {
            return;
        }
        else
        {
            int partitionIndex = partition(arr, begin, end);
            
            quickSort(arr, begin, partitionIndex-1);
            
            quickSort(arr, partitionIndex+1, end);
         }
    }
    /**
     * partition sorts the array by dividing the array into halfs that is either 
     * bigger or smaller than the specified pivot element, last element of the array.
     * @param arr represents the array thats being sorted
     * @param begin represents the startingpoint of the array
     * @param end represents the endingpoint of the array
     * @return 
     */
    private static int partition(int arr[], int begin, int end) 
    {
        int pivot = arr[end];
        int i = (begin-1);
        
        for (int j = begin; j < end; j++) {
            
            if (arr[j] <= pivot) {
                i++;
                swap(arr,i, j);
            }
        }
        swap(arr, i+1,end);
        return (i+1);
    }
    /**
     * A simple method to swap to elements on two different indices in an array.
     * @param array represents the array where the the elements is being swapt.
     * @param i represents an indexposition in the array
     * @param j represents an indexposition in the array
     */
    private static void swap(int [] array,int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    private static void sort(int[] inputArr) {
         
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        
        int length = inputArr.length;
        quickSort(inputArr,0, length-1);
    }
    
}
