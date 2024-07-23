//1CSC - Jam Meisy F. Tan

import java.io.*;
import java.util.*;

public class MP3Java2 {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        //Demo initialization
        //Runs once if file has not created
        //Be wary of mismatched data with mapMP3.txt
        try {
            File demoFile = new File("accounts.txt");
            if (!demoFile.exists()) {
                demoFile.createNewFile();
                ObjectOutputStream accOut = new ObjectOutputStream(new FileOutputStream(demoFile));

                accOut.writeObject(new Account("111", 'O', '2', 1));
                accOut.writeObject(new Account("567", 'L', '1', 0));
                accOut.writeObject(new Account("456", 'O', '3', 2));
                accOut.writeObject(new Account("123", 'L', '2', 1));
                accOut.writeObject(new Account("890", 'O', '1', 1));
                accOut.writeObject(new Account("786", 'L', '3', 1));
                accOut.writeObject(new Account("057", 'O', '3', 2));
                accOut.writeObject(new Account("945", 'L', '2', 1));
                accOut.writeObject(new Account("655", 'O', '3', 3));
                accOut.writeObject(new Account("897", 'L', '1', 1));
                accOut.writeObject(new Account("801", 'O', '2', 2));
                accOut.writeObject(new EndFile());
                accOut.close();
                System.out.println("**** Demo file created ****");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            //Storing Accounts on Memory
            File accFile = new File("accounts.txt");
            ObjectInputStream accIn = new ObjectInputStream(new FileInputStream(accFile));

            List<Account> accountList = new ArrayList<>();
            Object tempAcc;
            while (!(((tempAcc = accIn.readObject()) instanceof EndFile)))
                accountList.add((Account) tempAcc);
            accIn.close();

            //Storing Maps on Memory
            File mapFile = new File("mapMP3.txt");
            BufferedReader mapIn = new BufferedReader(new FileReader(mapFile));

            HashMap<String, Person> map = new HashMap<>();
            String tempMapEntry;
            while ((tempMapEntry = mapIn.readLine()) != null) {
                String[] token = tempMapEntry.split(" ");
                map.put(token[0], new Person(token[1], token[2], token[3], token[4]));
            }
            mapIn.close();

            boolean running = true;
            while (running) {

                //Menu
                bar();
                System.out.println("\tCICS Condotel Menu");
                System.out.println("1. Add an Account");
                System.out.println("2. Delete an Account");
                System.out.println("3. Display an Account");
                System.out.println("4. Edit an Account");
                System.out.println("5. Search an Account");
                System.out.println("6. Display Total Monthly Dues");
                System.out.println("7. Display All Accounts");
                System.out.println("8. End");
                bar();
                int choice = validIntegerFromChoiceArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
                bar();

                switch (choice) {
                    case 1: {
                        String ID, fname, lname, age, address;
                        char unitType, occupantType;
                        int park;

                        System.out.println("Input the necessary details below");
                        bar();

                        ID = validID();

                        if (!map.containsKey(ID)) { //Unique ID
                            fname = validFirstName();
                            lname = validLastName();
                            age = validAge();
                            address = validAddress();
                            unitType = validUnitType();
                            occupantType = validOccupantType();
                            park = validParkingSpace(unitType);

                            Person person = new Person(fname, lname, age, address);
                            Account account = new Account(ID, occupantType, unitType, park);

                            accountList.add(account);
                            map.put(ID, person);
                        } else
                            System.out.println("Account already exists");
                        break;
                    }
                    case 2: {
                        String deleteID;
                        System.out.println("Deleting");
                        deleteID = validID();
                        bar();

                        if (map.containsKey(deleteID)) {
                            System.out.println("Account detected");
                            map.remove(deleteID);

                            for (int i = 0; i < accountList.size(); i++)
                                if (accountList.get(i).getID().equals(deleteID)) {
                                    //Confirmation of deletion
                                    System.out.println("Confirm deletion?");
                                    System.out.println("0. No");
                                    System.out.println("1. Yes");
                                    int confirm = validIntegerFromChoiceArray(new Integer[]{0, 1});
                                    if (confirm == 1) {
                                        System.out.println("Deleting contents");
                                        accountList.remove(i);
                                    } else
                                        System.out.println("Cancelled deletion");
                                    break;
                                }
                        } else
                            System.out.println("Account does not exist");

                        break;
                    }
                    case 3: {
                        String displayID;
                        System.out.println("Displaying");
                        displayID = validID();
                        bar();

                        if (map.containsKey(displayID))
                            for (Account account : accountList) {
                                if (account.getID().equals(displayID)) {
                                    Person person = map.get(displayID);
                                    System.out.printf("ID: %s\n", displayID);
                                    System.out.printf("First Name: %s\n", person.getFname());
                                    System.out.printf("Last Name: %s\n", person.getLname());
                                    System.out.printf("Age: %s\n", person.getAge());
                                    System.out.printf("Address: %s\n", person.getAddress());
                                    System.out.printf("Unit Type: %c-Bedroom\n", account.getUnitType());
                                    System.out.printf("Occupant Type: %s\n", account.getOccuType());
                                    System.out.printf("Number of Parking Spaces: %d\n", account.getNumPark());
                                    System.out.printf("Monthly Association Dues: %.2f\n", account.computeMAD());
                                    bar();
                                    break;
                                }
                            }
                        else
                            System.out.println("Account does not exist");
                        break;
                    }
                    case 4: {
                        String editID;
                        System.out.println("Editing");
                        editID = validID();


                        if (map.containsKey(editID)) {
                            Person person = map.get(editID);
                            Account account = null;
                            for (Account i : accountList)
                                if (i.getID().equals(editID))
                                    account = i;

                            assert account != null;

                            boolean editing = true;
                            while (editing) {
                                bar();
                                System.out.printf("\tEditing ID [%s]\n", editID);
                                System.out.printf("1. Edit First Name [%s]\n", person.getFname());
                                System.out.printf("2. Edit Last Name [%s]\n", person.getLname());
                                System.out.printf("3. Update Age [%s]\n", person.getAge());
                                System.out.printf("4. Update Address [%s]\n", person.getAddress());
                                System.out.printf("5. Update Unit Type [%c]\n", account.getUnitType());
                                System.out.printf("6. Update Occupant Type [%s]\n", account.getOccuType());
                                System.out.printf("7. Update Number of Parking Spaces [%d]\n", account.getNumPark());
                                System.out.println("8. Stop Editing");
                                bar();

                                int editChoice = validIntegerFromChoiceArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
                                bar();

                                switch (editChoice) {
                                    case 1: {
                                        person.setFname(validFirstName());
                                        break;
                                    }
                                    case 2: {
                                        person.setLname(validLastName());
                                        break;
                                    }
                                    case 3: {
                                        person.setAge(validAge());
                                        break;
                                    }
                                    case 4: {
                                        person.setAddress(validAddress());
                                        break;
                                    }
                                    case 5: {
                                        account.setUnitType(validUnitType());
                                        break;
                                    }
                                    case 6: {
                                        account.setOccuType(validOccupantType());
                                        break;
                                    }
                                    case 7: {
                                        account.setNumPark(validParkingSpace(account.getUnitType()));
                                        break;
                                    }
                                    case 8: {
                                        editing = false;
                                    }
                                }
                            }
                        } else
                            System.out.println("Account does not exist");
                        break;
                    }
                    case 5: {
                        System.out.println("1. Search by ID");
                        System.out.println("2. Search by Last Name");

                        int searchChoice = validIntegerFromChoiceArray(new Integer[]{1, 2});

                        if (searchChoice == 1) {
                            String searchID;
                            System.out.println("Searching");
                            searchID = validID();
                            bar();

                            if (map.containsKey(searchID)) {
                                System.out.printf("%5s%15s%15s\n", "ID", "First Name", "Last Name");
                                System.out.printf("%5s%15s%15s\n", searchID, map.get(searchID).getFname(), map.get(searchID).getLname());
                            } else
                                System.out.println("Account does not exist");
                        } else if (searchChoice == 2) //Accounts for multiple entries of the same name
                        {
                            String searchLname;
                            System.out.println("Searching");
                            searchLname = validLastName();
                            bar();

                            System.out.printf("%5s%15s%15s\n", "ID", "First Name", "Last Name");
                            for (Map.Entry<String, Person> personEntry : map.entrySet())
                                if (personEntry.getValue().getLname().equals(searchLname))
                                    System.out.printf("%5s%15s%15s\n", personEntry.getKey(),
                                            personEntry.getValue().getFname(), personEntry.getValue().getLname());
                        }
                        break;
                    }
                    case 6: {
                        //Initialization
                        double max, min, total;
                        max = accountList.get(0).computeMAD();
                        min = max;
                        total = 0;

                        for (Account loopAcc : accountList) {
                            double mad = loopAcc.computeMAD();
                            if (max < mad)
                                max = mad;
                            if (min > mad)
                                min = mad;

                            total += mad;
                        }

                        System.out.printf("Total Monthly Associated Dues: %.2f\n", total);
                        System.out.printf("Highest Monthly Associated Dues: %.2f\n", max);
                        System.out.printf("Lowest Monthly Associated Dues: %.2f\n", min);
                        break;
                    }
                    case 7: { //TABULAR FORM
                        accountList.sort((o1, o2) -> o1.getID().compareTo(o2.getID()));
                        System.out.printf("%5s%15s%15s%5s%15s%6s%10s%17s%25s\n",
                                "ID", "First Name", "Last Name",
                                "Age", "Address", "Unit",
                                "Occupant", "# Parking Space", "Monthly Associated Dues");
                        for (Account loopAcc : accountList) {
                            Person person = map.get(loopAcc.getID());
                            System.out.printf("%5s%15s%15s%5s%15s%6c%10s%17d%25.2f\n",
                                    loopAcc.getID(), person.getFname(), person.getLname(),
                                    person.getAge(), person.getAddress(), loopAcc.getUnitType(),
                                    loopAcc.getOccuType(), loopAcc.getNumPark(), loopAcc.computeMAD());
                        }
                        break;
                    }
                    case 8: {
                        running = false;
                    }
                }
            }

            //Failsafe to ensure both files have consistent data
            for (Account i : accountList)
                if (!map.containsKey(i.getID())) {
                    System.err.printf("Error: Account %s has been corrupted\n", i.getID());
                    System.err.println("Data does not correspond to \"mapMP3.txt\"");
                    System.err.println("Automaticallly deleting contents");
                    accountList.remove(i);
                }

            //Stores Accounts on File
            ObjectOutputStream accOut = new ObjectOutputStream(new FileOutputStream(accFile));
            for (Account i : accountList)
                accOut.writeObject(i);
            accOut.writeObject(new EndFile());
            accOut.close();

            System.out.println("\"account.txt\" has been saved!");

            //Stores Maps on File
            BufferedWriter mapOut = new BufferedWriter(new FileWriter(mapFile));
            for (Map.Entry<String, Person> i : map.entrySet()) {
                Person j = i.getValue();
                mapOut.write(i.getKey() + " " + j.getFname() + " " + j.getLname() + " " + j.getAge() + " " + j.getAddress() + "\n");
            }
            mapOut.close();
            System.out.println("\"mapMP3.txt\" has been saved!");
        } catch (AssertionError e) {
            System.err.println("Something went wrong with the program!");
        } catch (EOFException e) {
            System.err.println("File read data has been corrupted");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("File has been corrupted!");
            e.printStackTrace();
        }
        System.out.println("Ending Program...");
    }

