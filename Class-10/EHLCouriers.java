/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : EHL Couriers
 * Purpose : To calculate the postal charges on
 *           a package
 */
import java.util.*;
class EHLCouriers
{
    Scanner sc=new Scanner(System.in);
    int wt;//weight of parcel
    EHLCouriers()
    //default constructors
    {
        wt=0;
        //the variable is assigned to its defaul value
    }
    //method to accept weight for calculation
    void acceptWeight()
    {
        System.out.println("\t Enter weight of Parcel --");
        wt=sc.nextInt();
        //the weight is accepted
    }
    void display()
    //method to display price for the parcel
    {
        double pc=0.0;
        if ((wt>=0) && (wt<=50))
        //it is true when weight is neither
        //negative nor greater than 50
           { pc=20.0;}
        else if (wt>50)
        //it is true when weight is greater 
        //than 50
        {
            pc=20.0+(Math.ceil((wt-50)/30.0))*15.0;
        }
        else 
        //it is a wrong value so program terminates
        {
           System.out.println ("\t Wrong Input");
           System.exit(0);             
        }
        System.out.println("\n");
        System.out.println("----------------------------------------------------");
        System.out.println("\t Weight of Parcel   - "+wt+" gm");
        System.out.println("\t Postal Charges     - Rs."+pc);
        //the bill is displayed in the above format
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output
    {
        EHLCouriers ehlc=new EHLCouriers();
        //an object is created for the class 
        ehlc.acceptWeight();
        ehlc.display();
    }
    //main method ends
}
//the class ends here
