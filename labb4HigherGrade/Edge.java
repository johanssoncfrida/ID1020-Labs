
package id1020.labb4HigherGrade;

/* *****************************************************************************
 *  Name:    Frida Johansson
 *  Course:  ID1020
 *  Class:   Edge
 *
 *  Description:    Represents an edge of the undirected weighted graph.  
 *                  vertex and secondvertex represents the vericies connecting   
 *                  one edge and the weight is the cost in time between them.
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
public class Edge 
{
    private final int vertex; 
    private final int secondVertex; 
    private final int weight;
    
    
    public Edge(int v, int w, int weight)
    {
        this.vertex = v;
        this.secondVertex = w;
        this.weight = weight;
        
    }
    /**
     * returns the weight of the edge
     * @return an int value
     */
    public int weight()
    { 
        return weight; 
    }
    /**
     * returns the vertex of this edge
     * @return an in value
     */
    public int either()
    { 
        return vertex; 
    }
    /**
     * returns the second vertex of this edge
     * @return an int value
     */
    public int secondVertex()
    {
        
        return secondVertex;
    }
    /**
     * returns the other vertex of the edge that is not equal to 
     * parameter vertex
     * @param vertex the either vertex
     * @return an int value 
     */
    public int other(int vertex)
    {
        if (vertex == this.vertex) 
            return secondVertex;
        else if (vertex == secondVertex) 
            return this.vertex;
        else 
            throw new RuntimeException("Inconsistent edge");
    }
 
    @Override
    public String toString()
    { 
        StringBuilder sb = new StringBuilder();
        sb.append(vertex).append(" - ").append(secondVertex).append("  ");
        return sb.toString();
    }
}
