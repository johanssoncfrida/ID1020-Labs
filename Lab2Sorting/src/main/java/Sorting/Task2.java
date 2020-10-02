/*
 * Implement insertionsort. 
 * Augment the sorting process so that all the content of the array that is being sorted 
 * is printed after each inner loop iteration as well as number of swaps. 
 * Write a unit test in main() which allows the user to define the size of the input (N) 
 * and then input (N) integers from stdin which is to be sorted.
 */
package Sorting;

import java.util.Arrays;
import java.util.Scanner;
/**
 * Insertionsort, added number of swaps.
 * @author Frida Johansson
 */
public class Task2 
{

    /**
     * main, all calls goes from here.
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

        sortByInsertion(input);
        System.out.println("\n\nSorted array: " + Arrays.toString(input));

    }
    /**
     * sortByInsertion is sorting array in ascending order 
     * by comparing values at index pos and prepos.Also calculates number of swaps.
     * 
     * @param arr represents the array thats being sorted.
     */
    static void sortByInsertion(int [] arr)
    {
        
        int swaps = 0;
        for(int i = 1; i < arr.length ; i++)
        {
            int pos = arr[i];
            int prePos = i - 1;
            while(prePos >= 0 && arr[prePos] > pos )
            {
                arr[prePos + 1] = arr[prePos];
                prePos = prePos -1; 
                arr[prePos + 1] = pos;
                swaps += 1;
                System.out.println("Sorted array so far: "+Arrays.toString(arr));
            }
            
        }
        System.out.println("\nNumber of swaps: " + swaps);
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


