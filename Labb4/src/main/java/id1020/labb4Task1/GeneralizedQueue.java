package id1020.labb4Task1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* *****************************************************************************
 *  Name:    Frida Johansson
 * 
 *  Description:  Generalized queue of a generic type to make 
 *  it reusable for other inputs.
 *  @param <Element> represents a generic type that's being added to this queue.
 *
 *  Written:       2020-09-23
 *  Last updated:  2020-09-23
 *
 **************************************************************************** */
public class GeneralizedQueue<Element> implements Queue<Element>
{
    private Node first;
    private Node last;
    private int size;
    
    public GeneralizedQueue()
    {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    
    /**
     * Inserts element in end of the queue 
     * @param element represents inserted element
     */
    @Override
    public void enqueue(Element element) {
        Node newNode = new Node(element);
        if (first == null)
        {
            newNode.setNext(newNode);
            first = last = newNode;
            last.setNext(null);
        }
        else
        {
            Node oldLast = last;
            last = newNode;
            last.setNext(null);
            oldLast.next = last;
   
        }
        size++;
    }
    
    /**
     * Removes the last element of the queue.
     */
    @Override
    public void dequeue() {
        switch (size) 
        {
            case 0:
                System.out.println("List is empty");
                break;
            case 1:
                first = null;
                last = null;
                size = 0;
                break;
            default:
                
            Node current = first;
            
            while(current.next != last)
            {
                current = current.next;
                
            }
            
            last = current;
            last.setNext(null); 
            first.setNext(first.getNext());
            size--;
            break;
        }   
        
    }
    
    public Element dequeueElement() 
    {
        Node node = last;
        switch (size) 
        {
            case 0:
                System.out.println("List is empty");
                break;
            case 1:
                first = null;
                last = null;
                size = 0;
                break;
            default:
                
            Node current = first;
            
            while(current.next != last)
            {
                current = current.next;
                
            }
            
            last = current;
            last.setNext(null); 
            first.setNext(first.getNext());
            size--;
            break;
        }   
        return node.getElement();
    }
    
    /**
     * Removed an element on a certain position given by the parameter pos.
     * @param pos represents the position in queue where to remove an element.
     * @return a stringrepresentation of the removed element.
     */
    public String dequeueAtPosition(int pos)
    {
        pos = pos -1;
        String removed = "";
        if (this.size == 0) 
        {
            System.out.println("List is empty.");
	}

        else if (pos < 0 || pos >= this.size) {
                System.out.println("Invalid Index.");
                
        }

        else if (pos == 0) 
        {
            Node firstNode = first.getNext();
            first = null;
            first = firstNode;
            this.size --;
           // dequeue();
        } 
        else if (pos == this.size) 
        {
            dequeue();

        }
        else 
        {
            Node nodeBefore = getNodeAt(pos - 1);
            Node removedNode = nodeBefore.next;
            Node nodeAfterRemoved = removedNode.next;
            removed = removedNode.toString();
            nodeBefore.next = nodeAfterRemoved;
            this.size--;
        }
        return removed;
    }
    
    private Node getNodeAt(int index)  
    {
        Node temp = this.first;
        for (int i = 1; i <= index; i++) {
                temp = temp.next;
        }
        return temp;

    }
    
    @Override
    public String peek() {
        
        if(first != null) 
        {
            return last.element.toString();
        }
        return null;    }
    
    /**
     * contains method.
     * 
     * @param key the key to be checked if it is in the tree
     * @return key if it is not equal to null.
     */
    public boolean contains(Element element) 
    {
        if (element == null)
            throw new IllegalArgumentException("Key is null.");
        return get(element) != null;
    }
    
    public Element get(Element element){
        for(Element e : this)
            if(e.equals(element))
                return element;
        return null;
    }
    
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int getSize() {
        return size;
    }
    @Override
    public String toString() 
    {
       StringBuilder sb = new StringBuilder();
       int index = 0;

       for(Element e : this)
       {
           sb.append("[").append(e).append("]");
           index++;
           if(index < size)
           {
               sb.append(", ");
           }  
       }
        return sb.toString();
    }
   
   
    /**
     * Implemented Iterator that creates a private iterator for this queue that starts at current.
     * @return 
     */
    @Override
    public Iterator<Element> iterator() 
    {
        return new QueueIterator<Element>(first);
    }
    private class QueueIterator<Element> implements Iterator<Element>
    {
 
        private GeneralizedQueue<Element>.Node current;
         
        public QueueIterator(GeneralizedQueue<Element>.Node first) 
        {
            super();
            this.current = first;
        }
 
        @Override
        public boolean hasNext() 
        {
            return current != null;
                  
        }
 
        @Override
        public Element next() 
        {
            if(current != null)
            {
                
                Element element = current.getElement();
                current = current.getNext();
                return element;
            }
              
            else
                throw new NoSuchElementException ("element doesent exist");
            
        }       
    }
    /**
     * Nested privated class Node that represents the content of the queue. 
     * 
     */
    private class Node {

    private Node next;
    private Element element;
   

    public Node(Element e){
        element = e;
        
    }
    public Node(Element e, Node n)
    {
        next = n;
        element = e;
        
    }

    public Element getElement() {
        return element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    @Override
    public String toString()
    {
        return "[ " + element + " ] ";
    }
}
}
