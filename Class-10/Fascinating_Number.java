/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : Fascinating Number
 * Purpose : To check whether a number is a fascinating number
 *           or not
 */
import java.util.*;
class Fascinating_Number
{
    Scanner sc=new Scanner(System.in);
    int n=0;
    //variable to store the number
    Fascinating_Number()
    //default constructor
    {
        n=0;
        //variable assigned to its default value 
    }
    int countDigit(int m)
    //method to calculate number of digits
    {
        int c=0;
        //variable storing number of digits
        for ( ;m>0;m/=10)
        //loop runs from the number to 1
        //by a step of 1 digit
          c++;
        return c;
        //the number of digits is returned
    }
    void accept()
    //method to accept the number 
    {
        System.out.println("Enter any 3 or more digit number");
        n=sc.nextInt();
        //the number is accepted
        if (countDigit(n) < 3)
        //for numbers having less than 3 digits
        //the program terminates
        {
            System.out.println("Wrong Input");
            System.exit(0);
        }
    }
    boolean calculate()
    //method returning the true or false 
    //depending on the criteria
    {
        int p2=n*2;
        //stores the multiple of 2 
        int p3=n*3;
        //stores the multiple of 3
        int nn=0;
        //stores the new number
        //example - n=192
        nn=p3 + p2*(int)(Math.pow(10,countDigit(p3)));
        //nn=576 + 384 * 1000=384576
        nn+=n*(int)(Math.pow(10,countDigit(nn)));
        //nn= 384576 + 192*1000000=192384576
        int nt=0;
        //stores the number of times a digit occurs
        for (int i=1;i<=9;i++)
        //runs from 1 to 9
        //as it runs from 1 to 9
        //it dose not depend on the number of 0 s
        {
            for (int m=nn;m>0;m/=10)
            //runs from number to 1
            //with every step of a digit
            {
                if (m%10 == i)
                //if the number occurs nt 
                //increases by 1
                  nt++;
            }
            if (nt == 0 || nt>1)
            //if the digit doesnot occur or 
            //occurs more than once
              return false;
            nt=0;
            //number of times is assigned to
            //0 every time for next number
        }
        return true;
        //return true if the number passes all
        //criteria
    }
    void display()
    //method to display the answer
    {
        if(calculate())
        //if it is true it is a fascinating number
          System.out.println("It is a Fascinating Number");
        else
          System.out.println("Not a Fascinating Number");        
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output
    {
        Fascinating_Number fn=new Fascinating_Number();
        //object is created 
        fn.accept();
        fn.display();
        //methods are called
    }
    //main method ends
}
//class ends
