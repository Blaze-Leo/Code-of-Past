import java.util.*;
class Array_Equality
{
    public static void main(String args[])
    {
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter size of row ::");
        int r=sc.nextInt();
        System.out.println("Enter size of column ::");
        int c=sc.nextInt();
        char a[][]=new char[r][c];
        char b[][]=new char[r][c];
        System.out.println("Enter elements of first array ::");
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                a[i][j]=sc.next().charAt(0);
            }
        }
        System.out.println("Enter elements of second array ::");
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                b[i][j]=sc.next().charAt(0);
            }
        }
        boolean s=true;
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                if(a[i][j] != b[i][j])
                  s=false;
            }
        }
        String g=s?" ":" not ";
        System.out.println("The arrays are"+g+"equal");
    }
}