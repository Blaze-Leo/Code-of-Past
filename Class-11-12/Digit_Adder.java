import java.util.*;
class Digit_Adder
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any number : ");
        System.out.println("Total Sum = "+((sc.nextLong()+8)%9+1));
        sc.close();
    }
}
