import java.util.*;
class Personal
{
    String name,pan,acc_no;
    double basic_pay;
    Personal(String n,String p,String a,double bp)
    {
        name=n;pan=p;acc_no=a;basic_pay=bp;
    }
    void display()
    {
        System.out.println("\n\nName - "+name);
        System.out.println("Pan Number - "+pan);
        System.out.println("Account Number - "+acc_no);
        System.out.println("Basic Pay - "+basic_pay);
    }
}
