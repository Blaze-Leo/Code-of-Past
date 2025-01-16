import java.util.*;
class Duck_Number
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any number ::");
        int n=sc.nextInt();
        String g=(Integer.toString(n)).contains("0")?" a ":" not a ";
        System.out.println("It is"+g+"Duck Number");
    }
}