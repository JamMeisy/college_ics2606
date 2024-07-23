import java.io.*;

public class FileWord345 {
    public static void main(String[] args) {
        String display3 = "", display4 = "", display5 = "";
        try
        {
            File file = new File("test.txt");
            if (!file.exists())
            {
                System.out.println("File not detected");
                System.exit(0);
            }

            //Buffered Reader readLine() allows to store a String line
            BufferedReader f2 = new BufferedReader(new FileReader(file));
            String[] temp;

            while (f2.ready())
            {
                temp = f2.readLine().split(" ");
                for (String i : temp)
                {
                    if (i.length() == 3)
                        display3 += i + " ";
                    else if (i.length() == 4)
                        display4 += i + " ";
                    else if (i.length() == 5)
                        display5 += i + " ";
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Unexpected error occured");
            e.printStackTrace();
        }

        if (display3.equals(""))
            System.out.println("No 3-word detected");
        else
            System.out.println(display3);

        if (display4.equals(""))
            System.out.println("No 4-word detected");
        else
            System.out.println(display4);

        if (display5.equals(""))
            System.out.println("No 5-word detected");
        else
            System.out.println(display5);
    }
}
