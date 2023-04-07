public class DoubleArraySeq {

  // Invariant of the DoubleArraySeq class:
  //   1. The number of elements in the seqeunces is in the instance variable
  //      manyItems.
  //   2. For an empty sequence (with no elements), we do not care what is
  //      stored in any of data; for a non-empty sequence, the elements of the
  //      sequence are stored in data[0] through data[manyItems-1], and we
  //      don�t care what�s in the rest of data.
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

    manyItems = 0;
    currentIndex = 0;
    data = new double[INITIAL_CAPACITY];
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
      throw new IllegalArgumentException
          ("The initialCapacity is negative: " + initialCapacity);
    }

    manyItems = 0;
    currentIndex = 0;
    data = new double[initialCapacity];
  }
}
