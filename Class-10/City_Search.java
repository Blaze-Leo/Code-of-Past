import java.util.*;
class City_Search
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        String cn[]=new String[10];
        int std[]=new int[10];
        System.out.println("Enter the city names --");
        for(int i=0;i<10;i++)
        {cn[i]=sc.next();}
        System.out.println("Enter the STD codes --");
        for(int i=0;i<10;i++)
        {std[i]=sc.nextInt();}
        System.out.println("Enter name of desired City -");
        String n=sc.next();
        for(int i=0;i<10;i++)
        {
            if(n.equalsIgnoreCase(cn[i]))
            {
                System.out.println("Search Successful\nCity-"+cn[i]);
                System.out.println("STD Code-"+std[i]);
                break;
            }
            if(i==9)
            {System.out.println("Search Unsuccessful, No such city is in the list");}
        }
    }
}