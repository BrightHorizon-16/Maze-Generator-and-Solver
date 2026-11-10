public interface QueueInterface
{

  public boolean isEmpty();
  

  public void enqueue(Object newItem) throws QueueException;
  // Adds an item at the back of a queue.
  
  public Object dequeue() throws QueueException;
  // Retrieves and removes the front of a queue.
  

  public void dequeueAll();
  // Removes all items of a queue.

  public Object peek() throws QueueException;
  // Retrieves the item at the front of a queue.

} 