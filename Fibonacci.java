public class Fibonacci {
    /*
                               fib(5)
                            /``       ````````````````````````````\
                       fib(4)                                  fib(3)
                    /``       ``\                           /``       ``\
                fib(3)    +     fib(2)                   fib(2)    +     fib(1)
              /``    ``\          /    ``\              /     ``\            1 is base case add with return of fib(2)
          fib(2) +  fib(1)    fib(1) + fib(0)       fib(1) + fib(0)
          /    \        1         1        0            1    +    0 = 1 return to fib(2)
     fib(1) + fib(0)
         1        0

         For n = 5 it makes 15 total calls. But fib(2) and fib(1) is calling itself multiple times

        */
    public static int rFib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return rFib(n - 1) + rFib(n - 2);
        }
    }

    /*
        Term nth  0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
         n        0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
                 /   |   \
                /    |    \
            prev    curr    i = 2

            Move forward means:
         n        0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
                    /   |   \
                   /    |    \
               prev     curr   i
         Move prev pointer to curr position, move curr to sum of prev and curr, which is nextNum's position
         i is the counter we increment

         prevNum = 0
         currentNum = 1
         Means they are pointing to the first two numbers of the sequence
         To calculate the next sequence it's the sum of current and previous number.
         We can save it into a variable as nextNum
         Then we move the pointers forward


    */
    public static int iFib(int n) {
        if (n <= 1) {
            return n;
        }
        int prevNum = 0;
        int currentNum = 1;

        for (int i = 2; i <= n; i++) {
            int nextNum = prevNum + currentNum;
            // Move pointers forward
            prevNum = currentNum;
            currentNum = nextNum;
        }
        return currentNum;
    }


    public static void main(String[] args) {
        /////////////////////////////////
        ///// Term N ////////////////////
        int n = 5;

        /////////////////////////////////
        ////   Recursive Fib   //////////
        for (int i = 0; i < n; i++) {
            System.out.printf("%d ", rFib(i));
        }
        System.out.println();
        System.out.println("-".repeat(n * 2));

        /////////////////////////////////
        ////   Iterative Fib   //////////
        for (int i = 0; i < n; i++) {
            System.out.printf("%d ", iFib(i));
        }
    }
}
