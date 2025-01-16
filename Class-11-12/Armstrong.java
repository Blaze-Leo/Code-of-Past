import java.util.*;
class Armstrong
{
    int calculate(int n,int l)
    {
        int s=0;
        s+=(int)(Math.pow(n%10,l));
        if(n<10 && n>=0)
          return s;
        s+=calculate(n/10,l);
        return s;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Armstrong a=new Armstrong();
        System.out.println("Enter any number :");
        int n=sc.nextInt();
        String g=(a.calculate(n,Integer.toString(n).length()))==n?"It is an ":"It is not an ";
        System.out.println(g+"Armstrong Number");
    }
}

