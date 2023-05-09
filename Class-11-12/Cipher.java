import java.util.*;
class Cipher
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter sentence :");
        String s=sc.nextLine();
        String n="";
        System.out.println("Enter key :");
        String key=sc.next();
        int l=key.length();
        int f=0;
        for(int i=0;i<s.length();i++)
        {
            int c=(int)(s.charAt(i));
            if(c>64 && c<91)
            {
                f++;
                System.out.println(c);
                c+=key.charAt((f-1)%l)-64;
                c=c>90?c-26:c;
            }
            n+=(char)(c);
        }
        System.out.println("Plaintext : "+n);
    }
}