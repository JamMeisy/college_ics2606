import java.io.*;
import java.util.Scanner;

public class FileJennySalary {
    public static void main(String[] args) {

        double[][] data = new double[5][7];
        try
        {
            File file = new File("Book1.csv");
            System.out.println("Locating file...");
            if (!file.exists())
            {
                System.out.println("File not detected!");
                System.out.println("Please check file directory!");
                System.exit(0);
            }
            System.out.println("File located...");
            System.out.println("Jenny's Salary");

            Scanner in = new Scanner(file);
            for (int i = 0; i < 5 && in.hasNextLine(); i++)
            {
                String[] e = in.nextLine().split(",");
                for (int j = 0; j < 7; j++)
                    data[i][j] = Double.parseDouble(e[j]);
            }
            in.close();
        }
        catch (IOException e)
        {
            System.out.println("Unexpected error occurred!");
        }
        System.out.printf("Week 1: %.2f\n", computeSalary(data[0]));
        System.out.printf("Week 2: %.2f\n", computeSalary(data[1]));
        System.out.printf("Week 3: %.2f\n", computeSalary(data[2]));
        System.out.printf("Week 4: %.2f\n", computeSalary(data[3]));
        System.out.printf("Week 5: %.2f\n", computeSalary(data[4]));
    }

    public static double computeSalary(double[] week) {
        double salary = 0, payPerDay = 0, tHours = 0;
        for (int i = 0; i < 7; i++)
        {
            payPerDay = week[i] * 10; //Regular pay

            if (week[i] > 8)
                payPerDay += (week[i] - 8) * 1.5; //Overtime pay
            if (i == 0)
                payPerDay += payPerDay * .5; //Sunday bonus
            if (i == 6)
                payPerDay += payPerDay * 1.25; //Saturday bonus

            salary += payPerDay;
            tHours += week[i];
        }
        if (tHours > 40)
            salary += (tHours - 40) * 2.5; //Weekly overtime pay

        return salary;
    }
}
