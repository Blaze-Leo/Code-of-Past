import java.util.*;
class Ascending
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter first number ::");
        int a=sc.nextInt();
        System.out.println("Enter second number ::");
        int b=sc.nextInt();
        System.out.println("Enter third number ::");
        int c=sc.nextInt();
        int h=Math.max(a,Math.max(b,c));
        int s=Math.min(a,Math.min(b,c));
        int m=a+b+c-s-h;
        System.out.println("Smallest Number :: "+s);
        System.out.println("Next Higher Number :: "+m);
        System.out.println("Highest Number :: "+h);
    }
}