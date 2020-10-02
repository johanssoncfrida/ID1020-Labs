/*
 * Experiment with the cut-off to insertionsort in merge. 
 * How is the execution time affected by different values for the cut-off? 
 * A suitable range for cut-off values to test with could be [0-30]. 
 */
package Sorting;

import java.util.Arrays;



/**
 * Sorting algorithm mergesort with cut-off to insertionsort
 * @author Frida Johansson
 */
public class Task6 {
    
    
    /**
     * main, all calls goes from here.
     * The arrays is not printed, only time, due to the purpose of the task
     * is to measure the time it takes to sort the array.
     * @param args the command line arguments
     */
    public static void main(String args[]) {
   
        int [] arr = new int[90];
        
        for(int i = 0; i < arr.length; i++)
        {
            arr[arr.length-1-i] = i;
            
        }
        
        long startTime = System.nanoTime();
        arr = mergeSort(arr);
        long endTime = System.nanoTime();
        long interval = endTime - startTime;
        System.out.println("Execution time Merge cut of by insertion (nano = 10E-9): \n" + interval + "ns");
        System.out.println("\nSorted and merged array: "+Arrays.toString(arr));
    }
    /**
     * mergeSort splits array in half recursively until the subarray only contain 30 element.
     * mergeSort calls on method sortByInsertion to sort the subarrays and then calls on merge to merge the sorted arrays. 
     * @param arr represents the array being splitted in half
     * @return the whole array sorted with all subarrays merged. 
     */
    private static int[] mergeSort(int [] arr)
    {
        if(arr.length <= 30)
        {
            return arr;
        }
        
        int middle = arr.length/2;
        int [] leftHalf = new int[middle];
        int [] rightHalf;
        
        if(arr.length % 2 == 1)
        {
            rightHalf = new int[middle + 1];
        }
        else
        {
            rightHalf = new int[middle];
            
        }
        for(int i = 0; i < middle; i++)
        {
            leftHalf[i] = arr[i];
        }
        for(int j = 0; j < rightHalf.length; j++)
        {
            rightHalf[j] = arr[middle + j];
        }

        leftHalf = mergeSort(leftHalf);
        rightHalf = mergeSort(rightHalf);
      
        int [] sortedArray = new int[arr.length];
        
        sortByInsertion(leftHalf);
        sortByInsertion(rightHalf);
        
        sortedArray = merge(leftHalf, rightHalf);
        
        return sortedArray;
    }
    /**
     * sortByInsertion is sorting array in ascending order 
     * by comparing values at index pos and prepos.Also calculates number of swaps.
     * 
     * @param arr represents the array thats being sorted.
     */
    static void sortByInsertion(int [] arr)
    {
       
        for(int i = 1; i < arr.length ; i++)
        {
            int pos = arr[i];
            int prePos = i - 1;
            while(prePos >= 0 && arr[prePos] > pos )
            {
                arr[prePos + 1] = arr[prePos];
                prePos = prePos -1; 
                arr[prePos + 1] = pos;
            
            }
        }
        System.out.println("\nSorted: "+Arrays.toString(arr));
    }
    
    /**
     * merge puts together each subarray and sorts them into one array, result.
     * @param leftArray represents the left half of the array
     * @param rightArray represents the right half of the array
     * @return the two subarrays merged together and sorted into one result array.
     */
    private static int[] merge(int[] leftArray, int[] rightArray)
    {
        
        int [] result = new int[leftArray.length + rightArray.length];
 
        int leftPtr, rightPtr,resultPtr;
        leftPtr = rightPtr = resultPtr = 0;
        
        while(leftPtr < leftArray.length || rightPtr < rightArray.length)
        {
            if(leftPtr < leftArray.length && rightPtr < rightArray.length)
            {
                if(leftArray[leftPtr] < rightArray[rightPtr])
                {
                    result[resultPtr++] = leftArray[leftPtr++];
                }
                else
                {
                    result[resultPtr++] = rightArray[rightPtr++];
                }
                    
            }
            else if(leftPtr < leftArray.length)
            {
                result[resultPtr++] = leftArray[leftPtr++];
            }
            else if(rightPtr < rightArray.length)
            {
                result[resultPtr++] = rightArray[rightPtr++];
            }
   
        }
        
        return result;
    }
    
    
}
        
        
        
    

