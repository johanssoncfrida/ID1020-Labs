
package id1020.labb4HigherGrade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* *****************************************************************************
 *  Name:    Frida Johansson
 *  Course:  ID1020
 *  Class:   HigherGrade
 *
 *  Description:    Implement a program which allows the user to find the 
 *                  shortest path between two nodes in a graph possibly passing 
 *                  through a third node. I.e. the user should be able to ask 
 *                  questions like: Which is the shortest path from A to B 
 *                  passing through C? The program should output an ordered list 
 *                  of the nodes to traverse from A to B if such a path exists. 
 *                  If no such path exists then the program should output that 
 *                  no such path exists.                   
 *  
 *  ReadMe:         Has dependencies: EdgeWeightedGraph.java and
 *                  DijkstrasAlgorithm.java. The NYC.dat needs to be in the same
 *                  directory as this file.
 *                  See timecomplexity and the reason I chose to solve this task 
 *                  with Dijkstra at the bottom.
 *
 *  Written:       28/09/2020
 *  Last updated:  06/10/2020
 *
 *
 **************************************************************************** */


/**
 *
 * @author Frida Johansson
 */
public class HigherGrade 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws FileNotFoundException, IndexOutOfBoundsException
    {
        try
        {
            File file = new File("NYC.dat");
            Scanner in = new Scanner(file);
            EdgeWeightedGraph edgeGraph = new EdgeWeightedGraph(in);
            
            in.close();
            
            int[] input = getInput();
            dijkstrasShortestPath(edgeGraph,input[0], input[2],input[1]);
        
        }catch(FileNotFoundException exc)
        {
            throw new FileNotFoundException("File not found!");
        }

    }
    private static int[] getInput()
    {
        int [] in = new int[3];
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your startingpoint: ");
        in[0] = input.nextInt();
        System.out.println("\nEnter your destination ");
        in[1] = input.nextInt();
        System.out.println("Enter the vertex that needs to be passed");
        in[2] = input.nextInt();
        return in;
    }
    private static void dijkstrasShortestPath(EdgeWeightedGraph edgeGraph,int start, int passingVertex, int dest) throws IndexOutOfBoundsException
    {
        
        DijkstrasAlgorithm dijkstra = new DijkstrasAlgorithm(edgeGraph, passingVertex);
        StringBuilder sb = new StringBuilder();
        System.out.println("\n****************************************"); 
        
        if(dijkstra.hasPathTo(passingVertex) && dijkstra.hasPathTo(dest))
        {
            System.out.println("\nShortest path\n");
            dijkstra.getPath(start,sb);
            sb = sb.reverse();
            dijkstra.getPath(dest,sb);
            System.out.println("\n"+sb.toString());
            System.out.println("\nTotal traveltime: " + (dijkstra.distTo(start) + dijkstra.distTo(dest)));
        }
        else
            System.out.println("Path does not exist!");
        
        System.out.println("\n\n****************************************");
    }
}
/**
 * Tidskomplexitet och val av algoritm för denna uppgift.
 * Inför denna uppgift skulle vi hitta den kortaste vägen mellan två noder A och
 * B som skulle passera nod C. Jag valde Dijkstras algoritm för att dels var det
 * givet i uppgiften att vi inte behövde hantera negativa weights, då Bellman 
 * Ford istället hade kanske kunnat vara mer lämplig, men också för att den ger 
 * den kortaste vägen. Min implementation av Dijkstras algoritm bygger på en 
 * prioritetskö som då prioriterar minimumtiderna, min weights, i grafen. I och 
 * med att den prioriterar min weights vet jag att den alltid kommer välja den
 * kortaste vägen mellan två hörn. Då min prioritetskö är implementerad som en 
 * binary heap, likasinnig binary tree fast där trädnoderna är index i en array,
 * så uppnår jag en logaritmisk tidskomplexitet när jag ska hitta vägarna från
 * source. Tidskomplexiteten för algoritmen är O(ElogV) i worst case där V är 
 * antalet hörn, vertices, och E är andtalet bågar, edges. I min prioritetskö
 * använder jag extra minne proportionell till antalet hörn i grafen. 
 * 
 * Något att fundera kring:
 * 
 * Det är sant att algoritmen alltid väljer den båge med lägst weight mellan två
 * hörn. Däremot kan man fråga sig, om grafen hade sett ut på följande vi:
 * 
 *     3    3    3    3
 *   B----C----D----E---F
 * 3/                     \3
 * A-----------------------G
 *             5
 * I denna graf kommer den enligt implementationen av algoritmen välja väg A 
 * till B där distansen är 3. Enligt Dijkstra kommer denna väg bli:
 * 3+3+3+3+3+3=18. När vägen A-G = 5. Det är tydligt att vägen mellan A-G är den
 * kortaste vägen men algoritmen ger inte det svaret. 
 * Detta är väl någonting som jag funderat lite kring och som kan vara ett 
 * problem med denna algoritm. 
 * 
 * När jag tittade i datafilen såg jag däremot att grafen inte var byggd på 
 * detta vis så därav kände jag mig trygg med detta val av algoritm.
 */ 
