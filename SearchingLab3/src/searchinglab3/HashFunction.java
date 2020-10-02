
package searchinglab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/* *****************************************************************************
 *  Name:    Frida Johansson
 * 
 *  Description:  Hashfunction that shows how evenly the built-in hashcode() 
 *                function for strings in Java distributes the hashcodes for 
 *                the words found in the text by using modular hashing. This
 *                function uses a symbol table to count and store the words.
 *
 *                Note: This implementation is only to show the distribution
 *                of hashing. A real implementation would implement an array 
 *                of linked list to keep every key separately. A solution for 
 *                collision is that the number of words just increases in the 
 *                indexed array. The hash shows therefor the distribution, but
 *                does not separate the keys if it has the same hashcode. 
 *
 *  ReadMe:       When running this program the text file thetext.txt needs to 
 *                be in the same folder as this one. It also has a dependency 
 *                for BinarySearchST.java in this package.
 *
 *  Written:       2020-09-24
 *  Last updated:  2020-09-25
 *
 **************************************************************************** */

/**
 * M is a prime number for a even distribution of the keys
 * 
 */
public class HashFunction 
{
    private static final int M = 97;
    
    /**
     * hash function.
     * gets the hashcode of the string and mask the most significant bit to 
     * ensure a positive integer and use modulo of M = 97 to get final array index.
     * @param str represents the string key to be put in the array
     * @return an index in the array
     */
    private static int hash(String str) 
    {
        return (str.hashCode() & 0x7fffffff) % M;
    }

    /**
     * Main method for hashing.
     * When a new word is found in the text file it maps the key into a 
     * position in hashArray by the hash method.
     * 
     * @param args no command line arguments
     * @throws java.io.FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        BinarySearchST<String, Integer> symbolTable = new BinarySearchST<String, Integer>();
        int[] hashArray = new int[M];
        
        Scanner in = new Scanner(new File("thetext.txt"));
        int totalWords = 0;
        int definiteWords = 0;
       
        while (in.hasNext()) 
        {
            String key = in.next().toLowerCase().trim().replaceAll("[,,.*-:#]", "");
            if (key.length() < 2)
                continue;
            if (symbolTable.contains(key)) 
            {
                symbolTable.put(key, symbolTable.get(key) + 1);
            } 
            else 
            {
                symbolTable.put(key, 1);
                hashArray[hash(key)]++; //if same hashing, it will increment the value by one
                definiteWords++;
            }
            totalWords++; //does not include word length < 2
            

        }
        
        iterateHashArray(hashArray,totalWords,definiteWords);
        double sum = definiteWords/M;
        System.out.println("Distribution: " + sum + " per index");
    }
    /**
     * iterateHashArray method.
     * Iterates through the array and prints the content to show how
     * evenly distributed the words gets using a real hash table.
     * @param hashArray the array of hash indicies 
     * @param totalWords represents the total words of the text
     * @param definiteWords represents the unique words of the text
     */
    private static void iterateHashArray(int [] hashArray, int totalWords, int definiteWords)
    {
        System.out.println("\n");
        for (int i = 0; i < hashArray.length; i++)
            System.out.print( i + " " +hashArray[i] + "\n");
        System.out.println("\nTotal words in text : " + totalWords + "\nDefinite words of the text : " + definiteWords);
    }
    
}
    
