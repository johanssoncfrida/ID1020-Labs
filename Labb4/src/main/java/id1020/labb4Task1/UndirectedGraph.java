package id1020.labb4Task1;

/* *****************************************************************************
 *  Name:    Frida Johansson
 *  Course:  ID1020
 *  Class:   UndirectedGraph
 *
 *  Description:    A two way graph based on a Symbol table where key = String  
 *                  to hold vertex and value = Queue to hold all vertices the 
 *                  vertex edge to.
 *  
 *  ReadMe:         Has dependencies: ST and GeneralizedQueue from previous labs
 *                
 *
 *  Written:       28/09/2020
 *  Last updated:  01/10/2020
 *
 *
 **************************************************************************** */

/**
 * 
 * @author Frida Johansson
 */
@SuppressWarnings("unchecked")
public class UndirectedGraph {

    private ST<String, GeneralizedQueue<String>> st;
    private int edges; //number of edges

    public UndirectedGraph() {
        st = new ST<String, GeneralizedQueue<String>>();
    }

   /**
     * getNumberOfVertices method returns the number of vertices in the graph.
     *
     * @return the number of vertices in this graph
     */
    public int getNumberOfVertices() 
    {
        return st.size();
    }

   /**
     * getEdges method returns the number of edges in the graph
     *
     * @return the number of edges in this graph
     */
    public int getEdges() 
    {
        return edges;
    }

    // throw an exception if v is not a vertex
    private void validateVertex(String v) 
    {
        if (!hasVertex(v)) 
            throw new IllegalArgumentException(v + " is not a vertex");
    }

   /**
     * Returns the degree of vertex v in this graph.
     *
     * @param  v the vertex
     * @return the degree of {@code v} in this graph
     * @throws IllegalArgumentException if {@code v} is not a vertex in this graph
     */
    public int degree(String v) {
        validateVertex(v);
        return st.get(v).getSize();
    }

   /**
     * Adds the edge v-w to this graph (if it is not already an edge).
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     */
    public void addEdge(String v, String w) {
        if (!hasVertex(v)) 
            addVertex(v);
        if (!hasVertex(w)) 
            addVertex(w);
        if (!hasEdge(v, w)) 
            edges++;
        st.get(v).enqueue(w);
        st.get(w).enqueue(v);
    }

   /**
     * Add vertex method adds the vertex to the graph if not 
     * already in the graph.
     *
     * @param  v the vertex
     */
    public void addVertex(String v) {
        if (!hasVertex(v)) 
            st.put(v, new GeneralizedQueue<String>());
    }


   /**
     * Returns the vertices in this graph.
     *
     * @return the set of vertices in this graph
     */
    public Iterable<String> vertices() {
        return st.keys();
    }

   /**
     * Returns the set of vertices adjacent to v in this graph.
     *
     * @param  v the vertex
     * @return the set of vertices adjacent to vertex {@code v} in this graph
     * @throws IllegalArgumentException if {@code v} is not a vertex in this graph
     */
    public Iterable<String> adjacentTo(String v) {
        validateVertex(v);
        return st.get(v);
    }
    
    

   /**
     * Returns true if v is a vertex in this graph.
     *
     * @param  v the vertex
     * @return {@code true} if {@code v} is a vertex in this graph,
     *         {@code false} otherwise
     */
    public boolean hasVertex(String v) {
        return st.contains(v);
    }

   /**
     * Returns true if v-w is an edge in this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @return {@code true} if {@code v-w} is a vertex in this graph,
     *         {@code false} otherwise
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *         is not a vertex in this graph
     */
    public boolean hasEdge(String v, String w) {
        validateVertex(v);
        validateVertex(w);
        return st.get(v).contains(w);
    }

   /**
     * Returns a string representation of this graph.
     *
     * @return string representation of this graph
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (String v : st.keys()) {
            s.append(v + ": ");
            for (String w : st.get(v)) {
                s.append(w + " ");
            }
            s.append('\n');
        }
        return s.toString();
    }
}