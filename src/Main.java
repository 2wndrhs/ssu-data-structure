public class Main {

  public static void main(String[] args) {
    DoubleArraySeq numbers1 = new DoubleArraySeq();
    DoubleArraySeq numbers2 = new DoubleArraySeq(5);

    numbers1.addAfter(10.1);
    numbers1.addAfter(40.2);
    numbers1.addAfter(1.1);

    numbers2.addAfter(4.9);
    numbers2.addAfter(0.3);
    numbers2.addAfter(2.5);

    System.out.println("numbers1 size: " + numbers1.size()); // 3

    numbers1.start();
    System.out.println("numbers1 current element: " + numbers1.getCurrent()); // 10.1
    numbers1.advance();
    System.out.println("numbers1 current element: " + numbers1.getCurrent()); // 40.2
    numbers1.advance();
    System.out.println("numbers1 current element: " + numbers1.getCurrent()); // 1.1

    numbers1.addBefore(10.0);
    numbers1.addBefore(99.0);

    for (numbers1.start(); numbers1.isCurrent(); numbers1.advance()) {
      // 10.1 / 40.2 / 99.0 / 10.0 / 1.1
      System.out.println("numbers1 current element: " + numbers1.getCurrent());
    }

    numbers1.start();
    numbers1.advance();
    numbers1.removeCurrent();

    System.out.println("numbers1 current element: " + numbers1.getCurrent()); // 99.0

    numbers1.addAll(numbers2);

    for (numbers1.start(); numbers1.isCurrent(); numbers1.advance()) {
      // 10.1 / 99.0 / 10.0 / 1.1 / 4.9 / 0.3 / 2.5
      System.out.println("numbers1.addAll() current element: " + numbers1.getCurrent());
    }

    DoubleArraySeq concatedArray = DoubleArraySeq.concatenation(numbers1, numbers2);

    for (concatedArray.start(); concatedArray.isCurrent(); concatedArray.advance()) {
      // 10.1 / 99.0 / 10.0 / 1.1 / 4.9 / 0.3 / 2.5 / 4.9 / 0.3 / 2.5
      System.out.println("concatedArray current element: " + concatedArray.getCurrent());
    }

    DoubleArraySeq clonedArray = numbers2.clone();

    for (clonedArray.start(); clonedArray.isCurrent(); clonedArray.advance()) {
      // 4.9 / 0.3 / 2.5
      System.out.println("clonedArray current element: " + clonedArray.getCurrent());
    }

    numbers2.trimToSize();

    System.out.println("numbers2 trimmed size: " + numbers2.size()); // 3
  }
}