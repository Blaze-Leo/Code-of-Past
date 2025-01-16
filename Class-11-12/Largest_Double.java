import java.util.*;
class Largest_Double
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter first number ::");
        double n1=sc.nextDouble();
        System.out.println("Enter second number ::");
        double n2=sc.nextDouble();
        System.out.println("Enter third number ::");
        double n3=sc.nextDouble();
        double n=n1>n2?n1:n2;
        n=n>n3?n:n3;
        System.out.println("Largest Number = "+n);
    }
}