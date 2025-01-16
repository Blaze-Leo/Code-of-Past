import java.util.*;
class X_Series
{
    Scanner sc=new Scanner(System.in);
    int factorial(int n)
    //method for calculating factorial
    {
        int p=1;
        for(int i=1;i<=n;i++)
          p*=i;
        return p;
    }
    void series()
    //method for calculating sum of the series
    {
        double s=0;
        System.out.println("Enter any value of X ::");
        int x=sc.nextInt();
        System.out.println("Enter number of elements ::");
        int n=sc.nextInt();
        for(int i=1;i<=n;i++)
            s+=(i%2==0?-1:1)*(Math.pow(x,i))/(factorial(i));
        //for odd number it is addition and for even number it is subtraction
        System.out.println("Series : "+s);
        //the sum is displayed
    }
    public static void main(String args[])
    {
        X_Series xs=new X_Series();
        xs.series();
    }
}