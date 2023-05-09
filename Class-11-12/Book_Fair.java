import java.util.*;
class Book_Fair
{
    Scanner sc=new Scanner(System.in);
    String Bname;
    double price;
    Book_Fair()
    {
        Bname="";
        price=0.0;
    }
    void input()
    {
        System.out.println("Enter name of book");
        Bname=sc.next();
        System.out.println("Enter price of book");
        price=sc.nextDouble();
    }
    void calculate()
    {
        if(price <= 1000)
          price*=0.98;
        else if (price <= 3000)
          price*=0.9;
        else
          price*=0.85;
        System.out.println("Book Name - "+Bname);
        System.out.println("Discounted Price = Rs."+price);
    }
    public static void main(String args[])
    {
        Book_Fair bf= new Book_Fair();
        bf.input();
        bf.calculate();
    }
}