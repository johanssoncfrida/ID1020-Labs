/*
 * Compare the execution times for sorting large arrays of integers with 
 * insertionsort and merge sort. When should one select mergesort over insertionsort? 
 */
package Sorting;

import java.util.Arrays;



/**
 *  Mergesort vs Insertionsort
 * @author Frida Johansson
 */
public class Task5 {
    
    
    /**
     * main, all calls goes from here.
     * The arrays is not printed, only time, due to the purpose of the task
     * is to measure the time it takes to sort the array.
     * @param args the command line arguments
     */
    public static void main(String args[]) {
   
        testMergesort();
        testInsertion();
    }
    /**
     * testInsertion populates array in worst case order, 
     * calls sortByInsertion and measure the time in nano.
     */
    private static void testInsertion()
    {
        System.out.println("\n**********TEST INSERTION**********");
        int [] arr = new int[20];
        
        for(int i = 0; i < arr.length; i++)
        {
            arr[arr.length-1-i] = i;
        }
        
        System.out.println("Not sorted: "+Arrays.toString(arr));
        long startTimeInsertion = System.nanoTime();
        sortByInsertion(arr);
        long endTimeInsertion = System.nanoTime();
        long intervalInsertion = endTimeInsertion - startTimeInsertion;
        System.out.println("Execution time Insertion(nano = 10E-9): \n" + intervalInsertion + "ns");
        System.out.println("Sorted: "+Arrays.toString(arr));
    }
    /**
     * testMergesort populates array in worst case order, 
     * calls sortByInsertion and measure the time in nano.
     */
    private static void testMergesort()
    {
        System.out.println("\n**********TEST MERGESORT**********");
        int [] arr = new int[20];
        
        for(int i = 0; i < arr.length; i++)
        {
            arr[arr.length-1-i] = i;
        }
        System.out.println("Not sorted: "+Arrays.toString(arr));
        long startTime = System.nanoTime();
        arr = mergeSort(arr);
        
        long endTime = System.nanoTime();
        long interval = endTime - startTime;
        System.out.println("Execution time Merge (nano = 10E-9): \n" + interval + "ns");
        System.out.println("Sorted: "+Arrays.toString(arr));

    }
    /**
     * mergeSort splits array in half recursively until the subarray only contain one element.
     * mergeSort calls on method merge to put the subarrays together. 
     * @param arr represents the array being splitted in half
     * @return the whole array sorted with all subarrays merged. 
     */
    private static int[] mergeSort(int [] arr)
    {
        if(arr.length <= 1)
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
 
        sortedArray = merge(leftHalf, rightHalf);
        
        return sortedArray;
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
    }
}
        
        
        
    

