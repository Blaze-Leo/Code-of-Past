import java.util.*;
class Double_Letter 
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        String n;
        System.out.println("Enter any String");
        n=sc.nextLine();
        int c=0;
        for (int i=0; i<n.length(); i++)
        {
            n=n.replace(n.charAt(i),Character.toUpperCase(n.charAt(i)));
        }
        for (int i=0; i<n.length()-1; i++)
        {
            if(n.charAt(i) == n.charAt(i+1)){c++;}
        }
        System.out.println("Occurence - "+c);
    }
}
