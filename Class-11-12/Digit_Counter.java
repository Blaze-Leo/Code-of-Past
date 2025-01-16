import java.util.*;
class Digit_Counter
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int digit[]={0,0,0,0,0,0,0,0,0,0};
        System.out.println("Enter any number :");
        String n=sc.next();
        for(int i=0;i<n.length();i++)
          digit[Integer.valueOf(n.substring(i,i+1))]++;
        System.out.println("Digit      Frequency");
        for(int i=0;i<10;i++)
        {
            if(digit[i]!=0)
              System.out.println(i+"            "+digit[i]);
        }
    }
}