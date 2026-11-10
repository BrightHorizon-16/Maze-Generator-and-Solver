public class StackReferenceBased implements StackInterface
{
  private Node top;

  public StackReferenceBased()
  {
    top = null;
  }  // end default constructor
  //============================================================================
  //============================================================================
  //============================================================================

  public boolean isEmpty()
  {
    return top ==  null;
  }  // end isEmpty
//============================================================================
//============================================================================
//============================================================================

  public void push(Object newItem)
  {
    top = new Node(newItem, top);
  }  // end push
  //============================================================================
  //============================================================================
  //============================================================================

  public Object pop() throws StackException
  {
    if (!isEmpty())
    {
      Node temp = top;
      top = top.getNext();
      return temp.getItem();
    }
    else
    {
      throw new StackException("StackException on " + "pop: stack empty");
    }  // end if
  }  // end pop
  //============================================================================
  //============================================================================
  //============================================================================

  public void popAll()
  {
    top = null;
  }  // end popAll

//============================================================================
//============================================================================
//============================================================================
  public Object peek() throws StackException
  {
    if (!isEmpty())
    {
      return top.getItem();
    }
    else
    {
      throw new StackException("StackException on " + "peek: stack empty");
    }  // end if

  } // end peek
//============================================================================
// Dispalys the stack vertically
public void displayStack()
  {
    if (isEmpty())
    {
      System.out.println("The Stack is empty");
      return;
    } // end if

    System.out.println("-----------");
    Node curr = top;
    while (curr != null)
    {
      if (curr == top)
      {
        System.out.println("| " + curr.getItem() + "   <-- TOP");
      }
      else
      {
        System.out.println("| " + curr.getItem());
      }    //end if
      curr = curr.getNext();  //Downward movement
    } //end while
    System.out.println("-----------");
  } // end of displayStack

//============================================================================
//============================================================================

}  // end StackReferenceBased