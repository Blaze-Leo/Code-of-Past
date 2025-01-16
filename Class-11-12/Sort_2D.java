import java.util.*;
class Sort_2D
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter row and column length :");
        int[][] n=new int[sc.nextInt()][sc.nextInt()];
        System.out.println("Enter the elments :");
        for(int i=0;i<n.length;i++)
        {
            for(int j=0;j<n[i].length;j++)
              n[i][j]=sc.nextInt();
        }
        int[] a=new int[n.length+n[0].length];
        for(int i=0, x=0;i<n.length;i++)
        {
            for(int j=0;j<n[i].length;j++,x++)
              a[x]=n[i][j];
        }
        int l = a.length;  
        int temp = 0;  
        for(int i=0; i < l; i++)
        {  
            for(int j=1; j < (l-i); j++)
            {  
                if(a[j-1] > a[j])
                {  
                    temp = a[j-1];  
                    a[j-1] = a[j];  
                    a[j] = temp;  
                }  
            }  
        }
        for(int i=0, x=0;i<n.length;i++)
        {
            for(int j=0;j<n[i].length;j++,x++)
              n[i][j]=a[x];
        }
        for(int i=0;i<n.length;i++)
        {
            for(int j=0;j<n[i].length;j++)
              System.out.print(n[i][j]+" ");
            System.out.println();
        }
    }
}