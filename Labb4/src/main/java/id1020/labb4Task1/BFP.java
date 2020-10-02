/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id1020.labb4Task1;


/**
 * paths holds the path from start to a specified vertex
 * distance holds the length between vertices counting from start
 * 
 * @author Frida Johansson
 */
public class BFP
{
    private ST<String, String>  paths = new ST<String, String>();
    private ST<String, Integer> distance = new ST<String, Integer>();

    /**
     * BreadthFirstPaths checks all paths from start and adds 
     * 1 to the distance if paths gets further away while checking each
     * vertex on the way. 
     * @param graph the graph
     * @param start represent the startingpoint
     */
    public BFP(UndirectedGraph graph, String start) {

        GeneralizedQueue<String> queue = new GeneralizedQueue<String>();
        distance.put(start, 0); // 0 "steps" from starting point
        
        queue.enqueue(start);
        while (!queue.isEmpty()) 
        {
            String v = queue.dequeueElement();
            for (String w : graph.adjacentTo(v)) 
            {
                if (!distance.contains(w)) 
                {
                    queue.enqueue(w);
                    paths.put(w, v);
                    distance.put(w, distance.get(v)+1);
                }
            }
        }
        
    }

    /**
     * hasPathTo checks if there is a path to specified vertex
     * @param v represents the vertex destination
     * @return true or false depending if there exists a path
     */
    public boolean hasPathTo(String v) 
    {
        return distance.contains(v);
    }

    /**
     * distanceTo return the length to the destination v
     * @param v represents the destination
     * @return the distance if a path, if not it returns 0
     */
    public int distanceTo(String v) {
        if (!hasPathTo(v)) 
            return 0;
        return distance.get(v);
    }

    /**
     * pathTo calls get(and also rank) methods in symbolTable to
     * 
     * @param goal 
     * @return an iterable String 
     */
    public Iterable<String> pathTo(String goal) 
    {
        Stack<String> path = new Stack<String>();
        if(!hasPathTo(goal))
            System.out.println("The path to " + goal + " does not exist!");
        while (distance.contains(goal)) 
        {
           
            path.push(goal);
            goal = paths.get(goal);
            
            if(goal == null)
                break;
        }
        return path;
    }
    
    
}

