package HW3;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Sorting {

  private static final int SORING_SIZE = 32;

  public static void main(String[] args) {
    Set<Integer> numberSet = new LinkedHashSet<>();

    while (numberSet.size() < SORING_SIZE) {
      int randomNumber = (int) (Math.random() * SORING_SIZE);
      numberSet.add(randomNumber);
    }

    int[] source = numberSet.stream().mapToInt(Integer::intValue).toArray();

    // SelectionSort
    System.out.println("Selection Sort");
    System.out.println(Arrays.toString(source));
    SelectionSort.sort(source.clone(), 0, source.length);

    //InsertionSort
    System.out.println("\n" + "Insertion Sort");
    System.out.println(Arrays.toString(source));
    InsertionSort.sort(source.clone(), 0, source.length);

    //MergeSort
    System.out.println("\n" + "Merge Sort");
    System.out.println(Arrays.toString(source));
    MergeSort.sort(source.clone(), 0, source.length);

    //QuickSort
    System.out.println("\n" + "Quick Sort");
    System.out.println(Arrays.toString(source));
    QuickSort.sort(source.clone(), 0, source.length);
  }
}
