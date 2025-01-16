import java.util.*;
class Matrix_Mirror
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter size of error :");
        int m=sc.nextInt();
        if(m<2||m>20)
        {System.out.println("Invalid Input");System.exit(0);};
        int a[][]=new int[m][m];
        System.out.println("Enter elements \n");
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<m;j++)
              a[i][j]=sc.nextInt();
        }
        System.out.println("\n Original Array \n");
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<m;j++)
              System.out.print(a[i][j]+" ");
            System.out.println();
        }
        System.out.println("\n Mirrored Array \n");
        for(int i=0;i<m;i++)
        {
            for(int j=m-1;j>=0;j--)
              System.out.print(a[i][j]+" ");
            System.out.println();
        }
    }
}
