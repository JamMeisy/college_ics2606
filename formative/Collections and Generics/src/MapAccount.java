import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private final long accNumber;
    private int id;
    private String name;
    private final int year;

    public Account(long accNumber, int id, String name, int year) {
        this.accNumber = accNumber;
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public long getAccNumber() {
        return accNumber;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return String.format("Account Number: %d\nAccount Id: %d\nName: %s\nAccount Created on: %d", accNumber, id, name, year);
    }
}
class AccountApp {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int choice = 0;
        Map<Long, Account> accountMap = new HashMap<>();
        accountMap.put(1L, new Account(1L, 10, "Admin", 2000));
        accountMap.put(10000000L, new Account(10000000L, 1000, "Default", 2000));
        accountMap.put(20001234L, new Account(20001234L, 7596, "Ana", 2013));
        accountMap.put(20005555L, new Account(20005555L, 7599, "Jam", 2014));

        while(choice != 3)
        {
            System.out.println("\nMenu\n1.Add an Account\n2.Search Account\n3.End");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(in.nextLine());

            switch (choice)
            {
                case 1 -> {
                    System.out.print("Enter name:");
                    String name = in.nextLine();
                    System.out.print("Enter account number: ");
                    long acc = Long.parseLong(in.nextLine());
                    System.out.print("Enter ID: ");
                    int id = Integer.parseInt(in.nextLine());
                    System.out.println("Enter year: ");
                    int year = Integer.parseInt(in.nextLine());
                    accountMap.put(acc, new Account(acc, id, name, year));
                }
                case 2 -> {
                    System.out.print("Enter account number: ");
                    long acc = Long.parseLong(in.nextLine());
                    if (accountMap.containsKey(acc))
                        System.out.println(accountMap.get(acc).toString());
                    else
                        System.out.println("Not found!");
                }

            }
        }
    }
}
