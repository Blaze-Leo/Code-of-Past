/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : Numeric Pattern
 * Purpose : To display a pattern after accepting
 *           number of rows
 */
import java.util.*;
class Numeric_Pattern
{
    Scanner sc=new Scanner(System.in);
    int n;
    //variable to store number of rows
    void Numeric_Pattern()
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
        System.out.println();
    }
    void display()
    //method to display  pattern
    {
        int p,q;
        //variables to store start and end 
        //numbers of a row
        for(int i=1;i<=n;i++)
        {
            if(i%2==0)
            //when the line number is even
            //it prints in descending order
            {
                p=(i/2)*(i+1);
                q=p-i+1;
                //for line 2 
                //p=(2/2)*(2+1)=1*3=3
                //q=3-2+1=2
                for(int j=p;j>=q;j--)
                  System.out.print(j+" ");
            }
            else
            //when the line number is odd
            //it prints in ascending order
            {
                q=((i+1)/2)*i;
                p=q-i+1;
                //for line 3
                //q=((3+1)/2)*3=2*3=6
                //p=6-3+1=4
                for(int j=p;j<=q;j++)
                  System.out.print(j+" ");
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
        Numeric_Pattern np=new Numeric_Pattern();
        //object created
        np.accept();
        np.display();
        //methods are called
    }
    //main method ends
}
//class ends
