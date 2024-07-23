import java.util.Scanner;

public class OOPMyString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str;
        System.out.print("Enter a string : ");
        str = in.nextLine();
        char[] ch = str.toCharArray();
        MyString mystr = new MyString(ch,str.length());
        mystr.firstWord();
        mystr.lastWord();
        mystr.reverse();
        mystr.NumOfWords();
        System.out.println();
    }
}
class MyString
{
    private char[] ch;
    private int length;
    MyString(char[] ch, int length){
        this.ch = ch;
        this.length = length;
    }
    public void firstWord() {
        int i = -1;
        while (ch[++i] != ' ' && i != length)
            System.out.print(ch[i]);
        System.out.println();
    }
    public void lastWord() {
        int i = length - 1;
        String s = "";
        while (ch[i] != ' ' && i != 0) {
            s = ch[i] + s;
            i--;
        }
        System.out.println(s);
    }
    public void reverse() {
        String s = "";
        for(int i = length - 1; i >= 0; i--)
            System.out.print(ch[i]);
        System.out.println();
    }
    public void NumOfWords() {
        int count = 1;//Assumption for no leading whitespaces
        for (int i = 0; i < length; i++) {
            if (ch[i] == ' ') //Assumption for no trailing whitespaces
                count++;
        }
        System.out.printf("Number of words: %d", count);
    }
}
