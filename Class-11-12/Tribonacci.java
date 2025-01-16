import java.util.*;
class Tribonacci
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter value of n");
        int n=sc.nextInt();
        int a[]=new int[n];
        a[0]=0;
        a[1]=1;
        a[2]=1;
        for(int i=3;i<n;i++)
        {
            a[i]=a[i-1]+a[i-2]+a[i-3];
        }
        for(int i=0;i<n;i++)
        {
            if (i==n-1)
            {
                System.out.println(a[i]);
                break;
            }
            System.out.print(a[i]+",");
        }
        System.out.println("Last Term = "+a[a.length-1]);
        sc.close();
    }
}