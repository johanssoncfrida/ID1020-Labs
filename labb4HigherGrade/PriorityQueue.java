
package id1020.labb4HigherGrade;

import java.util.Iterator;

/**
 *
 * @author Me
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<Key extends Comparable<Key>> 
{
    private int maxElements;
    private int sizeOfPQ;
    private int[] priorityValues; //keys corresponding priority value. keys are sorted with a priority index in the array
    private Key[] keys; //by heapindicies you get corresponding key value
    private int[] positionInHeap; //by keys index in the array you get the position in the binary heap
    
    /**
     * Constructor of PriorityQueue.
     * Creates the arrays with the size of vertices in the graph
     * Populated the positions in the binary heap with -1 to simplify the contains method
     * and this is due to the comparable interface does not accept null.
     * @param maxElements 
     */
    public PriorityQueue(int maxElements)
    {
        if(maxElements < 0)
            throw new IllegalArgumentException("Must be a positive number");
        
        this.maxElements = maxElements;
        keys = (Key[]) new Comparable[maxElements];
        priorityValues = new int[maxElements];
        positionInHeap = new int[maxElements];
        
        for(int i = 0; i < maxElements; i++)
            positionInHeap[i] = -1;
        sizeOfPQ = 0;
    }
    
    /**
     * return the size of this queue
     * @return integer value of the size
     */
    public int size()
    {
        return sizeOfPQ;
    }
    
    /**
     * returns true or false depending if the queue is empty or not
     * @return a boolean value:
     * true  : empty 
     * false : not empty
     */
    public boolean isEmpty()
    {
        return sizeOfPQ == 0;
    }
    
    /**
     * Due to the positions in the binary heap array is populated at the 
     * beginning with -1 we know that if it is not -1 the parameter vertex 
     * is in the binary heap.
     * @param i the specified vertex which corresponds to the index in 
     * the binary heap
     * @return a boolean value
     * true  : is in the binary heap
     * false : is not in the binary heap
     */
    public boolean contains(int i)
    {
        return positionInHeap[i] != -1;
    }
    /**
     * Inserts a new key in the priority queue and uses the swim function
     * to rearrange the order if the binary heap has been violated.
     * @param i the index vertex
     * @param key the weight of the graph
     */
    public void insert(int i, Key key)
    {
        sizeOfPQ++;
        positionInHeap[i] = sizeOfPQ;
        priorityValues[sizeOfPQ] = i;
        keys[i] = key;
       
        swim(sizeOfPQ);
        
    }
    /**
     * This method deletes the key with the highest priority.
     * To avoid a hole in the binary heap it change the position of the
     * highest priority key with the lowest priority key. After it uses the
     * sink function to restore the order of the binary heap if the priorities
     * are not in order.
     * @return int index of the miniumkey
     */
    public int deleteMinKey()
    {
        int min = priorityValues[1];
        changePosition(1,sizeOfPQ--);
        positionInHeap[min] = -1;
        priorityValues[sizeOfPQ + 1] = -1;
        keys[min] = null;
        
        sink(1);
        return min;
    }
    
    /**
     * method that changes the positions in the binary heap.
     * 
     * @param i the parent
     * @param j the child
     */
    private void changePosition(int i, int j)
    {
        int temp = priorityValues[i];
        priorityValues[i] = priorityValues[j];
        priorityValues[j] = temp;
        positionInHeap[priorityValues[i]] = i;
        positionInHeap[priorityValues[j]] = j;
    }
    /**
     * Simply changes the key.
     * This is used when the djikstras algorithm finds a key
     * that already exists in the queue but is of higher priority. The method
     * inserts the new key instead and corrects the positions in the binary heap
     * with the swim function.
     * @param i
     * @param key 
     */
    public void changeKey(int i, Key key)
    {
        keys[i] = key;
        swim(positionInHeap[i]);
    }
    /**
     * This method restores the positions in the binary heap so
     * the priorities are in order.
     * The K represents the parent and by multiplying k with 2
     * you get it's children. The method continues to compare
     * the parent's two children and changes the position depending on the
     * boolean return value from greater.
     * @param k the parent, i.e. the vertex on the position of the highest
     * priority, not necessarily the vertex with the highest priority.
     */
    private void sink(int k)
    {
        while (2*k <= sizeOfPQ) 
        {
            int j = 2*k;
            if (j < sizeOfPQ && greater(j, j+1)) 
                j++;
            if (!greater(k, j)) 
                break;
            changePosition(k, j);
            k = j;
        }
    }
    /**
     * checks if the parent index is greater than the child
     * and will change the position in the binary heap if it is.
     * @param pos the index
     */
    private void swim(int pos)
    {
        while(pos > 1 && greater(pos/2,pos))
        {
            changePosition(pos, pos/2);
            pos = pos/2;
        }
    }
    /**
     * Checks if one priority at given index is greater than the other 
     * of the other index.
     * @param i index1 
     * @param j argIndex
     * @return a boolean value
     * true: if index1 > argIndex
     * false: if argIndex > index1
     */
    private boolean greater(int i, int j)
    {
        
        return keys[priorityValues[i]].compareTo(keys[priorityValues[j]])>0;
       
    }

}
