
package id1020.labb4Task2;

/* *****************************************************************************
 *  Name:    Frida Johansson
 *  Course:  ID1020
 *  Class:   BreadthFirstPaths
 *
 *  Description:    By a breadth first search it finds all paths connected the   
 *                  source and the edgeTo array defines all shortest paths   
 *                  connected to it. 
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
 * @author Frida Johansson
 */
public class BreadthFirstPaths
{
    private boolean[] marked; 
    private int[] edgeTo; 
    private final int source; 
    private int steps = 0;
    
    /**
     * Constructor of BreadthFirstPaths.
     * Calls a breadth first search and adds the source vertex, parent, to a 
     * queue. While iterating it keeps adding the adjacent vertices, 
     * its children, on the queue and mark them with a boolean value true when 
     * visited. When all children has been marked the queue pops the parent and 
     * continues with the next vertex which becomes the parent. 
     * This search iterated until the queue is empty.
     * @param G represents the graph
     * @param s represents the source
     */
    public BreadthFirstPaths(DiGraph G, int s)
    {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.source = s;
        bfs(G, s);
    }
    private void bfs(DiGraph G, int s)
    {
        GeneralizedQueue<Integer> queue = new GeneralizedQueue<Integer>();
        marked[s] = true; 
        queue.enqueue(s); 
        while (!queue.isEmpty())
        {
            int v = queue.dequeueElement(); 
            for (int w : G.adj(v))
                if (!marked[w]) 
                {
                    edgeTo[w] = v; 
                    marked[w] = true; 
                    queue.enqueue(w); 
                }
        }
    }
    /**
     * gets the number of steps from the source to the destination vertex
     * @return an int value of the number of steps taken subtracting the source
     */
    public int getSteps()
    {
        return (steps - 1);
    }
    /**
     * returns true or false if there is a path to the vertex v
     * @param v represents the vertex destination
     * @return a boolean value if there is a path
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