import java.util.*;
class Circular_Prime
{
    boolean prime(int n)
    {
        for(int i=2;i<n;i++)
        {
            if(n%i == 0)
              return true;
        }
        return false;
    }
    public static void main (String args[])
    {
        Scanner sc=new Scanner(System.in);
        Circular_Prime cp=new Circular_Prime();
        System.out.println("Enter any number ::");
        int n=sc.nextInt();
        boolean s=true;
        for(int i=0;i<(Integer.toString(n)).length();i++)
        {
            n=Integer.valueOf(Integer.toString(n%10)+Integer.toString(n/10));
            System.out.println(n);
            if(!cp.prime(n))
            {
                s=false;
            }
        }
        String g=!s?"a Circular Number":"not a Circular Number";
        System.out.println("It is "+g);
        sc.close();
    }
}