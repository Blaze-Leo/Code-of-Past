import java.util.*;
class Magic_String
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any string ::");
        String s=sc.nextLine();
        boolean n=true;
        for(int i=0;i<s.length()-1;i++)
        {
            if((int)(s.charAt(i+1))-(int)(s.charAt(i)) == 1)
            {
                n=false;
                break;
            }
        }
        String g=!n?"a Magic String":"not a Magic String";
        System.out.println("It is "+g);
    }
}