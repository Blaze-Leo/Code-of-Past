import java.util.*;
class Buzz_GCD
{
    Scanner sc=new Scanner(System.in);
    int m=0;
    void buzz(int n)
    {
        if((n%7 == 0) || (n%10==7))
        {System.out.println("It is a Buzz Number");}
        else
        {System.out.println("It is not a Buzz Number");}
    }
    void gcd(int m1,int m2)
    {
        int r;
        while(m1%m2 != 0)
        {
            r=m1%m2;
            m1=m2;
            m2=r;
        }
        System.out.println("GCD is "+m2);
    }
    void menu()
    {
        System.out.println("Menu\n1.Buzz Number\n2.GCD");
        m=sc.nextInt();
        switch(m)
        {
            case 1:
            System.out.println("Enter Number --");
            m=sc.nextInt();
            buzz(m);
            break;
            case 2:
            System.out.println("Enter Numbers --");
            int m1=sc.nextInt();int m2=sc.nextInt();
            if(m1>m2) {gcd(m1,m2);}
            else {gcd(m2,m1);}
            break;
            default:
            System.out.println("Wrong Input");
            System.exit(0);
        }
    }
    public static void main(String args[])
    {
        Buzz_GCD bg=new Buzz_GCD();
        bg.menu();
    }
}