
package id1020.labb4Task1;

/* *****************************************************************************
 *  Name:    Frida Johansson
 *  Course:  ID1020
 *  Class:   DepthFirstPaths
 *
 *  Description:    Finds the paths via a depth first search. Given a vertex  
 *                  index to the constructor it fins all paths connected to it.   
 *                  Returns the path by pathTo.
 *  
 *  ReadMe:        Dependency: Stack.java for toString method 
 *                
 *
 *  Written:       28/09/2020
 *  Last updated:  02/10/2020
 *
 *
 **************************************************************************** */

/**
 *
 * @author Me
 */
public class DepthFirstPaths
{
    private boolean[] marked; 
    private int[] edgeTo; 
    private final int source; 
    private int steps = 0;
    
    /**
     * Constructor of Graph that calls method dfs and finds all paths connected
     * to the source
     * @param G the graph
     * @param source the source vertex to find all paths to
     */
    public DepthFirstPaths(Graph G, int source)
    {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.source = source;
        dfs(G, source);
    }
    
    private void dfs(Graph G, int v)
    {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
            {
                edgeTo[w] = v;
                dfs(G, w);
            }
    }
    /**
     * gets the number of steps taken on the path subtracting the source
     * @return an int value of number of steps
     */
    public int getSteps()
    {
        return (steps - 1);
    }
    
    /**
     * checks if there is a path to the parameter vertex v
     * @param v the vertex
     * @return a boolean value 
     */
    public boolean hasPathTo(int v)
    { 
        return marked[v]; 
    }
    
    /**
     * gets the path to a given vertex v by pushing each visited vertex on 
     * a stack 
     * @param v the vertex
     * @return an iterable
     */
    public Iterable<Integer> pathTo(int v)
    {
        if (!hasPathTo(v)) 
            return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != source; x = edgeTo[x])
            path.push(x);
        path.push(source);
        steps = path.size();
        return path;
    }
}