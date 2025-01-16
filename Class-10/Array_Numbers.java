import java.util.*;
class Array_Numbers
{
    Scanner sc=new Scanner(System.in);
    int a[];
    void accept()
    {
        System.out.println("Enter number of terms");
        a=new int[sc.nextInt()];
        System.out.println("Enter the terms");
        for (int i=0; i<a.length ; i++)
        {a[i]=sc.nextInt();}
    }
    void calculate()
    {
        int max=0,min=a[0],sum=0;
        for (int i=0; i<a.length ; i++)
        {
            if (a[i] > max)
            {max = a[i];}
            if (a[i] < min)
            {min = a[i];}

            sum+=a[i];
        }
        System.out.println("Minimum Value : "+min);
        System.out.println("Maximum Value : "+max);
        System.out.println("Sum of Elements : "+sum);
    }
    public static void main(String args[]) 
    {
        Array_Numbers an = new Array_Numbers();
        an.accept();
        an.calculate();
    } 
}