import java.util.*;
class Conjecture
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any number ::");
        int n=sc.nextInt();
        while(n!=1)
        {
            if(n%2==0)
            {
                n/=2;
                System.out.println(n+"    ("+2*n+" / 2)");
            }
            else
            {
                System.out.println((n*3+1)+"    ("+n+" * 3 + 1)");
                n=n*3+1;
            }
        }
    }
}