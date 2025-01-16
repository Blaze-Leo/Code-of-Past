import java.util.*;
class Pallindrome 
{
    Scanner sc=new Scanner(System.in);
    String n,m="";
    void palin()
    {
        System.out.println("Enter any String");
        n=sc.next();
        for (int i=n.length() - 1; i>=0 ; i--)
        {m=m + n.charAt(i);}
        if (n.equalsIgnoreCase(m))
        {System.out.println("It is a Pallindromic String");}
        else
        {System.out.println("It is not a Pallindromic String");}
    }
    public static void main(String args[]) 
    {
        Pallindrome pp = new Pallindrome();
        pp.palin();
    } 
}
