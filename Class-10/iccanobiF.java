/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : icannobiF Number
 * Purpose : To check whether a number is a iccanobiF number
 *           or not
 */
import java.util.*;
class iccanobiF
{
    Scanner sc=new Scanner(System.in);
    int n=0;
    //variable to store the number
    iccanobiF()
    //default constructor
    {
        n=0;
        //variable assigned to its default value 
    }
    int reverse(int m)
    //method to calculate number of digits
    {
        int r=0;
        //variable storing reversed number
        for ( ;m>0;m/=10)
          r=r*10+m%10;
        return r;  
        //the reversed number is returned
    }
    void accept()
    //method to accept the number 
    {
        System.out.println("Enter any number");
        n=sc.nextInt();
        //the number is accepted
        if (n<0)
        //when number is negative
        {
            System.out.println("Sorry, You have entered an Invalid Number");
            System.exit(0);
        }
    }
    boolean calculate()
    //method returns true if number
    //belongs to the series 
    {
        int t1=1,t2=1,sum=0;
        //t1 is first term 
        //t2 is second term
        //sum stores their t1+t2 before they change
        for (int i=1; ;i++)
        //infinite loop gets broken when
        //it comes across return statement
        {
            sum=reverse(t1)+reverse(t2);
            t1=t2;
            t2=sum;
            //when t2 is 13 it becomes 8+31
            if (t1==n)
            //when number is a part of series
              return true;
            if (t1>n)
            //when the number can no longer be found
              return false;
        }
    }
    void display()
    //method to display the answer
    {
        if(calculate())
        //if it is true it is a fascinating number
          System.out.println("It is an iccanobiF Number");
        else
          System.out.println("Not an iccanobiF Number");        
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output
    {
        iccanobiF iF=new iccanobiF();
        //object is created 
        iF.accept();
        iF.display();
        //methods are called
    }
    //main method ends
}
//class ends
