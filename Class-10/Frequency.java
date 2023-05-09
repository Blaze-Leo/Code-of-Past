import java.util.*;
class Frequency
{
    Scanner sc=new Scanner(System.in);
    String s;
    int calculate(char ch)
    {
        int c=0;
        for(int i=0;i<s.length();i++)
        {
            if(ch==s.charAt(i))
            {c++;}
        }
        return c;
    }
    void display()
    {
        System.out.println("Enter String in Upper Case");
        s=sc.nextLine();
        System.out.println("CHARACTERS\t\tFREQUENCY");
        for(char c='A';c<='Z';c++)
        {
            if(calculate(c)!=0)
            {System.out.println(c+"\t\t\t"+calculate(c));}
        }
    }
    public static void main(String args[])
    {
        Frequency ff=new Frequency();
        ff.display();
    }
}