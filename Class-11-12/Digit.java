import java.util.*;
class Digit
{    
    int s=0,m=0,d=0;
    void count(int n)
    {
        if(n==0)
        {
            System.out.println("Number of Digits = "+d);
            System.out.println("Reversed Number = "+m);
            System.out.println("Sum of Digits = "+s);
            return;
        } 
        d++;
        s+=n%10;
        m=m*10+n%10;
        count(n/10);
    }
    void split(int n,int t)
    {
        if(n/(int)(Math.pow(10,2*t)) < 10)
        {
            int c1=n/(int)(Math.pow(10,t));
            int c2=n%(int)(Math.pow(10,t));
            System.out.println("First Part \n");
            count(c1);
            System.out.println("\nSecond Part\n");
            s=0;
            m=0;
            d=0;
            count(c2);
            return;
        }
        split(n,t+1);
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any number :");
        int n=sc.nextInt();
        Digit dd=new Digit();
        dd.split(n,1);
    }
}

