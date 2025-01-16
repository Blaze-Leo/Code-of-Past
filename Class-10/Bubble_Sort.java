import java.util.*;
class Bubble_Sort 
{
    public static void main(String args[]) 
    {
        Scanner sc=new Scanner(System.in);
        String n[],m;
        System.out.println("Enter number of Cities");
        n=new String[sc.nextInt()];
        System.out.println("Enter the City names --");
        for (int i=0; i<n.length ; i++)
        {n[i]=sc.nextLine();}
        System.out.println("Strings in order  :");
        for (int j = 0; j < n.length; j++) 
        {
            for (int i = j + 1; i < n.length; i++) 
            {
                if (n[i].compareTo(n[j]) < 0) 
                {
                    m = n[j];
                    n[j] = n[i];
                    n[i] = m;
                }
            }
        System.out.print(n[j]+",");
        }
    }
}