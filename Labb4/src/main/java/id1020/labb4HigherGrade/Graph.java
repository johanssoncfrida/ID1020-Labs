
package id1020.labb4HigherGrade;


/* *****************************************************************************
 *  Name:    Frida Johansson
 *  Course:  ID1020
 *  Class:   Graph
 *
 *  Description:    A 2-way graph based on a Stack to hold the adjacency lists,  
 *                  int value V to hold number of vertices and int value E to   
 *                  hold the number of edges.
 *  
 *  ReadMe:         Has dependencies: Stack.java
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
@SuppressWarnings("unchecked")
    public class Graph
    {
        private final int V; 
        private int E; 
        private Stack<Integer>[] adj; 
        
        /**
         * Constructor of the class Graph and creates the Stack of linked lists
         * from SymbolGraph class of ST size
         * @param V 
         */
        public Graph(int V)
        {
            this.V = V; 
            this.E = 0;
            adj = (Stack<Integer>[]) new Stack[V]; 
            for (int v = 0; v < V; v++) 
                adj[v] = new Stack<Integer>(); 
        }
       
        /**
         * returns the number of vertices in the graph
         * @return an integer value of the vertices
         */
        public int V() 
        { 
            return V; 
        }
        /**
         * return the number of edges in the graph
         * @return an integer value of the edges
         */
        public int E() 
        { 
            return E; 
        }
        /**
         * adds the edges to the stack where each vertex adds to each vertex
         * list so it will become a undirected graph
         * @param v first vertex
         * @param w second vertex
         */
        public void addEdge(int v, int w)
        {
            adj[v].push(w); 
            adj[w].push(v); 
            E++;
        }
        /**
         * iterated through the adjacency lists
         * @param v is the vertex
         * @return an iterable stack
         */
        public Iterable<Integer> adj(int v)
        { 
            return adj[v]; 
        }
        
        @Override
        public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
