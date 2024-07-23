
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class FileClassRecord {
    public static void main(String[] args) {
        boolean run = true;
        while (run)
        {
            bar();
            System.out.println("            Menu             ");
            System.out.println(" A - Add a Record            ");
            System.out.println(" B - Display Record          ");
            System.out.println(" C - Quit                    ");
            bar();

            char[] menu = {'A', 'B', 'C'};

            Scanner in = new Scanner(System.in);
            System.out.println("Enter an option: ");
            char select = in.next().charAt(0);

            try {
                boolean test = false;
                for (char i : menu)
                    if (i == select) {
                        test = true;
                        break;
                    }
                if (!test)
                    throw new InputMismatchException(); //Appropriate error added later
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid selection! "); //Add repeat method next time
                continue;
            }

            try
            {
                switch (select) {
                    case 'A': {
                        File file = new File("classrecord.txt");
                        bar();
                        if (file.createNewFile())
                            System.out.println("New file created.");
                        else
                            System.out.println("Existing file detected.");
                        bar();
                        FileWriter file1 = new FileWriter(file, true);
                        BufferedWriter buffer = new BufferedWriter(file1);
                        System.out.println("Enter Quiz 1: ");
                        buffer.write(validateScore(in.next()) + ", ");

                        System.out.println("Enter Quiz 2: ");
                        buffer.write(validateScore(in.next()) + ", ");

                        System.out.println("Enter Quiz 3: ");
                        buffer.write(validateScore(in.next()) + "\n");
                        buffer.close();
                        file1.close();
                        break;
                    }

                    case 'B': {
                        File file = new File("classrecord.txt");
                        bar();
                        if (file.createNewFile())
                            System.out.println("New file created.");
                        else
                            System.out.println("Existing file detected.");
                        bar();

                        FileReader file2 = new FileReader(file);
                        BufferedReader buffer = new BufferedReader(file2);
                        System.out.println("Student\tQuiz 1\tQuiz 2\tQuiz 3\tAverage");

                        String[][] temp = new String[100][]; //Can hold 100 students
                        int ctr = 0;

                        while (buffer.ready())
                        {
                           temp[ctr++] = buffer.readLine().split(",");
                           System.out.print(ctr + "\t");

                           double studAve = 0;
                           for (String i: temp[ctr - 1])
                           {
                                double j = Double.parseDouble(i);
                                System.out.printf("\t%.2f", j);
                                studAve += j;
                           }
                           studAve /= 3.00;
                           System.out.println("\t" + studAve);
                        }

                        System.out.print("Average");
                        for (int i = 0; i < 3; i++) {
                            double ave = 0;
                            for (int j = 0; j < ctr; j++)
                                ave += Double.parseDouble(temp[j][i]);
                            ave /= ctr;
                            System.out.printf("\t%.2f", ave);
                        }
                        System.out.println();

                        System.out.print("Max\t");
                        for (int i = 0; i < 3; i++) {
                            double max = -1, tempVal;
                            for (int j = 0; j < ctr; j++) {
                                tempVal = Double.parseDouble(temp[j][i]);
                                max = Math.max(tempVal, max);
                            }
                            System.out.printf("\t%.2f", max);
                        }
                        System.out.println();

                        System.out.print("Min\t");
                        for (int i = 0; i < 3; i++) {
                            double min = 101, tempVal;
                            for (int j = 0; j < ctr; j++) {
                                tempVal = Double.parseDouble(temp[j][i]);
                                min = Math.min(tempVal, min);
                            }
                            System.out.printf("\t%.2f", min);
                        }
                        System.out.println();
                        buffer.close();
                        file2.close();
                        break;
                    }
                    case 'C':
                    {
                        run = false;
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println("An unexpected error occurred!");
                System.exit(0);
            }
        }
    }
    public static void bar() {
        System.out.println("=============================");
    }
    public static String validateScore(String value) {
        try
        {
            double val = Double.parseDouble(value);
            if (val < 0 || val > 100)
                throw new NumberFormatException(); //Replaced with appropriate exception
            return String.valueOf(val);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Invalid Input!");
            System.exit(0);
        }
        return null;
    }
}
