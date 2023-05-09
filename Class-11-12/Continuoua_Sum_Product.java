import java.util.*;
class Continuous_Sum_Product
{
    int series(int i,int l,int n)
    {
        if(i<=n && n<(i+l))
          return multiply(i,n);
        int s=0;
        s+=multiply(i,i+l-1);
        s+=series(i+l,l+1,n);
        return s;
    }
    int multiply(int a,int b)
    {
        if(b==a)
          return a;
        int p=b;
        p*=multiply(a,b-1);
        return p;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Continuous_Sum_Product csm =new Continuous_Sum_Product();
        System.out.println("Enter number limit :");
        //int n=sc.nextInt();
        System.out.println("Sum of series = "+csm.series(1, 1,sc.nextInt()));
    }
}