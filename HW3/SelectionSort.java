package HW3;

import java.util.Arrays;

public class SelectionSort {

  /**
   * Sort an array of integers from smallest to largest, using a selection sort algorithm.
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
   *              <CODE>data[first] &lt;= data[first+1] &lt; ... &lt;= data[first+n-1]</CODE>.
   * @throws ArrayIndexOutOfBoundsException Indicates that <CODE>first+n-1</CODE> is an index beyond
   *                                        the end of the array.
   */
  static void sort(int[] data, int first, int n) {
    int i, j;
    int big;

    for (i = n - 1; i > 0; i--) {
      big = first;
      for (j = first + 1; j <= first + i; j++) {
        if (data[big] < data[j]) {
          big = j;
        }
      }

      int temp = data[first + i];
      data[first+i] = data[big];
      data[big] = temp;

      System.out.println(Arrays.toString(data));
    }
  }
}
