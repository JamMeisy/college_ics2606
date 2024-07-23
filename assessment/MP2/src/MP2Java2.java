//1CSC Jam Meisy Tan
import java.util.Scanner;

public class MP2Java2 {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        boolean flag = true;
        System.out.println("Welcome to the CICS Health Club!"); //Introduction Prompt
        System.out.println("-----------------------------");
        System.out.println("This program aims to compute your annual membership fee");
        System.out.println("-----------------------------");

        String name;
        int memType = 0, payType = 0, supp = 0;
        int flagChoice, errorControl = 0;

        while (flag)
        {
            System.out.println("Input the necessary details below:"); //Prompting Input
            System.out.println("-----------------------------");

            //Input of Name
            System.out.print("Full name: ");
            name = in.nextLine();
            System.out.println("-----------------------------");

            //Input of Member Type
            boolean innerFlag = true;
            while (innerFlag)
            {
                System.out.println("\t\tMEMBER TYPE");
                System.out.println("1 - [Regular]");
                System.out.println("2 - [VIP]");
                System.out.print("Please select a member type: ");

                try
                {
                    memType = Integer.parseInt(in.nextLine());
                    if (memType != 1 && memType != 2)
                        throw new InvalidSelectionException();
                    innerFlag = false;
                }
                catch (NumberFormatException | InvalidSelectionException e)
                {
                    errorControl++;
                    System.out.printf("Invalid input! You have (%d) attempt left.\n", 3 - errorControl);
                    System.out.println("-----------------------------");
                    if (errorControl == 3)
                    {
                        invalidInputPrompt();
                        errorControl = 0;
                    }
                    continue;
                }

                switch (memType)
                {
                    case 1: {
                        System.out.println("You have chosen [Regular]");
                        break;
                    }
                    case 2: {
                        System.out.println("You have chosen [VIP]");
                        break;
                    }
                    default: { //Failsafe
                        System.out.println("An error has occurred in the system, please contact a CICS Health Club Professional");
                    }
                }
            }
            System.out.println("-----------------------------");

            //Input of Payment Type
            innerFlag = true;
            errorControl = 0;
            while (innerFlag)
            {
                System.out.println("\t\tPAYMENT TYPE");
                System.out.println("1 - [Monthly]");
                System.out.println("2 - [Quarterly]");
                System.out.println("3 - [Semi-Annually]");
                System.out.println("4 - [Annually] (via Cash)");
                System.out.print("Please select a payment type: ");

                try
                {
                    payType = Integer.parseInt(in.nextLine());
                    if (payType < 1 || payType > 4)
                        throw new InvalidSelectionException();
                    innerFlag = false;
                }
                catch (NumberFormatException | InvalidSelectionException e)
                {
                    errorControl++;
                    System.out.printf("Invalid input! You have (%d) attempt left.\n", 3 - errorControl);
                    System.out.println("-----------------------------");
                    if (errorControl == 3)
                    {
                        invalidInputPrompt();
                        errorControl = 0;
                    }
                    continue;
                }

                switch (payType)
                {
                    case 1: {
                        System.out.println("You have chosen to pay [Monthly]");
                        break;
                    }
                    case 2: {
                        System.out.println("You have chosen to pay [Quarterly]");
                        break;
                    }
                    case 3: {
                        System.out.println("You have chosen to pay [Semi-Annually]");
                        break;
                    }
                    case 4: {
                        System.out.println("You have chosen to pay [Annually] via Cash");
                        break;
                    }
                    default: { //Failsafe
                        System.out.println("An error has occurred in the system, please contact a CICS Health Club Professional");
                    }
                }
            }
            System.out.println("-----------------------------");

            //Input No. of Supplementary Members (In another method)
            switch (memType)
            {
                case 1 -> supp = validateNumberOfMembers(3);
                case 2 -> supp = validateNumberOfMembers(5);
            }

            //Loading the System
            System.out.println("-----------------------------");
            System.out.println("Computing...");
            System.out.println("-----------------------------");

            //Declaration of objects
            CICSHealthClub person = null;
            switch (memType)  //Instantiation
            {
                case 1 : { person = new Regular(name, payType, supp); break; }
                case 2 : { person = new VIP(name, payType, supp); break; }
            }
            try  //Displaying information
            {
                System.out.printf("\t[%s]: %s\n", person.getMemType(), person.getName());
                System.out.printf("Payment Term: %s\n", person.getPaymentTerm());
                System.out.printf("Number of Supplementary Members: %d\n", person.getSupplementary());
                System.out.println();
                System.out.printf("Miscellaneous Fee (per Payment Term): %.2f\n", person.computeMiscFee());
                System.out.printf("Supplementary Members Fee (per Payment Term): %.2f\n", person.computeSupplementaryFeePerPaymentTerm());
                System.out.printf("Membership Fee (per Payment Term): %.2f\n", person.computeMembershipFeePerPaymentTerm());
                System.out.println();
                System.out.printf("Annual Membership Fee: %.2f\n", person.computeAnnualMembershipFee());
            }
            catch (NullPointerException e)  //Failsafe, catching null pointer
            {
                System.out.println("An error has occurred in the system, please try again");
            }

            //Reuse program Prompt
            System.out.println("-----------------------------");
            System.out.println("Do you wish to continue the program: ");
            System.out.println("0 - [No], 1 - [Yes]");
            System.out.println("-----------------------------");
            try
            {
                flagChoice = Integer.parseInt(in.nextLine());
                if (flagChoice == 1)
                    continue;
                else if (flagChoice == 0)
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
    public static int validateNumberOfMembers(int y)
    {
        int x, errorControl = 0;
        while (true)
        {
            System.out.print("# of Supplementary Members: ");
            try
            {
                x = Integer.parseInt(in.nextLine());
                if (x < 0)
                    throw new InvalidSelectionException();
                else if (x > y)
                    throw new MaximumMembersReachedException();
                return x;
            }
            catch (InvalidSelectionException e)
            {
                errorControl++;
                System.out.printf("Invalid input! You have (%d) attempt left.\n", 3 - errorControl);
            }
            catch (MaximumMembersReachedException e)
            {
                errorControl++;
                System.out.printf("Exceeds maximum number of members! You have (%d) attempt left.\n", 3 - errorControl);
            }
            catch (NumberFormatException e)
            {
                errorControl++;
                System.out.printf("Invalid input! You have (%d) attempt left.\n", 3 - errorControl);
            }
            finally
            {
                System.out.println("-----------------------------");
                if (errorControl == 3)
                {
                    invalidInputPrompt();
                    errorControl = 0;
                }
            }
        }
    }
    public static void invalidInputPrompt() //Gives user a second chance to continue
    {
        System.out.println("Multiple invalid inputs detected! Do you still wish to continue the program: ");
        System.out.println("0 - [No]\t1 - [Yes]");
        System.out.println("-----------------------------");
        try
        {
            int x = Integer.parseInt(in.nextLine());
            if (x == 1) {
                System.out.println("Continuing Program...");
                System.out.println("-----------------------------");
                return;
            }
            if (x == 0) {
                System.out.println("Stopping Program...");
                System.exit(0);
            } else
                throw new InvalidSelectionException();
        }
        catch (NumberFormatException | InvalidSelectionException e) {
            System.out.println("Invalid input! Terminating program...");
            System.exit(1);
        }
    }
}
abstract class CICSHealthClub {
    protected String name;
    protected String memType;
    protected int supplementary;
    protected int paymentTerm;
    protected double supplementaryRatePerMonth;
    protected double miscFeePerMonth;
    protected double basicFee;

    public String getName() {
        return name;
    }
    public String getMemType() {
        return memType;
    }

    public int getSupplementary() {
        return supplementary;
    }

    public String getPaymentTerm() {
        switch (paymentTerm)
        {
            case 1: { return "Monthly"; }
            case 2: { return "Quarterly"; }
            case 3: { return "Semi-annually"; }
            case 4: { return "Annually (via Cash)"; }
            default: { return "An error has occurred in the system"; } //Failsafe
        }
    }
    public double computeMembershipFeePerPaymentTerm() { //Individual Method for computing Payment per Term
        switch (paymentTerm)
        {
            case 1, 2, 3: { return computeMiscFee() + basicFee + computeSupplementaryFeePerPaymentTerm(); }
            case 4: { return computeMiscFee() + (basicFee * 0.85 * 12) + computeSupplementaryFeePerPaymentTerm(); }
            default: { return -999; }
        }
    }
    public double computeMiscFee() { //Individual Method for computing Misc. Fee
        switch (paymentTerm)
        {
            case 1: { return miscFeePerMonth; }
            case 2: { return miscFeePerMonth * 3; }
            case 3: { return miscFeePerMonth * 6; }
            case 4: { return miscFeePerMonth * 12; }
            default: { return -999; }
        }
    }
    public double computeSupplementaryFeePerPaymentTerm() { //Individual Method for computing Fee for Supplementary Members
        switch (paymentTerm)
        {
            case 1: { return supplementary * supplementaryRatePerMonth; }
            case 2: { return supplementary * supplementaryRatePerMonth * 3; }
            case 3: { return supplementary * supplementaryRatePerMonth * 6; }
            case 4: { return supplementary * supplementaryRatePerMonth * 12; }
            default: { return -999; }
        }
    }
    public double computeAnnualMembershipFee() {
        switch (paymentTerm)
        {
            case 1: { return computeMembershipFeePerPaymentTerm() * 12; }
            case 2: { return computeMembershipFeePerPaymentTerm() * 4; }
            case 3: { return computeMembershipFeePerPaymentTerm() * 2;  }
            case 4: { return computeMembershipFeePerPaymentTerm(); }
            default: { return -999; }
        }
    }
}
class Regular extends CICSHealthClub {
    Regular(String name, int paymentType, int suppMembers) {
        super.name = name;
        super.paymentTerm = paymentType;
        super.supplementary = suppMembers;
        memType = "Regular";
        miscFeePerMonth = 1000;
        supplementaryRatePerMonth = 900;
        switch (paymentTerm) //Computes basic fee based on payment term
        {
            case 1, 4: { basicFee = 1200; break; }
            case 2: { basicFee = 3890; break; }
            case 3: { basicFee = 8230; break; }
            default: { basicFee = -999; }
        }
    }
}
class VIP extends CICSHealthClub {
    VIP(String name, int paymentType, int suppMembers) {
        super.name = name;
        super.paymentTerm = paymentType;
        super.supplementary = suppMembers;
        memType = "VIP";
        miscFeePerMonth = 1650;
        supplementaryRatePerMonth = 1000;
        switch (paymentTerm) //Computes basic fee based on payment term
        {
            case 1, 4: { basicFee = 1500; break; }
            case 2: { basicFee = 4865; break; }
            case 3: { basicFee = 10650; break; }
            default: { basicFee = -999; }
        }
    }
}
class InvalidSelectionException extends Exception
{
    InvalidSelectionException() {}
    InvalidSelectionException(String e)
    {
        super(e);
    }
}
class MaximumMembersReachedException extends Exception
{
    MaximumMembersReachedException() {}
    MaximumMembersReachedException(String e)
    {
        super(e);
    }
}