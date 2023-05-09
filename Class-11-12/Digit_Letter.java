import java.util.*;
class Digit_Letter
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any charachter ::");
        char n=sc.next().charAt(0);
        if((int)(n) <= 57 && (int)(n) >= 48)
          System.out.println("It is a Digit");
        else if(((int)(n) <= 90 && (int)(n) >= 65 ) || ((int)(n) <= 122 && (int)(n) >= 97))
          System.out.println("It is a Letter");
        else
          System.out.println("It is a special character");
    }
}