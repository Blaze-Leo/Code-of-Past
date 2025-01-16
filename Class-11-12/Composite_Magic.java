import java.util.*;
class Composite_Magic
{
    static boolean composite(int n)
    {
        for(int i=2;i<n/2;i++)
        {
            if(n%i==0)
              return true;
        }
        return false;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter starting point :");
        int m=sc.nextInt();
        System.out.println("Enter ending point :");
        int n=sc.nextInt();
        if(m>n)
        {System.out.println("Invalid Input");System.exit(0);}
        int c=0;
        System.out.print(". ");
        for(int i=m;i<=n;i++)
        {
            if(composite(i) && i%9==1)
            {
                c++;
                System.out.print(i+" . ");
            }
        }
        System.out.println("\nFrequency of Composite Magic Numbers = "+c);
    }
}