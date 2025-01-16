import java.util.*;
class ROT13
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter sentence :");
        String s=sc.nextLine();
        String n="";
        for(int i=0;i<s.length();i++)
        {
            int c=s.charAt(i);
            if(c>64 && c<91)
            {
                if(c<78)
                  c+=13;
                else
                  c-=13;
            }
            else if(c>96 && c<123)
            {
                if(c<110)
                  c+=13;
                else
                  c-=13;
            }
            n+=(char)(c);
        }
        System.out.println("\n"+n);
    }
}
