import java.util.*;
class Transpose
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of rows and columns ::");
        int r=sc.nextInt();
        int c=sc.nextInt();
        System.out.println("Enter matrix (row-column) ::");
        int a[][]=new int[r][c]; 
        int b[][]=new int[c][r]; 
        for(int i=0;i<r;i++)
        {
            for (int j=0;j<c;j++)
            {
                a[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<r;i++)
        {
            for (int j=0;j<c;j++)
            {
                b[j][i]=a[i][j];
            }
        }
        for(int i=0;i<c;i++)
        {
            for (int j=0;j<r;j++)
            {
                System.out.print(b[i][j]+" ");
            }
            System.out.println();
        }
        sc.close();
    }
}