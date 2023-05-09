import java.util.*;
class Fac_Sum
{
    int fact(int p,int n)
    {
        if(n==1)
        return p;
        p=p*n;
        return fact(p,n-1);
    }
    int sum(int s,int n)
    {
        if(n==1)
        return (s+1);
        s+=fact(1,n);
        return sum(s,n-1);
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Fac_Sum fs=new Fac_Sum();
        System.out.println("Enter any number :");
        System.out.println("Sum = "+fs.sum(0,sc.nextInt()));
    }
}
