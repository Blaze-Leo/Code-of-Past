/*
 * Author : Anurag Gupta
 * Date : 21.4.20
 * Program : Value of S
 * Purpose : To calculate the sum of series upto 
 *           n terms according to a menu
 */
import java.util.*;
class Value_Of_S
{
    Scanner sc=new Scanner(System.in);
    double s;
    //variable to calculate value of S
    Value_Of_S()
    //default constructor
    {
        s=0.0;
        //variable assigned to its default value
    }
    int factorial(int m)
    //method to calculate factorial
    {
        int f=1;
        //variable to store value
        for ( ;m>0;m--)
        //for loop running from m to 1
          f*=m;
        return f;
        //value is returned
    }
    double series_1(int n1)
    //method to calculate value of s in first series
    {
        int n=0,d=0;
        //n is numerator
        //d is denominator
        for (int i=1;i<=n1;i++)
        {
            for (int j=1;j<=i;j++)
            //calculates only value in numerator
              n+=j;
            for (int k=1;k<=i;k++)
            //calculates only value of denominator
              d+=factorial(k);
            s+=((double)(n))/((double)(d));
        }
        n=0;d=0;
        //the variables will again start a from 0
        //for the next term
        return s;
        //value of s is returned
    }
    double series_2(int n2)
    //calculates value of s in second series
    {
        for(int i=1;i<=n2;i++)
          s+=Math.pow(i,n2-i+1);
        //n2-i+1 because at first it is n2-1+1=n2
        //and at last it is n2-n2+1=1
        return s;
        //value of s is returned
    }
    void display()
    //method to display menu and answer
    {
        int ch=0;
        //variable to store choice number
        System.out.println("\tMenu\n");
        System.out.println("1. S=1/1! +(1+2)/(1!+2!) + (1+2+3)/(1!+2!+3!)+...");
        System.out.println("2. S=1^n + 2^(n-1) +...+ (n-1)^2 + n^1");
        //menu is displayed in the above format
        System.out.println("\n Enter your choice");
        ch=sc.nextInt();
        //the  choice accepted
        switch (ch)
        //switch case to display value according to choice
        {
            case 1:
            //for first series
            System.out.println("Enter value of n");
            int n1=sc.nextInt();
            System.out.println("Value of S = "+series_1(n1));
            break;
            
            case 2:
            //for second series
            System.out.println("Enter value of n");
            int n2=sc.nextInt();
            System.out.println("Value of S = "+series_2(n2));
            break;
            
            default:
            //for wrong choice
            System.out.println("Wrong Choice");
            System.exit(0);
            break;
        }
    }
    public static void main (String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output    
    {
        Value_Of_S vos=new Value_Of_S();
        //object is assigned
        vos.display();
        //the method is called
    }
    //main method ends
}
//class ends