    public static void bar() {
        System.out.println("=====================================");
    }

    public static Integer validIntegerFromChoiceArray(Integer[] array) //Validates choice input
    {
        while (true) {
            System.out.print("Select a number: ");
            try {
                Integer x = Integer.parseInt(in.nextLine());
                for (Integer i : array)
                    if (x.equals(i))
                        return x;
                throw new InvalidSelectionException();
            } catch (InvalidSelectionException e) {
                System.out.println("Invalid menu selection!");
                //stillContinueProgramPrompt();

            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                //stillContinueProgramPrompt();
            }
        }
    }

    public static String validID() {
        while (true) {
            try {
                System.out.print("ID: ");
                String x = in.nextLine();
                int y = Integer.parseInt(x);

                if (x.length() != 3)
                    throw new InvalidSelectionException();

                if (y >= 0 && y <= 999)
                    return x;

                throw new InvalidSelectionException();

            } catch (InvalidSelectionException e) {
                System.out.println("Invalid ID number!");
                //stillContinueProgramPrompt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                //stillContinueProgramPrompt();
            }
        }
    }

    public static String validFirstName() {
        while (true) {
            try {
                System.out.print("First Name: ");
                String x = validWord();
                return x;
            } catch (InvalidSelectionException e) {
                System.out.println("Invalid name!");
                //stillContinueProgramPrompt();
            }
        }
    }

