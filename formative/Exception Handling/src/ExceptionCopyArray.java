import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class ExceptionCopyArray {
    static Scanner in = new Scanner(System.in);
    static Random rng = new Random();
    public static void main(String[] args) {
        System.out.println("Array Copy");
        System.out.println("---------------------------------");
        int size, copy, index;

        System.out.println("Input array size of A and B:");
        while (true)
        {
            try
            {
                size = Integer.parseInt(in.next());
                if (size <= 0)
                    throw new ArithmeticException();
                break;
            }
            catch (ArithmeticException e)
            {
                System.out.println("Invalid size!");
                invalidInputPrompt();
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input!");
                invalidInputPrompt();
            }
        }

        Object[] A = new Object[size];
        Object[] B = new Object[size];
        for (int i = 0; i < size; i++) {
            A[i] = rng.nextInt(100) + 1;
            B[i] = rng.nextInt(100) + 1;
        }

        System.out.println("Enter number of elements to be copied: ");
        while (true)
        {
            try
            {
                copy = Integer.parseInt(in.next());
                if (copy > size)
                    throw new ArithmeticException();
                break;
            }
            catch (ArithmeticException e)
            {
                System.out.println("Invalid size!");
                invalidInputPrompt();
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input!");
                invalidInputPrompt();
            }
        }

        System.out.println("Enter starting index: ");
        while (true)
        {
            try
            {
                index = Integer.parseInt(in.next());
                if (index > size - 1)
                    throw new ArithmeticException();
                break;
            }
            catch (ArithmeticException e)
            {
                System.out.println("Invalid index!");
                invalidInputPrompt();
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input!");
                invalidInputPrompt();
            }
        }

        try
        {
            for (int i = index; i < copy + index; i++)
                B[i] = A[i];
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Array index exceed maximum array size. Terminating program...");
            System.exit(1);
        }
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Array B: " + Arrays.toString(B));
    }
    public static void invalidInputPrompt()
    {
        System.out.println("Do you still wish to continue the program? 0 - No, 1 - Yes");
        try
        {
            int x = Integer.parseInt(in.next());
            if (x == 1)
            {
                System.out.println("Continuing Program...");
                return;
            }
            if (x == 0)
            {
                System.out.println("Stopping Program...");
                System.exit(0);
            }
            else
                throw new NumberFormatException();
        }
        catch (NumberFormatException e)
        {
            System.out.println("Invalid input! Terminating program...");
            System.exit(1);
        }
    }
}
