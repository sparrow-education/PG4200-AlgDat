/**
 * Question 2: Stack
 */
public class Stack {
    private int top = -1;
    private int height = 0;
    private int[] value;
    private static Stack minimum = new Stack();


    // If no capacity is provided, default to 10.
    private Stack() {
        this.value = new int[10];
    }

    public Stack(int capacity) {
        if (capacity > 0) {
            this.value = new int[capacity];
        } else if (capacity == 0) {
            this.value = new int[10];
        } else {
            throw new IllegalArgumentException("Provided capacity is invalid");
        }
    }

    /**
     * Current stack is empty, push x into the stack, move pointer to correct index.
     * Assume that most language is zero index based. Since we're in Java, array starts at 0.
     * Increment top by 1, to prepare for next push.
     *
     *  push(x)
     *
     *  |   |
     *  |   |
     *  |_x_| top = 0
     *
     */
    public void push(int x) {
        if (this.top == this.value.length - 1) {
            throw new StackOverflowError("Stack is full");
        }
        // As per requirement, constraint is 1 to 100
        if (x > 100 || x <= 0) {
            throw new IllegalArgumentException("Value is constrained at range 1 to 100");
        }

        // Assigning value to the original stack
        this.value[this.top + 1] = x;
        this.top = this.top + 1;
        this.height++;

        // Assigning the first value to the minimum stack, and update the minimum stack accordingly
        if (minimum.top == -1 || x < minimum.peek()) {
            minimum.value[minimum.top + 1] = x;
            minimum.top = minimum.top + 1;
            minimum.height++;
        }
    }

    /**
     *  if container is empty, return -1
     *  else memoize the value at the top, empty the value at the top, decrement top by 1.
     *  return memoized value.
     *
     *  I want to emphasize that primitive type has a default value of 0. So setting to 0 means nullifying that index.
     *  A = [0, 0, 0, 0, 0] means initializing an Array of size 5 as empty in contrast to C language
     *  A = [/, /, /, /, /]
     */
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return this.top;
        } else {
            int temp = value[top];
            value[top] = 0;
            top--;
            height--;
            System.out.printf("\nPopped value: %s\n", temp);

            // If the popped value equals exist in the minimum stack, remove it to sync the minimum stack.
            if ( temp == minimum.value[minimum.top]) {
                minimum.value[minimum.top] = 0;
                minimum.top--;
                minimum.height--;
            }

            return temp;
        }
    }

    /**
     *   A = [1, 0, 6, 1] suppose given a stack of 4 elements
     *        |
     *   Lowest value points to the first element in the stack A[0]
     *   Each comparison, if the current value is less than the lowest value
     *   Update the lowest value to the current value.
     *   Traverse the entire stack, return the lowest value found.
     */
    public void getMin() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            System.out.println("Minimum value is: " + minimum.peek());
        }
    }

    /**
     * Helper method to peek-a-boo the top of the container.
     * Strictly speaking, I'm well aware peek aren't supposed to return anything.
     * But I don't want to pop unless I am popping from the original stack.
     *
     * I am doing this because in the context of this question, I don't see any constraint of not using refactoring.
     */
    private int peek() {
        if (!minimum.isEmpty()) {
            return minimum.value[minimum.top];
        }
        return -1;
    }

    /**
     *  |   |
     *  |   |
     *  |___|
     *  top = -1
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Print my stack.
     */
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            System.out.println("-".repeat(40));
            System.out.print("Bottom -> ");
            for (int i = 0; i < value.length; i++) {
                // I dislike 0 denoted as empty.
                if (value[i] != 0)
                    System.out.printf("%d ", value[i]);
            }
            System.out.print(" <- Top");
            System.out.print("\nHeight: " + height);
            System.out.println("\n" + "-".repeat(40));
        }
    }

    public static void main(String[] args) {
        /////////////////////////////////////////
        /// Instantiate a stack of capacity 10 //
        Stack stack = new Stack(10);

        /////////////////////////////////////////
        //// Random insertions of val ///////////
        int N = 10;
        for (int i = 0; i < N; i++) {
            stack.push( (int)(1 + Math.random() * 99));
        }

        /////////////////////////////////////////
        ///// Manual insertion for testing //////
        // stack.push(0);
        // stack.push(101);

        /////////////////////////////////////////
        //// Print the stack after insertion ////
        stack.printStack();

        /////////////////////////////////////////
        /// Get the minimum value in the stack //
        stack.getMin();

        /////////////////////////////////////////
        /// Pop the top of the stack ////////////
        int times = 5;
        for (int i = 0; i < times; i++) stack.pop();

        /////////////////////////////////////////
        //////// After popping the stack ////////
        stack.printStack();
        stack.getMin();
    }
}
