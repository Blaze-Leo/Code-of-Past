import java.util.*;
class Alternate_Fibonacci
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter value of n");
        int n=sc.nextInt();
        int a[]=new int[n];
        a[0]=0;
        a[1]=1;
        for(int i=2;i<n;i++)
        {
            a[i]=a[i-1]+a[i-2];
        }
        System.out.print("Alternate Fibonacci Series ::");
        for(int i=0;i<n;i+=2)
        {
            System.out.print(" "+a[i]);
        }
    }
}