/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchinglab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Frida Johansson
 */
public class TestTheoryQuestion {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws FileNotFoundException {
        File file = new File("WOECALH.txt");
        BinarySearchTree<Character, Integer> tree = new BinarySearchTree<Character, Integer>();
        Scanner in = new Scanner(file);
        
        
        while (in.hasNext())
        {
            char word = in.next().charAt(0);
            
            tree.put(word, 1);
            
        }
        System.out.print("Preorder: ");
        tree.printPreOrder();
        System.out.print("\nIn order: ");
        tree.printInOrder();
        System.out.print("\nPost order: ");
        tree.printPostorder();
        
        in.close();
            
    }
}
