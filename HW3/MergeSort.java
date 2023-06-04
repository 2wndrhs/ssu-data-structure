package HW3;

import java.util.Arrays;

public class MergeSort {

  /**
   * Sort an array of integers from smallest to largest, using a merge sort algorithm.
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
    int n1;
    int n2;

    if (n > 1) {
      n1 = n / 2;
      n2 = n - n1;

      sort(data, first, n1);
      sort(data, first + n1, n2);

      merge(data, first, n1, n2);
    }
  }

  // Precondition: data has at least n1 + n2 components starting at data[first]. The first
  // n1 elements (from data[first] to data[first + n1 � 1] are sorted from smallest
  // to largest, and the last n2 (from data[first + n1] to data[first + n1 + n2 - 1]) are also
  // sorted from smallest to largest.
  // Postcondition: Starting at data[first], n1 + n2 elements of data
  // have been rearranged to be sorted from smallest to largest.
  // Note: An OutOfMemoryError can be thrown if there is insufficient
  // memory for an array of n1+n2 ints.
  private static void merge(int[] data, int first, int n1, int n2) {
    int[] temp = new int[n1 + n2];
    int copied = 0;
    int copied1 = 0;
    int copied2 = 0;

    while ((copied1 < n1) && (copied2 < n2)) {
      if (data[first + copied1] < data[first + n1 + copied2]) {
        temp[copied] = data[first + copied1];
        copied += 1;
        copied1 += 1;
        continue;
      }

      temp[copied] = data[first + n1 + copied2];
      copied += 1;
      copied2 += 1;
    }

    while (copied1 < n1) {
      temp[copied] = data[first + copied1];
      copied += 1;
      copied1 += 1;
    }

    while (copied2 < n1) {
      temp[copied] = data[first + n1 + copied2];
      copied += 1;
      copied2 += 1;
    }

    for (int index = 0; index < copied; index++) {
      data[first + index] = temp[index];
    }

    System.out.println(Arrays.toString(data));
  }
}

