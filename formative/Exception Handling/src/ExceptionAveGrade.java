import javax.security.sasl.SaslClient;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionAveGrade {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Score Average");
        System.out.println("---------------------------------");

        int ctr = 0;
        double sum = 0;

        System.out.println("Input 5 scores, each out of 100");
        while (ctr < 5)
        {
            try
            {
                double x = Double.parseDouble(in.next());
                if (x < 0 || x > 100)
                    throw new InvalidGradeException();
                sum += x;
                ctr++;
            }
            catch (InvalidGradeException e)
            {
                System.out.println("Invalid grade!");
                invalidInputPrompt();
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input!");
                invalidInputPrompt();
            }
        }
        System.out.println("Average of scores: "+ sum/5.0);
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
                throw new InputMismatchException();
        }
        catch (InputMismatchException e)
        {
            System.out.println("Invalid input! Terminating program...");
            System.exit(1);
        }
    }
}
