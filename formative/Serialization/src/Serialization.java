import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Serialization {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        while(true)
        {
            //Menu
            bar();
            System.out.println("                           Menu                            ");
            System.out.println(" 1 - Create a Class Record");
            System.out.println(" 2 - Edit a Record");
            System.out.println(" 3 - Display Class Record");
            System.out.println(" 4 - Display a Specific Record");
            System.out.println(" 5 - Quit");
            bar();

            //Choice (Validated in a method)
            Integer[] choices = {1, 2, 3, 4, 5};
            int choice = validChoiceInt(choices);
            bar();

            //Early exit when user wants to quit
            if (choice == 5)
                break;


            try
            {
                File file = new File("records.txt");
                //File fileTemp = new File("temp.txt");
                System.out.println("Locating file...");
                //Creates a new file when no file is detected
                if (file.createNewFile())
                    System.out.println("New file is created!");
                else
                    System.out.println("File located");

                Object check;
                List<Student> record = new ArrayList<>();
                try
                {
                    ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file));
                    while (!((check = reader.readObject()) instanceof EndFile))
                        record.add((Student) check);
                    reader.close();
                }
                catch (EOFException e)
                {
                    System.out.println("File has no contents");
                }

                bar();


                switch (choice) {
                    case 1 ->
                    {
                        String name;
                        int id, n;
                        double q1, q2, q3;

                        System.out.println("Enter number of students: ");
                        n = Integer.parseInt(in.nextLine());

                        //Prompts information (without verification)
                        for (int i = 1; i <= n; i++)
                        {
                            System.out.printf("RECORD %d\n", i );
                            bar();
                            System.out.printf("Enter student %d information...\n", i);
                            System.out.print("Name: ");
                            name = in.nextLine();
                            System.out.print("Student ID:");
                            id = Integer.parseInt(in.nextLine());
                            bar();
                            System.out.println("Enter quiz scores...");
                            System.out.print("Quiz 1: ");
                            q1 = Double.parseDouble(in.nextLine());
                            System.out.print("Quiz 2: ");
                            q2 = Double.parseDouble(in.nextLine());
                            System.out.print("Quiz 3: ");
                            q3 = Double.parseDouble(in.nextLine());
                            bar();

                            //Creation of object
                            record.add(new Student(name, id, q1, q2, q3));
                            System.out.println("Record created successfully...");
                            bar();
                        }
                    }
                    case 2 ->
                    {
                        int id, q;
                        Student found = null;

                        //Accounts for not finding ID
                        while (true)
                        {
                            //Prompts ID
                            System.out.print("Enter Student ID:");
                            id = Integer.parseInt(in.nextLine());
                            bar();

                            //Searches record
                            for (Student i : record)
                                if (i.id == id) {
                                    found = i;
                                    break;
                                }

                            //Editing record
                            if (found != null)
                            {
                                System.out.print("Enter quiz # to be edited: ");
                                q = Integer.parseInt(in.nextLine());
                                switch (q) {
                                    case 1 -> {
                                        System.out.print("Enter new score for quiz 1: ");
                                        found.quiz1 = Double.parseDouble(in.nextLine());
                                    }
                                    case 2 -> {
                                        System.out.print("Enter new score for quiz 2: ");
                                        found.quiz2 = Double.parseDouble(in.nextLine());
                                    }
                                    case 3 -> {
                                        System.out.print("Enter new score for quiz 3: ");
                                        found.quiz3 = Double.parseDouble(in.nextLine());
                                    }
                                }
                                bar();
                                System.out.println("Record edited successfully...");
                                bar();
                                break;
                            }
                            else
                            {
                                System.out.println("Record not found... Please try again!");
                                bar();
                            }
                        }
                    }
                    case 3 ->
                    {
                        int ctr = 0;
                        double ave1 = 0, ave2 = 0, ave3 = 0,
                                max1 = 0, max2 = 0, max3 = 0,
                                min1 = 100, min2 = 100, min3 = 100;

                        System.out.println("Student\tID\tQuiz 1\tQuiz 2\tQuiz 3\tAverage");
                        for (Student i: record)
                        {
                            System.out.print(i.name + "\t");
                            System.out.print(i.id + "\t");

                            System.out.printf("%.2f\t", i.quiz1);
                            System.out.printf("%.2f\t", i.quiz2);
                            System.out.printf("%.2f\t", i.quiz3);

                            ave1 += i.quiz1;
                            ave2 += i.quiz2;
                            ave3 += i.quiz3;

                            max1 = Math.max(i.quiz1, max1);
                            max2 = Math.max(i.quiz2, max2);
                            max3 = Math.max(i.quiz3, max3);

                            min1 = Math.min(i.quiz1, min1);
                            min2 = Math.min(i.quiz2, min2);
                            min3 = Math.min(i.quiz3, min3);

                            System.out.println(String.format("%.2f", (i.quiz1 + i.quiz2 + i.quiz1) / 3.00));
                            ctr++;
                        }

                        if (ctr != 0) {
                            ave1 /= ctr;
                            ave2 /= ctr;
                            ave3 /= ctr;

                            System.out.printf("Average\t%.2f\t%.2f\t%.2f\n", ave1, ave2, ave3);
                            System.out.printf("Max\t\t%.2f\t%.2f\t%.2f\n", max1, max2, max3);
                            System.out.printf("Min\t\t%.2f\t%.2f\t%.2f\n", min1, min2, min3);
                        }

                        bar();
                    }
                    case 4 ->
                    {
                        int id;
                        Student found = null;

                        //Accounts for not finding ID
                        while (true)
                        {
                            //Prompts ID
                            System.out.print("Enter Student ID:");
                            id = Integer.parseInt(in.nextLine());
                            bar();

                            //Searches record
                            for (Student i : record)
                                if (i.id == id) {
                                    found = i;
                                    break;
                                }

                            //Displays record
                            if (found != null)
                            {
                                System.out.println("Student\t\tID\tQuiz 1\tQuiz 2\tQuiz 3\tAverage");
                                System.out.print(found.name + "\t");
                                System.out.print(found.id + "\t");

                                System.out.printf("%.2f\t", found.quiz1);
                                System.out.printf("%.2f\t", found.quiz2);
                                System.out.printf("%.2f\t", found.quiz3);

                                System.out.println((found.quiz1 + found.quiz2 + found.quiz1) / 3.00);
                                bar();
                                break;
                            }
                            else
                            {
                                System.out.println("Record not found... Please try again!");
                                bar();
                            }
                        }
                    }
                }
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
                for (Student i: record)
                    writer.writeObject(i);
                writer.writeObject(new EndFile());
                writer.close();
            }
            catch (IOException | ClassNotFoundException e)
            {
                System.out.println("Unexpected error occurred! File may be missing or corrupted.");
                e.printStackTrace();
            }
        }
        System.out.println("Exiting program!");
        bar();
    }

    public static void bar() {
        System.out.println("===========================================================");
    }

    public static Integer validChoiceInt(Integer[] array) //Validates choice input
    {
        while (true)
        {
            System.out.println("Select an option:");
            try
            {
                Integer x = Integer.valueOf(in.nextLine());
                for (Integer i : array)
                    if (x.equals(i))
                        return x;
                throw new InvalidSelectionException();
            }
            catch (NumberFormatException | InvalidSelectionException e) {
                invalidInputPrompt();
            }
        }
    }

    public static void invalidInputPrompt() //Gives user a second chance to continue
    {
        bar();
        System.out.println("Invalid input detected!");
        System.out.println("0 - [No]\t1 - [Yes]");
        System.out.println("Do you still wish to continue the program:");

        try
        {
            int x = Integer.parseInt(in.nextLine());
            bar();
            if (x == 1) {
                System.out.println("Continuing Program...");
                return;
            }
            if (x == 0) {
                System.out.println("Stopping Program...");
                System.exit(0);
            }
            else
                throw new InvalidSelectionException();
        }
        catch (NumberFormatException | InvalidSelectionException e)
        {
            System.out.println("Invalid Input!");
            System.out.println("Terminating program...");
            System.exit(1);
        }
    }
}

