/*
 * Augment the test code from assignment 1 so that the array is sorted in 
 * descending order instead of ascending order (you may add O(N) operations)
 * Clarification: You should not change the sorting method, nor should you sort 
 * the array an extra time. You may traverse the array once before sorting and 
 * once after sorting. During these traversals you may not move any elements. 
 * (Hint: you need not and should not use any extra memory)
 */
package Sorting;

import java.util.Arrays;
import java.util.Scanner;
/**
 * Insertionsort while using the ascending sort method 
 * but sorts the array in descending order by traverse 
 * the array before and after the sortingmethod.
 * @author Frida Johansson
 */
public class HigherGradeTask1 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of your array: ");
        int size = scanner.nextInt();
        System.out.println("Your size of array is: " + size);
        
        int input [] = new int[size];
        storeIntegers(scanner, input, size);
        System.out.println("\n\nYour Array: " + Arrays.toString(input));
        
        for(int i= 0; i < input.length; i++)
        {
            input[i] *= (-1);
        }
        sortByInsertion(input);
        
        for(int i= 0; i < input.length; i++)
        {
            input[i] *= (-1);
        }
        System.out.println("Sorted in descending order: "+Arrays.toString(input));
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
    
    /**
     * storeIntegers populates array with user input.
     * @param scanner user input
     * @param input array to be populated
     * @param size size of the array by user input
     */
    static private void storeIntegers(Scanner scanner, int[] input, int size)
    {
        System.out.println("Enter numbers you want to store in an array:");
        int counter = 0;
        
        while(counter < size)
        {
            input[counter] = scanner.nextInt();
            System.out.println(Arrays.toString(input));
            counter ++;
        }
        
        
    }
    
    
}