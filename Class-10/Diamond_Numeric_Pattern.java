/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : Diamond Numeric Pattern
 * Purpose : To display a pattern after accepting
 *           number of rows
 */
import java.util.*;
class Diamond_Numeric_Pattern
{
    Scanner sc=new Scanner(System.in);
    int n;
    //variable to store number of rows
    Diamond_Numeric_Pattern()
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
        int s=0;
        //variable storing number of spaces
        int d=1;
        //variable storing starting number of each row
        for(int i=1;i<=2*n-1;i++)
        //loop runs from 1 to 2n-1 
        {
            for (int k=1;k<=s;k++)
            //loop to print spaces
              System.out.print(" ");
            for (int j=d;j<=n;j++)
            //loop to print digits as given
              System.out.print(j+" ");
            System.out.println();
            //goes to next line after
            //printing every row
            if(i<n)
            //when less than n
            {
                 s++;
                 d++;
                 //number of spaces and value
                 //of starting digit increases
            }
            else
            //when equal to or greater than n
            {
                s--;
                d--;
                //number of spaces and value
                //of starting digit decreases
            }
        }
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output     
    {
        Diamond_Numeric_Pattern dnp=new Diamond_Numeric_Pattern();
        //object created
        dnp.accept();
        dnp.display();
        //methods are called
    }
    //main method ends
}
//class ends
