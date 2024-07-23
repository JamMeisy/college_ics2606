//1CSC - Jam Meisy Tan

import java.io.*;
import java.util.Scanner;
public class MPBonus {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        while(true)
        {
            //Menu
            bar();
            System.out.println("                           Menu                            ");
            System.out.println(" 1 - Add a string");
            System.out.println(" 2 - Display list of strings");
            System.out.println(" 3 - Display list of words that start with a vowel");
            System.out.println(" 4 - Display list of words that start with th");
            System.out.println(" 5 - Display list of words that start with a capital letter");
            System.out.println(" 6 - Quit");
            bar();

            //Choice (Validated in a method)
            Integer[] choices = {1, 2, 3, 4, 5, 6};
            int choice = validChoiceInt(choices);
            bar();

            //Early exit when user wants to quit
            if (choice == 6)
                break;

            File file = new File("string.txt");
            System.out.println("Locating file...");
            try
            {
                //Creates a new file when no file is detected
                if (file.createNewFile())
                    System.out.println("New file is created!");
                else
                    System.out.println("File located");

                //Verifies if file is not missing or corrupted
                if (!file.canExecute())
                    throw new IOException();
                bar();

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String[] temp;
                switch (choice)
                {
                    case 1:
                    {
                        System.out.println("Add a string: ");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                        writer.write(in.nextLine() + "\n");
                        writer.close();
                        reader.close();
                        bar();
                        break;
                    }
                    case 2:
                    {
                        System.out.println("List of all strings:");
                        while (reader.ready())
                            System.out.println(reader.readLine());
                        reader.close();
                        bar();
                        break;
                    }
                    case 3:
                    {
                        char[] vowel = {'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'};
                        System.out.println("List of words that start with a vowel:");
                        while (reader.ready())
                        {
                            temp = reader.readLine().split(" ");
                            for (String i: temp)
                                for (char j : vowel)
                                    if (validWord(i) && i.charAt(0) == j)
                                    {
                                        System.out.println(i);
                                        break;
                                    }
                        }
                        reader.close();
                        bar();
                        break;
                    }
                    case 4:
                    {
                        System.out.println("List of words that start with 'th':");
                        while (reader.ready())
                        {
                            temp = reader.readLine().split(" ");
                            for (String i: temp)
                                if (validWord(i) && i.startsWith("th"))
                                    System.out.println(i);
                        }
                        reader.close();
                        bar();
                        break;
                    }
                    case 5:
                    {
                        System.out.println("List of words that start with a capital letter:");
                        while (reader.ready())
                        {
                            temp = reader.readLine().split(" ");
                            for (String i: temp)
                                if (validWord(i) && i.charAt(0) >= 65 && i.charAt(0) <= 90)
                                    System.out.println(i);
                        }
                        reader.close();
                        bar();
                        break;
                    }
                }
            }
            catch (IOException e) {
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
    public static boolean validWord(String el) //Validates a string if it is a word
    {

        char[] array = el.toCharArray();
        for (char i : array) {
            if (!( (i >= 65 && i <= 90)||(i >= 97 && i <= 122) ))
                return false;
        }
        return true;
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


class InvalidSelectionException extends Exception {
    InvalidSelectionException() {}
}