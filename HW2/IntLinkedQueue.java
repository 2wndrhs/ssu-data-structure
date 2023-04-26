package HW2;

import java.util.NoSuchElementException;

public class IntLinkedQueue implements Cloneable {

  // Invariant of the IntLinkedQueue class:
  //   1. The number of items in the queue is stored in the instance variable
  //      manyNodes.
  //   2. The items in the queue are stored in a linked list, with the front
  //      of the queue stored at the head node, and the rear of the queue at
  //      the final node.
  //   3. For a non-empty queue, the instance variable front is the head
  //      reference of the linked list of items and the instance variable rear
  //      is the tail reference of the linked list. For an empty queue, both
  //      front and rear are the null reference.
  private int manyNodes;
  IntNode front;
  IntNode rear;


  /**
   * Initialize an empty queue.
   * <b>Postcondition:</b>
   * This queue is empty.
   **/
  public IntLinkedQueue() {
    front = null;
    rear = null;
  }


  /**
   * Generate a copy of this queue.
   *
   * @return The return value is a copy of this queue. Subsequent changes to the copy will not
   * affect the original, nor vice versa. Note that the return value must be type cast to an
   * <CODE>IntLinkedQueue</CODE> before it can be used.
   * @throws OutOfMemoryError Indicates insufficient memory for creating the clone.
   **/
  public Object clone() {  // Clone an IntLinkedQueue.
    IntLinkedQueue answer;
    IntNode[] cloneInfo;

    try {
      answer = (IntLinkedQueue) super.clone();
    } catch (CloneNotSupportedException e) {
      // This exception should not occur. But if it does, it would probably indicate a
      // programming error that made super.clone unavailable. The most comon error
      // The most common error would be forgetting the "Implements Cloneable"
      // clause at the start of this class.
      throw new RuntimeException
          ("This class does not implement Cloneable");
    }

    cloneInfo = IntNode.listCopyWithTail(front);
    answer.front = cloneInfo[0];
    answer.rear = cloneInfo[1];

    return answer;
  }


  /**
   * Get the front item, removing it from this queue.
   * <b>Precondition:</b>
   * This queue is not empty.
   *
   * @return The return value is the front item of this queue, and the item has been removed.
   * @throws NoSuchElementException Indicates that this queue is empty.
   **/
  public int getFront() {
    if (manyNodes == 0)
    // NoSuchElementException is from java.util and its constructor has no argument.
    {
      throw new NoSuchElementException("Queue underflow");
    }

    int answer = front.getData();
    front = front.getLink();
    manyNodes--;

    if (manyNodes == 0) {
      rear = null;
    }

    return answer;
  }


  /**
   * Put a new a new item in this queue.
   *
   * @param item the item to be pushed onto this queue
   *             <b>Postcondition:</b>
   *             The item has been pushed onto this queue.
   * @throws OutOfMemoryError Indicates insufficient memory for increasing the queue's capacity.
   *                          <b>Note:</b>
   *                          An attempt to increase the capacity beyond
   *                          <CODE>Integer.MAX_VALUE</CODE> will cause the queue to fail with an
   *                          arithmetic overflow.
   **/
  public void insert(int item) {
    if (isEmpty()) {  // Insert first item.
      front = new IntNode(item, null);
      rear = front;
    } else {  // Insert an item that is not the first.
      rear.addNodeAfter(item);
      rear = rear.getLink();
    }
    
    manyNodes++;
  }


  /**
   * Determine whether this queue is empty.
   *
   * @return <CODE>true</CODE> if this queue is empty;
   * <CODE>false</CODE> otherwise.
   **/
  public boolean isEmpty() {
    return (manyNodes == 0);
  }


  /**
   * Accessor method to determine the number of items in this queue.
   *
   * @return the number of items in this queue
   **/
  public int size() {
    return manyNodes;
  }

}
