import java.util.*;
class Happy
{
    int n;
    Happy(){n=0;}
    void getnum(int nn){n=nn;}
    int sum_sCLdigits(int x)
    {
        if(x<10)
          return x;
        int s=0;
        while(x>0)
        {
            s+=(x%10)*(x%10);
            x/=10;
        }
        return sum_sCLdigits(s);
    }
    void ishappy()
    {
        if(sum_sCLdigits(n)==1)
          System.out.println("It is a Happy Number ");
        else
          System.out.println("It is not a Happy Number ");
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any number :");
        Happy h=new Happy();
        h.getnum(sc.nextInt());
        h.ishappy();
    }
}