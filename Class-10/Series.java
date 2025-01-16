import java.util.*;
class Series
{
    int x=0;
    int series1(int n)
    {
        int s=0;
        for(int i=2,c=1; i<=n ; i+=2)
        {
            i=c==1?i:-i;
            c=c==1?0:1;
            s+=i;
            i=c==0?i:-i;
        }
        return s;
    }
    double series2(int n,int x)
    {
        double s=0.0;
        for (double i=2.0;i<=n;i+=3.0)
        {s+= (double)(x)/i;}
        return s;
    }
    public static void main(String args[])
    {
        Series ss=new Series();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter value of x");
        ss.x=sc.nextInt();
        System.out.println("Sum of First Series : "+ss.series1(20));
        System.out.println("Sum of Second Series : "+ss.series2(20,ss.x));
    }
}