    public static String validLastName() {
        while (true) {
            try {
                System.out.print("Last Name: ");
                String x = validWord();
                return x;
            } catch (InvalidSelectionException e) {
                System.out.println("Invalid name!");
                //stillContinueProgramPrompt();
            }
        }
    }

    public static String validAge() {
        while (true) {
            try {
                System.out.print("Age: ");
                int x = Integer.parseInt(in.nextLine());
                if (x >= 10 && x <= 99)
                    return String.valueOf(x);

                throw new InvalidSelectionException();

            } catch (InvalidSelectionException e) {
                System.out.println("Invalid age!");
                //stillContinueProgramPrompt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                //stillContinueProgramPrompt();
            }
        }
    }
    public static String validAddress() {
        while (true) {
            try {
                System.out.print("Address: ");
                String x = validWord();
                return x;
            } catch (InvalidSelectionException e) {
                System.out.println("Invalid address!");
                //stillContinueProgramPrompt();
            }
        }
    }

    public static char validUnitType() {
        while (true) {
            try {
                System.out.print("Unit Type: ");
                char x = in.nextLine().charAt(0);
                if (x == '1' || x == '2' || x == '3')
                    return x;

                throw new InvalidSelectionException();
            } catch (InvalidSelectionException e) {
                System.out.println("Invalid input!");
                //stillContinueProgramPrompt();
                System.out.println("Selections: ");
                System.out.println("1 - [1-Bedroom]");
                System.out.println("2 - [2-Bedroom]");
                System.out.println("3 - [3-Bedroom]");
                bar();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                //stillContinueProgramPrompt();
            }
        }
    }

