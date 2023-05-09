/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : Upcounting Digits 
 * Purpose : To display the next smallest
 *           Upcounting Digits number
 */
import java.util.*;
class Upcounting_Digits
{
    Scanner sc=new Scanner(System.in);
    int n=0;
    //variable to store the number
    Upcounting_Digits()
    //default constructor
    {
        n=0;
        //variable assigned to its default value 
    }
    void accept()
    //method to accept the number 
    {
        System.out.println("Enter any number");
        n=sc.nextInt();
        //the number is accepted
        if (n<=0)
        //for negative numbers
        //program terminates
        {
            System.out.println("Wrong Input");
            System.exit(0);
        }
    }
    boolean check(int m)
    //method returning true if the 
    //number satisfies all criteria
    {
        int d=m%10;
        //number storing a digit
        m/=10;
        //if m=567
        //d=7 and m=56
        for ( ;m>0;d=m%10,m/=10)
        {
            if (d-1 != m%10)
            //6 != 6 = false
              return false;
            //the loop continues until all three
            //digits are checked or it returns false
        }
        return true;
    }
    void display()
    //method displaying the next number
    {
        for (n++ ; ;n++)
        {
            if(check(n))
            {
                System.out.println("Next Upcounting Digit Number is "+n);
                break;
            }
        }
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output
    {
        Upcounting_Digits ud=new Upcounting_Digits();
        //object is created 
        ud.accept();
        ud.display();
        //methods are called
    }
    //main method ends
}
//class ends
