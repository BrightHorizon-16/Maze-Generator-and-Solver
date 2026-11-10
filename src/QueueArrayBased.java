public class QueueArrayBased implements QueueInterface {
  private final int MAX_QUEUE;
  private Object[] items;
  private int front, back, count;

  public QueueArrayBased(int capacity) {
    if (capacity < 1){
      throw new IllegalArgumentException("Queue Capacity has to be ateast 1");
    }
    MAX_QUEUE = capacity;
    items = new Object[MAX_QUEUE];
    front = 0;
    back = MAX_QUEUE - 1;
    count = 0;
  }

  public boolean isEmpty() {
    return count == 0;
  }

  public boolean isFull() {
    return count == MAX_QUEUE;
  }

  public void enqueue(Object newItem) {
    if (isFull()) {
      throw new QueueException("QueueException on enqueue: Queue full");
    }
    back = (back + 1) % MAX_QUEUE;
    items[back] = newItem;
    count++;
  }

  public Object dequeue() throws QueueException {
    if (isEmpty()) {
      throw new QueueException("QueueException on dequeue: Queue empty");
    }
    Object queueFront = items[front];
    items[front] = null;
    front = (front + 1) % MAX_QUEUE;
    count--;
    return queueFront;
  }

  public void dequeueAll() {
    items = new Object[MAX_QUEUE];
    front = 0;
    back = MAX_QUEUE - 1;
    count = 0;
  }

  public Object peek() throws QueueException {
    if (isEmpty()) {
      throw new QueueException("Queue exception on peek: Queue empty");
    }
    return items[front];
  }


  public void displayQueue() {
    if (isEmpty()) {
      System.out.println("[FRONT/BACK] -> (empty)");
      return;
    }

    System.out.print("FRONT -> ");
    for (int i = 0; i < count; i++) {
      int index = (front + i) % MAX_QUEUE;
      System.out.print(items[index]);
      if (i < count - 1) {
        System.out.print(" -> ");
      }
    }
    System.out.println(" <- BACK");
  }
}
