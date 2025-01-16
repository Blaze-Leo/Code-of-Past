import java.util.*;
class Factorial_Sum
{
    int factorial(int n)
    {
        if(n==1)
          return 1;
        int p=n;
        p*=factorial(n-1);
        return p;
    }
    int digit(int n)
    {
        if(n<10)
          return factorial(n);
        int s=0;
        s+=factorial(n%10);
        s+=digit(n/10);
        return s;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Factorial_Sum fs=new Factorial_Sum();
        System.out.println("Enter any number :");
        int n=sc.nextInt();
        String g =fs.digit(n)==n?"It satisfies":"It does not satisfy";
        System.out.println(g+" the condition");
    }
}
