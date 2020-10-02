
package searchinglab3;


/* *****************************************************************************
 *  Name:    Frida Johansson
 * 
 *  Description: A Binary search tree where the leftmost key represents the 
 *               smallest key and the rightmost the largest.
 *
 *  ReadMe:      This BST has a dependency of GeneralizedQueue.java in this
 *               package.
 *
 *  Written:       2020-09-23
 *  Last updated:  2020-09-27
 *
 **************************************************************************** */
public class BinarySearchTree<Key extends Comparable<Key>, Value>
{
    private Node root; 
    
    private class Node
    {
        private final Key key; 
        private Value val; 
        private Node left, right; 
        private int N;
        private int [] positions = new int[1];
        int pos;
        
        public Node(Key key, Value val, int N)
        { 
            this.key = key; 
            this.val = val; 
            this.N = N; 
        }
        public Node(Key key, Value val, int N, int charcount)
        { 
            this.key = key; 
            this.val = val; 
            this.N = N;
            this.positions[pos] = charcount;
            
        }
        
    }
    public int size()
    { 
        return size(root); 
    }
    private int size(Node x)
    {
    if (x == null) 
        return 0;
    else 
        return x.N;
    }
    /*---------------------------------------------------------
     *  Following 2 methods get:
     *  First, gets key and calls second method get with parameters; root and key.
     *  Second, returns the value associated with key in the subtree rooted at x.
     *---------------------------------------------------------*/
    
