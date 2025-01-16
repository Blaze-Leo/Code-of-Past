class Array2D
{
    public static void main(String args[])
    {
        int[][] arr={{2,3,6},{5,6,1}};
        int m=2,n=3;
        int d1=0,d2=0;
        for(int i=1;i<=m*n;i++)
        {
            System.out.print(arr[d1][d2]+" ");
            d2++;
            if(d2==n)
            {
                d2=0;
                d1++;
                System.out.println();
            }
        }
    }
}