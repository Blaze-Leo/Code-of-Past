/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : Diamond Pattern
 * Purpose : To display a pattern after accepting
 *           number of rows
 */
import java.util.*;
class Diamond_Pattern
{
    Scanner sc=new Scanner(System.in);
    int n;
    //variable to store number of rows
    Diamond_Pattern()
    //default constructor
    {
        n=0;
        //variable assigned to its default value
    }
    void accept()
    //method to accept the number of rows
    {
        System.out.println("Enter number of rows");
        n=sc.nextInt();
        //n is accepted from user
    }
    void display()
    //method to display  pattern
    {
        int s=0,e=-1;
        //variables to store number of spaces 
        //and number of elements in each row
        for(int i=1;i<=n*2-1;i++)
        {
            if (i<=n)
            {
                s=n-i;
                //s decreases from n-1 to 0
                e+=2;
                //e increases from 1 to n*2-1
            }
            else
            {
                s=i-n;
                //s increases from 0 to n-1
                e-=2;
                //e decreases from n*2-1 to 1
            }
            for(int k=1;k<=s;k++)
            //displays spaces
              System.out.print(" ");
            for (int j=1;j<=e;j++)
            //displays characters and in between 
            //spaces
            {
                if(j==1 || j==e)
                //for first and last "*"
                  System.out.print("*");
                else
                //for in between spaces
                  System.out.print(" ");
            }
            System.out.println();
            //goes to next line after one row
            //is displayed
        }
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output     
    {
        Diamond_Pattern dp=new Diamond_Pattern();
        //object created
        dp.accept();
        dp.display();
        //methods are called
    }
    //main method ends
}
//class ends
