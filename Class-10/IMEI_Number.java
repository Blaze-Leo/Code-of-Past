/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : IMEI Number
 * Purpose : To check whether a number is a valid IMEI number
 *           or not
 */
import java.util.*;
class IMEI_Number
{
    Scanner sc=new Scanner(System.in);
    long n=0;
    //variable to store the number
    IMEI_Number()
    //default constructor
    {
        n=0;
        //variable assigned to its default value 
    }
    long sumOfDigits(long m)
    //method to calculate number of digits
    {
        long sum=0;
        //variable storing sum of digits
        for ( ;m>0;m/=10)
        //loop runs from the number to 1
        //by a step of 1 digit
          sum+=m%10;
        return sum;
        //the sum of digits is returned
    }
    void accept()
    //method to accept the number 
    {
        System.out.println("Enter an IMEI number");
        n=sc.nextLong();
        //the number is accepted
        if (n<(long)(Math.pow(10,14)) || n>=(long)(Math.pow(10,15)))
        //for numbers having less than 15 digits or more
        {
            System.out.println("Wrong Input");
            System.exit(0);
        }
    }
    void display()
    //method to display whether a number is a valid IMEI 
    //number or not
    {
        long s=0,m=n;
        //s stores final sum to be checked
        //m stores the number
        for (int i=1;i<=15;n/=10,i++)
        {
            if (i%2==0)
            //for even order of digits
              s+=sumOfDigits(n%10+n%10);
            else
            //for odd order of digits
              s+=n%10;
        }
        if (s%10 ==0)
        //displays when sum is divisible by 10
          System.out.println(m+" - Is an IMEI Number");
        else
        //displays when sum is not divisible by 10
          System.out.println(m+" - Is not an IMEI Number");
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output
    {
        IMEI_Number In=new IMEI_Number();
        //object is created 
        In.accept();
        In.display();
        //methods are called
    }
    //main method ends
}
//class ends
