import java.util.*;
class Marks
{
    Scanner sc=new Scanner(System.in);
    int rn[]=new int[50];
    int sua[]=new int[50];
    int sub[]=new int[50];
    int suc[]=new int[50];
    double avg[]=new double[50];
    void accept()
    {
        for(int i=0;i<50;i++)
        {
            System.out.println("Enter Roll No. for Student-"+(i+1));
            rn[i]=sc.nextInt();
            System.out.println("Enter Marks for Subject A");
            sua[i]=sc.nextInt();
            System.out.println("Enter Marks for Subject B");
            sub[i]=sc.nextInt();
            System.out.println("Enter Marks for Subject C");
            suc[i]=sc.nextInt();
            avg[i]=(sua[i]+sub[i]+suc[i])/3.0;
        }
    }
    void calculate()
    {
        System.out.println("AVERAGE MARKS\n");
        for(int i=0;i<50;i++)
        {System.out.println("For Student-"+(i+1)+" is"+avg[i]);}
        System.out.println("MARKS ABOVE 80\n");
        for(int i=0;i<50;i++)
        {
            if(avg[i]>80)
            {
                System.out.println("Student -"+(i+1));
                System.out.println("Roll No.- "+rn[i]);
                System.out.println("Average Marks- "+avg[i]);     
            }
        }
        System.out.println("MARKS BELOW 40\n");
        for(int i=0;i<50;i++)
        {
            if(avg[i]<40)
            {
                System.out.println("Student -"+(i+1));
                System.out.println("Roll No.- "+rn[i]);
                System.out.println("Average Marks- "+avg[i]);     
            }
        }
    }
    public static void main(String args[])
    {
        Marks mm=new Marks();
        mm.accept();
        mm.calculate();
    }
} 