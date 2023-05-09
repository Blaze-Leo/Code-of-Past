import java.util.*;
class Banner
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of participating teams :");
        int n=sc.nextInt();String dum=sc.nextLine();
        if(n<3 || n>8)
        {System.out.println("Invalid Input");System.exit(0);}
        String ban[]=new String[n];
        int max=0;
        for(int i=0;i<n;i++)
        {
            System.out.println("Enter name of Team-"+(i+1));
            ban[i]=sc.nextLine();
            if(ban[i].length()>max)
              max=ban[i].length();
        }
        char name[][]=new char[max][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<ban[i].length();j++)
              name[j][i]=ban[i].charAt(j);
        }
        for(int i=0;i<name.length;i++)
        {
            for(int j=0;j<name[i].length;j++)
            {
                if(name[i][j]=='\u0000')
                  System.out.print(" ");
                System.out.print(name[i][j]+"       ");
            }
            System.out.println();
        }
    }
}