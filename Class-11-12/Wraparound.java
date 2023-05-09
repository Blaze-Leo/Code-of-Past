import java.util.*;
class Wraparound
{
    static boolean wrap(String s)
    {
        int n[]=new int[s.length()];
        for(int i=0;i<s.length();i++)
          n[i]=s.charAt(i)-'0';
        int c=0;int p=0;
        while(n[c]!=-1)
        {
            p=c;
            c+=n[c];
            n[p]=-1;
            c%=n.length;
        }
        for(int i=0;i<s.length();i++)
          if(n[i]!=-1)
            return false;
        return true;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter number : ");
            String n=sc.next();
            if(n.compareTo("0")==0)
              break;
            if(wrap(n))
              System.out.println(n+" is a Wraparound number");
            else
              System.out.println(n+" is not a Wraparound number");
        }
    }
}
