
package id1020.labb4Task2;

/* *****************************************************************************
 *  Name:    Frida Johansson
 * 
 *  Description: A system table implemented where keys and corresponding values 
 *               are stored in arrays. The keys in the key-array are ordered in
 *               and lexiographical order. Only the necessary methods for this 
 *               task are included. The main method includes a frequency counter 
 *               of keys and a clock to check the most repeated key and to check 
 *               the time it takes to put all keys in this ST.
 *  
 *  ReadMe:      This class has a dependency of the Stack.java
 *
 *  Written:       2020-09-22
 *  Last updated:  2020-09-25
 *
 **************************************************************************** */
@SuppressWarnings("unchecked")
public class ST<Key extends Comparable<Key>, Value >
{
    private Key[] keys;
    private Value[] vals;
    private int N;
 
    public ST()
    { 
        keys = (Key[]) new Comparable[1];
        vals = (Value[]) new Object[1];
        N = 0;
    }
    public int size()
    { 
        return N; 
    }
    /**
     * gets the value of the specified key.
     * @param key represents the key that is searched for its 
     * corresponding value
     * @return the keys value
     */
    public Value get(Key key)
    {
        
        int i = rank(key);
        
        if (i < N && keys[i].compareTo(key) == 0)
        {
            
            return vals[i];
        }

        else 
            return null;
    }
    public boolean isEmpty()
    {
        return keys == null;
    }
    /**
     * Method rank.
     * The rank compute the number of keys using binary search in the table that 
     * are smaller than key. The method Compares this key with the key in the 
     * middle: if it is equal, return its index; if it is less, look in the left 
     * half; if it is greater, look in the right half.
     * @param key this specified key
     * @return return the index of the key
     */
    public int rank(Key key)
    {
        int lo = 0, hi = N-1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) //key < keys[mid]
                hi = mid - 1;
            else if (cmp > 0) // key > keys[mid 
                lo = mid + 1;
            else 
                return mid;
        }
        return lo; 
    }
    /**
     * puts the key with it's value in the correct place.
     * Checks firstly if it is null, then puts the key with its value in the
     * correct place and if needed resize the array.
     * @param key represents the key to be added
     * @param val the keys value
     */
    public void put(Key key, Value val)
    { 
        if (key == null)
                throw new IllegalArgumentException("Key is null.");

            int pos = rank(key);
            
            if ((pos < N) && keys[pos].compareTo(key) == 0) {
                vals[pos] = val;
                return;
            }

            if (N == keys.length)
                resizeArrays( 2 * keys.length);
            
            for (int i = N; i > pos; i--) 
            {
                
                keys[i] = keys[i - 1];
                vals[i] = vals[i - 1];
            }

            keys[pos] = key;
            vals[pos] = val;
            
            N++;
        }
  
    /**
     * resizeArrays.
     * Makes the array size 2 times bigger
     * @param newSize a x2 size of array
     */
    private void resizeArrays(int newSize) 
    {
            Key[] newKeyArray = (Key[]) new Comparable[newSize];
            Value[] newValueArray = (Value[]) new Object[newSize];
            
            for (int i = 0; i < N; i++) 
            {
                newKeyArray[i] = keys[i];
                newValueArray[i] = vals[i];
            }
            keys = newKeyArray;
            vals = newValueArray;
    }
    /**
     * contains.
     * Checks if the key already exists in the array.
     * @param key the key to be checked
     * @return the found key, otherwise null.
     */
    public boolean contains(Key key)
    {

        if(key == null)
            throw new IllegalArgumentException ("Key is null");
        return get(key) != null;
    }
    /*---------------------------------------------------------
     *  Following 2 methods keys:
     *  Returns an iterable stack
     *---------------------------------------------------------*/
    
    /**
     * 
     * @return an iterable stack of all keys.
     */
    public Iterable<Key> keys() 
    {
        return keys(keys[0], keys[N-1]);
    }
    
    private Iterable<Key> keys(Key low, Key high) 
    {
            if (low == null)
                throw new IllegalArgumentException("Low key is null.");
            if (high == null)
                throw new IllegalArgumentException("High key is null.");

            Stack<Key> stack = new Stack<Key>();
            if (low.compareTo(high) > 0)
                return stack;
            for (int i = rank(low); i <= rank(high); i++)
            {
                
                stack.push(keys[i]);
            }
            return stack;
    }
    
    
    
    @Override
    public String toString() 
    {
       StringBuilder sb = new StringBuilder();
       int index = 0;
       for(Key e : keys)
       {
           sb.append("[").append(e).append("]");
           index++;
           if(index < N)
           {
               sb.append(", ");
           }  
       }
        return sb.toString();
    }
}
 