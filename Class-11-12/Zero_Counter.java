import java.util.*;
class Zero_Counter
{
    int counter(int n)
    {
        int s=0;
        for(;n>0;n/=10)
        {
            if(n%10==0)
              s++;
        }
        return s;
    }
    public static void main(String args[])
    {
        Zero_Counter zc=new Zero_Counter();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter end value :");
        int n=sc.nextInt();
        int s=0;
        for(int i=1;i<=n;i++)
          s+=zc.counter(i);
        System.out.println("Number of Zeroes = "+s);
    }
}
