import java.util.*;
class Magic_No_Loop 
{   
    int sum(int n)
    {
        if(n/10==0)
          return n;
        return (n%10+sum(n/10));
    }
    int magic(int m)
    {
        if(m/10==0)
          return m;
        return (magic(sum(m)));
    }
    public static void main(String args[])
    {
        Magic_No_Loop mnl=new Magic_No_Loop();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any number");
        if (mnl.magic(sc.nextInt()) == 1)
          System.out.println("It is a Magic Number");
        else
        System.out.println("It is Not a Magic Number");
    }
}
