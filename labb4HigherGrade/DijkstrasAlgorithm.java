
package id1020.labb4HigherGrade;

/* *****************************************************************************
 *  Name:    Frida Johansson
 *  Course:  ID1020
 *  Class:   DijkstrasAlgorithm
 *
 *  Description:    A 2-way graph based on a Stack to hold the adjacency lists,  
 *                  int value V to hold number of vertices and int value E to   
 *                  hold the number of edges.
 *  
 *  ReadMe:         Has dependencies: PriorityQueue.java, Edge.java and 
 *                  GeneralizedQueue.java 
 *
 *  Written:        28/09/2020
 *  Last updated:   06/10/2020
 *
 *
 **************************************************************************** */

/**
 *
 * @author Frida Johansson
 */
@SuppressWarnings("unchecked")
public class DijkstrasAlgorithm 
{
    private final int [] distTo;
    private final Edge [] edgeTo;
    private final PriorityQueue<Integer> priQueue;
    
    public DijkstrasAlgorithm(EdgeWeightedGraph g, int source) throws IndexOutOfBoundsException
    {
        try
        {
  
        distTo = new int[g.V()];
        edgeTo = new Edge[g.V()];
        
        for(int v = 0; v < g.V(); v++)
        {
            distTo[v] = Integer.MAX_VALUE;
        }
        distTo[source] = 0;
        
        priQueue = new PriorityQueue(g.V());
        priQueue.insert(source, distTo[source]);
        
        while(!priQueue.isEmpty())
        {
            int vertex = priQueue.deleteMinKey();
            for(Edge e: g.adj(vertex))
            {
                relax(e, vertex);
            }
        }
        }catch(IndexOutOfBoundsException exc)
        {
            throw new IndexOutOfBoundsException("This path does not exist!");
        }
        
    }
    /**
     * Realx checks if it can decrease the path beteen two vertices
     * and updates the distTo array with a decreased path if it exists.
     * @param e the adjacent edge to be checked
     * @param v the current destination vertex
     */
    private void relax(Edge e, int v)
    {
        int w = e.other(v);
 
        if (distTo[w] > distTo[v] + e.weight()) 
        {

            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if(priQueue.contains(w))
                priQueue.changeKey(w, distTo[w]);
            else
            {
                
            priQueue.insert(w, distTo[w]);
            }
        }
    }
    
    /**
     * Return the weight from sourceindex to the destination vertex
     * @param v the startvertex
     * @return an int value representing the weight
     */
    public int distTo(int v)
    {
        return distTo[v];
    }
    /**
     * Checks if there is a path to the parameter vertex v
     * @param v the vertex to be checked
     * @return a boolean value true or false
     */
    public boolean hasPathTo(int v)
    {
        return distTo[v] < Integer.MAX_VALUE;
    }
    
    /**
     * Returns the path between start and destination vertex.
     * This method works when doing a search from a sourcevertex in between 
     * these two vertices so the method will get the path from the sourcevertex
     * to both directions start and dest.
     * 
     * @param start Starting vertex
     * @param dest the goal vertex
     * @return a generalizedqueue containing the path
     */
    public Stack<Edge> getPath(int dest, StringBuilder sb) 
    {
        
        if (!hasPathTo(dest)) 
            return null;
        Stack<Edge> path = new Stack<Edge>();
        int x = dest;
        for (Edge e = edgeTo[dest]; e != null; e = edgeTo[x])
        {
            
            path.push(e);
            x = e.other(x);
        }
        
        sb.append(path);
        return path;
    }
    
   
}
