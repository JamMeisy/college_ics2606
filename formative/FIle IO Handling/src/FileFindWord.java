import java.io.*;
import java.util.Scanner;

public class FileFindWord {
    public static void main(String[] args) {
        try
        {
            File f1 = new File("test.txt");
            if (!f1.exists())
            {
                System.out.println("File not detected");
                System.exit(0);
            }

            //Prompts user a word to be detected
            Scanner in = new Scanner(System.in);
            System.out.println("Enter searched word:");
            String detect = in.next();
            in.close();

            //Buffered Reader readLine() allows to store a String line
            BufferedReader f2 = new BufferedReader(new FileReader(f1));
            StringBuilder s = new StringBuilder();
            
            int line = 0, ctr = 0;
            while (f2.ready())
            {
                ctr++;
                String[] temp = f2.readLine().split(" ");
                for (String i: temp)
                {
                    if (i.matches(detect)) {
                        line = ctr;
                    }
                    s.append(i).append(" ");
                }
            }
            if (line == 0)
                System.out.println("Word not detected");
            else
                System.out.println(detect + " detected at line " + line);
            System.out.println();
            System.out.println("Text File:" + s);
        }
        catch (IOException e)
        {
            System.out.println("Unexpected error occured");
            e.printStackTrace();
        }
    }
}
