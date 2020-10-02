
package id1020.labb4Task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* *****************************************************************************
 *  Name:    Frida Johansson
 *  Course:  ID1020
 *  Task:    2
 *
 *  Description:    Program based on DFS which can answer questions of the type: 
 *                  "Find the a path from X to Y" Which should result in a list 
 *                  of vertices traversed from X to Y if there is a path using
 *                  a directed graph.
 *  
 *  ReadMe:         The .dat file needs to be in the same folder as this file.
 *                
 *
 *  Written:       28/09/2020
 *  Last updated:  28/09/2020
 *
 *
 **************************************************************************** */
public class Task2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws FileNotFoundException 
    {
        
        
        SymbolGraph sg = new SymbolGraph("thedatabase.dat");
        DiGraph G = sg.G();
        System.out.println(sg.toString());
        Scanner in = new Scanner(new File("thedatabase.dat"));
        while(in.hasNextLine())
        {
            String source = in.nextLine();
            if(sg.contains(source))
            {
                int s = sg.index(source);
                for(int w: G.adj(s))
                    System.out.println(" " + sg.name(w));
            }
        }
        in.close();
        
        String start = args[0];
        String end = args[1];
        DFP(sg,G,start, end);
        BFP(sg,G,start, end);
   
    }
    private static void BFP(SymbolGraph sg,DiGraph G,String start, String end)
    {
        System.out.println("\n\n***************BFS***************\n");
        int s = sg.index(start); 
        int e = sg.index(end);
        BreadthFirstPaths BFP = new BreadthFirstPaths(G,s);

        if (!BFP.hasPathTo(e))
            System.out.println("Enter another destination, the path does not exist!");
        else
        {
            System.out.print("Vertices past: ");
            for(int v: BFP.pathTo(e))
                System.out.print("-->" + sg.name(v));

            System.out.println("\ndistance between : " +  start + " and " + end + 
                " is " + BFP.getSteps() + " steps.");
        }
    }
    
    
    private static void DFP(SymbolGraph sg,DiGraph G,String start, String end)
    {
        System.out.println("\n\n***************DFS***************\n");
        int s = sg.index(start); 
        int e = sg.index(end);
        
        DepthFirstPaths DFP = new DepthFirstPaths(G,s);
        if (!DFP.hasPathTo(e))
            System.out.println("Enter another destination, the path does not exist!");
        else
        {
            System.out.print("Vertices past: ");
            for(int v: DFP.pathTo(e))
                System.out.print("-->" + sg.name(v));

            System.out.println("\ndistance between : " +  start + " and " + end + 
                " is " + DFP.getSteps() + " steps.");
        }
    }
}