package HW2;

public class StackDemo {

  private final static String INT_STACK_PEEK = "INT_STACK의 peek: ";
  private final static String INT_LINKED_STACK_PEEK = "INT_LINKED_STACK의 peek: ";

  public static void main(String[] args) {
    IntStack intStack = new IntStack();
    IntLinkedStack intLinkedStack = new IntLinkedStack();

    pushNumberToIntStack(intStack);
    popNumberFromIntStack(intStack);

    pushNumberToIntLinkedStack(intLinkedStack);
    popNumberFromIntLinkedStack(intLinkedStack);
  }

  private static void pushNumberToIntStack(IntStack intStack) {
    for (int number = 0; number < 5; number++) {
      intStack.push(number);

      int peek = intStack.peek();
      System.out.println(INT_STACK_PEEK + peek);
    }
  }

  private static void popNumberFromIntStack(IntStack intStack) {
    for (int index = 0; index < 5; index++) {
      int peek = intStack.peek();
      System.out.println(INT_STACK_PEEK + peek);

      intStack.pop();
    }
  }

  private static void pushNumberToIntLinkedStack(IntLinkedStack intLinkedStack) {
    for (int number = 0; number < 5; number++) {
      intLinkedStack.push(number);

      int peek = intLinkedStack.peek();
      System.out.println(INT_LINKED_STACK_PEEK + peek);
    }
  }

  private static void popNumberFromIntLinkedStack(IntLinkedStack intLinkedStack) {
    for (int index = 0; index < 5; index++) {
      int peek = intLinkedStack.peek();
      System.out.println(INT_LINKED_STACK_PEEK + peek);

      intLinkedStack.pop();
    }
  }
}
