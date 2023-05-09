import java.util.*;
class Digital_World
{
    Scanner sc=new Scanner(System.in);
    int cost;//accepts cost of laptop
    //default constructor
    Digital_World()
    {
      cost=0;  //variable is set to its default values
    }
    //method to accept cost
    void accept()
    {
        System.out.println("Enter Cost of Laptop --");
        cost=sc.nextInt();
        //cost of laptop is accepted
    }
    //method to calculate final amount to be paid
    double calculate()
    {
        double fcost=0.0;//variable to calculate final cost
        if(cost<20000)
        {
          fcost=cost;
        }
        if(cost>=20000 && cost<30000)
        {
          fcost=cost - cost*0.1;
        }
        if(cost>=30000 && cost<40000)
        {
          fcost=cost - cost*0.15;
        }
        if(cost>=40000 && cost<50000)
        {
          fcost=cost - cost*0.18;
        }
        if(cost>=50000)
        {
          fcost=cost - cost*0.2;
        }
        //now for extra discount an sales tax
        fcost-=fcost*0.05;
        fcost+=fcost*0.12;
        return fcost;
    }
    void display()
    //method to display final cost payable
    {
        System.out.println("Amount Payable -- " +calculate());
    }
    public static void main(String args[])
    //the main method is used to call the methods in the 
    //desired way to get a correct output
    {
        Digital_World dw=new Digital_World();
        //an object is created for the class 
        dw.accept();
        dw.display();
    }
    //main method ends
}
//the class ends here
