import java.util.*;
class Quiz
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of participants :");
        int n=sc.nextInt();
        if(n<4 || n>10)
        {System.out.println("Input Size out of range");System.exit(0);}
        char a[][]=new char[n][5];
        for(int i=0;i<n;i++)
        {
            System.out.println("Enter answers of Participant-"+(i+1));
            for(int j=0;j<5;j++)
              a[i][j]=sc.next().charAt(0);
        }
        System.out.println("Enter Key :");
        char k[]=new char[5];
        for(int j=0;j<5;j++)
            k[j]=sc.next().charAt(0);
        int m[]=new int[n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<5;j++)
            {
                if(a[i][j]==k[j])
                  m[i]++;
            }
        }
        int max=0;
        System.out.println("\nScores \n");
        for(int i=0;i<n;i++)
        {
            System.out.println("Participant-"+(i+1)+" = "+m[i]);
            if(m[i]>max)
              max=m[i];
        }
        System.out.println("\nHighest Score\n");
        for(int i=0;i<n;i++)
        {
            if(m[i]==max)
              System.out.println("Partcipant-"+(i+1));
        }
    }
} 