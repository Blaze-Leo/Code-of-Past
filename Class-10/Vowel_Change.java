import java.util.*;
class Vowel_Change 
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        String n,m="";char c;
        System.out.println("Enter any String");
        n=sc.next();
        for (int i=0; i<n.length(); i++)
        {
            c=Character.toLowerCase(n.charAt(i));
            if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u')
            {m=m + (char)(c+1);}
            else
            {m=m+c;}
        }
        System.out.println("New String -- "+m);
    }
}
