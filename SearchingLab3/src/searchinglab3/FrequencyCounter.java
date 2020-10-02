
package searchinglab3;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.out;

import java.util.Scanner;


/* *****************************************************************************
 *  Name:    Frida Johansson
 * 
 *  Description:  Frecuency counter that counts the most repeated word in
 *                a text file while choosing from 2 different algorithms and 
 *                counts the time it takes to put all the words in the right 
 *                place.
 *
 *  ReadMe:       When running this program the text file thetext.txt needs to 
 *                be in the same folder as this one. It requires 3 command line
 *                arguments:
 *                - args[0] = program, 1 for symbol table and 2 for BST
 *                - args[1] = the minimum wordlimit
 *                - args[2] = maxwords to be read in the document
 *                It also has dependencies 
 *                for BinarySearchST.java and BinarySearchTree.java in this
 *                package.
 *
 *  Written:       2020-09-23
 *  Last updated:  2020-09-27
 *
 **************************************************************************** */

public class FrequencyCounter 
{
    /**
     * @param args the command line arguments
     * args[0] = program, 1 for symboltable and 2 for BST
     * args[1] = the minimum wordlimit
     * args[2] = maxwords to be read in the document
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException 
    {   
        File file = new File("thetext.txt");
        
        if(isCompletelyWritten(file))
        {
            Scanner in = new Scanner(file);
            int program = Integer.parseInt(args[0]);
            if(program == 1)
            {
                runProgramST(args, in);
            }
            if(program == 2)
            {
                runProgramBST(args, in);
            }
            else if(program >2)
                throw new IllegalArgumentException("Enter number 1 or 2 to choose a valid program.");
        }
        System.out.println("Time complexity: "
                + "\nSearch method Symboltable: lg(N) for average and worst case."
                + "\nInsert method Symboltable: average case (N), worst case (2N)."
                + "\n\nInsert and search method BST: average case 2(lg(N)). Worst case (N).");
    }

    private static void runProgramST(String [] args, Scanner in) throws FileNotFoundException
    {
        
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        
        int minWordLimit = Integer.parseInt(args[1]);
        int maxwords = Integer.parseInt(args[2]);
        if (minWordLimit < 0)
            throw new IllegalArgumentException("Enter a valid length!");
        int limit = 0;
        
        long startTime = System.nanoTime();
        while (in.hasNext() && (limit <= maxwords))
        { 
            String key = in.next().toLowerCase().trim().replaceAll("[,,.*-:#]", "");
            
            if(key.length() < minWordLimit)
                continue;
            if(st.contains(key))
                st.put(key, (st.get(key)+1));
            else
               st.put(key, 1); 
            
            limit++;
        }
        
        String max = "";
        st.put(max, 0);
        for (String words : st.keys())
            if (st.get(words) > st.get(max))
            {
                
                max = words;
            }
        long endTime = System.nanoTime();
        long interval = endTime - startTime;
        out.println("Most repetead word: " + max + " " + (st.get(max)) + " repetitions. "+ " Time elapsed: " + interval);
        
        in.close();
    }
    private static void runProgramBST(String [] args, Scanner in) throws FileNotFoundException
    {
       
        BinarySearchTree<String, Integer> tree = new BinarySearchTree<String, Integer>();
        
        int minLimit = Integer.parseInt(args[1]);
        int maxwords = Integer.parseInt(args[2]);
        
        if (minLimit < 0)
            throw new IllegalArgumentException("Enter a valid length!");
        int limit = 0;
        
        long startTime = System.nanoTime();
        while (in.hasNext() && (limit <= maxwords))
        { 
            String key = in.next().toLowerCase().trim().replaceAll("[,,.*-:#]", "");
            
            if(key.length() < minLimit)
                continue;
            if(tree.contains(key))
                tree.put(key, tree.get(key)+1);
            else
               tree.put(key, 1); 
            
            limit++;
        }
        
        String max = "";
        tree.put(max, 0);
        
        for (String words : tree.keys())
            if (tree.get(words) > tree.get(max))
                max = words; 

        long endTime = System.nanoTime();
        long interval = endTime - startTime;
        out.println("Most repetead word: " + max + " " + (tree.get(max)) + " repetitions. "+ " Time elapsed: " + interval);
        
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

