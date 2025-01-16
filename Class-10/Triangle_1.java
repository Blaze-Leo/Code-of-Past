/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : Triangle
 * Purpose : To display area of selected geometric figure
 *           as per user's choice
 */
import java.util.*;
class Triangle_1
{
    Scanner sc=new Scanner(System.in);
    float ar;
    //stores area of triangle
    Triangle_1()
    //default constructor
    {
        ar=0.0f;
        //variable assigned to its default value
    }
    int menu()
    //method to display menu in desired manner
    {
        int opt;
        //variable accepting choice
        System.out.println("\t Menu Options");
        System.out.println(" 1. Scalene");
        System.out.println(" 2. Isosceles");
        System.out.println(" 3. Equilateral");
        System.out.println(" 4. Exit");
        //menu displayed in the above manner
        System.out.println("\n Enter Choice");
        opt=sc.nextInt();
        //choice is accepted
        return opt;
        //choice is returned
    }
    void display()
    //method to display the are of the figure of t
    //the user's choice
    {
        switch (menu())
        {
            case 1:
            //for scalene
            int a,b,c;
            //three sides of triangle
            System.out.println(" Enter value of 1st side --");
            a=sc.nextInt();
            System.out.println(" Enter value of 2nd side --");
            b=sc.nextInt();
            System.out.println(" Enter value of 3rd side --");
            c=sc.nextInt();
            float s=(float)((a+b+c)/2.0);
            ar=(float)(Math.sqrt(s*(s-a)*(s-b)*(s-c)));
            break;
            
            case 2:
            //for isosceles
            int co,ba;
            //common side and base
            System.out.println(" Enter the value of equal sides --");
            co=sc.nextInt();
            System.out.println(" Enter the value of base --");
            ba=sc.nextInt();
            ar=(float)((ba/2.0)*(Math.sqrt(co*co-((ba*ba)/4.0))));
            break;
            
            case 3:
            //for equilateral
            int eq;
            //equal side
            System.out.println(" Enter the value of equal sides --");
            eq=sc.nextInt();
            ar=(float)(((Math.sqrt(3.0))/4.0)*eq*eq);
            break;
            
            case 4:
            //for exit
            System.out.println("\t Program Terminated");
            System.exit(0);
            break;
            
            default:
            //for wrong choice
            System.out.println("\t Wrong Input");
            System.exit(0);
            break;
        }
        System.out.println("\n\n Area of Geometrical Figure (Triangle)   = "+ar);
    }
    public static void main (String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output    
    {
        Triangle_1 t=new Triangle_1();
        //object of class created
        t.display();
        //method is called
    }
    //main method ends
}
//class ends
