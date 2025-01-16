import java.util.*;
class Coin_Shift
{
    static String shift(String s,int n)
    {
        String g=s.substring(0,n);
        g=g.replace("0","2");
        g=g.replace("1","0");
        g=g.replace("2","1");
        s=s.substring(n);
        g=reverse(g);
        s=g+s;
        return s;
    }
    static String reverse(String s)
    {
        String g="";
        for(int i=s.length()-1;i>=0;i--)
          g+=""+s.charAt(i);
        return g;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of coins :");
        int n= sc.nextInt();
        int c=0;
        String s="";
        for(int i=0;i<n;i++)
          s+="0";
        s+=" ";
        String g=s;
        do
        {
            g=shift(g,c%n+1);
            c++;
        }while(!(g.equals(s)));
        System.out.println(c);
    }
}