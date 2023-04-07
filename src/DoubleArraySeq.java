public class DoubleArraySeq {

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
    ensureCapacity((manyItems + 1) * 2);

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
    boolean isCurrent = !(currentIndex == manyItems);
    if (!isCurrent) {
      throw new IllegalStateException("There is no current element, so advance may not be called.");
    }

    currentIndex += 1;
  }
}
