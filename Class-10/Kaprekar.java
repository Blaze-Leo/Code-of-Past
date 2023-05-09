import java.util.*;
class Kaprekar
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any number ::");
        int n=sc.nextInt();
        int d=Integer.toString(n).length();
        int m=n*n;
        m=m%(int)(Math.pow(10,d)) + m/(int)(Math.pow(10,d));
        String g=m==n?"It is a ":"It is not a ";
        System.out.println(g+"Kaprekar Number");
    }
}