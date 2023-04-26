package HW2;

public class QueueDemo {
  private static final String INSERT_QUEUE = "INSERT %d to QUEUE %n";
  private static final String QUEUE_FRONT = "QUEUE의 front: %d %n";
  private static final String DIVIDER = "-----------------";
  public static void main(String[] args) {
    IntQueue intQueue = new IntQueue();
    IntLinkedQueue intLinkedQueue = new IntLinkedQueue();

    addNumberToIntQueue(intQueue);
    removeNumberFromIntQueue(intQueue);

    System.out.println(DIVIDER);

    addNumberToIntLinkedQueue(intLinkedQueue);
    removeNumberFromIntLinkedQueue(intLinkedQueue);
  }

  private static void addNumberToIntQueue(IntQueue intQueue) {
    for (int number = 0; number < 5; number++) {
      intQueue.insert(number);
      System.out.printf(INSERT_QUEUE, number);
    }
  }

  private static void removeNumberFromIntQueue(IntQueue intQueue) {
    for (int index = 0; index < 5; index++) {
      int front = intQueue.getFront();
      System.out.printf(QUEUE_FRONT, front);
    }
  }

  private static void addNumberToIntLinkedQueue(IntLinkedQueue intLinkedQueue) {
    for (int number = 0; number < 5; number++) {
      intLinkedQueue.insert(number);
      System.out.printf(INSERT_QUEUE, number);
    }
  }

  private static void removeNumberFromIntLinkedQueue(IntLinkedQueue intLinkedQueue) {
    for (int index = 0; index < 5; index++) {
      int front = intLinkedQueue.getFront();
      System.out.printf(QUEUE_FRONT, front);
    }
  }
}
