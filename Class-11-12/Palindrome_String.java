import java.util.*;
class Palindrome_String 
{
    static boolean palin(String n)
    {
        String m="";
        for (int i=n.length() - 1; i>=0 ; i--)
        {m=m + n.charAt(i);}
        return n.equalsIgnoreCase(m);
    }
    public static void main(String args[]) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any String");
        String n=sc.nextLine();
        int c=0;
        String[] word=n.split(" ");
        for(int i=0;i<word.length;i++)
        {
            if(palin(word[i]))
            {System.out.print(word[i]+" ");c++;}
        }
        if(c==0)
        System.out.println("\nNo Palindromic words");
        else
        System.out.println("\nNumber of Palindromes = "+c);
    } 
}
