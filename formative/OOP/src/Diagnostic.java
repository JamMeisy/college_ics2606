//1CSC Jam Meisy Franco Tan

import java.util.Random;
import java.util.Scanner;

public class Diagnostic
{
    static Scanner in = new Scanner(System.in);
    static Random rng = new Random();
    public static void main(String[] args)
    {
        boolean runningProgram = true;
        while (runningProgram)
        {
            System.out.print("Input array size: ");
            int n = validate();
            int[] arrayA = new int[n];
            int[] arrayB = new int[n];
            int[] arrayC = new int[n];

            System.out.print("Input an integer (5-15): ");
            int x = validate(in.nextInt());
            double sumA = 0, sumB = 0, sumC = 0;

            for (int i = 0; i < n; i++) {
                arrayA[i] = 10 + rng.nextInt(21);
                arrayB[i] = arrayA[i] - x;
                if (arrayB[i] >= x)
                    arrayC[n - 1 - i] = arrayB[i];

                sumA += arrayA[i];
                sumB += arrayB[i];
                sumC += arrayC[n - 1 - i];
            }

            System.out.print("\n\nArray A:");
            for (int a : arrayA)
                System.out.print(" " + a);
            System.out.printf("\nSum of Array A = %.2f", sumA);
            System.out.printf("\nAverage of Array A = %.2f", average(sumA, n));

            System.out.print("\n\nArray B:");
            for (int a : arrayB)
                System.out.print(" " + a);
            System.out.printf("\nSum of Array B = %.2f", sumB);
            System.out.printf("\nAverage of Array B = %.2f", average(sumB, n));

            System.out.print("\n\nArray C:");
            for (int a : arrayC)
                System.out.print(" " + a);
            System.out.printf("\nSum of Array C = %.2f", sumC);
            System.out.printf("\nAverage of Array C = %.2f", average(sumC, n));

            runningProgram = continueProgram();
        }
    }
    public static int validate()
    {
        int x = in.nextInt();
        while (x <= 0 || x > 50)
        {
            System.out.print("Please input a valid size: ");
            x = in.nextInt();
        }
        return x;
    }
    public static int validate(int x)
    {
        while (x < 5 || x > 15)
        {
            System.out.print("Please input a valid integer: ");
            x = in.nextInt();
        }
        return x;
    }
    public static double average(double x, int n)
    {
        return x/(double)n;
    }
    public static boolean continueProgram()
    {
        System.out.print("\n\nPress 'Y' to continue: ");
        return (Character.toUpperCase(in.next().charAt(0)) == 'Y');
    }
}