import java.util.*;
class Marks_Tabulation
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        String name[]=new String[50];
        int mark[]=new int[50];
        for(int i=0;i<50;i++)
        {
            System.out.println("Enter name of Student-1 :");
            name[i]=sc.nextLine();
            System.out.println("Enter marks of Student-1 :");
            mark[i]=sc.nextInt();
        }
        int avg=0;
        String maxn="";
        int max=0;
        for(int i=0;i<50;i++)
        {
            avg+=mark[i];
            if(mark[i]>max)
            {
                max=mark[i];
                maxn=name[i];
            }
        }
        avg/=50;
        System.out.println("Average = "+avg+"\nMaximum Marks\nName - "+maxn+"\nMarks - "+max);
    }
}