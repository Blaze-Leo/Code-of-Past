class Repeat
{
    public static void main(String args[])
    {
        int a=69;
        for(int i=1;i<=10;i++)
        {
            System.out.print(a);
            a+=i%2==0?44:-44;
        }
    }
}