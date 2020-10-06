
package id1020.labb4HigherGrade;

import java.util.NoSuchElementException;
import java.util.Scanner;


/* *****************************************************************************
 *  Name:    Frida Johansson
 *  Course:  ID1020
 *  Class:   EdgeWeightedGraph
 *
 *  Description:    A 2-way graph based on a Stack to hold the adjacency lists,  
 *                  int value V to hold number of vertices and int value E to   
 *                  hold the number of edges.
 *  
 *  ReadMe:         Has dependencies: Stack.java
 *                
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
@SuppressWarnings("unchecked")
    public class EdgeWeightedGraph
    {
        private int V; 
        private int E; 
        private Stack<Edge>[] adj; 
   
    
    public EdgeWeightedGraph(Scanner in) 
    {
        if (in == null) 
            throw new IllegalArgumentException("Check if your argument is not null!");
        try {
            V = in.nextInt();
            
            adj = (Stack<Edge>[]) new Stack[V];
            for (int v = 0; v < V; v++) 
            {
                adj[v] = new Stack<Edge>();
            }

            E = in.nextInt();
            while(in.hasNextInt()) 
            {
                int v = in.nextInt();
                int w = in.nextInt();
                
                int weight = in.nextInt();
                Edge e = new Edge(v, w, weight);
                addEdge(e);
            }
        }   
        catch (NoSuchElementException e) 
        {
            throw new IllegalArgumentException("invalid input format", e);
        }
    }

    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int V() 
    {
        return V;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int E() 
    {
        return E;
    }
    
    /**
     * Adds the undirected edge e to this edge-weighted graph.
     *
     * @param  e the edge
     */
    public void addEdge(Edge e) 
    {
        int v = e.either();
        int w = e.other(v);

        adj[v].push(e);
        adj[w].push(e);

    }

    /**
     * Returns the edges adjacent to vertex v.
     *
     * @param  v the vertex
     * @return the edges adjacent to vertex v as an Iterable
     */
    public Iterable<Edge> adj(int v) 
    {
        return adj[v];
    }
    
    /**
     * Returns the degree of vertex v, i.e the number of edges 
     * adjacent to vertex v.
     *
     * @param  v the vertex
     * @return the degree of vertex v               
     */
    public int degree(int v) 
    {
        return adj[v].size();
    }

    /**
     * Returns a string representation of the edge-weighted graph.
     *
     * @return the number of vertices V, followed by the number of edges E,
     *         followed by the V adjacency lists of edges
     */
        @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        sb.append(V).append(" ").append(E).append("\n");
        for (int v = 0; v < V; v++) 
        {
            sb.append(v).append(": ");
            for (Edge e : adj[v]) 
            {
                sb.append("[").append(e).append("]  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    
}
