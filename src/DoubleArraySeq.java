public class DoubleArraySeq implements Cloneable {

  // Invariant of the DoubleArraySeq class:
  //   1. The number of elements in the seqeunces is in the instance variable
  //      manyItems.
  //   2. For an empty sequence (with no elements), we do not care what is
  //      stored in any of data; for a non-empty sequence, the elements of the
  //      sequence are stored in data[0] through data[manyItems-1], and we
  //      don't care what's in the rest of data.
  //   3. If there is a current element, then it lies in data[currentIndex];
  //      if there is no current element, then currentIndex equals manyItems.
  private double[] data;
  private int manyItems;
  private int currentIndex;

  /**
   * Initialize an empty sequence with an initial capacity of 10.  Note that the addAfter and
   * addBefore methods work efficiently (without needing more memory) until this capacity is
   * reached.
   *
   * @throws OutOfMemoryError Indicates insufficient memory for: new double[10].
   * @postcondition This sequence is empty and has an initial capacity of 10.
   **/
  public DoubleArraySeq() {
    final int INITIAL_CAPACITY = 10;

    data = new double[INITIAL_CAPACITY];
    manyItems = 0;
    currentIndex = 0;
  }

  /**
   * Initialize an empty sequence with a specified initial capacity. Note that the addAfter and
   * addBefore methods work efficiently (without needing more memory) until this capacity is
   * reached.
   *
   * @param initialCapacity the initial capacity of this sequence
   * @throws IllegalArgumentException Indicates that initialCapacity is negative.
   * @throws OutOfMemoryError         Indicates insufficient memory for: new
   *                                  double[initialCapacity].
   * @precondition initialCapacity is non-negative.
   * @postcondition This sequence is empty and has the given initial capacity.
   **/
  public DoubleArraySeq(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("The initialCapacity is negative: " + initialCapacity);
    }

    data = new double[initialCapacity];
    manyItems = 0;
    currentIndex = 0;
  }

  /**
   * Add a new element to this sequence, after the current element. If the new element would take
   * this sequence beyond its current capacity, then the capacity is increased before adding the new
   * element.
   *
   * @param element the new element that is being added
   * @throws OutOfMemoryError Indicates insufficient memory for increasing the sequence's capacity.
   * @postcondition A new copy of the element has been added to this sequence. If there was a
   * current element, then the new element is placed after the current element. If there was no
   * current element, then the new element is placed at the end of the sequence. In all cases, the
   * new element becomes the new current element of this sequence.
   * @note An attempt to increase the capacity beyond Integer.MAX_VALUE will cause the sequence to
   * fail with an arithmetic overflow.
   **/
  public void addAfter(int element) {
    if (manyItems == data.length) {
      ensureCapacity((manyItems + 1) * 2);
    }

    if (currentIndex != manyItems) {
      for (int index = manyItems; index > currentIndex + 1; index--) {
        data[index] = data[index - 1];
      }

      currentIndex += 1;
      data[currentIndex] = element;
      manyItems += 1;

      return;
    }

    data[currentIndex] = element;
    manyItems += 1;
  }

  /**
   * Add a new element to this sequence, before the current element. If the new element would take
   * this sequence beyond its current capacity, then the capacity is increased before adding the new
   * element.
   *
   * @param element the new element that is being added
   * @throws OutOfMemoryError Indicates insufficient memory for increasing the sequence's capacity.
   * @postcondition A new copy of the element has been added to this sequence. If there was a
   * current element, then the new element is placed before the current element. If there was no
   * current element, then the new element is placed at the start of the sequence. In all cases, the
   * new element becomes the new current element of this sequence.
   * @note An attempt to increase the capacity beyond Integer.MAX_VALUE will cause the sequence to
   * fail with an arithmetic overflow.
   **/
  public void addBefore(int element) {
    if (manyItems == data.length) {
      ensureCapacity((manyItems + 1) * 2);
    }

    if (currentIndex != manyItems) {
      for (int index = manyItems; index > currentIndex; index--) {
        data[index] = data[index - 1];
      }

      data[currentIndex] = element;
      manyItems += 1;

      return;
    }

    data[currentIndex] = element;
    manyItems += 1;
  }

  /**
   * Place the contents of another sequence at the end of this sequence.
   *
   * @param addend a sequence whose contents will be placed at the end of this sequence
   * @throws NullPointerException Indicates that addend is null.
   * @throws OutOfMemoryError     Indicates insufficient memory to increase the size of this
   *                              sequence.
   * @precondition The parameter, addend, is not null.
   * @postcondition The elements from addend have been placed at the end of this sequence. The
   * current element of this sequence remains where it was, and the addend is also unchanged.
   * @note An attempt to increase the capacity beyond Integer.MAX_VALUE will cause an arithmetic
   * overflow that will cause the sequence to fail.
   **/
  public void addAll(DoubleArraySeq addend) {
    ensureCapacity((this.manyItems + 1) * 2);

    System.arraycopy(addend.data, 0, this.data, this.manyItems, addend.manyItems);
    this.manyItems += addend.manyItems;
  }


  /**
   * Move forward, so that the current element is now the next element in this sequence.
   *
   * @throws IllegalStateException Indicates that there is no current element, so advance may not be
   *                               called.
   * @precondition isCurrent() returns true.
   * @postcondition If the current element was already the end element of this sequence (with
   * nothing after it), then there is no longer any current element. Otherwise, the new element is
   * the element immediately after the original current element.
   **/
  public void advance() {
    if (!isCurrent()) {
      throw new IllegalStateException("There is no current element, so advance may not be called.");
    }

    currentIndex += 1;
  }

  /**
   * Generate a copy of this sequence.
   *
   * @return The return value is a copy of this sequence. Subsequent changes to the copy will not
   * affect the original, nor vice versa.
   * @throws OutOfMemoryError Indicates insufficient memory for creating the clone.
   **/
  public DoubleArraySeq clone() {  // Clone a DoubleArraySeq object.
    DoubleArraySeq answer;

    try {
      answer = (DoubleArraySeq) super.clone();
    } catch (
        CloneNotSupportedException e) {
      throw new RuntimeException
          ("This class does not implement Cloneable");
    }

    answer.data = data.clone();

    return answer;
  }

  /**
   * Create a new sequence that contains all the elements from one sequence followed by another.
   *
   * @param s1 the first of two sequences
   * @param s2 the second of two sequences
   * @return a new sequence that has the elements of s1 followed by the elements of s2 (with no
   * current element)
   * @throws NullPointerException Indicates that one of the arguments is null.
   * @throws OutOfMemoryError     Indicates insufficient memory for the new sequence.
   * @precondition Neither s1 nor s2 is null.
   * @note An attempt to create a sequence with a capacity beyond Integer.MAX_VALUE will cause an
   * arithmetic overflow that will cause the sequence to fail.
   **/
  public static DoubleArraySeq catenation(DoubleArraySeq s1, DoubleArraySeq s2) {
    DoubleArraySeq newSequence = new DoubleArraySeq(s1.manyItems + s2.manyItems);

    System.arraycopy(s1.data, 0, newSequence.data, 0, s1.manyItems);
    System.arraycopy(s2.data, 0, newSequence.data, s1.manyItems, s2.manyItems);

    newSequence.manyItems = s1.manyItems + s2.manyItems;
    newSequence.currentIndex = newSequence.manyItems;

    return newSequence;
  }

  /**
   * Change the current capacity of this sequence.
   *
   * @param minimumCapacity the new capacity for this sequence
   * @throws OutOfMemoryError Indicates insufficient memory for: new int[minimumCapacity].
   * @postcondition This sequence's capacity has been changed to at least minimumCapacity. If the
   * capacity was already at or greater than minimumCapacity, then the capacity is left unchanged.
   **/
  public void ensureCapacity(int minimumCapacity) {
    double[] biggerArray;

    if (data.length < minimumCapacity) {
      biggerArray = new double[minimumCapacity];
      System.arraycopy(data, 0, biggerArray, 0, manyItems);
      data = biggerArray;
    }
  }

  /**
   * Accessor method to get the current capacity of this sequence. The add method works efficiently
   * (without needing more memory) until this capacity is reached.
   *
   * @return the current capacity of this sequence
   **/
  public int getCapacity() {
    return data.length;
  }

  /**
   * Accessor method to get the current element of this sequence.
   *
   * @return the current element of this sequence
   * @throws IllegalStateException Indicates that there is no current element, so getCurrent may not
   *                               be called.
   * @precondition isCurrent() returns true.
   **/
  public double getCurrent() {
    if (!isCurrent()) {
      throw new IllegalStateException(
          "There is no current element, so getCurrent may not be called.");
    }

    return data[currentIndex];
  }

  /**
   * Accessor method to determine whether this sequence has a specified current element that can be
   * retrieved with the getCurrent method.
   *
   * @return true (there is a current element) or false (there is no current element at the moment)
   **/
  public boolean isCurrent() {
    return !(currentIndex == manyItems);
  }

  /**
   * Remove the current element from this sequence.
   *
   * @throws IllegalStateException Indicates that there is no current element, so removeCurrent may
   *                               not be called.
   * @precondition isCurrent() returns true.
   * @postcondition The current element has been removed from this sequence, and the following
   * element (if there is one) is now the new current element. If there was no following element,
   * then there is now no current element.
   **/
  public void removeCurrent() {
    if (!isCurrent()) {
      throw new IllegalStateException(
          "There is no current element, so removeCurrent may not be called.");
    }

    for (int index = currentIndex; index < manyItems; index++) {
      data[index] = data[index + 1];
    }

    manyItems -= 1;
  }

  /**
   * Determine the number of elements in this sequence.
   *
   * @return the number of elements in this sequence
   **/
  public int size() {
    return manyItems;
  }

  /**
   * Set the current element at the front of this sequence.
   *
   * @postcondition The front element of this sequence is now the current element (but if this
   * sequence has no elements at all, then there is no current element).
   **/
  public void start() {
    currentIndex = 0;
  }
}
