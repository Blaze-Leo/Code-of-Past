import java.util.*;
class Expression_Run
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any Number :");
        String value=sc.next();
        int l=value.length();
        double s1=0.0,s2=0.0;
        char c;
        String ff=" ";
        for(int i=0;i<l;i++)
        {
            if(Character.isDigit(value.charAt(i)))
            {
                s1=s1*10 + Double.parseDouble(Character.toString(value.charAt(i)));
            }
            else 
            {
                ff=Character.toString(value.charAt(i));
                break;
            }
        }
        for(int i=value.indexOf(ff)+1;i<l;i++)
        {
            c=value.charAt(i);
            if(Character.isDigit(c))
            {
                s2=s2*10 + Double.parseDouble(Character.toString(c));
            }
            else
            {
                ff+=Character.toString(c);
            }
            if(ff.length()>1)
            {
                char y=ff.charAt(0);
                ff=ff.substring(1);
                s1=y=='/'?s1/s2:(y=='*'?s1*s2:(y=='-'?s1-s2:s1+s2));
                s2=0;
            }
            
        }
        char y=ff.charAt(0);
        s1=y=='/'?s1/s2:(y=='*'?s1*s2:(y=='-'?s1-s2:s1+s2));
        value=Double.toString(s1);
        System.out.println(value);
    }
}