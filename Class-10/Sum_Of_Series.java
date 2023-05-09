/*
 * Author : Anurag Gupta
 * Date : 21.4.20
 * Program : Sum of Series
 * Purpose : To calculate the sum of series upto 
 *           n terms according to a menu
 */
import java.util.*;
class Sum_Of_Series
{
    Scanner sc=new Scanner(System.in);
    int opt=0;
    //variable to store option number entered by user
    Sum_Of_Series()
    //default constructor
    {
        opt=0;
        //assigned default value
    }
    int getFactorial(int m)
    //method to calculate facterial of a number
    {
        int f=1;
        //variable storing the factorial
        for ( ;m>0;m--)
        //the number decreases from m to 1
        //and all those numbers are multiplied
        //one by one
          f*=m;
        //the value is returned  
        return f;
    }
    double series1(int n1)
    //method to calculate sum of first series
    {
        double s1=0;
        //variable to store sum
        for (int i=1;i<=n1;i++)
        //4*i is first 4,then 8,12,...
        //as i=1,2,3...
          s1+=(1.0)/(4*i);
          //the sum is returned
        return s1;
        //variable is returned
    }
    double series2(int n2)
    //method to calculate sum of second series
    {
        int d=0;
        //variable to store the + or - sign
        double s2=0;
        //variable to store sum
        for (int i=1;i<=n2;i++)
        {
            d=i%2==0?-1:1;
            //for odd values of i the sign is +
            //and for even values sign is -
            s2+=(d)*((double)(i)/getFactorial(i));
            //the factorial function is called
        }
        return s2;
        //variable is returned
    }
    int series3(int n3)
    {
        int e=0;
        //variable to store 9 in negative or
        //or in positive numbers                                                              
        int s3=0;
        for (int i=1;i<=n3;i++)
        {
            e=i>20?-9:9;
            //when i>20 the value will be negative
            //so the 9 should be -9   like --
            //...1,19,0,9,-1,-19,-2,-29,...
            s3+=i%2!=0?9-(i/2):10*(10-(i/2)) + e;
            //in first and second term
            //i=1,  so it is 9-0=9
            //when i=2 it is 10*(10-1)+9=99
            //when i=22 term = -19
            //10*(10-11)-9= -10-9=-1
        }
        return s3;
        //variable is returned    
    }
    void menuDisplay()
    //method to display method in the specified
    //format
    {
        //menu is displayed in this format
        System.out.println("\tMenu");
        System.out.println("1.S=1/4+1/8+1/12...n terms");
        System.out.println("2.S=1/1!-2/2!+3/3!...n terms");
        System.out.println("3.S=9+99+8+89+7...n terms");
        //the series is displayed
        System.out.println("\n\nEnter Option--");
        opt=sc.nextInt();
        //the option is accepted
        switch(opt)
        //switch function is used
        {
            case 1:
            //for first series
            System.out.println("Enter value of 'n'");
            int n1=sc.nextInt();
            System.out.println("Sum of Series = "+series1(n1));
            break;
            
            case 2:
            //for second series
            System.out.println("Enter value of 'n'");
            int n2=sc.nextInt();
            System.out.println("Sum of Series = "+series2(n2));
            break;
            
            case 3:
            //for third series
            System.out.println("Enter value of 'n'");
            int n3=sc.nextInt();
            System.out.println("Sum of Series = "+series3(n3));
            break;
            
            default:
            //for wrong choice
            System.out.println("Wrong Choice");
            System.exit(0);
            break;
        }
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output
    {
        Sum_Of_Series sos=new Sum_Of_Series();
        //an object is made for the class
        sos.menuDisplay();
        //the method is called
    }
    //main method ends
}
//class ends
