package HW3;

import java.util.Arrays;

public class QuickSort {

  /**
   * Sort an array of integers from smallest to largest, using a quicksort algorithm.
   *
   * @param data  the array to be sorted
   * @param first the start index for the portion of the array that will be sorted
   * @param n     the number of elements to sort
   *              <b>Precondition:</b>
   *              <CODE>data[first]</CODE> through <CODE>data[first+n-1]</CODE> are valid
   *              parts of the array.
   *              <b>Postcondition:</b>
   *              If <CODE>n</CODE> is zero or negative then no work is done. Otherwise, the
   *              elements of <CODE>data</CODE> have been rearranged so that
   *              <CODE>data[first] &lt;= data[first+1] &lt;= ... &lt;= data[first+n-1]</CODE>.
   * @throws ArrayIndexOutOfBoundsException Indicates that <CODE>first+n-1</CODE> is an index beyond
   *                                        the end of the array.
   */
  static void sort(int[] data, int first, int n) {
    int pivotIndex;
    int n1;
    int n2;

    if (n > 1) {
      pivotIndex = partition(data, first, n);

      n1 = pivotIndex - first;
      n2 = n - n1 - 1;

      sort(data, first, n1);
      sort(data, pivotIndex + 1, n2);
    }
  }

  private static int partition(int[] data, int first, int n) {
    // Precondition: n > 1, and data has at least n elements starting at
    // data[first].
    // Postcondition: The method has selected some "pivot value" that occurs
    // in data[first]. . .data[first+n-1]. The elements of data have then been
    // rearranged and the method returns a pivot index so that
    //   -- data[pivot index] is equal to the pivot;
    //   -- each element before data[pivot index] is <= the pivot;
    //   -- each element after data[pivot index] is > the pivot.

    int pivot = data[first];
    int tooBigIndex = first + 1;
    int tooSmallIndex = first + n - 1;

    while(tooBigIndex <= tooSmallIndex) {
      while(tooBigIndex < first + n && data[tooBigIndex] <= pivot) {
        tooBigIndex += 1;
      }

      while (data[tooSmallIndex] > pivot) {
        tooSmallIndex -= 1;
      }

      if(tooBigIndex < tooSmallIndex) {
        int temp = data[tooBigIndex];
        data[tooBigIndex] = data[tooSmallIndex];
        data[tooSmallIndex] = temp;
      }
    }

    data[first] = data[tooSmallIndex];
    data[tooSmallIndex] = pivot;

    System.out.println(Arrays.toString(data));

    return tooSmallIndex;
  }
}
