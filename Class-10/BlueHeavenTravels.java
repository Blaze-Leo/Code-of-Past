/*
 * Author : Anurag Gupta
 * Date : 20.4.20
 * Program : Blue Heaven Travels
 * Purpose : To calculate fare to be paid after journey
 *           as per the criteria.
 */
import java.util.*;
class BlueHeavenTravels
{
    Scanner sc=new Scanner(System.in);
    String pasgName;//passenger name
    int dist;//distance travelled
    char tMode;//mode of travelling
    //default constructor
    BlueHeavenTravels()
    {
        pasgName="";
        dist=0;
        tMode='\u0000';
        // all variables are set to their default values
    }
    //method to accept all the data for calculation
    void accept()
    {
        System.out.println("\t Enter Passenger's name --");
        pasgName=sc.nextLine();
        //name of passenger is accepted
        System.out.println("\t Enter distance travelled --");
        dist=sc.nextInt();
        //distance travelled is accepted
        System.out.println("\t Enter mode of travel ('C' for Car, 'B' for Bus) --");
        tMode=sc.next().charAt(0);
        //charecter is accepted where C means Car
        //and B means Bus
        tMode=Character.toUpperCase(tMode);
        //if by mistake user enters small character 
        //it turns it into Upper Case
    }
    //method to calculate fare to be paid
    double calculateFare()
    {
        double fare=0.0;
        //assigning variable for calculating fare 
        if (tMode=='C')
        // when option choosed is car
        {
            if (dist<0)
            //it is a wrong value so program terminates
              { 
                System.out.println ("\t Wrong Input");
                System.exit(0);
              }
            else if(dist<=20)
            // first criteria
              { fare=dist*40.0;}
            else if((dist<=60)&&(dist>20))
            // the first criteria is followed and the remaining distance 
            // is calculated by second criteria
              { fare=20.0*40.0 + (dist - 20)*35.0;}
            else
            //the first and second criteria are followed and then the
            //remaining distance is calculated by third criteria
              { fare=20.0*40.0 + 40.0*35.0 + (dist - 60)*32.0;}
        }
        else if (tMode=='B')
        //when option choosed is bus
        {
            if (dist<0)
            //it is a wrong value so program terminates
              { 
                System.out.println ("\t Wrong Input");
                System.exit(0);
              }
            else if(dist<=20)
            // first criteria
              { fare=dist*25.0;}
            else if((dist<=60)&&(dist>20))
            // the first criteria is followed and the remaining distance 
            // is calculated by second criteria
              { fare=20.0*25.0 + (dist - 20)*20.0;}
            else
            //the first and second criteria are followed and then the
            //remaining distance is calculated by third criteria
              { fare=20.0*25.0 + 40.0*20.0 + (dist - 60)*15.0;}
        }
        else
        //the program terminates if the user enters any other charecter
        {
            System.out.println ("\t Wrong Input");
            System.exit(0);
        }
        return fare;
        //the fare calculated is returned in double data type
    }
    void display()
    //method to display the journey details and fair
    {
        String modeT= (tMode=='C')?"Car":"Bus";
        //this variable stores the words Car or Bus 
        //based on the option accepted by user
        System.out.print("Passenger Name\t Mode of Journey\t ");
        System.out.println("Distance Travelled\t Total Fare");
        System.out.print("==============\t ===============\t ");
        System.out.println("==================\t ==========");
        System.out.print(pasgName+"\t\t"+modeT+"\t\t\t"+dist+" km");
        System.out.println("\t\t   Rs. "+calculateFare());
        //the bill is displayed in the above format
        //Every two consecutive printing functions are in reality
        //displayed in one line in the terminal window
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output
    {
        BlueHeavenTravels bht=new BlueHeavenTravels();
        //an object is created for the class 
        bht.accept();
        //the method for calculating fare cannot be called 
        //as it has a double type method declaration and not
        //void 
        //it has already been called in display() method
        bht.display();
    }
    //main method ends
}
//the class ends here