    public static char validOccupantType() {
        while (true) {
            try {
                System.out.print("Occupant Type: ");
                char x = Character.toUpperCase(in.nextLine().charAt(0));
                if (x == 'L' || x == 'O')
                    return x;

                throw new InvalidSelectionException();
            } catch (InvalidSelectionException e) {
                System.out.println("Invalid input!");
                //stillContinueProgramPrompt();
                System.out.println("Selections: ");
                System.out.println("L - [Lesser]");
                System.out.println("O - [Owner]");
                bar();
            }
        }
    }

    public static int validParkingSpace(char unit) {
        while (true) {
            try {
                System.out.print("Number of Parking Spaces: ");
                int x = Integer.parseInt(in.nextLine());
                int y = Character.getNumericValue(unit); //Convenient method to compare
                if (x < 0)
                    throw new InvalidSelectionException();
                if (x > y)
                    throw new MaximumReachedException();
                return x;
            } catch (InvalidSelectionException e) {
                System.out.println("Invalid number of parking spaces!");
                //stillContinueProgramPrompt();
            } catch (MaximumReachedException e) {
                System.out.println("Exceeds maximum number of parking spaces!");
                //stillContinueProgramPrompt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                //stillContinueProgramPrompt();
            }
        }
    }

    public static String validWord() throws InvalidSelectionException//Validates a string if it is a word
    {
        String x = in.nextLine();
        char[] array = x.toCharArray();
        for (char i : array)
            if (!((i >= 65 && i <= 90) || (i >= 97 && i <= 122)))
                throw new InvalidSelectionException();
        return x;

    }

    /* Removed since it causes inconvenience

    public static void stillContinueProgramPrompt()
    {
        bar();
        System.out.println("Do you still wish to continue the program?");
        System.out.println("0. No");
        System.out.println("1. Yes");

        try {
            System.out.print("Choice: ");
            int x = Integer.parseInt(in.nextLine());
            if (x == 1) {
                System.out.println("Continuing Program...");
                bar();
                return;
            }
            if (x == 0) {
                System.out.println("Stopping Program...");
                System.exit(0);
            } else
                throw new InvalidSelectionException();
        }
        catch (NumberFormatException | InvalidSelectionException e) {
            System.err.println("Invalid input!");
            System.err.println("All file changes will not be saved");
            System.err.println("Terminating program...");
            System.exit(1);
        }
    }
     */
}

class Person {
    private String fname;
    private String lname;
    private String age;
    private String address;

    public Person(String fname, String lname, String age, String address) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.address = address;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

class Account implements Serializable {
    private String ID;
    private char occuType;
    private char unitType;
    private int numPark;

    public Account(String ID, char occuType, char unitType, int numPark) {
        this.ID = ID;
        this.occuType = occuType;
        this.unitType = unitType;
        this.numPark = numPark;
    }

    public String getID() {
        return ID;
    }

    public String getOccuType() {
        if (occuType == 'L')
            return "Lesser";
        else if (occuType == 'O')
            return "Owner";
        else
            return "An error has occurred";
    }

    public char getUnitType() {
        return unitType;
    }

    public int getNumPark() {
        return numPark;
    }

    public void setOccuType(char occuType) {
        this.occuType = occuType;
    }

    public void setUnitType(char unitType) {
        this.unitType = unitType;
    }

    public void setNumPark(int numPark) {
        this.numPark = numPark;
    }

    public double computeMAD() {
        double area = 0, rate = 0;
        switch (unitType) {
            case '1': {
                area = 30;
                if (occuType == 'L')
                    rate = 89.50;
                else if (occuType == 'O')
                    rate = 85.75;
                break;
            }
            case '2': {
                area = 65;
                if (occuType == 'L')
                    rate = 95.75;
                else if (occuType == 'O')
                    rate = 89.50;
                break;
            }
            case '3': {
                area = 96;
                if (occuType == 'L')
                    rate = 102.80;
                else if (occuType == 'O')
                    rate = 93.25;
                break;
            }
        }
        return (area + (numPark * 12.5)) * rate;
    }
}

class EndFile implements Serializable {
}

class InvalidSelectionException extends Exception {
    InvalidSelectionException() {
    }

    InvalidSelectionException(String e) {
        super(e);
    }
}

class MaximumReachedException extends Exception {
    MaximumReachedException() {
    }

    MaximumReachedException(String e) {
        super(e);
    }
}