package id1020.labb4Task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* *****************************************************************************
 *  Name:    Frida Johansson
 *  Course:  ID1020
 *  Task:    1
 *
 *  Description:    Program based on DFS which can answer questions of the type: 
 *                  "Find the a path from X to Y" Which should result in a list 
 *                  of vertices traversed from X to Y if there is a path using
 *                  a undrected graph.
 *  
 *  ReadMe:         The .dat file needs to be in the same folder as this file.
 *                  Dependencies: SymbolGraph.java, Graph.java, 
 *                  BreadthFirstPaths.java, DepthFirstPaths.java
 *
 *  Written:       28/09/2020
 *  Last updated:  01/10/2020
 *
 *
 **************************************************************************** */
public class Task1 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException 
    {
        
        
        SymbolGraph sg = new SymbolGraph("thedatabase.dat");
        Graph G = sg.G();
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
    private static void BFP(SymbolGraph sg,Graph G,String start, String end)
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
    
    
    private static void DFP(SymbolGraph sg,Graph G,String start, String end)
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
        /*

        try
        {
            Scanner in = new Scanner(new File("thedatabase.dat"));
            UndirectedGraph graph = new UndirectedGraph();
            
            while (in.hasNextLine()) 
            {
                String line = in.nextLine();
                String[] names = line.split(" ");
                for (int i = 1; i < names.length; i++) 
                {
                    graph.addEdge(names[0], names[i]); 
                }
   
            }
            in.close();
            
            System.out.println(graph.toString());
  
            //BFP(graph, args[0], args[1]);
            DepthFirstPaths depthPath = new DepthFirstPaths(graph, args[0]);
        }catch(FileNotFoundException exc){
            throw new FileNotFoundException("File does not exist");
        }
    }
    private static void BFP(UndirectedGraph graph, String start, String end)
    {
        BreadthFirstPaths breadthPath = new BreadthFirstPaths(graph, start);
        System.out.print("Vertices past: ");
            for(String v: breadthPath.pathTo(end))
                 System.out.print("-->" + v);
            if (!breadthPath.hasPathTo(end))
            {
                System.out.println("Enter another destination!");
            }
            else
                System.out.println("\ndistance between : " +  start + " and " + end + " is " + breadthPath.distanceTo(end) + " steps.");
    }

}*/
