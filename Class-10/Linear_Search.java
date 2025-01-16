import java.util.*;
class Linear_Search
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of terms -");
        int a[]=new int[sc.nextInt()];
        System.out.println("Enter the terms --");
        for(int i=0;i<a.length;i++)
        {a[i]=sc.nextInt();}
        System.out.println("Enter number to be searched -");
        int n=sc.nextInt();
        for(int i=0;i<a.length;i++)
        {
            if(a[i]==n)
            {System.out.println("Element "+n+"is at "+(i+1));break;}
            if(i==(a.length-1))
            {System.out.println("Search element not found");}
        }
    }
}