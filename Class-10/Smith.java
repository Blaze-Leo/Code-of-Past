import java.util.*;
class Smith
{
    boolean prime(int n)
    {
        for(int i=2;i<n;i++)
        {
            if(n%i==0)
              return false;
        }
        return true;
    }
    int sum(int n)
    {
        int s=0;
        while(n>0)
        {
            s+=n%10;
            n/=10;
        }
        return s;
    }
    void display(int n)
    {
        if(prime(n) || n==1)
        {
            System.out.println("It is not a Composite Number");
            return;
        }
        int d=0,f=0;
        d=sum(n);
        for(int i=2;i<=n;i++)
        {
            if(prime(i) && n%i==0)
            {
                f=f*(int)(Math.pow(10,Integer.toString(i).length())) + i;
                n/=i;
                i--;
            }
        }
        f=sum(f);
        String g=d==f?"It is a ":"It is not a ";
        System.out.println(g+"Smith Number");
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Smith ss=new Smith();
        System.out.println("Enter any composite number ::");
        ss.display(sc.nextInt());
    }
}