import java.util.*;
class Series_2 
{
    double s1=0.0;
    int s2=0;
    int factorial(int n)
    {
        int p=1;
        while(n>0)
        {
            p*=n;
            n--;
        }
        return p;
    }
    double series1(int x,int n)
    {
        for(int i=1;i<n+1;i++)
        {
            s1+=(n%2==0?1:-1)*(Math.pow(x,n*2.0)/factorial(2*n+1));
        }
        return s1;
    }
    int series2(int n)
    {
        for(int i=1;i<n+1;i++)
        {
            for(int j=0;j<=2*n+1;j++)
              s2+=j;
        }
        return s2;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Series ss=new Series();
        System.out.println("Enter 1 or 2 for Series");
        if (sc.nextInt()==1)
        {
            System.out.println("Enter x and n ::");
            System.out.println(ss.series1(sc.nextInt(),sc.nextInt()));
        }
        else
        {
            System.out.println("Enter n ::");
            System.out.println(ss.series2(sc.nextInt()));
        }
    }
}
