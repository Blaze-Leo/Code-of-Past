import java.util.*;
class N_Series
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter value of n");
        int n=sc.nextInt();
        int i=1,s=0;
        // if n is not a natural number then it is showed that
        // sum series is not possible
        if(n<1)
        {
            System.out.println("Sum series is not possible");
            return;
        }
        while(i<n)
        {
            System.out.print(i+" + ");
            s+=i;
            i++;
        }
        //the last number is added and the value is displayed
        s+=i;
        System.out.println(i+" = "+s);
    }
}