/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : Neon Emirp Spy Numbers
 * Purpose : To display whether a number is
 *           neon or emirp or spy according to a menu
 */
import java.util.*;
class Neon_Emirp_Spy
{
    Scanner sc=new Scanner(System.in);
    int ch;
    //variable storing the choice of user
    Neon_Emirp_Spy()
    //default constructor
    {
        ch=0;
        //variable assigned to default value
    }
    boolean isNeon(int n)
    //method to check whether a number is
    //a neon number or not
    {
        int m=n*n;
        //m is square of number
        int s=0;
        //stores sum of digits
        for ( ;m>0;m/=10)
        //the loop runs from m to 0
        //with a step of 1 digit
          s+=m%10;
        return (s==n);
        //if sum is equal to number
        //the value returned is true
    }
    boolean isPrime(int n)
    //checks whether a number is prime or not
    {
        for (int i=2;i<=n/2;i++)
        //loop runs from 2 to n/2
        {
            if (n%i==0)
            //if it has one factor 
            //other than 1
              return false;
        }
        return true;
        //if it has no factors
    }
    boolean isEmirp(int n)
    //checks whether a number is an
    //emirp number or not
    { 
        int m=0;
        //this variable stores the
        //reversed number
        if (isPrime(n))
        //first checks whether number is prime
        {
            for ( ;n>0;n/=10)
            //runs from n to 1
            //with step of 1 digit
              m=m*10 + n%10;
            if (isPrime(m))
            //checks whether reversed number is prime
              return true;
        }
        return false;
        //returns true if either condition 
        //is false
    }
    boolean isSpy(int n)
    //checks whether a number is a spy 
    //number or not
    {
        int s=0,p=1;
        //s stores sum of digits
        //p stores product of digits
        for ( ;n>0;n/=10)
        //loop runs from n to 1
        //with a step of 1 digit
        {
            s+=n%10;
            p*=n%10;
        }
        return (s==p);
        //returns true if both are equal
    }
    void display()
    {
        System.out.println("\tMenu\n1.Neon Number");
        System.out.println("2.Emirp Number\n3.Spy Number");
        System.out.println("4.Exit");
        //menu is displayed in the above format
        System.out.println("\n\nEnter your choice --");
        ch=sc.nextInt();
        //the choice of user is accepted
        switch(ch)
        //switch case is used
        {
            case 1:
            //when choice is Neon Number
            System.out.println("Enter any Number");
            int n1=sc.nextInt();
            if (isNeon(n1))
              System.out.println("It is a Neon Number");
            else
              System.out.println("It is not a Neon Number");
            break;  
           
            case 2:
            //when choice is Emirp Number
            System.out.println("Enter any Number");
            int n2=sc.nextInt();
            if (isEmirp(n2))
              System.out.println("It is an Emirp Number");
            else
              System.out.println("It is not an Emirp Number");
            break;
            
            case 3:
            //when choice is Spy Number
            System.out.println("Enter any Number");
            int n3=sc.nextInt();
            if (isSpy(n3))
              System.out.println("It is a Spy Number");
            else
              System.out.println("It is not a Spy Number");
            break;
            
            case 4:
            //when choice is exit
            System.out.println("Program Terminated");
            //System.exit(0);
            break;
            
            default:
            //if user gives wrong input
            System.out.println("Wrong Input");
            //System.exit(0);
            break;
        }
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output    
    {
        Neon_Emirp_Spy nes=new Neon_Emirp_Spy();
        //object is created
        nes.display();
        //the method is called
    }
    //main method ends
}
//the class ends
