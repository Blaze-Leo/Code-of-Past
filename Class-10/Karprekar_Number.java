/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : Karprekar Number
 * Purpose : To display all Karprekar numbers 
 *           in a range
 */
import java.util.*;
class Karprekar_Number
{
    Scanner sc=new Scanner(System.in);
    int p,q;
    //variable to store range
    Karprekar_Number()
    //default constructor
    {
        p=q=0;
        //variable assigned to its default value
    }
    void accept()
    //method to accept range from user
    {
        System.out.println("Enter starting Number");
        p=sc.nextInt();
        System.out.println("Enter ending Number");
        q=sc.nextInt();
        //range is accepted
        if (p>q || p<=0 || q<=0)
        {
            System.out.println("Wrong Input");
            System.exit(0);
        }
    }
    boolean check(int n)
    //method to check whether a
    //number is a karprekar number or not
    {
        int m=0;
        int s=n*n;
        int c=0;
        //s stores squared number
        //c stores number of digits
        for (int i=s;i>0;i/=10)
        //loop to count number of digits
          c++;  
        if (c%2==0)
        //when there are even number of digits
        {
            m=s%(int)(Math.pow(10,c/2));
            m+=(s-m)/(Math.pow(10,c/2));
        }
        else
        //when there are even number of digits
        {
            m=s%(int)(Math.pow(10,c/2+1));
            m+=(s-m)/(Math.pow(10,c/2+1));
        }
        return (m==n);
        //the result is returned
    }
    void display()
    //to display whether a number is a
    //Karpekar Number or not
    {
        System.out.println("Kaprekar numbers are :\n");
        int f=0;
        //variable to store frequency of
        //kaprekar number
        for (int i=p;i<=q;i++)
        {
            if(check(i))
            {
                System.out.print(i+";");
                f++;
            }
        }
        System.out.println("\nFrequency of Kaprekar Numbers = "+f);
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output        
    {
        Karprekar_Number kn=new Karprekar_Number();
        //object is created
        kn.accept();
        kn.display();
        //the methods are called
    }
    //main method ends
}
//the class ends
