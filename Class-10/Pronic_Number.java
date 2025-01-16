import java.util.*;
class Pronic_Number
{
    boolean pronic(int n)
    {
        for (int i = 1;i < n; i++)
        {
            if (n%i==0)
            {
                if ((i * (i+1)) == n)
                {return true;} 
            }
        }
        return false;
    }
    public static void main(String args[]) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter any Number");
        int n=sc.nextInt();
        Pronic_Number pn = new Pronic_Number();
        if (pn.pronic(n))
        {System.out.println("It is a Pronic Number");}
        else
        {System.out.println("It is not a Pronic Number");}
    }
}