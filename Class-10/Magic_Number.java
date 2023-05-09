/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : Magic Number
 * Purpose : To display whether a number is
 *           magic number or not
 */
import java.util.*;
class Magic_Number
{
    Scanner sc=new Scanner(System.in);
    int n;
    //variable to store number
    Magic_Number()
    //default constructor
    {
        n=0;
        //variable assigned to its default value
    }
    void accept()
    //method to accept number from user
    {
        System.out.println("Enter any Number");
        n=sc.nextInt();
        //number is accepted
    }
    void display()
    //method to calculate and display whether a
    //number is a magic number or not
    {
        int s=n;
        //s stores final sum
        //s=n so that if the number is only 1
        //it shows it to be a magic number
        while (n/10 > 0)
        //condition is false when number has only
        //1 digit
        {
            s=0;
            //every time s becomes 0
            //to add the digits
            for ( ;n>=1;n/=10)
            //runs until all the digits are added
              s+=n%10;
            n=s;
            //value of n becomes 0 in loop
            //so it is assigned value of s
            //to check in while loop
        }
        if(s==1)
          System.out.println("Magic Number");
        else
          System.out.println("Not a Magic Number");
        //the result is displayed in the above way
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output        
    {
        Magic_Number mn=new Magic_Number();
        //object is created
        mn.accept();
        mn.display();
        //the methods are called
    }
    //main method ends
}
//the class ends
