
package searchinglab3;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;



/* *****************************************************************************
 *  Name:    Frida Johansson
 * 
 *  Description:  IndexProgramTask4 gets the position where the user input word  
 *                args[0 ]occure in the text file. Uses a BST to store all words
 *                in the text. The class BinarySearchTree,BST, has a nested 
 *                class Node thats been modified for this task with an array
 *                
 *
 *  ReadMe:       When running this program the text file thetext.txt needs to 
 *                be in the same folder as this one. It also has a dependency 
 *                for BinarySearchTree.java in this package.
 *
 *  Written:       2020-09-24
 *  Last updated:  2020-09-28
 *
 **************************************************************************** */
public class IndexProgramTask4 
{
    
    /**
     * @param args the command line arguments
     * args[0] represents the word that the program will search for 
     * its positions/occurencies in the text.
     */
    public static void main(String args[]) throws FileNotFoundException, InterruptedException, IOException 
    {
        File file = new File("thetext.txt");
        BinarySearchTree<String, Integer> tree = new BinarySearchTree<String, Integer>();
        Scanner in = new Scanner(file);
        String searchedWord = args[0];
       
        int charcount = 0;
        
        while (in.hasNext())
        { 
            String key = in.next().toLowerCase().trim().replaceAll("[,,.*-:#]", "");
            charcount += key.length();
            
            if(tree.contains(key))
                tree.put(key, tree.get(key)+1,charcount - (key.length()-1));
            else
               tree.put(key, 1, charcount - (key.length()-1)); 
           
        }
        
        for (String words : tree.keys())
            if (searchedWord.equals(words))
                System.out.println("Your word "+searchedWord + " at positions from beginning "+Arrays.toString(tree.getPositions(searchedWord)));
        
        
        in.close();
        
    }
      

    private static boolean isCompletelyWritten(File file) throws InterruptedException
    {
        Long fileSizeBefore = file.length();
        Thread.sleep(3000);
        Long fileSizeAfter = file.length();

        System.out.println("comparing file size " + fileSizeBefore + " with " + fileSizeAfter);

        if (fileSizeBefore.equals(fileSizeAfter)) 
        {
            return true;
        }
    return false;
    }
   
    
}

