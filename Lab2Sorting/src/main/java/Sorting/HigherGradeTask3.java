/*
 * Compare the execution times of quicksort where the first element in 
 * each sub-array is selected as partitioning element to that of quicksort 
 * with median-of-three partitioning.
 */
package Sorting;

import java.util.Arrays;
import java.util.Random;


/**
 *
 * @author Frida Johansson
 */
public class HigherGradeTask3 {
    

    /**
     * main, all calls goes from here.
     * The arrays is not printed, only time, due to the purpose of the task
     * is to measure the time it takes to sort the array.
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        int [] input = new int[100];
        
        for(int i = 0; i < input.length; i++)
        {
            input[input.length-1-i] = i;
            
        }
        long startTime = System.nanoTime();
        sort(input);
        long endTime = System.nanoTime();
        long interval = endTime - startTime;
        System.out.println("Execution time quicksort (nano = 10E-9): \n" + interval + "ns");
        
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
     * bigger or smaller than the specified pivot element, first element of the array.
     * @param arr represents the array thats being sorted
     * @param begin represents the startingpoint of the array
     * @param end represents the endingpoint of the array
     * @return 
     */
    private static int partition(int arr[], int begin, int end) 
    {
        
        int pivot = begin;
        int firstIndex = begin;
        int lastIndex = end;
        
        while(firstIndex < lastIndex)
        {
            
            while(arr[firstIndex] <= arr[pivot] && firstIndex < end)
            {
                firstIndex++;
            }
            
            while(arr[lastIndex]> arr[pivot] && lastIndex > begin)
            {
                lastIndex--;
            }
            if(firstIndex < lastIndex)
            {
                swap(arr,firstIndex,lastIndex);
            }
        }
        
        swap(arr, lastIndex, pivot);
        
        return lastIndex;
    }
    private static int medianOfThree(int i, int j, int k) 
    {
    if ((i > j) && (i < k)) 
        return i;
    else if ((j > i) && (j < k)) 
        return j;
    else
        return k;
}
 
    private static void swap(int [] array,int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    private static void sort(int[] inputArr) {
       
        int length = inputArr.length;
        
        quickSort(inputArr,0, length-1);
        
    }
    
}
