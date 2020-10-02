package id1020.labb4Task1;
/**
 * Represents a queue interface that inherits Iterable interface.
 * @author Frida Johansson
 * @param <Element>
 */
public interface Queue <Element> extends Iterable<Element>
{
    
    public void enqueue(Element element);
     
    public void dequeue();
     
    public String peek();
     
    public boolean isEmpty();
     
    public int getSize();

}
