import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CollectionSet {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        Set<Integer> setC = new HashSet<>();

        //Menu
        bar();
        System.out.println("                           Menu                            ");
        System.out.println(" 1 - Add element to Set A");
        System.out.println(" 2 - Add element to Set B");
        System.out.println(" 3 - Remove element from Set A");
        System.out.println(" 4 - Remove element from Set B");
        System.out.println(" 5 - Display Set A");
        System.out.println(" 6 - Display Set B");
        System.out.println(" 7 - Display Union of A and B");
        System.out.println(" 8 - Display Intersection of A and B");
        System.out.println(" 9 - Display A - B");
        System.out.println(" 10 - Display B - A");
        System.out.println(" 11 - Check if A is a subset of B");
        System.out.println(" 12 - Check if B is a subset of A");
        System.out.println(" 13 - Quit");
        bar();
        Integer[] choices = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        while(true)
        {
            //Choice (Validated in a method)

            int choice = validChoiceInt(choices);
            bar();

            //Early exit when user wants to quit
            if (choice == 13)
                break;

            switch (choice)
            {
                case 1 ->
                {
                    System.out.println("Enter element to set A");
                    setA.add(in.nextInt());
                }
                case 2 ->
                {
                    System.out.println("Enter element to set B");
                    setB.add(in.nextInt());
                }
                case 3 ->
                {
                    System.out.println("Remove element from set A");
                    setA.remove(in.nextInt());
                }
                case 4 ->
                {
                    System.out.println("Remove element from set B");
                    setB.remove(in.nextInt());
                }
                case 5 -> System.out.println("Set A: " + setA);
                case 6 -> System.out.println("Set B: " + setB);
                case 7 ->
                {
                    setC.addAll(setA);
                    setC.addAll(setB);
                    System.out.println("Set A union B: " + setC);

                }
                case 8 ->
                {
                    setC.addAll(setA);
                    setC.retainAll(setB);
                    System.out.println("Set A intersects B: " + setC);
                }
                case 9 ->
                {
                    setC.addAll(setA);
                    setC.removeAll(setB);
                    System.out.println("Set A - B: " + setC);
                }
                case 10 ->
                {
                    setC.addAll(setB);
                    setC.removeAll(setA);
                    System.out.println("Set B - A: " + setC);
                }
                case 11 ->
                {
                    if (setB.containsAll(setA))
                        System.out.println("A is a subset of B");
                    else
                        System.out.println("A is not a subset of B");
                }
                case 12 ->
                {
                    if (setA.containsAll(setB))
                        System.out.println("B is a subset of A");
                    else
                        System.out.println("B is not a subset of A");
                }
            }
            setC.clear();
            bar();
        }
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
                Integer x = in.nextInt();
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
