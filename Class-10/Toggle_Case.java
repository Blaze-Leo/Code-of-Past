import java.util.*;
class Toggle_Case 
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        String n,m="";
        System.out.println("Enter any String");
        n=sc.nextLine();
        for (int i=0; i<n.length(); i++)
        {
            if(Character.isUpperCase(n.charAt(i)))
            {m=m + (Character.toLowerCase(n.charAt(i)));}
            else if(Character.isLowerCase(n.charAt(i)))
            {m=m + (Character.toUpperCase(n.charAt(i)));}
            else
            {m=m+n.charAt(i);}
        }
        System.out.println("New String -- "+m);
    }
}
