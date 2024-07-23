import java.util.Scanner;

public class ExceptionSumOfIntegers {
    static Scanner in = new Scanner(System.in);
    public static <e> void main(String[] args) {
        int x = 1, sum = 0;
        System.out.println("Sum of Integers");
        System.out.println("---------------------------------");
        System.out.println("Input integers, Input 0 to stop:");
        while (x != 0)
        {
            try
            {
                x = Integer.parseInt(in.next());
                if (x < 0)
                    throw new IllegalArgumentException();
                sum += x;
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Invalid input! Would you like to continue? Press 1 to continue, 0 to stop");
                try
                {
                    x = Integer.parseInt(in.next());
                    if (x == 1)
                        System.out.println("Continuing program...");
                    else if (x == 0)
                        System.out.println("Computing sum...");
                    else
                        throw new IllegalArgumentException();
                }
                catch (IllegalArgumentException f)
                {
                    System.out.println("Invalid input! Terminating program...");
                    System.exit(1);
                }
            }
        }
        System.out.println("Sum of integers: " + sum);
    }
}
