/*
 * Implement insertionsort. 
 * Add a method which counts the number of inversions in the input array 
 * and prints a list of all inversions on the format [i,a[i]], [j, a[j]] 
 * where i and j are indices and a[i], a[j] are the values of the elements. 
 * Call the method from main() before the array is sorted. 
 * Calculates the time complexity for the algorithm.
 */
package Sorting;

import java.util.Arrays;
import java.util.Scanner;
/**
 * Insertionsort, added number of inversions.
 * @author Frida Johansson
 */
public class Task3 
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
        
        printInversions(input);
        sortByInsertion(input);
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
                prePos = prePos - 1; 
                arr[prePos + 1] = pos;
                System.out.println("Sorted array so far: "+Arrays.toString(arr));
                swaps += 1;
            } 
        }
        System.out.println("\nNumber of swaps: " + swaps);
    }
    
    /**
     * printInversions counts and prints the number of inversion the method sortByInsertion makes
     * 
     * @param arr represents the input array
     */
    static private void printInversions(int [] arr)
    {
        int numberOfInversions = 0;
        for(int i = 0; i < arr.length -1 ; i++)
        {
            for(int j = i + 1; j < arr.length; j++)
            {
                if(arr[i] > arr[j])
                {
                    System.out.println( "[" + i + "," + arr[i] + "]" + " <--> " 
                            + "[" + j + "," + arr[j] + "]");
                    numberOfInversions += 1; 
                }
            } 
        }
        System.out.println("Number of inversions: " + numberOfInversions);
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
