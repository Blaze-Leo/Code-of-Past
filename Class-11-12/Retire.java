import java.util.*;
class Retire extends Personal
{
    int yrs;
    double pf,grat;
    Retire(String n,String p,String a,double b,int y)
    {
        super(n,p,a,b);
        name=n;pan=p;acc_no=a;yrs=y;
    }
    void provident()
    {
        pf=0.02*basic_pay*yrs;
        pf=(double)((int)(pf*100))/100;
    }
    void gratuity()
    {
        grat=yrs>9?basic_pay:0;
        grat=(double)((int)(grat*100))/100;
    }
    void display1()
    {
        provident();
        gratuity();
        System.out.println("Provident Fund = "+pf);
        System.out.println("Gratuity = "+grat);
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Name :");
        String name=sc.nextLine();
        System.out.println("Enter Pan Number :");
        String pan=sc.nextLine();
        System.out.println("Enter Account Number :");
        String acc_no=sc.nextLine();
        System.out.println("Enter Basic Pay :");
        double basic_pay=sc.nextDouble();
        System.out.println("Enter Years of service :");
        int yrs=sc.nextInt();
        Retire rr=new Retire(name,pan,acc_no,basic_pay,yrs);
        rr.display();
        rr.display1();
    }
}