    /**
     * get method.
     * Searches the tree for parameter key and 
     * returns the value if it is found.
     * @param key
     * @return 
     */
    public Value get(Key key)
    { 
        return get(root, key); 
    }
    private Value get(Node x, Key key)
    { 
        if (x == null) 
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) 
            return get(x.left, key);
        else if (cmp > 0) 
            return get(x.right, key);
        else 
            return x.val;
    }
    /*---------------------------------------------------------
     *  Following 2 methods getPositions (for Task4):
     *  returns the positions in a text file of the key word
     *---------------------------------------------------------*/    
    /**
     * getPositions (For task 4).
     * get the positions of the specified key in a text file.
     * @param key represents the searched keyword
     * @return the charpositions from beginning of the text when keyword occure
     */
    public int[] getPositions(Key key)
    { 
        return getPositions(root, key); 
    }
    private int[] getPositions(Node x,Key key)
    {
        if (x == null) 
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) 
            return getPositions(x.left, key);
        else if (cmp > 0) 
            return getPositions(x.right, key);
        else 
            return x.positions;
    }
    /*---------------------------------------------------------
     *  Following 2 methods put:
     *  First, search for key and updates the value if it is found.
     *  Second, Change key’s value to val if key in subtree rooted at x.
     *  Otherwise, add new node to subtree associating key with val.
     *---------------------------------------------------------*/    
    
    /**
     * put method.
     * Puts the key in right position in tree. 
     * If key is already in the tree the value of the key will be updated.
     * @param key
     * @param val 
     */
    public void put(Key key, Value val)
    { 
     root = put(root, key, val);
    }
    
    private Node put(Node x, Key key, Value val)
    {
     if (x == null) 
         return new Node(key, val, 1);
     int compare = key.compareTo(x.key);
     if (compare < 0) //key < root[key]
         x.left = put(x.left, key, val);
     else if (compare > 0) //key > root[key]
         x.right = put(x.right, key, val);
     else 
         x.val = val;
     
     x.N = size(x.left) + size(x.right) + 1;
     
     return x;
    }
    
    /*---------------------------------------------------------
     *  Following 2 methods put for Task4, added the positions 
     *  of the text charcount.
     *  First, search for key and updates the value if it is found.
     *  Second, Change key’s value to val if key in subtree rooted at x.
     *  Otherwise, add new node to subtree associating key with val.
     *---------------------------------------------------------*/    
    
    /**
     * put method.Puts the key in right position in tree, Task4. 
     * If key is already in the tree the value of the key will be updated.
     * @param key the word to be put in tree
     * @param val the value of the key
     * @param charcount the position in the text in number of chars. 
     */
    public void put(Key key, Value val, int charcount)
    { 
     root = put(root, key, val, charcount);
    }
    
    private Node put(Node x, Key key, Value val, int charcount)
    {
     if (x == null)
     {
         return new Node(key, val, 1,charcount);
     }
     int compare = key.compareTo(x.key);
     if (compare < 0) //key < root[key]
         x.left = put(x.left, key, val, charcount);
     else if (compare > 0) //key > root[key]
         x.right = put(x.right, key, val, charcount);
     else
     {
         x.val = val;
         x.N = size(x.left) + size(x.right) + 1;
 
         if(x.pos == (x.positions.length))
             resizeArrays(x.positions.length*2, x);
         
         x.positions[x.pos] = charcount;
         x.pos += 1;
     }
     return x;
    }
    
    
    private void resizeArrays(int newSize, Node x) 
    {
        int newPositions [] = new int[newSize];
            
            for (int i = 0; i < x.positions.length; i++) {
                int temp = x.positions[i];
                newPositions[i] = temp;
            }
            x.positions = newPositions;
            
    }
    
    /**
     * contains method.
     * 
     * @param key the key to be checked if it is in the tree
     * @return key if it is not equal to null.
     */
    public boolean contains(Key key) 
    {
        if (key == null)
            throw new IllegalArgumentException("Key is null.");
        return get(key) != null;
    }
    
    /*---------------------------------------------------------
     *  Following 4 methods min/max:
     *  Returns the min/max of tree by recursively search through tree.
     *---------------------------------------------------------*/
   
    /**
     * min method.
     * Gets the leftmost key of the tree
     * @return the minimum key of the tree
     */
    public Key min()
    {
        return min(root).key;
    }
    private Node min(Node x)
    {
        if (x.left == null) 
            return x;
        return min(x.left);
    }
    /**
     * max method.
     * Gets the rightmost key of the tree
     * @return the maximum key of the tree
     */
    public Key max()
    {
        return max(root).key;
    }
    private Node max(Node x)
    {
        if (x.right == null) 
            return x;
        return max(x.right);
    }
   
    /*---------------------------------------------------------
     *  Following 3 methods keys:
     *  Returns an iterable queue
     *---------------------------------------------------------*/
    /**
     * 
     * @return an iterable queue of all keys.
     */
    public Iterable<Key> keys()
    { 
        return keys(min(), max()); 
    }
    /**
     * keys method.
     * Inserts all keys in an iterable queue
     * @param lo represents the leftmost key
     * @param hi represents the rightmost key
     * @return an iterable queue within range lo and hi.
     */
    public Iterable<Key> keys(Key lo, Key hi)
    {
        GeneralizedQueue<Key> queue = new GeneralizedQueue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node x, GeneralizedQueue<Key> queue, Key lo, Key hi)
    {
        if (x == null) 
            return;
     int compareLo = lo.compareTo(x.key);
     int compareHi = hi.compareTo(x.key);
     if (compareLo < 0) 
         keys(x.left, queue, lo, hi);
     if (compareLo <= 0 && compareHi >= 0) 
         queue.enqueue(x.key);
     if (compareHi > 0) 
         keys(x.right, queue, lo, hi);
    }
    /**
     * toString method.
     * If the left/right node point to null it is represented as a '*'
     * @return this binary tree as a string representation of each key and its value.
     */
    @Override
    public String toString() 
    {
	return toString(root);
    }

    private String toString(Node T) 
    {
        StringBuilder sb = new StringBuilder();
	if(T == null ) 
            return "*";
        
        return "[" + toString(T.left) + "," + T.key + "," + toString(T.right) + "]";

    }
    /**
     * Depth First Traversal PostOrder
     * (Left, Right, Root)
     */
    public void printPostorder() 
    {
        printPostorder(root);
    }
    private void printPostorder(Node node) 
    { 
        if (node == null) 
            return; 
        printPostorder(node.left); 
        printPostorder(node.right); 
        System.out.print(node.key + " "); 
    } 
    /**
     * Depth First Traversal InOrder
     * (Left, Root, Right)
     */
    public void printInOrder() 
    {
        printInorder(root);
    }
    
    private void printInorder(Node node) 
    { 
        if (node == null) 
            return; 
        printInorder(node.left); 
        System.out.print(node.key + " "); 
        printInorder(node.right); 
    } 
    /**
     * Depth First Traversal PreOrder
     * (Root, Left, Right) 
     */
    public void printPreOrder() 
    {
        printPreorder(root);
    }

    private void printPreorder(Node node) 
    { 
        if (node == null) 
            return; 
        System.out.print(node.key + " "); 
        printPreorder(node.left); 
        printPreorder(node.right); 
    } 

}
