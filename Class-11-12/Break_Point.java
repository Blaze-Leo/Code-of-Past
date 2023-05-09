import java.util.*;
class Break_Point
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter upper limit ::");
        int n=sc.nextInt();
        System.out.println("Enter Break Point ::");
        int b=sc.nextInt();
        int g=b;
        int s=0;
        if(n<1)
        {
            System.out.println("Sum series is not possible");
            return;
        }
        System.out.print(n);
        n--;
        b--;
        while(n>0)
        {
            if(b==0)
            {
                System.out.println(" = "+s);
                s=0;
                System.out.print(n);
                s+=n;
                n--;
                b=g-1;
            }
            System.out.print(" + "+n);
            s+=n;
            n--;
            b--;
        }
        System.out.println(" = "+s);
    }
}