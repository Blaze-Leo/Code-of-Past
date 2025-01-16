import java.util.*;
class Diagonal_Add
{
    public static void main(String args[])
    {
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter size of matrix ::");
        int n=sc.nextInt();
        int a[][]=new int[n][n];
        boolean s=true;
        System.out.println("Enter elements ::");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                a[i][j]=sc.nextInt();
                if(i!=j && a[i][j] != 0)
                  s=false;
            }
        }
        if(s)
        {
        for(int i=0;i<n;i++)
        {
            if(a[i][i] ==0)
              break;
            else
            {
                System.out.println("It is a diagonal array ");
                System.exit(0);
            }
        }
        }
        int ss=0;
        for(int i=0;i<n;i++)
        {
            ss+=a[i][i]+a[i][n-1-i];
        }
        ss-=n%2==0?0:a[n/2+1][n/2+1];
        System.out.println ("Sum ="+ss);
    }
}