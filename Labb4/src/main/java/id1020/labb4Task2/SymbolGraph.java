
package id1020.labb4Task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* *****************************************************************************
 *  Name:    Frida Johansson
 *  Course:  ID1020
 *  Class:   SymbolGraph
 *
 *  Description:    A wrapper for the Graph class to be able to read String  
 *                  vertices. Instancevariables are a Symbol table, where the   
 *                  key = String vertices and value = index, a keys array to 
 *                  hold the index(value in Symbol table) in an array.
 *  
 *  ReadMe:         Has dependencies: ST.java and Graph.java
 *                
 *
 *  Written:       28/09/2020
 *  Last updated:  02/10/2020
 *
 *
 **************************************************************************** */

/**
 *
 * @author Frida Johansson
 */
public class SymbolGraph
{
    private ST<String, Integer> st; // String -> index
    private String[] keys; // index -> String
    private DiGraph G; // the graph
    
    public SymbolGraph(String stream) throws FileNotFoundException
    {
        st = new ST<String, Integer>();
        Scanner in = new Scanner(new File(stream)); 
        
        while (in.hasNextLine()) 
        {
            String[] a = in.nextLine().split(" "); 
            
            for (int i = 0; i < a.length; i++)
            {
                if (!st.contains(a[i])) 
                    st.put(a[i], st.size()); 
            }
            
        }
        
        
        keys = new String[st.size()]; 
        for (String name : st.keys())
        {
            keys[st.get(name)] = name; 
        }
        
       
        in.close();
        G = new DiGraph(st.size());
        
        in = new Scanner(new File(stream)); // Second pass
        while (in.hasNextLine()) // builds the graph
        {
            String[] a = in.nextLine().split(" "); // by connecting the
            
            int v = st.get(a[0]); // first vertex
            for (int i = 1; i < a.length; i++) // on each line
                G.addEdge(v, st.get(a[i])); // to all the others.
            
        }
        in.close();
        
    }
    /**
     * contans return true or false depending if s 
     * is in the symbol table or not
     * @param s representing a key for ST
     * @return a boolean value depending if S is in ST or not
     */
    public boolean contains(String s) 
    { 
        return st.contains(s);
    }
    /**
     * gets the corresponding index for String vertex name
     * @param s is a String which is the type reference for key in ST
     * @return an int value corresponding to the String key
     */
    public int index(String s) 
    { 
        return st.get(s);
    }
    /**
     * gets the corresponding key for index value v in the graph
     * @param v the index
     * @return a String reresentation for the index value
     */
    public String name(int v) 
    { 
        return keys[v];
    }
    /**
     * returns the directed graph
     * @return a directed graph
     */
    public DiGraph G() 
    { 
        return G; 
    } 
    /**
     * gets the value of the symbol table
     * @param key represent the key in ST
     * @return an int value which is the key's value
     */
    public int getValue(String key)
    {
        return st.get(key);
    }
    
    @Override
    public String toString() 
    {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(G.V() + " vertices, " + G.E() + " edges " + NEWLINE);
        for (int v = 0; v < G.V(); v++) 
        {  
            s.append(keys[v] + ": ");
            
            for (int w : G.adj(v)) 
            {
                s.append(keys[w] + " ");
                
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
