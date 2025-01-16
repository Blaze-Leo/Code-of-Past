import java.util.*;
class Combine
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of terms in P-");
        int p[]=new int[sc.nextInt()];
        System.out.println("Enter number of terms in Q-");
        int q[]=new int[sc.nextInt()];
        int r[]=new int[p.length+q.length];
        System.out.println("Enter the terms for P --");
        for(int i=0;i<p.length;i++)
        {p[i]=sc.nextInt();}
        System.out.println("Enter the terms for Q --");
        for(int i=0;i<q.length;i++)
        {q[i]=sc.nextInt();}
        for(int i=0;i<r.length;i++)
        {
            if(i<(p.length))
            {r[i]=p[i];}
            else
            {r[i]=q[i-p.length];}
        }
        System.out.println("Array R--");
        for(int i=0;i<r.length;i++)
        {System.out.println(r[i]);}
    }
}