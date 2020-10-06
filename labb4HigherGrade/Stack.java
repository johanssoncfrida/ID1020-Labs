
package id1020.labb4HigherGrade;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* *****************************************************************************
 *  Name:    Frida Johansson
 *  Course:  ID1020
 *  Class:   Stack
 * 
 *  Description:  LIFO-stack of generic type 
 *                @param <T> represents a generic type that's being added to 
 *                this queue.
 *
 *  Written:       2020-09-09
 *  Last updated:  2020-10-05
 *
 **************************************************************************** */

/**
 * Class representing a LIFO-Stack and its associated methods.
 * @author Frida Johansson
 */
public class Stack<T> implements Iterable <T> 
{
    private Node <T> first;
    private int size;
    
    private class Node<T>
    {
        private T item;
        private Node <T> next;

    }

    public Stack()
    {
        first = null;
        size = 0; 
    }
    /**
     * returns the top node in the stack
     * @return the generic <T> type of the node
     */
    public T peek() 
    {
        if (isEmpty()) 
            throw new NoSuchElementException("Stack is empty");
        return first.item;
    }
    /**
     * Checks if the item <T> is in the stack
     * @param t is the item <T> stored in the stack 
     * @return a boolean value depending if stack contains the <T> item
     */ 
    public boolean contains(T t) 
    {
        if (t == null)
            throw new IllegalArgumentException("Key is null.");
        return get(t) != null;
    }
    /**
     * Iterates through the stack and returns item <T> if its in the stack,
     * otherwise returns null
     * @param t the item <T>
     * @return the item <T> or null
     */
    public T get(T t)
    {
        for(T e : this)
            if(e.equals(t))
                return t;
        return null;
    }
    /**
     * Push adds the users input on top of the stack and size of stack increases
     * @param item represents the added item of generic type.
     */
    public void push(T item)
    {
        
        Node<T> oldFirst = first;
        first = new Node<T>();
        first.item = item;
        first.next = oldFirst;
        size ++;
    }
    /**
     * Pop removes the top of the stack, decrementing size.
     * @return <T> item that is removed.
     */
    public T pop()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("Stack already empty");
        }
        T item = first.item;
        first = first.next;
        size--;
        return item;
        
    }
    /**
     * Checks if stack is empty
     * @return true or false depending if stack is empty.
    **/
    public boolean isEmpty()
    {
        return first == null;
    }
    /**
     * Gets the size of the stack
     * @return size of the stack
     */
    public int size()
    {
        return size;
    }
    

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        
        int counter = 0;
        for(T item: this)
        {
            sb.append(item);
            
        }
        
        
        
        return sb.toString();
    }

    @Override
     public Iterator<T> iterator() 
     {
        return new LinkedIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<T> 
    {
        private Node<T> current;

        public LinkedIterator(Node<T> first) 
        {
            current = first;
        }

        @Override
        public boolean hasNext() 
        {
            return current != null;
        }

        @Override
        public void remove() 
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public T next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next; 
            return item;
        }

    }
}
