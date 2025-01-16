import java.util.*;
class Correlation
{
    int[] rank(int s[])
    {
        int[] a=new int[s.length];
    int n = s.length;
    for(int i=0;i<n;i++)
    a[i]=s[i];
    int r[] = new int[n];
    for (int i = 1; i <= n; ++i)
      r[i - 1] = i;
      for (int i = 1; i < n; ++i) 
      {
        int k1 = a[i], k2 = r[i];
        int j = i - 1;
        while (j >= 0 && a[j] > k1) 
        {
            a[j + 1] = a[j];
            r[j + 1] = r[j];
            j = j - 1;
        }
        a[j + 1] = k1;
        r[j + 1] = k2;
      }
          return r;
    }
    double rho(int x[], int y[])
    {
      int[] d1 = rank(x);
      int[] d2 = rank(y);
      int n=d1.length;
      int d[] = new int[y.length];
      for (int i = 0; i < n; ++i)
        d[i] = d1[i] - d2[i];
      double r = 0;
      for (int i = 0; i < n; ++i)
        r += d[i] * d[i];
      r*=6;
      r=r/(n*n*n-n);
      r=1-r;
      return r;
    }
    double tau(int x[], int y[])
    {
      double t = 0;
      int n=x.length;
      for (int i = 0; i < n; ++i)
      {
        for (int j = i+1; j < n; ++j)
        {
          if ((x[i] > x[j]  && y[i] > y[j] ) || (x[i] < x[j]  && y[i] < y[j] ))
            t += 2;
          else
            t -= 2;
        }
      }
      t=t/(n*n-n);
      return t;
    }
    void check(int x[], int y[])
    {
      double r = rho(x, y);
      double t = tau(x, y);
      System.out.println("\nRho Value :"+r+"\nTau Value :"+t);
      String g = r == 0 && t == 0 ? "Positive" : "Negative";
      System.out.println("\n"+g + "Correlation");
    }
    public static void main(String args[])
    {
      Correlation cc = new Correlation();
      Scanner sc=new Scanner(System.in);
      System.out.println("Enter size :");
      int n = sc.nextInt();
      System.out.println(" Enter x values :");
      int x[] = new int[n];
      for (int i = 0; i < n; ++i)
        x[i] = sc.nextInt();
      System.out.println(" Enter y values :");
      int y[] = new int[n];
      for (int i = 0; i < n; ++i)
        y[i] = sc.nextInt();
      cc.check(x, y);
      sc.close();
    }
}