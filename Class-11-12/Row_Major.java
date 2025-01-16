import java.util.*;
class Row_Major
{
    public static void main(String args[])
    {
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter size of row ::");
        int r=sc.nextInt();
        System.out.println("Enter size of column ::");
        int c=sc.nextInt();
        int a[][]=new int[r][c];
        int s[]=new int[r*c];
        System.out.println("Enter elements ::");
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                a[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                s[i*c+j]=a[i][j];
            }
        }
        System.out.print("Array = ["+s[0]);
        for(int i=1;i<r*c;i++)
        {
            System.out.print(","+s[i]);
        }
        System.out.print("]");
    }
}