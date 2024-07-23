import com.sun.jdi.InvalidLineNumberException;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionFacultySalary
{
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        boolean flag = true;
        System.out.println("Welcome to the CICS Academy!");
        System.out.println("-----------------------------");
        System.out.println("This program aims to compute for the salary and semestral bonus");
        System.out.println("of a faculty member");
        System.out.println("-----------------------------");
        while (flag)
        {
            System.out.println("1 - [Instructor]");
            System.out.println("2 - [Assistant Professor]");
            System.out.println("3 - [Professor]");
            System.out.print("Please select a faculty: ");
            int x = 0;
            try
            {
                x = Integer.parseInt(in.nextLine());
                if (x < 1 || x > 3)
                    throw new InvalidSelectionException();
            }
            catch (NumberFormatException | InvalidSelectionException e)
            {
                System.out.println("Invalid input!");
                invalidInputPrompt();
            }

            System.out.println("-----------------------------");
            switch (x)
            {
                case 1 -> System.out.println("[Instructor]");
                case 2 -> System.out.println("[Assistant Professor]");
                case 3 -> System.out.println("[Professor]");
                default -> {
                    System.out.println("An error has occurred in the system, please try again");
                    continue;
                }
            }

            System.out.println("Input the necessary details below:");
            System.out.println("-----------------------------");
            int lectureUnit, labUnit, preparations = 0;
            double evalScore;
            String name;
            System.out.print("Full name: ");
            name = in.nextLine();
            System.out.print("# of Lecture Units: ");
            lectureUnit = validateUnits();
            System.out.print("# of Lab Units: ");
            labUnit = validateUnits();
            System.out.print("# of Preparations ");
            switch (x)
            {
                case 1 -> preparations = validateUnits(3);
                case 2, 3 -> preparations = validateUnits(4);
            }
            System.out.print("Competence Evaluation Score: ");
            evalScore = validateScore(30);
            System.out.println("-----------------------------");
            System.out.println("Computing...");
            System.out.println("-----------------------------");
            CICSFaculty person = null;
            switch (x)
            {
                case 1 -> person = new Instructor(name, lectureUnit, labUnit, preparations, evalScore);
                case 2 -> person = new AsstProfessor(name, lectureUnit, labUnit, preparations, evalScore);
                case 3 -> person = new Professor(name, lectureUnit, labUnit, preparations, evalScore);
            }
            try {
                System.out.println(String.format("[%s] %s", person.rank, person.name));
                System.out.println(String.format("Salary: %.2f", person.getSalary()));
                System.out.println(String.format("Semestral Bonus: %.2f", person.getSemBonus()));
            }
            catch (NullPointerException e)
            {
                System.out.println("");
            }
            System.out.println("-----------------------------");
            System.out.println("Do you wish to continue the program: ");
            System.out.println("0 - [No], 1 - [Yes]");
            System.out.println("-----------------------------");
            try
            {
                x = Integer.parseInt(in.nextLine());
                if (x == 1)
                    flag = true;
                else if (x == 0)
                    flag = false;
                else
                    throw new InvalidSelectionException();
            }
            catch (NumberFormatException | InvalidSelectionException e)
            {
                System.out.println("Invalid input!");
                invalidInputPrompt();
            }
            System.out.println("-----------------------------");
        }
        System.out.println("Ending Program...");
    }
    public static void invalidInputPrompt() {
        System.out.println("Do you still wish to continue the program? 0 - No, 1 - Yes");
        try {
            int x = Integer.parseInt(in.nextLine());
            if (x == 1) {
                System.out.println("Continuing Program...");
                System.out.println();
                return;
            }
            if (x == 0) {
                System.out.println("Stopping Program...");
                System.exit(0);
            } else
                throw new InputMismatchException();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Terminating program...");
            System.exit(1);
        }
    }
    public static int validateUnits()
    {
        int x;
        while (true) 
        {
            try 
            {
                x = Integer.parseInt(in.nextLine());
                if (x < 0)
                    throw new ArithmeticException();
                return x;
            } 
            catch (ArithmeticException e) 
            {
                System.out.println("Invalid number of units! Please try again: ");
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid input!");
                invalidInputPrompt();
            }
        }
    }
    public static int validateUnits(int y)
    {
        int x;
        while (true)
        {
            try
            {
                x = Integer.parseInt(in.nextLine());
                if (x < 0 || x > y)
                    throw new ArithmeticException();
                return x;
            }
            catch (ArithmeticException e)
            {
                System.out.println("Invalid number of units! Please try again: ");
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input!");
                invalidInputPrompt();
            }
        }
    }
    public static double validateScore(double y)
    {
        double x;
        while (true)
        {
            try
            {
                x = Double.parseDouble(in.nextLine());
                if (x < 0 || x > y)
                    throw new InvalidGradeException();
                return x;
            }
            catch (InvalidGradeException e)
            {
                System.out.println("Invalid grade! Score must be a number between 0 to 30");
                System.out.println("Please try again: ");
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input!");
                invalidInputPrompt();
            }
        }
    }
}

class CICSFaculty {
    protected String name;
    protected String rank;
    protected int lecture;
    protected double lectureRate;
    protected int lab;
    protected double labRate;
    protected int preparation;
    protected double preparationRate;
    protected double competenceRate;
    CICSFaculty(String name, int lec, int lab, int pre, double eval)
    {
        this.name = name;
        this.lecture = lec;
        this.lab = lab;
        this.preparation = pre;
        this.competenceRate = CMR(eval);
    }

    public String getName() {
        return name;
    }
    public String getRank() {
        return rank;
    }
    public double getSemBonus() {
        return preparation * preparationRate * getSalary() + competenceRate * getSalary();
    }

    protected double getSalary() {
        return lecture * lectureRate + lab * labRate;
    }
    protected static double CMR(double eval) 
    {
        while (true) //Double-checking
        {
            try
            {
                if (eval < 0 || eval > 30)
                    throw new InvalidGradeException();
                break;
            }
            catch (Exception e) 
            {
                System.out.println("An error has occured in the system.");
                System.out.println("CMR bonus defaulting to 0");
                eval = 0;
            }
        }

        if (eval < 22)
            return 0;
        else if (eval < 24)
            return 0.05;
        else if (eval < 27)
            return 0.065;
        else if (eval <= 30)
            return 0.075;
        else
            return -999;
    }
}
class Instructor extends CICSFaculty
{
    Instructor(String name, int lec, int lab, int pre, double eval){
        super(name, lec, lab, pre, eval);
        rank = "Instructor";
        lectureRate = 2100;
        labRate = 2450;
        preparationRate = 0.023;
    }
}
class AsstProfessor extends CICSFaculty
{
    AsstProfessor(String name, int lec, int lab, int pre, double eval){
        super(name, lec, lab, pre, eval);
        rank = "Assistant Professor";
        lectureRate = 2550;
        labRate = 2950;
        preparationRate = 0.03;
    }
}
class Professor extends CICSFaculty
{
    Professor(String name, int lec, int lab, int pre, double eval){
        super(name, lec, lab, pre, eval);
        rank = "Professor";
        lectureRate = 3160;
        labRate = 3500;
        preparationRate = 0.04;
    }
}
